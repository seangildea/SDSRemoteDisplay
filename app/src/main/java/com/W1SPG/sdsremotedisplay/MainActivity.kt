package com.W1SPG.sdsremotedisplay

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.W1SPG.sdsremotedisplay.ui.theme.subValues
import com.felhr.usbserial.UsbSerialDevice
import com.felhr.usbserial.UsbSerialInterface.DATA_BITS_8
import com.felhr.usbserial.UsbSerialInterface.FLOW_CONTROL_XON_XOFF
import com.felhr.usbserial.UsbSerialInterface.PARITY_NONE
import com.felhr.usbserial.UsbSerialInterface.STOP_BITS_1
import com.felhr.usbserial.UsbSerialInterface.UsbReadCallback
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Timer
import java.util.TimerTask

var m_device: UsbDevice? = null
var m_serial: UsbSerialDevice? = null
var m_connection: UsbDeviceConnection? = null
val vm = viewModel()
val sdsData = ParseScannerData(vm)
var connectedToScannerUSB = false
val ACTION_USB_PERMISSION = "permission"

var connectedToScannerWIFI = false

val REQUEST_CODE = 1
var locationPermissionGranted: Boolean = false
var locationRequestDenied = false
lateinit var m_usbManager: UsbManager
lateinit var locationManager: LocationManager

var rxData: String = ""

var latitude: Double = 0.0
var longitude: Double = 0.0

val displayTimer = Timer()
val gpsTimer = Timer()

var isPortraitMode: Boolean = true

var inStartUpScreen: Boolean by mutableStateOf(true)

class MainActivity : ComponentActivity() {
    val networkViewModel: NetworkViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m_usbManager = getSystemService(USB_SERVICE) as UsbManager

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        val filter = IntentFilter()
        filter.addAction(ACTION_USB_PERMISSION)
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        registerReceiver(BroadcastReceiver, filter, RECEIVER_EXPORTED)

        setContent {
            KeepScreenOn()
            MyScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        DoStuff()
    }

