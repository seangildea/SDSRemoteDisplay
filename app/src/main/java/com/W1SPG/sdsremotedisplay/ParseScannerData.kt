package com.W1SPG.sdsremotedisplay

import android.util.Log

class ParseScannerData(var vm: viewModel) {

    fun decodeResponse(responseString: String) {
        try {
            var messages = mutableListOf<String>()
            var response = responseString

            for (command in vm.scannerCommands) {

                if (response.take(3) == command) {
                    var endIndexes = mutableListOf<Int>()

                    for (endCommand in vm.scannerCommands) {

                        if (command != endCommand) {
                            if (response.contains(endCommand)) {
                                endIndexes.add(response.indexOf(endCommand))
                            } else {
                                continue
                            }
                        } else {
                            continue
                        }
                    }
                    var first: Int
                    if (endIndexes.size == 0) {
                        first = response.length
                    } else {
                        first = endIndexes.min() // find lowest value, which will be the next command
                    }
                    var scannerMess = response.substring(0, first)
                    if (scannerMess.takeLast(1) == "\r") {
                        scannerMess = scannerMess.substring(0, scannerMess.length - 1) //remove line feed
                    }
                    messages.add(scannerMess)
                    response = response.substring(first, response.length)

                }
            }

            for (message in messages) {
                //message = message.trimStart() //remove spaces from beginning
                //Log.d("Message: ", message)
                var messageType: String = ""

                /*
                if (message.take(1) == "<") { //xml lines
                    if (message.contains(" ")) { //skip xml lines that do not contain a space
                        messageType =
                            message.take(message.indexOf(" ")) //everything up to first space char
                    }
                } else { //non xml line
                    messageType = message.take(3)
                }
                //Log.d("Type of Response", messageType)
                 */
                messageType = message.take(3)

                when (messageType) {
                    "GSI" -> {
                        //val pattern2 = Regex("<([\\/\\w]+)(\\s+[^>]*)?\\/>")
                        //val lines =
                            //pattern2.findAll(responseString, 0).map { it.value }.toList()
                        var lines = message.split(">")
                        lines.forEach() {
                            var line = it.trim()
                            var end = line.indexOf(" ", 0)
                            if (end > 0) {
                                var prefix = line.substring(1, end)
                                when (prefix) {

                                    "ScannerInfo" -> {
                                        // preserve last value if it comes back blank
                                        var mode = GSI_FindItem(message, "Mode")
                                        if (mode != "") {
                                            vm.scannerInfoMode = GSI_FindItem(message, "Mode")
                                        }
                                        var v_screen = GSI_FindItem(message, "V_Screen")
                                        if (v_screen != "") {
                                            vm.scannerInfoV_screen = GSI_FindItem(message, "V_Screen")
                                        }
                                    }

                                    "MonitorList" -> {
                                        vm.monitorListName = GSI_FindItem(it, "Name")
                                        vm.monitorListIndex = GSI_FindItem(it, "Index")
                                        vm.monitorListListType = GSI_FindItem(it, "ListType")
                                        vm.monitorListQ_Key = GSI_FindItem(it, "Q_Key")
                                        vm.monitorListN_Tag = GSI_FindItem(it, "N_Tag")
                                        vm.monitorListDB_Counter = GSI_FindItem(it, "DB_Counter")
                                    }

                                    "System" -> {
                                        vm.systemName = GSI_FindItem(it, "Name")
                                        vm.systemIndex = GSI_FindItem(it, "Index")
                                        vm.systemAvoid = GSI_FindItem(it, "Avoid")
                                        vm.systemSystemType = GSI_FindItem(it, "SystemType")
                                        vm.systemQ_Key = GSI_FindItem(it, "Q_Key")
                                        vm.systemN_Tag = GSI_FindItem(it, "N_Tag")
                                        vm.systemHold = GSI_FindItem(it, "Hold")
                                    }

                                    "Department" -> {
                                        vm.departmentName = GSI_FindItem(it, "Name")
                                        vm.departmentIndex = GSI_FindItem(it, "Index")
                                        vm.departmentAvoid = GSI_FindItem(it, "Avoid")
                                        vm.departmentQ_Key = GSI_FindItem(it, "Q_Key")
                                        vm.departmentHold = GSI_FindItem(it, "Hold")
                                    }

                                    "TGID" -> {
                                        //vm.tgidName = GSI_FindItem(it, "Name")
                                        vm.convFrequencyName= GSI_FindItem(it, "Name") //workaround
                                        vm.tgidIndex = GSI_FindItem(it, "Index")
                                        vm.tgidAvoid = GSI_FindItem(it, "Avoid")
                                        vm.tgidTGID = GSI_FindItem(it, "TGID")
                                        vm.tgidSetSlot = GSI_FindItem(it, "SetSlot")
                                        vm.tgidN_Tag = GSI_FindItem(it, "N_Tag")
                                        vm.tgidHold = GSI_FindItem(it, "Hold")
                                        //vm.tgidSvcType = GSI_FindItem(it, "Svc_Type")
                                        vm.convFrequencySvcType = GSI_FindItem(message, "SvcType") //workaround
                                        vm.tgidP_Ch = GSI_FindItem(it, "P_Ch")
                                        vm.tgidLVL = GSI_FindItem(it, "LVL")
                                    }

                                    "ConvFrequency" -> {
                                        vm.convFrequencyName= GSI_FindItem(it, "Name")
                                        vm.convFrequencyMod = GSI_FindItem(message, "Mod")
                                        vm.convFrequencyIndex = GSI_FindItem(message, "Index")
                                        vm.convFrequencyFreq = GSI_FindItem(message, "Freq")
                                        vm.convFrequencySvcType = GSI_FindItem(message, "SvcType")
                                        vm.convFrequencySAS = GSI_FindItem(message, "SAS")
                                        vm.convFrequencyP_Ch = GSI_FindItem(message, "P_Ch")
                                        vm.convFrequencySAD = GSI_FindItem(message, "SAD")
                                        vm.convFrequencyRecSlot = GSI_FindItem(message, "RecSlot")
                                        vm.convFrequencyLVL = GSI_FindItem(message, "LVL")
                                        vm.convFrequencyIFX = GSI_FindItem(message, "IFX")
                                        vm.convFrequencyTGID = GSI_FindItem(message, "TGID")
                                        vm.convFrequencyU_Id = GSI_FindItem(message, "U_Id")
                                        vm.convFrequencyHold = GSI_FindItem(message, "Hold")
                                    }

                                    "UnitID" -> {
                                        vm.unitIDName = GSI_FindItem(it, "Name")
                                        vm.unitU_Id = GSI_FindItem(it, "U_Id")
                                    }

                                    "Site" -> {
                                        vm.siteName = GSI_FindItem(it, "Name")
                                        vm.siteIndex = GSI_FindItem(it, "Index")
                                        vm.siteAvoid = GSI_FindItem(it, "Avoid")
                                        vm.siteQ_Key = GSI_FindItem(it, "Q_Key")
                                        vm.siteHold = GSI_FindItem(it, "Hold")
                                        //vm.siteMod = GSI_FindItem(it, "Mod")
                                        vm.convFrequencyMod = GSI_FindItem(message, "Mod") //workaround
                                    }

                                    "SiteFrequency" -> {
                                        //vm.siteFrequencyFreq = GSI_FindItem(it, "Freq")
                                        vm.convFrequencyFreq = GSI_FindItem(message, "Freq") //workaround
                                        vm.siteFrequencyIFX = GSI_FindItem(it, "IFX")
                                        vm.siteFrequencySAS = GSI_FindItem(it, "SAS")
                                        vm.siteFrequencySAD = GSI_FindItem(it, "SAD")
                                    }

                                    "DualWatch" -> {
                                        vm.dualWatchPRI = GSI_FindItem(it, "PRI")
                                        vm.dualWatchCC = GSI_FindItem(it, "CC", previousValue = vm.dualWatchCC)
                                        vm.dualWatchWX = GSI_FindItem(it, "WX")
                                    }

                                    "Property" -> {
                                        vm.propertyF = GSI_FindItem(it, "F")
                                        vm.propertyVOL = GSI_FindItem(it, "VOL", previousValue = vm.propertyVOL)
                                        vm.propertySQL = GSI_FindItem(it, "SQL", previousValue = vm.propertySQL)
                                        vm.propertySig = GSI_FindItem(it, "Sig")
                                        vm.propertyAtt = GSI_FindItem(it, "Att")
                                        vm.propertyRec = GSI_FindItem(it, "Rec")
                                        vm.propertyKeyLock = GSI_FindItem(it, "KeyLock")
                                        vm.propertyP25Status = GSI_FindItem(it, "P25Status")
                                        vm.propertyMute = GSI_FindItem(it, "Mute")
                                        vm.propertyBacklight = GSI_FindItem(it, "Backlight")
                                        vm.propertyA_led = GSI_FindItem(it, "A_Led")
                                        vm.propertyDir = GSI_FindItem(it, "Dir")
                                        vm.propertyRssi = GSI_FindItem(it, "Rssi")
                                    }
                                }
                            }
                        }
                    }


                    "MDL" -> {
                        // ex. MDL,BCD536HP
                        vm.model = message.substring((message.indexOf(",") + 1))
                    }

                    "LCR" -> {
                        try {
                            //ex. LCR,32.985321,-97.048116,0.0
                            var parts = message.split(",")
                            vm.latitude = parts[1]
                            vm.longitude = parts[2]
                            vm.range = parts[3]
                        } catch (e: Exception) {
                            println("Error 176: ${e.message}")
                        }
                    }

                    "STS" -> {
                        //Log.d("STS Message: ", message)
                        when {
                            vm.isSDSScanner() -> stsDecodeSDS(message)
                            vm.model.contains("536") -> stsDecode536(message)
                            else -> vm.stsLines = message.split(",").toTypedArray()
                        }
                    }

                    "PWR" -> {
                        var parts = message.split(",")
                        vm.pwrRSSI = parts[1]
                        vm.pwrFreq = parts[2]
                    }

                    else -> {
                        //Log.d("Unkown Mes type", message)
                    }

                }
            }
        } catch (e: Exception) {
            println("Error 202: ${e.message}")
        }
    }

