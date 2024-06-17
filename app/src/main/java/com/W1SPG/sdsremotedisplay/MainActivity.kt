package com.W1SPG.sdsremotedisplay

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.felhr.usbserial.UsbSerialDevice
import com.felhr.usbserial.UsbSerialInterface.DATA_BITS_8
import com.felhr.usbserial.UsbSerialInterface.FLOW_CONTROL_XON_XOFF
import com.felhr.usbserial.UsbSerialInterface.PARITY_NONE
import com.felhr.usbserial.UsbSerialInterface.STOP_BITS_1
import com.felhr.usbserial.UsbSerialInterface.UsbReadCallback
import java.util.Timer
import java.util.TimerTask

lateinit var m_usbManager: UsbManager
var m_device: UsbDevice? = null
var m_serial: UsbSerialDevice? = null
var m_connection: UsbDeviceConnection? = null
val vm = viewModel()
val sdsData = ParseScannerData(vm)
var connectedToScanner = false
val ACTION_USB_PERMISSION = "permission"

val REQUEST_CODE = 1
var locationPermissionGranted: Boolean = false
var locationRequestDenied = false
lateinit var locationManager: LocationManager

var rxData: String = ""

var latitude: Double = 0.0
var longitude: Double = 0.0

val displayTimer = Timer()
val gpsTimer = Timer()

//Done:
// removed code for press . on scanner
// fixed qkstatus not showing on 536
// keep screen on
// & displayed as &amp
// auto dismiss "press "." for serial (SDS only?)
// close call hit screen
// search screen
// speed 57600

