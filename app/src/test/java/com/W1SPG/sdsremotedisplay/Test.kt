package com.W1SPG.sdsremotedisplay

import org.junit.Test
import org.junit.Assert.*

class STSTests {
    @Test
    fun stsTest_100_Conv_Simple() {
        val testData =
            "STS,00110110110000,              Jan19 22:42     ,                              ,  V:5   S:6                   ,______________________________,Jacksonville (ZJX) Air  ,                        ,Route Traffic Control \u0006\u0007,                        ,Air                           ,______________________________,Florida RCAG Sites -    ,                        ,Tallahassee, FL - RCAG  ,                        ,                              ,______________________________,Sector 33 Geneva High   ,************************,                        ,************************, 360.800000MHz                ,                              ,Aircraft                      ,______________________________,AM                GPS   CC    ,                              , SYSTEM      DEPT     CHANNEL ,********* ********** *********,0,1,0,0,,,0,OFF,3"
        val vm = viewModel()
        val psd = ParseScannerData(vm)
        psd.stsDecodeSDS(testData)
        assertEquals(vm.stsLines[1], "00110110110000")
        assertEquals(vm.stsLines[14], "Tallahassee, FL - RCAG  ")
        assertEquals(vm.stsLines[22], " 360.800000MHz                ")
    }

    @Test
    fun stsTest_200_Trunk_Detail() {
        val testData =
            "STS,00001010100000000,              Jul19 10:58     ,                              ,F0:-1********               xx,                              ,S0:-1--------   VOL: 9 SQL: 3 ,                              ,D9:-------78*   Tag:01.01.--- ,                              ,Brevard County                ,                              ,Brevard County                ,                              ,                              ,                              ,Beach Simulcast               ,                              ,ID Scanning...                ,                              ,                              ,                              ,                              ,                              ,Sys ID: ---      851.862500MHz,                              ,RFSS ID: ---    Site ID: ---  ,                              ,                Batt:-.--V    ,                              ,UID: ---        RSSI: ---     ,                              ,                              ,                              , SYSTEM      DEPT     CHANNEL ,********* ********** *********,1,1,0,0,,,0,BLUE,3"
        val vm = viewModel()
        val psd = ParseScannerData(vm)
        psd.stsDecodeSDS(testData)
        assertEquals(vm.stsLines[1], "00001010100000000")
        assertEquals(vm.stsLines[6], "S0:-1--------   VOL: 9 SQL: 3 ")
        assertEquals(vm.stsLines[24], "Sys ID: ---      851.862500MHz")
    }

    @Test
    fun stsTest_536_Trunk() {
        val testData =
            "STS,0000100, F0:-1******** 11:26          xx    ,, S0:-1-------- Jul19                ,,Brevard County                      ,,Cocoa                               ,,Fire                                ,, Fire-Talk    TGID:1449             ,,                                    ,,1,1,0,0,,,5,OFF,3"
        val vm = viewModel()
        val psd = ParseScannerData(vm)
        psd.stsDecode536(testData)
        assertEquals(vm.stsLines[1], "0000100")
        assertEquals(vm.stsLines[4], " S0:-1-------- Jul19                ")
        assertEquals(vm.stsLines[12], " Fire-Talk    TGID:1449             ")
    }

    @Test
    fun stsTest_536_conv() {
        val testData =
            "STS,0000100, F0:-** ****** 16:25        xxxx    ,, S0:0 23456--- Jul20                ,,Melbourne International Aixx        ,,Air Traffic Control                 ,,Miami ARTCC/Melbourne Remote        ,, Aircraft                           ,, 119.8250MHzxtx     Air             ,,1,0,0,0,,,5,OFF,3"
        val vm = viewModel()
        val psd = ParseScannerData(vm)
        psd.stsDecode536(testData)
        assertEquals(vm.stsLines[1], "0000100")
        assertEquals(vm.stsLines[2], " F0:-** ****** 16:25        xxxx    ")
        assertEquals(vm.stsLines[10], "Miami ARTCC/Melbourne Remote        ")
    }

