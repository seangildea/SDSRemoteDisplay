package com.W1SPG.sdsremotedisplay

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel

class viewModel : ViewModel() {

    val BLUE: Color = Color(0xFF0000FF)
    val RED: Color = Color(0xFFFF0000)
    val MAGENTA: Color = Color(0xFFFF00FF)
    val GREEN: Color = Color(0xFF00FF00)
    val CYAN: Color = Color(0xFF00FFFF)
    val YELLOW: Color = Color(0xFFFFD600)
    val WHITE: Color = Color(0xFFFFFFFF)
    val BACKGROUNDCOLOR: Color = Color(0xFF444444)
    val FOOTERTEXTCOLOR: Color = Color("#e77475".toColorInt())
    val defaultSystemTextColor = Color(0xFFFF4600)
    val defaultDeptTextColor = Color(0xFFFF8800)
    val defaultChanTextColor = Color(0xFFFFD600)

    var backGroundColor: Color by mutableStateOf(BACKGROUNDCOLOR)
    var systemTextColor: Color by mutableStateOf(defaultSystemTextColor)
    var systemBackColor: Color by mutableStateOf(BACKGROUNDCOLOR)
    var departmentTextColor: Color by mutableStateOf(defaultDeptTextColor)
    var deptBackColor:  Color by mutableStateOf(BACKGROUNDCOLOR)
    var channelTextColor: Color by mutableStateOf(defaultChanTextColor)
    var channelBackColor:  Color by mutableStateOf(BACKGROUNDCOLOR)

    var model: String = ""
    var latitude: String = ""
    var longitude: String = ""
    var range: String = ""

    var displayLines = Array<String>(50) { "" }

    var scannerInfoMode: String = ""
    var scannerInfoV_screen: String = ""

    var monitorListName: String = ""
    var monitorListIndex: String = ""
    var monitorListListType: String = ""
    var monitorListQ_Key: String = ""
    var monitorListN_Tag: String = ""
    var monitorListDB_Counter: String = ""

    var systemName: String = ""
    var systemIndex: String = ""
    var systemAvoid: String = ""
    var systemSystemType: String = ""
    var systemQ_Key: String = ""
    var systemN_Tag: String = ""
    var systemHold: String = ""

    var departmentName: String = ""
    var departmentIndex: String = ""
    var departmentAvoid: String = ""
    var departmentQ_Key: String = ""
    var departmentHold: String = ""

    var convFrequencyName: String = ""
    var convFrequencyIndex: String = ""
    var convFrequencyAvoid: String = ""
    var convFrequencyFreq: String = ""
    var convFrequencyMod: String = ""
    var convFrequencyHold: String = ""
    var convFrequencySvcType: String = ""
    var convFrequencyP_Ch: String = ""
    var convFrequencySAS: String = ""
    var convFrequencySAD: String = ""
    var convFrequencyRecSlot: String = ""
    var convFrequencyLVL: String = ""
    var convFrequencyIFX: String = ""
    var convFrequencyTGID: String = ""
    var convFrequencyU_Id: String = ""

    var tgidIndex: String = ""
    var tgidAvoid: String = ""
    var tgidTGID: String = ""
    var tgidSetSlot: String = ""
    var tgidN_Tag: String = ""
    var tgidHold: String = ""
    var tgidSvcType: String  = ""
    var tgidP_Ch: String = ""
    var tgidLVL: String = ""

    var unitIDName: String = ""
    var unitU_Id: String = ""

    var siteName: String = ""
    var siteIndex: String = ""
    var siteAvoid: String = ""
    var siteQ_Key: String = ""
    var siteHold: String = ""

    var siteFrequencyIFX: String = ""
    var siteFrequencySAS: String = ""
    var siteFrequencySAD: String = ""

    var dualWatchPRI: String = ""
    var dualWatchCC: String = ""
    var dualWatchWX: String = ""

