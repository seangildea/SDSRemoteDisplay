package com.W1SPG.sdsremotedisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//var wifi = Network()

@Composable
fun startUpScreen(wifi: Network, sdsData: ParseScannerData) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(vm.backGroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "SDSRemoteDisplay", fontSize = 45.sp,
            color = Color.Red  )
        Text(text = " By W1SPG", fontSize = 25.sp,
            color = Color.White)
        Spacer(modifier = Modifier.height(80.dp))
        Text(text = "Connect USB cable to scanner to continue", fontSize = 20.sp,
            color = Color.White)
        Spacer(modifier = Modifier.height(80.dp))


        var address by remember { mutableStateOf("") }
        TextField(
            value = address,
            onValueChange = { address = it},
            label = { Text("Scanner IP Address") },
            textStyle = TextStyle.Default.copy(fontSize = 45.sp),
            modifier = Modifier.padding(16.dp)
        )

        FilledTonalButton(
            onClick = { begin(wifi, address)
                      },
            shape = RectangleShape,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(width = 200.dp, height = 50.dp)
        ) { Text(text = "Connect to Wifi") }

    }
}

fun begin(wifi: Network, address: String) {
    wifi.connect( address, sdsData)
    connectedToScannerWIFI = true
    inStartUpScreen = false
    wifi.wifiRxData()
}