    private fun DoStuff() {
        val displayTimerDelay = 0
        var displayTimerPeriod = 800
        if (connectedToScannerUSB) { displayTimerPeriod=300 }

        try {
            displayTimer.schedule(object : TimerTask() {
                override fun run() {
                    //runOnUiThread {
                    if (connectedToScannerUSB or connectedToScannerWIFI) {

                        for (command in vm.scannerCommands) {
                            if (vm.clearToSend) {
                                SendUsbData(command)
                            } else if (connectedToScannerWIFI) {
                                networkViewModel.wifiSendData(command)
                            }
                        }

                        //send any button presses
                        if (vm.keyPress != "") {
                            var keys = vm.keyPress.split(" ")
                            for (key in keys) {
                                Log.d("In Key press", key)
                                if (connectedToScannerUSB) {
                                    SendUsbData(key)
                                } else if (connectedToScannerWIFI) {
                                    networkViewModel.wifiSendData(key)
                                }
                            }
                            vm.keyPress = ""
                        }

                        //check for a disconnect
                        if (connectedToScannerUSB) {
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
                        }

                        vm.updateDisplayData()
                    }
                    //}
                }
            }, displayTimerDelay.toLong(), displayTimerPeriod.toLong())

            val gpsTimerDelay = 1000
            val gpsTimerPeriod = 5000 //update gps every 5 seconds
            gpsTimer.schedule(object : TimerTask() {
                override fun run() {
                    var nmeaSentence = getNMEASentence()
                    if (nmeaSentence != "") {
                        if (connectedToScannerUSB) {
                            SendUsbData(nmeaSentence)
                        } else if (connectedToScannerWIFI) {
                            networkViewModel.wifiSendData(nmeaSentence)
                        }
                        //Log.d("NMEA Sent", nmeaSentence)
                    }
                }
            }, gpsTimerDelay.toLong(), gpsTimerPeriod.toLong())

            // Set the scanner to local time
            val timeSetDelay = 5000
            val timerSetPeriod = 200000
            gpsTimer.schedule(object : TimerTask() {
                override fun run() {
                    timeSet()
                }
            }, timeSetDelay.toLong(), timerSetPeriod.toLong())

        } catch (e: Exception) {
            //println("Error: ${e.message}")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
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
                            m_serial!!.setBaudRate(9600)
                            m_serial!!.setDataBits(DATA_BITS_8)
                            m_serial!!.setStopBits(STOP_BITS_1)
                            m_serial!!.setParity(PARITY_NONE)
                            m_serial!!.setFlowControl(FLOW_CONTROL_XON_XOFF)
                            m_serial!!.read(mCallback)

                        }
                    }
                }
            } else if (intent.action!! == UsbManager.ACTION_USB_DEVICE_ATTACHED) {
                UsbConnect()
            } else if (intent.action!! == UsbManager.ACTION_USB_DEVICE_DETACHED) {
                //todo: figure out why this intent will not fire
                UsbDisconnect()
            }
        }
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
                    connectedToScannerUSB = true
                    keep = true
                    inStartUpScreen = false //flag to switch to display and Hdisplay screens
                }
            }
            if (keep) {
                return
            }
        }
    }

    @Composable
    fun MyScreen() {
        if (inStartUpScreen) {
            startUpScreen(networkViewModel)
        } else {
            HandleOrientationChanges()
        }
    }

    fun SendUsbData(input: String) {
        try {
            var byteInput = input.toByteArray() + "\r".toByteArray()
            if (m_serial != null) {
                m_serial?.write(byteInput)
                //Log.d("Sent:", input )
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

                var rxSub = substituteUnidenIcons(rx)
                var usbRxData = String(rxSub, Charsets.UTF_8)


                if (usbRxData.contains("MDL")) {
                    vm.lastResponseTime = System.currentTimeMillis()
                }

                if (usbRxData.contains("ERR")) {
                    return
                }
                //Log.d("Partial:", usbRxData)
                rxData += usbRxData

                var lastChar: String = usbRxData.takeLast(1)
                if (lastChar == "\r") { //concatenate until last char is \r
                    //Log.d("Rx:", rxData)
                    if (rxData != "") {
                        sdsData.decodeResponse(rxData)
                    }
                    rxData = ""
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    private fun UsbDisconnect() {
        m_serial?.close()
        connectedToScannerUSB = false
    }

    fun substituteUnidenIcons(data: ByteArray): ByteArray {
        subValues.forEach { value ->
            val unidenFontByte = value.key.toByte()
            var found = data.indexOf(unidenFontByte)
            if (found > 0) {
                data.set(found, value.value.toByte())
            }
        }
        return data
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

    @Composable
    fun HandleOrientationChanges() {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (isLandscape) {
            HDisplay()
            isPortraitMode = false
        } else {
            Display()
            isPortraitMode = true
        }
    }


    // set the time on the scanner to android system time with the DTM command
    fun timeSet() {
        val dtmFormat = SimpleDateFormat("yyyy,MM,dd,HH,mm,ss")
        val DTMCommand = "DTM,1," + dtmFormat.format(Date())
        if (connectedToScannerUSB) {
            SendUsbData(DTMCommand)
        } else if (connectedToScannerWIFI) {
            networkViewModel.wifiSendData(DTMCommand)
        }
    }

    //=========================================================
    // GPS location stuff

    private fun checkLocationPermission(): Boolean {
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (hasGps) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
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
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE
                    )
                }
            }
        }
        return false
    }

    private fun getNMEASentence(): String {
        locationPermissionGranted = checkLocationPermission()
        if (locationPermissionGranted) {
            //Log.d("GPS Enabled", "GPS Enabled")
            var lat = locationDDtoDDm(latitude, false)
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
            var nmeaSentence =
                "\$GPRMC,,A," + lat + "," + latNS + "," + long + "," + lonEW + ",,,,,,"
            return nmeaSentence
        }
        return ""
    }

    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            latitude = location.latitude
            longitude = location.longitude
        }
    }

    private fun locationDDtoDDm(element: Double, longitude: Boolean): String {
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
        var minSec = (("." + degMin[1]).toDouble() * 60).toString().split(".")
        var minutes = minSec[0]
        var seconds = minSec[1].take(4)

        if (minutes.length == 1) {
            minutes = "0" + minutes //pad out degrees to two digits
        }

        if (seconds.length < 4) { //not sure is this part is needed
            var len = seconds.length
            when (len) { //pad out to 4 chars
                0 -> seconds += "0000"
                1 -> seconds += "000"
                2 -> seconds += "00"
                3 -> seconds += "0"
            }
        }

        var ddmElement = degrees + minutes + "." + seconds.take(4)
        return ddmElement
    }

}