    var propertyF: String = ""
    var propertyVOL: String = ""
    var propertySQL: String = ""
    var propertySig: String = ""
    var propertyAtt: String = ""
    var propertyRec: String = ""
    var propertyKeyLock: String = ""
    var propertyP25Status: String = ""
    var propertyMute: String = ""
    var propertyBacklight: String = ""
    var propertyA_led: String = ""
    var propertyDir: String = ""
    var propertyRssi: String = ""

    var srchFreqFreq: String = ""
    var srchFreqMode: String = ""
    var srchFreqHold: String = ""
    var searchBanksBankStatus: String = ""
    var searchBanksName: String = ""
    var searchBanksBankNo: String = ""
    var searchRangeLower: String = ""
    var searchRangeUpper: String = ""
    var searchRangeMod: String = ""
    var searchRangeStep: String = ""

    var pwrRSSI: String = ""
    var pwrFreq: String = ""

    var keyPress: String = ""

    var closeCallHit: Boolean = false
    var searchScreen: Boolean = false

    var displayLED: Color by mutableStateOf(backGroundColor)
    var displayQuickKeyStatus1: String by mutableStateOf("")
    var displayQuickKeyStatus2: String by mutableStateOf("")
    var displayQuickKeyStatus3: String by mutableStateOf("")
    var displayVolume: String by mutableStateOf("")
    var displaySquelch: String by mutableStateOf("")
    var displayHeader1: String by mutableStateOf("Not Connected")
    var displayHeader2: String by mutableStateOf("")
    var displayHeader3: String by mutableStateOf("")
    var displayFooter1: String by mutableStateOf("")
    var displayFooter2: String by mutableStateOf("")
    var displayFooter3: String by mutableStateOf("")
    var displayFooter4: String by mutableStateOf("")
    var displayFooter5: String by mutableStateOf("")
    var displayFooter6: String by mutableStateOf("")
    var displayFooter7: String by mutableStateOf("")
    var displayLatitude: String by mutableStateOf("")
    var displayLongitude: String by mutableStateOf("")
    var holdButtonText: String by mutableStateOf("Hold")

    var textDisplayWeight: Float = .85f

    var lostConnection: Boolean = false


    fun isTrunk(): Boolean { // true if channel is trunked, false if conventional
        if (scannerInfoV_screen == "trunk_scan") {
            return true
        } else {
            return false
        }
    }

    fun isSDSScanner(): Boolean { //true if scanner is SDS100 or SDS200, false if not
        if (model.contains("SDS")) {
            return true
        } else {
            return false
        }
    }

    fun pressButton(button: String) {
        try {
            when (button) {
                "avoid" -> keyPress = "KEY,L,P"
                "system" -> keyPress = "KEY,A,P"
                "department" -> keyPress = "KEY,B,P"
                "channel" -> keyPress = "KEY,C,P"
                "hold" -> keyPress = "KEY,C,P"
                "toscan" -> if (isSDSScanner()) {
                    keyPress = "KEY,A,P"
                } else {
                    keyPress =  "KEY,F,P KEY,A,P"
                }
                "dot" -> keyPress = "KEY,.,P"
                "<" -> keyPress = "KEY,<,P"
                ">" -> keyPress = "KEY,>,P"

                "volup" -> {
                    var curVol = propertyVOL.toInt()
                    if (curVol < 15) {
                        var newVol = curVol + 1
                        keyPress = "VOL," + newVol.toString()
                    }
                }

                "voldn" -> {
                    var curVol = propertyVOL.toInt()
                    if (curVol > 0) {
                        var newVol = curVol - 1
                        keyPress = "VOL," + newVol.toString()
                    }
                }

                "squp" -> {
                    var curSQ = propertySQL.toInt()
                    if (curSQ < 15) {
                        var newSQ = curSQ + 1
                        keyPress = "SQL," + newSQ.toString()
                    }
                }

                "sqdn" -> {
                    var curSQ = propertySQL.toInt()
                    if (curSQ > 0) {
                        var newSQ = curSQ - 1
                        keyPress = "SQL," + newSQ.toString()
                    }
                }
            }
        } catch (e: Exception) {
            //println("Error: ${e.message}")
        }
    }

