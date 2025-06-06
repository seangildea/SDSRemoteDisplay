package com.W1SPG.sdsremotedisplay

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.W1SPG.sdsremotedisplay.ui.theme.subValues

class viewModel : ViewModel() {

    val BLUE: Color = Color(0xFF0000FF)
    val RED: Color = Color(0xFFFF0000)
    val MAGENTA: Color = Color(0xFFFF00FF)
    val GREEN: Color = Color(0xFF00FF00)
    val CYAN: Color = Color(0xFF00FFFF)
    val YELLOW: Color = Color(0xFFFFD600)
    val WHITE: Color = Color(0xFFFFFFFF)
    val BACKGROUNDCOLOR: Color = Color(0xFF444444)
    val FOOTERTEXTCOLOR: Color = Color(0xFFe77475)
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

    val scannerCommands = listOf("MDL", "GSI", "LCR", "STS", "PWR")

    var stsLines by mutableStateOf(Array<String>(50) { "" })

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
    //var tgidSvcType: String  = ""
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
    var simpleMode: Boolean = false

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
    var closeCallString:String by mutableStateOf("")

    var textDisplayWeight: Float = .85f

    var lostConnection: Boolean = false

    var clearToSend: Boolean = true
    var lastResponseTime: Long = 0
    var clearToSendWatchdogTimer: Long = 0

    var displayLinesLength:Int by mutableStateOf(0)
    val BIGFONT = 28.sp
    val SMALLFONT = 14.sp
    val X36BIGFONT =  40.sp
    val X36SMALLFONT = 20.sp

    var maxVolume = 15
    var maxSquelch = 15

    var HDisplay by mutableStateOf (Array<String>(21) {""})

    var firstButton by mutableStateOf("System")
    var secondButton by mutableStateOf("Dept")
    var thirdButton by mutableStateOf("Channel")
    var highlightArray by mutableStateOf (Array<Boolean>(20) { false })
    var underlineArray by mutableStateOf(Array<Boolean>(20) { false })
    var colorArray by mutableStateOf(Array<Color>(20) { WHITE })

    fun isTrunk(): Boolean { // true if channel is trunked, false if conventional
        if (scannerInfoV_screen == "trunk_scan") {
            return true
        } else {
            return false
        }
    }

    fun isSDSScanner(): Boolean { // true if scanner is SDS100 or SDS200, false if not
        if (model.contains("SDS") || model == "") {
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
                "serv" -> keyPress = "KEY,T,P"
                "rang" -> keyPress = "KEY,R,P"
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
                    if (curVol < maxVolume) {
                        var newVol = curVol + 1
                        keyPress = "VOL," + newVol.toString()
                        //Log.d("VOLUME", "up")
                    }
                }

                "voldn" -> {
                    var curVol = propertyVOL.toInt()
                    if (curVol > 0) {
                        var newVol = curVol - 1
                        keyPress = "VOL," + newVol.toString()
                        //Log.d("VOLUME", "down")
                    }
                }

                "squp" -> {
                    var curSQ = propertySQL.toInt()
                    if (curSQ < maxSquelch) {
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
                else -> keyPress = "KEY,$button,P"
            }
        } catch (e: Exception) {
            //println("Error: ${e.message}")
        }
    }

