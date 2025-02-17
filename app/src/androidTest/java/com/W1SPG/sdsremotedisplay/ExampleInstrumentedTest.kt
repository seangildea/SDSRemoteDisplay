package com.W1SPG.sdsremotedisplay


import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisplayTests {
    @get:Rule
    val composeTestRule = createComposeRule()
    //val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun simulator() {
        val vm = viewModel()
        val parse = ParseScannerData(vm)
        connectedToScannerUSB = true
        isPortraitMode = false

        val lines = object {}.javaClass.getResourceAsStream("/res/raw/sds200trunksimple.txt")?.bufferedReader()?.readLines()
        if (lines != null) {
            composeTestRule.setContent { HDisplay() }

            for (line in lines) {
                var bdata = line.toByteArray()
                var data = String(bdata, Charsets.UTF_8)
                vm.lostConnection = false
                vm.clearToSend = true
                vm.lastResponseTime = 0
                parse.decodeResponse(data)
                vm.updateDisplayData()
                composeTestRule.waitForIdle()
                Thread.sleep(250)
            }
        }
    }
}