    @Test
    fun parseMDL() {
        val testData = "MDL,BCD536HP\r"
        val vm = viewModel()
        val psd = ParseScannerData(vm)
        psd.decodeResponse(testData)
        assertEquals(vm.model, "BCD536HP")
    }

    @Test
    fun parse536Blob() {
        val testData = "GSI,<XML>,<?xml version=\"1.0\" encoding=\"utf-8\"?><ScannerInfo Mode=\"Trunk Scan\" V_Screen=\"trunk_scan\">  <MonitorList Name=\"Brevard County\" Index=\"0\" ListType=\"FL\" Q_Key=\"1\" N_Tag=\"1\" DB_Counter=\"0\" />  <System Name=\"Brevard County\" Index=\"52\" Avoid=\"Off\" SystemType=\"EDACS\" Q_Key=\"1\" N_Tag=\"1\" Hold=\"Off\" />  <Department Name=\"West Melbourne\" Index=\"577\" Avoid=\"Off\" Q_Key=\"6\" Hold=\"Off\" />  <TGID Name=\"West Melbourne PD\" Index=\"587\" Avoid=\"Off\" TGID=\"TGID:259\" SetSlot=\"Slot Any\" RecSlot=\"Slot None\" N_Tag=\"None\" Hold=\"Off\" SvcType=\"Law Dispatch\" P_Ch=\"Off\" LVL=\"0\" />  <UnitID Name=\"UID:8687\" U_Id=\"UID:8687\" />  <Site Name=\"South Simulcast\" Index=\"69\" Avoid=\"Off\" Q_Key=\"98\" Hold=\"Off\" Mod=\"FM\" />  <SiteFrequency Freq=\" 852.3125MHz\" IFX=\"Off\" SAS=\"All\" SAD=\"None\" />  <AGC A_AGC=\"Off\" D_AGC=\"Off\" />  <DualWatch PRI=\"Off\" CC=\"Off\" WX=\"Off\" />  <Property F=\"Off\" VOL=\"9\" SQL=\"3\" Sig=\"5\" WiFi=\"3\" Att=\"Off\" Rec=\"Off\" KeyLock=\"Off\" P25Status=\"None\" Mute=\"Unmute\" Backlight=\"100\" A_Led=\"Off\" Dir=\"Up\" Rssi=\"1.392\" />  <ViewDescription>    <InfoArea1 Text=\"F0:- ********\" />    <InfoArea2 Text=\"S0:- --------\" />  </ViewDescription></ScannerInfo>LCR,28.159962,-80.587177,0.0STS,0000100, F0:- ******** 19:13  GPS   ��      ,, S0:- -------- Jan19                ,,Brevard County                      ,,West Melbourne                      ,,West Melbourne PD                   ,, Law Dispatch TGID:259              ,,UID:8687                            ,,1,0,0,0,,,5,OFF,3PWR,432,08523125\r"

        val vm = viewModel()
        val psd = ParseScannerData(vm)
        psd.decodeResponse("MDL,BCD536HP\r")
        psd.decodeResponse(testData)
        assertEquals(vm.scannerInfoMode, "Trunk Scan")
        assertEquals(vm.monitorListName, "Brevard County")
        assertEquals(vm.systemName, "Brevard County")
        assertEquals(vm.departmentName, "West Melbourne")
        assertEquals(vm.tgidIndex, "587")
        assertEquals(vm.unitU_Id, "UID:8687")
        assertEquals(vm.siteFrequencySAD, "None")
        assertEquals(vm.dualWatchCC,"Off")
        assertEquals(vm.propertyVOL, "9")
        assertEquals(vm.latitude, "28.159962")
        assertEquals(vm.stsLines[12], " Law Dispatch TGID:259              ")
        assertEquals(vm.pwrFreq, "08523125")
    }

    @Test
    fun parseSDSMDL() {
        var testData = "MDL,SDS100\r"
        val vm = viewModel()
        val psd = ParseScannerData(vm)
        psd.decodeResponse(testData)
        assertEquals(vm.model, "SDS100")
    }

