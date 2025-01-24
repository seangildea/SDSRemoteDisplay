package com.W1SPG.sdsremotedisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Display() {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(vm.textDisplayWeight)
                //.background(vm.getBackGrndColor())
                .background(vm.backGroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Modifier.weight(10f, true)

            Text(text="", Modifier  //Uniden LED notification
                //blue, red magenta, green, cyan yellow, white
                .background(vm.displayLED)
                .fillMaxWidth()
                .height(15.dp))

            Row {
                Column {
                    Text(
                        text = vm.displayQuickKeyStatus1, fontSize = 20.sp, color = Color.White,
                        fontFamily = FontFamily.Monospace, modifier = Modifier.padding(1.dp)
                    )
                    Text(
                        text = vm.displayQuickKeyStatus2, fontSize = 20.sp, color = Color.White,
                        fontFamily = FontFamily.Monospace, modifier = Modifier.padding(1.dp)
                    )
                    if (vm.isSDSScanner()) {
                        Text(
                            text = vm.displayQuickKeyStatus3, fontSize = 20.sp, color = Color.White,
                            fontFamily = FontFamily.Monospace, modifier = Modifier.padding(1.dp)
                        )
                    }
                }

                Column {
                    Spacer(modifier = Modifier.size(20.dp))
                }

                Column {
                    Text(
                        text = vm.displayVolume, color = Color.White, fontSize = 20.sp,
                        modifier = Modifier.padding(1.dp)
                    )
                    Text(
                        text = vm.displaySquelch, color = Color.White, fontSize = 20.sp,
                        modifier = Modifier.padding(1.dp)
                    )
                }
                Column {
                    Spacer(modifier = Modifier.size(20.dp))
                }

                Column {
                    Text(text = vm.closeCallString, color = Color.White, fontSize = 20.sp,
                        modifier = Modifier.padding(1.dp))
                }
            }

            //System
            TextButton(onClick =  {vm.pressButton("system")}) {
                Text(
                    text = vm.displayHeader1,
                    fontSize = 40.sp,
                    color = vm.systemTextColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .background(vm.systemBackColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            //Department
            TextButton(onClick =  {vm.pressButton("department")}) {
                Text(
                    text = vm.displayHeader2,
                    fontSize = 40.sp,
                    color = vm.departmentTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .background(vm.deptBackColor)
                        .padding(10.dp)
                )
            }

            //Channel
            TextButton(onClick =  {vm.pressButton("channel")}) {
                Text(
                    text = vm.displayHeader3,
                    fontSize = 40.sp,
                    color = vm.channelTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .background(vm.channelBackColor)
                        .padding(10.dp)
                )
            }

            Text(
                text = vm.displayFooter1, color = vm.FOOTERTEXTCOLOR, fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = vm.displayFooter2, color = vm.FOOTERTEXTCOLOR, fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = vm.displayFooter3, color = vm.FOOTERTEXTCOLOR, fontSize = 25.sp,
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )

            Text(
                text = vm.displayFooter4, color = vm.FOOTERTEXTCOLOR, fontSize = 25.sp,
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )

            Text(
                text = vm.displayFooter5, color = vm.FOOTERTEXTCOLOR, fontSize = 25.sp,
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )

            Text(
                text = vm.displayFooter6, color = vm.FOOTERTEXTCOLOR, fontSize = 25.sp,
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )

            Text(text = vm.displayFooter7, color = vm.FOOTERTEXTCOLOR, fontSize = 25.sp,
                maxLines = 1, overflow = TextOverflow.Ellipsis)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                if (locationPermissionGranted) {
                    Column {
                        Text(
                            vm.displayLatitude,
                            color = vm.FOOTERTEXTCOLOR,
                            fontSize = 18.sp
                        )
                    }
                    Column {
                        Spacer(modifier = Modifier.size(20.dp))
                    }

                    Column {
                        Text(
                            vm.displayLongitude,
                            color = vm.FOOTERTEXTCOLOR,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        // hold and to scan buttons
        if (vm.searchScreen || vm.closeCallHit) {
            Row(
                modifier = Modifier
                    .weight(.1f)
                    .background(vm.backGroundColor)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {

                FilledTonalButton(
                    onClick = { vm.pressButton("hold") }, shape = RectangleShape
                ) {
                    Text(
                        text = vm.holdButtonText,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .height(40.dp)
                            .width(55.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                FilledTonalButton(
                    onClick = { vm.pressButton("<") }, shape = RectangleShape
                ) {
                    Text(
                        text = "<",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .height(40.dp)
                            .width(15.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                FilledTonalButton(
                    onClick = { vm.pressButton(">") }, shape = RectangleShape
                    ) {
                    Text(
                        text = ">",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .height(40.dp)
                            .width(15.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                // don't display the "to scan" button during a close call hit
                var toScanButtonAlpha = if (vm.searchScreen) 1f else 0f
                FilledTonalButton(
                    onClick = { vm.pressButton("toscan") }, shape = RectangleShape,
                    modifier = Modifier.alpha(toScanButtonAlpha)
                ) {
                    Text(
                        text = "To Scan",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .height(40.dp)
                            .width(55.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }

        // avoid, volume and squelch buttons
        Row(
            modifier = Modifier
                .weight(.1f)
                .background(vm.backGroundColor)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(onClick = { vm.pressButton("avoid") }, shape = RectangleShape
            ) {
                Text(text = "Avoid",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .height(75.dp)
                        .width(50.dp)
                        .align(Alignment.CenterVertically) )
            }
            Column {
                FilledTonalButton(onClick = { vm.pressButton("volup") },) {
                    Text(text = "Vol Up")

                }
                FilledTonalButton(onClick = { vm.pressButton("voldn") },) {
                    Text(text = "Vol Dn")
                }
            }
            Column {
                FilledTonalButton(onClick = { vm.pressButton("squp") }) {
                    Text(text = "SQ Up")

                }
                FilledTonalButton(onClick = { vm.pressButton("sqdn") }) {
                    Text(text = "SQ Dn")
                }
            }
        }
    }
}
