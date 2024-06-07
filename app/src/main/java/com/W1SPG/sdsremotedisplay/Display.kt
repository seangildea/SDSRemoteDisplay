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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt


@Composable
fun Display() {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f)
                //.background(vm.getBackGrndColor())
                .background(vm.backGroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Modifier.weight(10f, true)

            Text(text="", Modifier  //Uniden LED notification
                //blue, red magenta, green, cyan yellow, white
                .background(vm.getLEDColor())
                .fillMaxWidth()
                .height(15.dp))

            Row {
                Column {
                    Text(
                        text = vm.getFavQK(), fontSize = 20.sp, color = Color.White,
                        modifier = Modifier.padding(1.dp)
                    )
                    Text(
                        text = vm.getSystemQK(), fontSize = 20.sp, color = Color.White,
                        modifier = Modifier.padding(1.dp)
                    )
                    if (vm.isSDSScanner()) {
                        Text(
                            text = vm.getDeptQK(), fontSize = 20.sp, color = Color.White,
                            modifier = Modifier.padding(1.dp)
                        )
                    }
                }

                Column {
                    Spacer(modifier = Modifier.size(20.dp))
                }

                Column {
                    Text(
                        text = vm.getVolume(), color = Color.White, fontSize = 20.sp,
                        modifier = Modifier.padding(1.dp)
                    )
                    Text(
                        text = vm.getSquelch(), color = Color.White, fontSize = 20.sp,
                        modifier = Modifier.padding(1.dp)
                    )
                }
            }

            //System
            TextButton(onClick =  {vm.pressButton("system")}) {
                Text(
                    text = vm.getSystem(),
                    fontSize = 40.sp,
                    color = vm.systemTextColor,
                    modifier = Modifier.padding(10.dp)
                        .background(vm.systemBackColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            //Department
            TextButton(onClick =  {vm.pressButton("department")}) {
                Text(
                    text = vm.getDept(),
                    fontSize = 40.sp,
                    color = vm.departmentTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.background(vm.deptBackColor)
                        .padding(10.dp)
                )
            }

            //Channel
            TextButton(onClick =  {vm.pressButton("channel")}) {
                Text(
                    text = vm.getChan(),
                    fontSize = 40.sp,
                    color = vm.channelTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.background(vm.channelBackColor)
                        .padding(10.dp)
                )
            }

            Text(
                text = vm.getSVCType(), color = Color("#e77475".toColorInt()), fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = vm.getFrequency(), color = Color("#e77475".toColorInt()), fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )
            Text(vm.getMode(),
                color = Color("#e77475".toColorInt()),
                fontSize = 25.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

            if (vm.isTrunk()) {
                Text(
                    text = vm.getSite(),
                    color = Color("#e77475".toColorInt()),
                    fontSize = 25.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = vm.getTGIDID(), color = Color("#e77475".toColorInt()), fontSize = 25.sp
                )

                if (vm.systemSystemType == "P25 Trunk") {
                    Text(
                        text = vm.systemSystemType,
                        color = Color("#e77475".toColorInt()), fontSize = 25.sp
                    )
                }
            }
            if(vm.isSDSScanner()) {
                Text(vm.getRSSI(), color = Color("#e77475".toColorInt()), fontSize = 25.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                if (locationPermissionGranted) {
                    Column {
                        Text(
                            vm.getLat(),
                            color = Color("#e77475".toColorInt()),
                            fontSize = 18.sp
                        )
                    }
                    Column {
                        Spacer(modifier = Modifier.size(20.dp))
                    }

                    Column {
                        Text(
                            vm.getLong(),
                            color = Color("#e77475".toColorInt()),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { vm.pressButton("avoid") }, shape = RectangleShape

            ) {
                Text(text = "Avoid",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .height(75.dp)
                        .width(50.dp)
                        .align(Alignment.CenterVertically) )
            }
            Column {
                Button(onClick = { vm.pressButton("volup") }) {
                    Text(text = "Vol Up")

                }
                Button(onClick = { vm.pressButton("voldn") }) {
                    Text(text = "Vol Dn")
                }
            }
            Column {
                Button(onClick = { vm.pressButton("squp") }) {
                    Text(text = "SQ Up")

                }
                Button(onClick = { vm.pressButton("sqdn") }) {
                    Text(text = "SQ Dn")
                }
            }
        }

    }
}