    fun getRSSI(): String {
        try {
            var rssiString = ""
            if (pwrRSSI != "") {
                var rssiVal = pwrRSSI.toDouble()
                if (rssiVal == -999.0) {
                    rssiString = "---"
                } else {
                    rssiString = pwrRSSI
                }
            }
            return "RSSI:" + rssiString + " dBm"
        } catch (e: Exception) {
            //println("Error: ${e.message}")
            return ""
        }
    }

    fun updateDisplayData() {

        /*
        //todo: remove this?
        if ("USB Cable Detected" in displayLines[6]) {
            pressButton("dot")
            displayLines[6] = ""
        }
        */

        //system hold
        if (systemHold == "On") {
            systemTextColor = BACKGROUNDCOLOR
            systemBackColor = defaultSystemTextColor
        } else {
            systemTextColor = defaultSystemTextColor
            systemBackColor = BACKGROUNDCOLOR
        }

        //department hold
        if (departmentHold == "On") {
            departmentTextColor = BACKGROUNDCOLOR
            deptBackColor = defaultDeptTextColor
        } else {
            departmentTextColor = defaultDeptTextColor
            deptBackColor = BACKGROUNDCOLOR
        }

        //channel hold
        if (tgidHold == "On" || convFrequencyHold == "On") {
            channelTextColor = BACKGROUNDCOLOR
            channelBackColor = defaultChanTextColor
        } else {
            channelTextColor = defaultChanTextColor
            channelBackColor = BACKGROUNDCOLOR
        }

        //search hold
        if (searchScreen && srchFreqHold == "On") { //search hold
            channelTextColor = BACKGROUNDCOLOR
            channelBackColor = defaultChanTextColor
            holdButtonText = "Resume"
        } else if (closeCallHit && srchFreqHold == "On") { //close call hold
            channelTextColor = BACKGROUNDCOLOR
            channelBackColor = defaultChanTextColor
            holdButtonText = "Release"
        } else {
            channelTextColor = defaultChanTextColor
            channelBackColor = BACKGROUNDCOLOR
            holdButtonText = "Hold"
        }

        when (scannerInfoV_screen) {
            "close_call" -> {
                closeCallHit = true
                textDisplayWeight = .75f
            }
            "custom_search" -> {
                searchScreen = true
                textDisplayWeight = .75f
            }
            else -> {
                closeCallHit = false
                searchScreen = false
                textDisplayWeight = .85f
            }
        }

        //LED
        when (propertyA_led) {
            //Blue, Red, Magenta, Green, Cyan, Yellow, White, Off
            "Blue" -> displayLED = BLUE
            "Red" -> displayLED = RED
            "Magenta" -> displayLED = MAGENTA
            "Green" -> displayLED = GREEN
            "Cyan" -> displayLED = CYAN
            "Yellow" -> displayLED = YELLOW
            "White" -> displayLED = WHITE
            "Off" -> displayLED = backGroundColor
            else -> displayLED = backGroundColor
        }

        //QuickKeyStatus field 1
        when {
            closeCallHit -> displayQuickKeyStatus1 = ""
            searchScreen -> displayQuickKeyStatus1 = "Search"
            isSDSScanner() -> displayQuickKeyStatus1 = displayLines[4].take(13).trim()
            !(isSDSScanner()) -> displayQuickKeyStatus1 = displayLines[2].take(13).trim()
            else -> displayQuickKeyStatus1 = ""
        }

        //Quick key status field 2
        try {
            when {
                closeCallHit -> displayQuickKeyStatus2 = ""
                isSDSScanner() -> displayQuickKeyStatus2 = displayLines[6].take(13).trim()
                !(isSDSScanner()) -> displayQuickKeyStatus2 = displayLines[4].take(13).trim()
                else -> displayQuickKeyStatus2 = ""
            }
        } catch (e: Exception) {
            println(e)
            // rare exception here when STS result only has 4 entries during search
        }

        //Quick key status field 3
        try {
            when {
                closeCallHit -> displayQuickKeyStatus3 = ""
                isSDSScanner() -> displayQuickKeyStatus3 = displayLines[8].take(13).trim()
                else -> displayQuickKeyStatus3 = ""
            }
        } catch (e: Exception) {
            println(e)
            // rare exception here when STS result only has 4 entries during search
            // not sure if we are missing responses or the scanner is not sending them
        }

        //volume
        displayVolume = "Vol: " + propertyVOL
        //squelch
        displaySquelch = "SQ: " + propertySQL

        //Header 1 display (system name / "Close call" label / "Searching" label)
        when {
            lostConnection -> displayHeader1 = "Lost Connection"
            closeCallHit -> displayHeader1 = "Close Call"
            searchScreen -> displayHeader1 = "Searching"
            else -> displayHeader1 = systemName
        }

        //Header 2 display (dept name / "Scanning" label / search bank name / close call freq)
        when {
            lostConnection -> displayHeader2 = ""
            searchScreen -> displayHeader2 = searchBanksName
            closeCallHit -> displayHeader2 = ""
            (departmentName == "") && (systemName != "Not Connected") -> displayHeader2 = "Scanning..."
            else -> displayHeader2 = departmentName
        }

        //Header 3 display ( freq name / search freq )
        when {
            lostConnection -> displayHeader3 = ""
            searchScreen -> displayHeader3 = srchFreqFreq
            closeCallHit -> displayHeader3 =  srchFreqFreq
            else -> displayHeader3 = convFrequencyName
        }

        //footer 1 (svcType /  current search mode / close call hit mode)
        when {
            closeCallHit -> displayFooter1 = srchFreqMode
            searchScreen -> displayFooter1 = srchFreqMode
            else -> displayFooter1 = convFrequencySvcType
        }

        //Footer 2 (Frequency / Lower search range)
        when {
            searchScreen -> displayFooter2 = "Lower: " + searchRangeLower
            closeCallHit -> displayFooter2 = getRSSI()
            else -> displayFooter2 = convFrequencyFreq
        }

        //Footer 3 (Mode/UID Name/upper search rannge)
        when {
            searchScreen -> displayFooter3 = "Upper: " + searchRangeUpper
            closeCallHit -> displayFooter3 = ""
            (isTrunk() && unitU_Id != "") -> displayFooter3 = unitIDName
            else -> displayFooter3 = convFrequencyMod
        }

        //Footer 4 (Site)
        when {
            searchScreen -> displayFooter4 = "Step: " + searchRangeStep
            closeCallHit -> displayFooter4 = ""
            isTrunk() -> displayFooter4 = siteName
            else -> displayFooter4 = ""
        }

        //Footer 5 (TGID ID)
        when {
            searchScreen -> displayFooter5 = getRSSI()
            closeCallHit -> displayFooter5 = ""
            isTrunk() -> displayFooter5 = tgidTGID
            else -> displayFooter5 = ""
        }

        //Footer 6 (P25 Trunk)
        when {
            (systemSystemType == "P25 Trunk") -> displayFooter6 = systemSystemType
            else -> displayFooter6 = ""
        }

        //Footer 7 (RSSI)
        when {
            (systemName == "Not Connected") -> displayFooter7 = ""
            isSDSScanner() &&  !searchScreen -> displayFooter7 = getRSSI()
            else -> displayFooter7 = ""
        }

        //Lat - Long
        when {
            closeCallHit -> {
                displayLatitude = ""
                displayLongitude = ""
            }
            searchScreen -> {
                displayLatitude = ""
                displayLongitude = ""
            }
            locationPermissionGranted -> {
                displayLatitude = "Latitude: " + latitude
                displayLongitude = "Longitude: " + longitude
            }
            else -> {
                displayLatitude = ""
                displayLongitude = ""
            }
        }
    }
}