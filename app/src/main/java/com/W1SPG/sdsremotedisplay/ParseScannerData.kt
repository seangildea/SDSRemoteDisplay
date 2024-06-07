package com.W1SPG.sdsremotedisplay

import android.util.Log

class ParseScannerData(var vm: viewModel) {

    fun decodeResponse(responseString: String) {
        try {
            var messages = responseString.split("\r")
            for (message in messages) {
                var message = message.trimStart() //remove spaces from beginning
                Log.d("Message: ", message)
                var messageType: String = ""

                if (message.take(1) == "<") { //xml lines
                    if (message.contains(" ")) { //skip xml lines that do not contain a space
                        messageType =
                            message.take(message.indexOf(" ")) //everything up to first space char
                    }
                } else { //non xml line
                    messageType = message.take(3)
                }
                Log.d("Type of Response", messageType)

                when (messageType) {
                    "<ScannerInfo" -> {
                        vm.scannerInfoMode = GSI_FindItem(message, "Mode")
                        vm.scannerInfoV_screen = GSI_FindItem(message, "V_Screen")
                    }

                    "<MonitorList" -> {
                        vm.monitorListName = GSI_FindItem(message, "Name")
                        vm.monitorListIndex = GSI_FindItem(message, "Index")
                        vm.monitorListListType = GSI_FindItem(message, "ListType")
                        vm.monitorListQ_Key = GSI_FindItem(message, "Q_Key")
                        vm.monitorListN_Tag = GSI_FindItem(message, "N_Tag")
                        vm.monitorListDB_Counter = GSI_FindItem(message, "DB_Counter")
                    }

                    "<System" -> {
                        vm.systemName = GSI_FindItem(message, "Name")
                        vm.systemIndex = GSI_FindItem(message, "Index")
                        vm.systemAvoid = GSI_FindItem(message, "Avoid")
                        vm.systemSystemType = GSI_FindItem(message, "SystemType")
                        vm.systemQ_Key = GSI_FindItem(message, "Q_Key")
                        vm.systemN_Tag = GSI_FindItem(message, "N_Tag")
                        vm.systemHold = GSI_FindItem(message, "Hold")
                    }

                    "<Department" -> {
                        vm.departmentName = GSI_FindItem(message, "Name")
                        vm.departmentIndex = GSI_FindItem(message, "Index")
                        vm.departmentAvoid = GSI_FindItem(message, "Avoid")
                        vm.departmentQ_Key = GSI_FindItem(message, "Q_Key")
                        vm.departmentHold = GSI_FindItem(message, "Hold")
                    }

                    "<ConvFrequency" -> {
                        vm.convFrequencyName  = GSI_FindItem(message, "Name")
                        vm.convFrequencyIndex = GSI_FindItem(message, "Index")
                        vm.convFrequencyAvoid = GSI_FindItem(message, "Avoid")
                        vm.convFrequencyFreq = GSI_FindItem(message, "Freq")
                        vm.convFrequencyMod = GSI_FindItem(message, "Mod")
                        vm.convFrequencyHold = GSI_FindItem(message, "Hold")
                        vm.convFrequencySvcType = GSI_FindItem(message, "SvcType")
                        vm.convFrequencyP_Ch = GSI_FindItem(message, "P_Ch")
                        vm.convFrequencySAS = GSI_FindItem(message, "SAS")
                        vm.convFrequencySAD = GSI_FindItem(message, "SAD")
                        vm.convFrequencyRecSlot = GSI_FindItem(message, "RecSlot")
                        vm.convFrequencyLVL = GSI_FindItem(message, "LVL")
                        vm.convFrequencyIFX = GSI_FindItem(message, "IFX")
                        vm.convFrequencyTGID = GSI_FindItem(message, "TGID")
                        vm.convFrequencyU_Id = GSI_FindItem(message, "U_Id")
                    }

                    "<TGID" -> {
                        //vm.tgidName = GSI_FindItem(message, "Name")
                        vm.convFrequencyName= GSI_FindItem(message, "Name") //workaround
                        vm.tgidIndex = GSI_FindItem(message, "Index")
                        vm.tgidAvoid = GSI_FindItem(message, "Avoid")
                        vm.tgidTGID = GSI_FindItem(message, "TGID", 5)
                        vm.tgidSetSlot = GSI_FindItem(message, "SetSlot")
                        vm.tgidN_Tag = GSI_FindItem(message, "N_Tag")
                        vm.tgidHold = GSI_FindItem(message, "Hold")
                        //vm.tgidSvcType = GSI_FindItem(message, "SvcType")
                        vm.convFrequencySvcType = GSI_FindItem(message, "SvcType") //workaround
                        vm.tgidP_Ch = GSI_FindItem(message, "P_Ch")
                        vm.tgidLVL = GSI_FindItem(message, "LVL")
                    }

                    "<UnitID" -> {
                        vm.unitIDName = GSI_FindItem(message, "Name")
                        vm.unitU_Id = GSI_FindItem(message, "U_Id")
                    }

                    "<Site" -> {
                        vm.siteName = GSI_FindItem(message, "Name")
                        vm.siteIndex = GSI_FindItem(message, "Index")
                        vm.siteAvoid = GSI_FindItem(message, "Avoid")
                        vm.siteQ_Key = GSI_FindItem(message, "Q_Key")
                        vm.siteHold = GSI_FindItem(message, "Hold")
                        //vm.siteMod = GSI_FindItem(message, "Mod")
                        vm.convFrequencyMod = GSI_FindItem(message, "Mod") //workaround
                    }

                    "<SiteFrequency" -> {
                        //vm.siteFrequencyFreq = GSI_FindItem(message, "Freq")
                        vm.convFrequencyFreq = GSI_FindItem(message, "Freq") //workaround
                        vm.siteFrequencyIFX = GSI_FindItem(message, "IFX")
                        vm.siteFrequencySAS = GSI_FindItem(message, "SAS")
                        vm.siteFrequencySAD = GSI_FindItem(message, "SAD")
                    }

                    "<DualWatch" -> {
                        vm.dualWatchPRI = GSI_FindItem(message, "PRI")
                        vm.dualWatchCC = GSI_FindItem(message, "CC")
                        vm.dualWatchWX = GSI_FindItem(message, "WX")
                    }

                    "<Property" -> {
                        vm.propertyF = GSI_FindItem(message, "F")
                        vm.propertyVOL = GSI_FindItem(message, "VOL")
                        vm.propertySQL = GSI_FindItem(message, "SQL")
                        vm.propertySig = GSI_FindItem(message, "Sig")
                        vm.propertyAtt = GSI_FindItem(message, "Att")
                        vm.propertyRec = GSI_FindItem(message, "Rec")
                        vm.propertyKeyLock = GSI_FindItem(message, "KeyLock")
                        vm.propertyP25Status = GSI_FindItem(message, "P25Status")
                        vm.propertyMute = GSI_FindItem(message, "Mute")
                        vm.propertyBacklight = GSI_FindItem(message, "Backlight")
                        vm.propertyA_led = GSI_FindItem(message, "A_Led")
                        vm.propertyDir = GSI_FindItem(message, "Dir")
                        vm.propertyRssi = GSI_FindItem(message, "Rssi")
                    }


                    "MDL" -> {
                        // ex. MDL,BCD536HP
                        vm.model = message.substring((message.indexOf(",") + 1),)
                    }

                    "LCR" -> {
                        try {
                            //ex. LCR,32.985321,-97.048116,0.0
                            var parts = message.split(",")
                            vm.latitude = parts[1]
                            vm.longitude = parts[2]
                            vm.range = parts[3]
                        } catch (e: Exception) {
                            println("Error: ${e.message}")
                        }
                    }

                    "STS" -> {
                        vm.displayLines = message.split(",").toTypedArray()
                    }

                    "PWR" -> {
                        var parts = message.split(",")
                        vm.pwrRSSI = parts[1]
                        vm.pwrFreq = parts[2]
                    }

                    else -> {
                        Log.d("Unkown Mes type", message)
                    }

                }
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun GSI_FindItem(textLine: String, name: String, beginIndex: Int = 0): String {
        if ( textLine.substring(beginIndex).contains(name) ) {
            val itemName = name + "=\""
            val start = textLine.indexOf(itemName, beginIndex) + itemName.length
            val end = textLine.indexOf("\"", start)
            val result = textLine.substring(start, end)
            return result
        } else {
            return ""
        }
    }
}