    private fun GSI_FindItem(textLine: String, name: String, beginIndex: Int = 0, previousValue: String = ""): String {
        if ( textLine.substring(beginIndex).contains(" " + name) ) {
            val itemName = name + "=\""
            val start = textLine.indexOf(itemName, beginIndex) + itemName.length
            if (start == itemName.length - 1) { //not found
                return previousValue
            }
            val end = textLine.indexOf("\"", start)
            var result = textLine.substring(start, end)

            result = result.replace("&amp;", "&")
            result = result.replace("&apos;", "'")
            return result
        } else {
            return ""
        }
    }

    fun stsDecodeSDS(string: String) {
        var data = string.replace("STS,", "")
        var nextCommaLoc = data.indexOf(",")
        vm.stsLines[1] = data.substring(0, nextCommaLoc)
        data = data.removeRange(0, nextCommaLoc + 1 )
        for (i in 2..(((vm.stsLines[1].length) * 2) +1) ) {
            if (data.get(24) == ',') {
                vm.stsLines[i] = data.substring(0, 24)
                data = data.removeRange(0, 25)
            } else if (data.get(30) == ',') {
                vm.stsLines[i] = data.substring(0, 30)
                data = data.removeRange(0, 31)
            }
        }
    }

    fun stsDecode536(string: String) {
        var data = string.replace("STS,", "")
        var nextCommaLoc = data.indexOf(",")
        vm.stsLines[1] = data.substring(0, nextCommaLoc)
        data = data.removeRange(0, nextCommaLoc + 1 )
        for (i in 2..(((vm.stsLines[1].length) * 2) +1) ) {
            if (data.get(0) == ',') {
                vm.stsLines[i] = ""
                data = data.removeRange(0, 1)
            } else if (data.get(35) == ',') {
                vm.stsLines[i] = data.substring(0, 35)
                data = data.removeRange(0, 36)
            } else if (data.get(36) == ',') {
                vm.stsLines[i] = data.substring(0, 36)
                data = data.removeRange(0, 37)
            }
        }
    }
}