    @Test
    fun parseSDSBlob() {  //todo: finish
        val testData = "GSI,<XML>,<?xml version=\"1.0\" encoding=\"utf-8\"?><ScannerInfo Mode=\"Trunk Scan\" V_Screen=\"trunk_scan\">  <MonitorList Name=\"Brevard County\" Index=\"0\" ListType=\"FL\" Q_Key=\"0\" N_Tag=\"1\" DB_Counter=\"0\" />  <System Name=\"Brevard County\" Index=\"54\" Avoid=\"Off\" SystemType=\"EDACS\" Q_Key=\"0\" N_Tag=\"1\" Hold=\"Off\" />  <Department Name=\"Brevard County Fire / EMS\" Index=\"465\" Avoid=\"Off\" Q_Key=\"0\" Hold=\"Off\" />  <TGID Name=\"Dispatch 4 - District 80\" Index=\"493\" Avoid=\"Off\" TGID=\"TGID:407\" SetSlot=\"Slot Any\" RecSlot=\"Slot None\" N_Tag=\"None\" Hold=\"Off\" SvcType=\"Fire Dispatch\" P_Ch=\"Off\" LVL=\"0\" />  <UnitID />  <Site Name=\"South Simulcast\" Index=\"71\" Avoid=\"Off\" Q_Key=\"98\" Hold=\"Off\" Mod=\"FM\" />  <SiteFrequency Freq=\" 851.537500MHz\" IFX=\"Off\" SAS=\"All\" SAD=\"None\" />  <DualWatch PRI=\"Off\" CC=\"DND\" WX=\"Off\" />  <Property F=\"Off\" VOL=\"5\" SQL=\"3\" Sig=\"5\" Att=\"Off\" Rec=\"Off\" KeyLock=\"Off\" P25Status=\"None\" Mute=\"Mute\" Backlight=\"100\" A_Led=\"Off\" Dir=\"Up\" Rssi=\"-94\" />  <ViewDescription>  </ViewDescription></ScannerInfo>LCR,28.159782,-80.588015,0.0STS,00001010100000000,              Jan23 16:24     ,                              ,F0:- ********                 ,                              ,S0:- --------   VOL: 5 SQL: 3 ,                              ,D0:-1234 6789   Tag:01.01.--- ,                              ,Brevard County          ,                        ,Brevard County                ,                              ,Brevard County Fire / \u0006\u0007,                        ,South Simulcast               ,                              ,Dispatch 4 - District 80,                        ,TGID:407                      ,                              ,Fire Dispatch                 ,                              ,Sys ID: Dh       851.537500MHz,                              ,RFSS ID: ---    Site ID: ---  ,                              ,WACN: ---       Batt:4.06V    ,                              ,UID: ---        RSSI: -95dBm  ,                              ,FM                      CC    ,                              , SYSTEM      DEPT     CHANNEL ,********* ********** *********,1,1,0,0,,,5,OFF,3PWR,-93,08515375"
        val vm = viewModel()
        val psd = ParseScannerData(vm)
        psd.decodeResponse("MDL,SDS100\n")
        psd.decodeResponse(testData)
        assertEquals(vm.scannerInfoV_screen, "trunk_scan")
        assertEquals(vm.monitorListName, "Brevard County")
        assertEquals(vm.systemName, "Brevard County")
        assertEquals(vm.tgidTGID, "TGID:407")
        assertEquals(vm.siteName, "South Simulcast")
        assertEquals(vm.departmentName, "Brevard County Fire / EMS")
        assertEquals(vm.convFrequencySvcType, "Fire Dispatch")
        assertEquals(vm.tgidTGID, "TGID:407")
        assertEquals(vm.siteFrequencySAS, "All")
        assertEquals(vm.dualWatchPRI, "Off")
        assertEquals(vm.propertySQL, "3")
        assertEquals(vm.longitude, "-80.588015")
        assertEquals(vm.stsLines[16], "South Simulcast               ")
        assertEquals(vm.pwrRSSI, "-93")
    }
}