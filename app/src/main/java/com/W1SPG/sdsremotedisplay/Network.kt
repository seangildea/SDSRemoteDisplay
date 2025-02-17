package com.W1SPG.sdsremotedisplay

import android.util.Log
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class Network() {
    private val port = 50536
    private val scannerSocket : DatagramSocket = DatagramSocket()

    var receiveBuffer = ByteArray(4096)
    lateinit var scannerAddr: InetAddress
    lateinit var parseData: ParseScannerData

    fun connect(ipAddress: String, sdsData: ParseScannerData) {
        parseData = sdsData
        try {
            scannerAddr = InetAddress.getByName(ipAddress)
        }
        catch (e: Exception) {
            Log.e("Connect Exception:", (e.message).toString())
        }
    }

    fun wifiSendData(input: String) {
        try {
            //Log.d("Send start", "")
            var byteInput = input.toByteArray() + "\r".toByteArray()

            //var toSend = DatagramPacket(byteInput, byteInput.size)
            var packet :DatagramPacket = DatagramPacket(byteInput, byteInput.size, scannerAddr, port)
            scannerSocket.send(packet)

        } catch (e: Exception) {
            Log.e("TX Exception:", (e.message).toString())
        }
    }

    fun wifiRxData() {
        Thread {
            while (connectedToScannerWIFI) {
                try {
                    val packet = DatagramPacket(receiveBuffer, receiveBuffer.size)
                    scannerSocket.receive(packet)
                    var response = String(packet.data, 0, packet.length)

                    //Log.d("Rx:", response)
                    if (response != "") {
                        parseData.decodeResponse(response)
                    }
                    //}
                    rxData = ""

                } catch (e: Exception) {
                    Log.e("RX Exception", (e.message).toString())
                }
            }
        }.start()
    }
}