//todo:
// create landscape display

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m_usbManager = getSystemService(Context.USB_SERVICE) as UsbManager

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val filter = IntentFilter()
        filter.addAction(ACTION_USB_PERMISSION)
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        registerReceiver(BroadcastReceiver, filter, RECEIVER_EXPORTED)
        setContent {
            KeepScreenOn()
            Display()
        }
    }

    override fun onResume() {
        super.onResume()
        DoStuff()
    }

    private fun DoStuff() {
        var commands = listOf("MDL", "GSI", "LCR", "STS", "PWR")
        val displayTimerDelay = 0
        val displayTimerPeriod = 150 // repeat

        try {
            displayTimer.schedule(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        if (connectedToScanner) {
                            for (command in commands) {
                                SendUsbData(command)
                            }
                            //send any button presses
                            if (vm.keyPress != "") {
                                var keys = vm.keyPress.split(" ")
                                for (key in keys) {
                                    SendUsbData(key)
                                }
                                vm.keyPress = ""
                            }

                            //check for a disconnect
                            try {
                                var deviceList: HashMap<String, UsbDevice>? =
                                    m_usbManager.deviceList
                                if (deviceList != null) {
                                    if (deviceList.isEmpty()) {
                                        vm.lostConnection = true
                                    } else {
                                        vm.lostConnection = false
                                    }
                                }
                            } catch (e: Exception) {
                                //println("Error: ${e.message}")
                            }

                            vm.updateDisplayData()
                        }
                    }
                }
            },  displayTimerDelay .toLong(), displayTimerPeriod.toLong() )

            val gpsTimerDelay = 1000
            val gpsTimerPeriod = 5000 //update gps every 5 seconds
            gpsTimer.schedule(object : TimerTask() {
                override fun run() {
                    //if (connectedToScanner) {
                        var nmeaSentence = getNMEASentence()
                        if (nmeaSentence != "") {
                            SendUsbData(nmeaSentence)
                            //Log.d("NMEA Sent", nmeaSentence)
                        }
                    //}
                }
            }, gpsTimerDelay.toLong(), gpsTimerPeriod.toLong())


        } catch (e: Exception) {
            //println("Error: ${e.message}")
        }
    }

    // location stuff

    private fun getNMEASentence(): String {
        locationPermissionGranted = checkLocationPermission()
        if (locationPermissionGranted) {
            Log.d("GPS Enabled", "GPS Enabled")
            var lat = locationDDtoDDm(latitude,false)
            var long = locationDDtoDDm(longitude, true)

            var latNS = "N"
            if (latitude < 0.0) {
                latNS = "S"
            }

            var lonEW = "W"
            if (longitude > 0.0) {
                lonEW = "E"
            }

            //build NMEA - scanner only cares about latitude and longitude data
            var nmeaSentence = "\$GPRMC,,A," + lat + "," + latNS + "," + long + "," + lonEW + ",,,,,,"
            return nmeaSentence
        }
        return ""
    }

    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: android.location.Location) {
            latitude = location.latitude
            longitude = location.longitude
        }
    }

    private fun checkLocationPermission(): Boolean {
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (hasGps) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) {
                runOnUiThread {
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    try {
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            5000,
                            10.toFloat(),
                            locationListener
                        )
                    } catch (e: Exception) {
                        //println("Error: ${e.message}")
                    }
                }
                return true
            } else {
                if (!locationRequestDenied) { //don't ask again if user already said no
                    // Request permission
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE
                    )
                }
            }
        }
        return false
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true
                } else {
                    locationPermissionGranted = false
                    locationRequestDenied = true //only ask for location permission once
                }
            }
        }
    }

    private fun locationDDtoDDm(element: Double, longitude :Boolean): String {
        //Convert lat or long from DD format to DDm format
        //Integer is the degrees
        //Multiply the decimal part by 60
        //integer of result is minutes, append the remaining seconds to 4 decimal places
        var ddElement = element.toString()

        if (ddElement.first() == '-') {
            ddElement = ddElement.drop(1) //remove negative sign
        }

        var degMin = (ddElement.toString()).split(".")
        var degrees = degMin[0]

        if (longitude) {
            if (degrees.length < 3) {
                when (degrees.length) { //pad longitude degrees out to 3 chars
                    0 -> degrees = "000" + degrees
                    1 -> degrees = "00" + degrees
                    2 -> degrees = "0" + degrees
                }
            }
        } else {
            if (degrees.length == 1) {
                degrees = "0" + degrees //pad out latitude degrees to two digits
            }
        }

        // multiple decimal remainder by 60, integer is minutes, decimal is seconds
        var minSec = ( ("." + degMin[1]).toDouble() * 60).toString().split(".")
        var minutes = minSec[0]
        var seconds = minSec[1].take(4)

        if (minutes.length == 1) {
            minutes = "0" + minutes //pad out degrees to two digits
        }

        if (seconds.length < 4) { //not sure is this part is needed
            var len = seconds.length
            when(len) { //pad out to 4 chars
                0 -> seconds += "0000"
                1 -> seconds += "000"
                2 -> seconds += "00"
                3 -> seconds += "0"
            }
        }

        var ddmElement = degrees + minutes + "." +seconds.take(4)
        return ddmElement
    }


    // USB Stuff

    private fun UsbConnect() {
        val usbDevices: HashMap<String, UsbDevice>? = m_usbManager.deviceList
        if (!usbDevices?.isEmpty()!!) {
            var keep = false
            for ((_, value) in usbDevices) {
                m_device = value
                val vendorId: Int? = m_device?.vendorId
                if (vendorId == 6501) { //Uniden
                    val intent: PendingIntent =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            PendingIntent.getBroadcast(
                                this, 0, Intent(ACTION_USB_PERMISSION),
                                PendingIntent.FLAG_IMMUTABLE
                            )
                        } else {
                            PendingIntent.getBroadcast(
                                this, 0, Intent(ACTION_USB_PERMISSION),
                                PendingIntent.FLAG_IMMUTABLE
                            )
                        }

                    m_usbManager.requestPermission(m_device, intent)
                    keep = true
                }
            }
            if (keep) {
                return
            }
        }
    }

    fun SendUsbData(input: String) {
        try {
            var byteInput = input.toByteArray() + "\r".toByteArray()
            if (m_serial != null) {
                m_serial?.write(byteInput)
                Log.d("Sent:", input )
            }
        } catch (e: Exception) {
            //Log.e("Error:", (e.message).toString())
        }
    }

    private val mCallback = object : UsbReadCallback {
        @Override
        override fun onReceivedData(rx: ByteArray) {
            try {
                //Log.d("Byte:", rx.joinToString("") { "%02x".format(it) })
                var usbRxData = String(rx, Charsets.UTF_8)
                if (usbRxData.contains("ERR")) {
                    return
                }
                Log.d("Partial:", usbRxData)
                rxData += usbRxData

                var lastChar: String = usbRxData.takeLast(1)
                if (lastChar == "\r") { //concatenate until last char is \r
                    Log.d("Rx:", rxData)
                    sdsData.decodeResponse(rxData)
                    rxData = ""
                }
            } catch (e: Exception) {
                //println("Error: ${e.message}")
            }
        }
    }

    private fun UsbDisconnect() {
        m_serial?.close()
        connectedToScanner = false
    }

    private val BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action!! == ACTION_USB_PERMISSION) {
                //val granted: Boolean = intent.extras!!.getBoolean(UsbManager.EXTRA_PERMISSION_GRANTED)
                // there seems to be a bug in android related to sending the intent as mutable vs immutable
                // so we just have to assume it is granted here
                //https://stackoverflow.com/questions/73267829/androidstudio-usb-extra-permission-granted-returns-false-always
                var granted = true
                if (granted) {
                    m_connection = m_usbManager.openDevice(m_device)
                    m_serial = UsbSerialDevice.createUsbSerialDevice(m_device, m_connection)
                    if (m_serial != null) {
                        if (m_serial!!.open()) {
                            m_serial!!.setBaudRate(57600)
                            m_serial!!.setDataBits(DATA_BITS_8)
                            m_serial!!.setStopBits(STOP_BITS_1)
                            m_serial!!.setParity(PARITY_NONE)
                            m_serial!!.setFlowControl(FLOW_CONTROL_XON_XOFF)
                            m_serial!!.read(mCallback)
                            connectedToScanner = true
                        }
                    }
                }
            } else if (intent?.action!! == UsbManager.ACTION_USB_DEVICE_ATTACHED) {
                UsbConnect()
            } else if (intent?.action!! == UsbManager.ACTION_USB_DEVICE_DETACHED) {
                //todo: figure out why this intent will not fire
                UsbDisconnect()
            }
        }
    }

    @Composable
    fun KeepScreenOn() {
        val currentView = LocalView.current
        DisposableEffect(Unit) {
            currentView.keepScreenOn = true
            onDispose {
                currentView.keepScreenOn = false
            }
        }
    }
}