    fun checkSTSLineData(num: Int, contents: String, contains:Boolean = false): Boolean {
        if (stsLines.size > num) {
            if (!contains) { //looking for an exact match only
                if (stsLines[num] == contents) {
                    return true
                } else {
                    return false
                }
            } else if (stsLines[num].contains(contents)) { //looking to see if it contains the string
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }

    fun getSTSLineData(num: Int): String {
        if (stsLines.size > num) {
            return stsLines[num]
        } else {
            return ""
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

    fun getFontSize(num: Int): TextUnit {
        try {
            val isSDS = vm.isSDSScanner()
            var fontData = getSTSLineData(1)
            var size = fontData.substring(num - 1, num)
            when {
                (num - 1) > fontData.length && isSDS -> return SMALLFONT
                (num - 1) > fontData.length && !isSDS -> return X36SMALLFONT
                size == "0" && isSDS -> return SMALLFONT
                size == "0" && !isSDS -> return X36SMALLFONT
                size == "1" && isSDS -> return BIGFONT
                size == "1" && !isSDS -> return X36BIGFONT
                else -> return SMALLFONT
            }
        } catch (e: Exception) {
            //println(e)
            return SMALLFONT
        }
    }

    fun getHighLightedStatus() {
        var data: String
        for (num in 1..10) {
            data = getSTSLineData((num * 2) + 1)
            if (data.contains("*")) {
                highlightArray[num] = true
            } else {
                highlightArray[num] = false
            }
        }
        // Needed to get jetpack to recompose
        highlightArray = highlightArray.copyOf()
    }

    fun getUnderlinedStatus() {
        var data: String
        for (num in 1..16) {
            data = getSTSLineData((num * 2) + 1)
            if (data.contains("_")) {
                underlineArray[num] = true
            } else {
                underlineArray[num] = false
            }
        }
        // Needed to get jetpack to recompose
        underlineArray = underlineArray.copyOf()
    }

    fun buildButtonText(num: String, subText: String): AnnotatedString
    {
        var butText = buildAnnotatedString {
            append(num)
            withStyle(style = SpanStyle(fontSize = 8.sp)) {
                append(" " + subText)
            }
        }
        return butText
    }

    fun isSimpleMode() {
        when {
            scannerInfoV_screen == "trunk_scan" || scannerInfoV_screen == "conventional_scan" -> {
                if (getSTSLineData(5).contains("_")
                    && getSTSLineData(11).contains("_")
                    && getSTSLineData(17).contains("_")) {
                        simpleMode = true
                } else {
                    simpleMode = false
                }
            }
        } // if not in scan mode, keep previous determined value
    }

    fun setMaxVolumeAndSquelch() {
        when (vm.model) {
            "SDS100" -> {
                maxVolume = 15
                maxSquelch = 15
            }
            "SDS200" -> {
                maxVolume = 29
                maxSquelch = 19
            }
            "BCD536HP" -> {
                maxVolume = 29
                maxSquelch = 19
            }
            "BCD436HP" -> { //Todo: what is the max vol and squelch for a 436?
                maxVolume = 15
                maxSquelch = 15
            }
        }
    }

    fun updateDisplayData() {
        // This will stop sending commands to the scanner when it is not responding
        // to try to prevent button lag
        var currentTime: Long = System.currentTimeMillis()
        if ((currentTime - lastResponseTime) > 1000) {
            clearToSend = false
            if (clearToSendWatchdogTimer == 0L) {
                clearToSendWatchdogTimer = currentTime
            }
        } else {
            clearToSend = true
            clearToSendWatchdogTimer = 0L
        }
        if ((currentTime - clearToSendWatchdogTimer) > 2500) {
            clearToSend = true
            clearToSendWatchdogTimer = 0L
        }

        isSimpleMode()

        setMaxVolumeAndSquelch()

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

        if (isPortraitMode) {
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

            when {
                systemHold == "On" -> {
                    systemTextColor = BACKGROUNDCOLOR
                    systemBackColor = defaultSystemTextColor
                }

                departmentHold == "On" -> {
                    departmentTextColor = BACKGROUNDCOLOR
                    deptBackColor = defaultDeptTextColor
                }

                tgidHold == "On" || convFrequencyHold == "On" -> {
                    channelTextColor = BACKGROUNDCOLOR
                    channelBackColor = defaultChanTextColor
                }

                searchScreen && srchFreqHold == "On" -> {
                    channelTextColor = BACKGROUNDCOLOR
                    channelBackColor = defaultChanTextColor
                    holdButtonText = "Resume"
                }

                closeCallHit && srchFreqHold == "On" -> {
                    channelTextColor = BACKGROUNDCOLOR
                    channelBackColor = defaultChanTextColor
                    holdButtonText = "Release"
                }

                else -> {
                    systemTextColor = defaultSystemTextColor
                    systemBackColor = BACKGROUNDCOLOR
                    departmentTextColor = defaultDeptTextColor
                    deptBackColor = BACKGROUNDCOLOR
                    channelTextColor = defaultChanTextColor
                    channelBackColor = BACKGROUNDCOLOR
                    holdButtonText = "Hold"
                }
            }


            //QuickKeyStatus field 1
            when {
                simpleMode -> displayQuickKeyStatus1 = ""
                closeCallHit -> displayQuickKeyStatus1 = ""
                searchScreen -> displayQuickKeyStatus1 = "Search"
                isSDSScanner() -> displayQuickKeyStatus1 = getSTSLineData(4).take(13).trim()
                !(isSDSScanner()) -> displayQuickKeyStatus1 = getSTSLineData(2).take(13).trim()
                else -> displayQuickKeyStatus1 = ""
            }

            //Quick key status field 2
            when {
                simpleMode -> displayQuickKeyStatus2 = ""
                closeCallHit -> displayQuickKeyStatus2 = ""

                (isSDSScanner() && searchScreen) -> displayQuickKeyStatus2 =
                    getSTSLineData(6).take(13).trimEnd()

                (!(isSDSScanner()) && searchScreen) -> displayQuickKeyStatus2 =
                    getSTSLineData(4).take(13).trimEnd()

                isSDSScanner() -> displayQuickKeyStatus2 = getSTSLineData(6).take(13).trim()
                !(isSDSScanner()) -> displayQuickKeyStatus2 = getSTSLineData(4).take(13).trim()
                else -> displayQuickKeyStatus2 = ""
            }

            //Quick key status field 3
            when {
                simpleMode -> displayQuickKeyStatus3= ""
                closeCallHit -> displayQuickKeyStatus3 = ""
                isSDSScanner() -> displayQuickKeyStatus3 = getSTSLineData(8).take(13).trim()
                else -> displayQuickKeyStatus3 = ""
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
                checkSTSLineData(19, "Nothing to", contains = true) ->
                    displayHeader1 = "Nothing to Scan"

                else -> displayHeader1 = systemName
            }

            //Header 2 display (dept name / "Scanning" label / search bank name / close call freq)
            when {
                lostConnection -> displayHeader2 = ""
                searchScreen -> displayHeader2 = searchBanksName
                closeCallHit -> displayHeader2 = ""
                (departmentName == "") && (systemName != "Not Connected") -> displayHeader2 =
                    "Scanning..."

                else -> displayHeader2 = departmentName
            }

            //Header 3 display ( freq name / search freq )
            when {
                lostConnection -> displayHeader3 = ""
                searchScreen -> displayHeader3 = srchFreqFreq
                closeCallHit -> displayHeader3 = srchFreqFreq
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
                searchScreen -> displayFooter6 = ""
                closeCallHit -> displayFooter6 = ""
                (systemSystemType == "P25 Trunk") -> displayFooter6 = systemSystemType
                else -> displayFooter6 = ""
            }

            //Footer 7 (RSSI)
            when {
                (systemName == "Not Connected") -> displayFooter7 = ""
                isSDSScanner() && !searchScreen -> displayFooter7 = getRSSI()
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

            if (dualWatchCC == "DND") {
                closeCallString = "CC:DND"
            } else if (dualWatchCC == "Priority") {
                closeCallString = "CC:Pri"
            } else {
                closeCallString = ""
            }
        } else { // Landscape mode
            //======================================================================================
            //======================================================================================

            displayLinesLength = getSTSLineData(1).length

            for (line in 2..40 step 2) {
                HDisplay[line / 2] = getSTSLineData(line)
            }

            if (!connectedToScannerUSB && !connectedToScannerWIFI) {
                displayLinesLength = 5
                HDisplay[1] = "111111" //force large font
                HDisplay[4] = "NO CONNECTION"
            }

            // remove uniden font
            if (!scannerInfoV_screen.contains("menu")) {
                HDisplay[1] = HDisplay[1].replace("��", "")
            }

            // function mode indicator
            if (propertyF == "On") {
                HDisplay[1] = "FUNC" + HDisplay[1]
            }

            // get bottom button text
            var displayButtonTextArray = mutableListOf<String>()
            var buttonData = mutableListOf<String>()
            if (displayLinesLength == 17 ) {
                displayButtonTextArray = HDisplay[17].trim().split("  ").toMutableList()
                HDisplay[17] = ""
            } else if (displayLinesLength == 14) {
                displayButtonTextArray = HDisplay[14].trim().split("  ").toMutableList()
                HDisplay[14] = ""
            } else if (displayLinesLength == 20) { //close call only mode
                displayButtonTextArray = HDisplay[20].trim().split("  ").toMutableList()
                HDisplay[20] = ""
            }
            for (button in displayButtonTextArray) {
                if (button != "") {
                    buttonData.add(button)
                }
            }

            try {
                if (!buttonData[0].contains("x0")) {
                    firstButton = buttonData[0].trim()
                } else {
                    firstButton = ""
                }
            } catch (e: Exception) {
                firstButton = ""
            }

            try {
                if (!buttonData[1].contains("x01")) {
                    secondButton = buttonData[1].trim()
                } else {
                    secondButton = ""
                }
            } catch (e: Exception) {
                secondButton = ""
            }

            try {
                if (!buttonData[2].contains("x01")) {
                    thirdButton = buttonData[2].trim()
                } else {
                    thirdButton = ""
                }
            } catch (e: Exception) {
                thirdButton = ""
            }
            if (!vm.isSDSScanner()) {
                firstButton = "System"
                secondButton = "Dept"
                thirdButton = "Channel"
            }
        }
        getHighLightedStatus()
        getUnderlinedStatus()

        for (i in 1..(colorArray.size-1)) {
            when {
                scannerInfoMode == "Menu tree" -> colorArray[i] = WHITE
                !simpleMode -> {
                    when {
                        i < 5 || i == 16 -> colorArray[i] = WHITE
                        i > 4 && i < 7 -> colorArray[i] = defaultSystemTextColor
                        i > 6 && i < 9 -> colorArray[i] = defaultDeptTextColor
                        i > 7 && i < 12 -> colorArray[i] = defaultChanTextColor
                        else -> colorArray[i] = FOOTERTEXTCOLOR
                    }
                }
                simpleMode -> {
                    when {
                        i < 3 || i == 13 -> colorArray[i] = WHITE
                        i > 2 && i < 6 -> colorArray[i] = defaultSystemTextColor
                        i > 5 && i < 9 -> colorArray[i] = defaultDeptTextColor
                        i > 8 && i < 12 -> colorArray[i] = defaultChanTextColor
                        else -> colorArray[i] = FOOTERTEXTCOLOR
                    }
                }
                else -> colorArray[i] = FOOTERTEXTCOLOR
            }
            // Needed to get Jetpack to recompose
            colorArray = colorArray.copyOf()
        }

    }
}