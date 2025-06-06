package com.W1SPG.sdsremotedisplay

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class NetworkViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    private val port = 50536
    private val scannerSocket: DatagramSocket = DatagramSocket()
    var receiveBuffer = ByteArray(4096)
    lateinit var scannerAddr: InetAddress
    lateinit var parseData: ParseScannerData
    private var vlcPlayer: VlcPlayer? = null

    fun connect(ipAddress: String, sdsData: ParseScannerData) {
        parseData = sdsData
        try {
            scannerAddr = InetAddress.getByName(ipAddress)
        } catch (e: Exception) {
            //Log.e("Connect Exception:", e.message ?: "Unknown error")
        }
    }

    fun wifiSendData(input: String) {
        try {
            val byteInput = (input + "\r").toByteArray()
            val packet = DatagramPacket(byteInput, byteInput.size, scannerAddr, port)
            scannerSocket.send(packet)
        } catch (e: Exception) {
            //Log.e("TX Exception:", e.message ?: "Unknown error")
        }
    }

    fun wifiRxData() {
        Thread {
            while (connectedToScannerWIFI) {
                try {
                    val packet = DatagramPacket(receiveBuffer, receiveBuffer.size)
                    scannerSocket.receive(packet)
                    val response = String(packet.data, 0, packet.length)
                    if (response.isNotEmpty()) {
                        parseData.decodeResponse(response)
                    }
                    rxData = ""
                } catch (e: Exception) {
                    //Log.e("RX Exception", e.message ?: "Unknown error")
                }
            }
        }.start()

        Thread {
            try {
                val audioURL = "rtsp:/" + scannerAddr + ":554/au:scanner.au"
                vlcPlayer = VlcPlayer(context, audioURL)
                if (vlcPlayer?.initializationError != null) {
                    //Log.e("Network", "VlcPlayer failed to initialize: ${vlcPlayer?.initializationError}")
                } else {
                    vlcPlayer?.play()
                }
            } catch (e: Exception) {
                //Log.e("Network", "Failed to initialize VlcPlayer: ${e.message}", e)
            }
        }.start()
    }

    fun stopVlcPlayback() {
        try {
            //Log.d("Network", "Stopped Called")
            vlcPlayer?.stop()
            vlcPlayer?.release()
        } catch (e: Exception) {
            //Log.e("Network", "Failed to stop VlcPlayer: ${e.message}", e)
        } finally {
            vlcPlayer = null
        }
    }

    fun cleanup() {
        try {
            stopVlcPlayback()
            scannerSocket.close()
        } catch (e: Exception) {
            //Log.e("Network", "Failed to cleanup: ${e.message}", e)
        }
    }

    companion object {
        var connectedToScannerWIFI = true
        var rxData = ""
    }
}