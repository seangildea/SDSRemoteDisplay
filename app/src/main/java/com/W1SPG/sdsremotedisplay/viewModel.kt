package com.W1SPG.sdsremotedisplay

import android.hardware.usb.UsbAccessory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
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
    var latitude: String by mutableStateOf("")
    var longitude: String by mutableStateOf("")
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

    var systemName: String by mutableStateOf("Not Connected")
    var systemIndex: String = ""
    var systemAvoid: String = ""
    var systemSystemType: String by mutableStateOf("")
    var systemQ_Key: String = ""
    var systemN_Tag: String = ""
    var systemHold: String = ""

    var departmentName: String by mutableStateOf("")
    var departmentIndex: String = ""
    var departmentAvoid: String = ""
    var departmentQ_Key: String = ""
    var departmentHold: String = ""

    var convFrequencyName: String by mutableStateOf("")
    var convFrequencyIndex: String = ""
    var convFrequencyAvoid: String = ""
    var convFrequencyFreq: String by mutableStateOf("")
    var convFrequencyMod: String by mutableStateOf("")
    var convFrequencyHold: String = ""
    var convFrequencySvcType: String by mutableStateOf("")
    var convFrequencyP_Ch: String = ""
    var convFrequencySAS: String = ""
    var convFrequencySAD: String = ""
    var convFrequencyRecSlot: String = ""
    var convFrequencyLVL: String = ""
    var convFrequencyIFX: String = ""
    var convFrequencyTGID: String = ""
    var convFrequencyU_Id: String = ""

    var tgidName: String by mutableStateOf("")
    var tgidIndex: String = ""
    var tgidAvoid: String = ""
    var tgidTGID: String by mutableStateOf("")
    var tgidSetSlot: String = ""
    var tgidN_Tag: String = ""
    var tgidHold: String = ""
    var tgidSvcType: String by mutableStateOf("")
    var tgidP_Ch: String = ""
    var tgidLVL: String = ""

    var unitIDName: String by mutableStateOf("")
    var unitU_Id: String by mutableStateOf("")

    var siteName: String by mutableStateOf("")
    var siteIndex: String = ""
    var siteAvoid: String = ""
    var siteQ_Key: String = ""
    var siteHold: String = ""
    var siteMod: String by mutableStateOf("")

    var siteFrequencyFreq: String by mutableStateOf("")
    var siteFrequencyIFX: String = ""
    var siteFrequencySAS: String = ""
    var siteFrequencySAD: String = ""

    var dualWatchPRI: String = ""
    var dualWatchCC: String = ""
    var dualWatchWX: String = ""

    var propertyF: String = ""
    var propertyVOL: String by mutableStateOf("")
    var propertySQL: String by mutableStateOf("")
    var propertySig: String = ""
    var propertyAtt: String = ""
    var propertyRec: String = ""
    var propertyKeyLock: String = ""
    var propertyP25Status: String = ""
    var propertyMute: String = ""
    var propertyBacklight: String = ""
    var propertyA_led: String = ""
    var propertyDir: String = ""
    var propertyRssi: String by mutableStateOf("")

    var srchFreqFreq: String by mutableStateOf("")
    var srchFreqMode: String by mutableStateOf("")

    var searchBanksBankStatus: String by mutableStateOf("")
    var searchBanksName: String by mutableStateOf("")
    var searchBanksBankNo: String by mutableStateOf("")

    var searchRangeLower: String by mutableStateOf("")
    var searchRangeUpper: String by mutableStateOf("")
    var searchRangeMod: String by mutableStateOf("")
    var searchRangeStep: String by mutableStateOf("")

    var favoriteQKStatus: String by mutableStateOf("")
    var systemQKStatus: String by mutableStateOf("")
    var departmentQKStatus: String by mutableStateOf("")

    var pwrRSSI: String by mutableStateOf("")
    var pwrFreq: String = ""

    var keyPress: String = ""
    var channel :String by mutableStateOf("")

    var closeCallHit: Boolean = false
    var searchScreen: Boolean = false

    fun getFavQK(): String {
        if (searchScreen) {
            return ""
        }
        favoriteQKStatus = favoriteQKStatus.trim()
        if (isSDSScanner()) {
            favoriteQKStatus = displayLines[4].take(13)
        } else {
            favoriteQKStatus = displayLines[2].take(13)
        }
        if (favoriteQKStatus.take(1)  == "F") {
            return favoriteQKStatus
        } else {
            return ""
        }
    }

    fun getSystemQK(): String {
        systemQKStatus = systemQKStatus.trim()
        if (isSDSScanner()) {
            systemQKStatus = displayLines[6].take(13)
        } else {
            systemQKStatus = displayLines[4].take(13)
        }
        if (systemQKStatus.take(1) == "S") {
            return systemQKStatus
        } else {
            return ""
        }

    }

    fun getDeptQK(): String {
        if (isSDSScanner()) {
            departmentQKStatus = displayLines[8].take(13)
        } else {
            departmentQKStatus = ""
        }
        if (departmentQKStatus.take(1) == "D") {
            return departmentQKStatus
        } else {
            return ""
        }
    }

    fun getSystem(): String {
        if (closeCallHit) {
            return "Close Call Hit"
        }
        if (searchScreen) {
            return "Custom Search"
        }
        return systemName
    }

    fun getDept(): String {
        if (closeCallHit) {
            return srchFreqFreq
        }
        if (searchScreen) {
            return srchFreqFreq
        }
        if ((departmentName == "") && (systemName != "Not Connected")) {
            return "Scanning..."
        } else {
            return departmentName
        }
    }

    fun getChan(): String {
        if (closeCallHit) {
            return srchFreqMode
        }
        if (searchScreen) {
            return srchFreqMode
        }
        channel = convFrequencyName
        return channel
    }

    fun getVolume(): String {
        return "Vol: " + propertyVOL
    }

    fun getSquelch(): String {
        return "SQ: " + propertySQL
    }

    fun getSVCType(): String {
        if (closeCallHit) {
            return ""
        }
        return convFrequencySvcType
    }

    fun getFrequency(): String {
        if (closeCallHit) {
            return ""
        }
        return convFrequencyFreq
    }

    fun getMode(): String {
        if (closeCallHit) {
            return ""
        }
        if (isTrunk() && unitU_Id != "") {
            return unitIDName
        } else {
            return convFrequencyMod
        }
    }

    fun getSite(): String {
        if (closeCallHit) {
            return ""
        }
        return siteName
    }

    fun getTGIDID(): String {
        if (closeCallHit) {
            return ""
        }
        return tgidTGID
    }

    fun getRSSI(): String {
        try {
            if (systemName == "Not Connected") {
                return ""
            }
            var rssiString = ""
            if (pwrRSSI != "") {
                var rssiVal = pwrRSSI.toDouble()
                if (rssiVal == -999.0) {
                    rssiString = "---"
                } else {
                    rssiString = pwrRSSI
                }
            }
            return "RSSI:" + rssiString
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return ""
        }
    }

    fun getLat(): String {
        return "Latitude: " + latitude
    }

    fun getLong(): String {
        return "Longitude: " + longitude
    }

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
            println("Error: ${e.message}")
        }
    }

    fun getBackGrndColor(): Color {
        return backGroundColor
    }

    fun getLEDColor(): androidx.compose.ui.graphics.Color {
        when (propertyA_led) {
            //Blue, Red, Magenta, Green, Cyan, Yellow, White, Off
            "Blue" -> return BLUE
            "Red" -> return RED
            "Magenta" -> return MAGENTA
            "Green" -> return GREEN
            "Cyan" -> return CYAN
            "Yellow" -> return YELLOW
            "White" -> return WHITE
            "Off" -> return backGroundColor
            else -> return backGroundColor
        }
    }

    fun getSysTextColor(): Color {
        if (systemHold == "On") {
            return backGroundColor
        } else {
            return systemTextColor
        }
    }

    fun getSysBackgroundColor(): Color {
        if (systemHold == "On") {
            return systemTextColor
        } else {
            return backGroundColor
        }
    }

    fun getDeptTextColor(): Color {
        if (departmentHold == "On") {
            return backGroundColor
        } else {
            return departmentTextColor
        }
    }

    fun getDeptBackgroundColor(): Color {
        if (departmentHold == "On") {
            return departmentTextColor
        } else {
            return backGroundColor
        }
    }

    fun getChanTextColor(): Color {
        if (isTrunk()) { //trunk channel
            if (tgidHold == "On") {
                return backGroundColor
            } else {
                return channelTextColor
            }
        } else { // Conventional channel
            if (convFrequencyHold == "On") {
                return backGroundColor
            } else {
                return channelTextColor
            }
        }
    }

    fun getChanBackgroundColor(): Color {
        if (isTrunk()) { //trunk channel
            if (tgidHold == "On") {
                return channelTextColor
            } else {
                return backGroundColor
            }
        } else { // Conventional channel
            if (convFrequencyHold == "On") {
                return channelTextColor
            } else {
                return backGroundColor
            }
        }
    }

    fun lostConnection() {
        systemName = "Not Connected"
        departmentName = ""
        tgidName = ""
        convFrequencyName = ""
    }

    fun checkForChanges() {

        if (systemHold == "On") {
            systemTextColor = BACKGROUNDCOLOR
            systemBackColor = defaultSystemTextColor
        } else {
            systemTextColor = defaultSystemTextColor
            systemBackColor = BACKGROUNDCOLOR
        }

        if (departmentHold == "On") {
            departmentTextColor = BACKGROUNDCOLOR
            deptBackColor = defaultDeptTextColor
        } else {
            departmentTextColor = defaultDeptTextColor
            deptBackColor = BACKGROUNDCOLOR
        }

        if (tgidHold == "On" || convFrequencyHold == "On") {
            channelTextColor = BACKGROUNDCOLOR
            channelBackColor = defaultChanTextColor
        } else {
            channelTextColor = defaultChanTextColor
            channelBackColor = BACKGROUNDCOLOR
        }

        //close call hit
        if (scannerInfoV_screen == "close_call") {
            closeCallHit = true
        } else {
            closeCallHit = false
        }

        if (scannerInfoV_screen == "custom_search") {
            searchScreen = true
        } else {
            searchScreen = false
        }

    }
}