package com.W1SPG.sdsremotedisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph

@Composable
fun startUpScreen() {
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
    }
}

/*
@Preview(widthDp = 50, heightDp = 50)
@Composable
fun startUpPreview() {
    startUpScreen()
} */