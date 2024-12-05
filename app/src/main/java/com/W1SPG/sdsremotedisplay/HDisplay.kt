package com.W1SPG.sdsremotedisplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//var HSIZE = 67.dp
//var VSIZE = 36.dp
//val HBUTTONSPACER = 10.dp

@Composable
fun HDisplay() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    var HSIZE = (screenWidth / 10.8).dp
    var VSIZE = (screenHeight / 9.2).dp
    val HBUTTONSPACER = 10.dp
    val SIGBARSIZE = 15.dp

    Row(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Left
    )
    {
        Column(
            Modifier
                .fillMaxWidth(.55f)
                .fillMaxHeight()
                .background(vm.backGroundColor)
        )
        {
            Row() {
                if (vm.displayLinesLength >= 1) {
                    var color: Color = vm.colorArray[1]
                    var bcolor: Color = vm.BACKGROUNDCOLOR
                    if (vm.highlightArray[1]) {
                        color = vm.backGroundColor
                        bcolor = vm.defaultSystemTextColor
                    }
                    var underlined = TextDecoration.None
                    if (vm.underlineArray[1]) {
                        underlined = TextDecoration.Underline
                    }
                    Text(
                        text = vm.HDisplayLine1,
                        fontSize = vm.getFontSize(1),
                        color = color,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.background(bcolor),
                        textDecoration = underlined
                    )
                }
                // signal bars
                when (vm.propertySig) {
                    "1" -> {
                        Image(
                            painter = painterResource(R.drawable._1bars),
                            contentDescription = "Signal Level 1",
                            Modifier.size(SIGBARSIZE)
                        )
                    }

                    "2" -> {
                        Image(
                            painter = painterResource(R.drawable._2bars),
                            contentDescription = "Signal Level 2",
                            Modifier.size(SIGBARSIZE)
                        )
                    }

                    "3" -> {
                        Image(
                            painter = painterResource(R.drawable._3bars),
                            contentDescription = "Signal Level 3",
                            Modifier.size(SIGBARSIZE)
                        )
                    }

                    "4" -> {
                        Image(
                            painter = painterResource(R.drawable._4bars),
                            contentDescription = "Signal Level 4",
                            Modifier.size(SIGBARSIZE)
                        )
                    }

                    "5" -> {
                        Image(
                            painter = painterResource(R.drawable._5bars),
                            contentDescription = "Signal Level 5",
                            Modifier.size(SIGBARSIZE)
                        )
                    }
                }
                Text(
                    text = "", Modifier  //Uniden LED notification
                        //blue, red magenta, green, cyan yellow, white
                        .background(vm.displayLED)
                        .fillMaxWidth()
                        .height(15.dp)
                )
            }
            if (vm.displayLinesLength >= 2) {
                var color: Color = vm.colorArray[2]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[2]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[2]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine2,
                    fontSize = vm.getFontSize(2),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 3) {
                var color: Color = vm.colorArray[3]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[3]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[3]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine3,
                    fontSize = vm.getFontSize(3),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 4) {
                var color: Color = vm.colorArray[4]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[4]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[4]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine4,
                    fontSize = vm.getFontSize(4),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 5) {
                var color: Color = vm.colorArray[5]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[5]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[5]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine5,
                    fontSize = vm.getFontSize(5),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 6) {
                var color: Color = vm.colorArray[6]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[6]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[6]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine6,
                    fontSize = vm.getFontSize(6),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 7) {
                var color: Color = vm.colorArray[7]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[7]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[7]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine7,
                    fontSize = vm.getFontSize(7),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 8) {
                var color: Color = vm.colorArray[8]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[8]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[8]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine8,
                    fontSize = vm.getFontSize(8),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 9) {
                var color: Color = vm.colorArray[9]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[9]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[9]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine9,
                    fontSize = vm.getFontSize(9),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 10) {
                var color: Color = vm.colorArray[10]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[10]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                var underlined = TextDecoration.None
                if (vm.underlineArray[10]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine10,
                    fontSize = vm.getFontSize(10),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor),
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 11) {
                var underlined = TextDecoration.None
                if (vm.underlineArray[11]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine11,
                    fontSize = vm.getFontSize(11),
                    color = vm.colorArray[11],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 12) {
                var underlined = TextDecoration.None
                if (vm.underlineArray[12]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine12,
                    fontSize = vm.getFontSize(12),
                    color = vm.colorArray[12],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 13) {
                var underlined = TextDecoration.None
                if (vm.underlineArray[13]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine13,
                    fontSize = vm.getFontSize(13),
                    color = vm.colorArray[13],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 14) {
                var underlined = TextDecoration.None
                if (vm.underlineArray[14]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine14,
                    fontSize = vm.getFontSize(14),
                    color = vm.colorArray[14],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 15) {
                var underlined = TextDecoration.None
                if (vm.underlineArray[15]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine15,
                    fontSize = vm.getFontSize(15),
                    color = vm.colorArray[15],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = underlined
                )
            }
            if (vm.displayLinesLength >= 16) {
                var underlined = TextDecoration.None
                if (vm.underlineArray[16]) {
                    underlined = TextDecoration.Underline
                }
                Text(
                    text = vm.HDisplayLine16,
                    fontSize = vm.getFontSize(16),
                    color = vm.colorArray[16],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    textDecoration = underlined
                )
            }


            Row(
                Modifier
                    .height(HSIZE)
                    .weight(.1f)
                    .fillMaxWidth()
                    .background(vm.backGroundColor),
                Arrangement.SpaceEvenly,
                Alignment.Bottom
            ) {
                FilledTonalButton(
                    onClick = { vm.pressButton("A") },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.firstButton) }
                FilledTonalButton(
                    onClick = { vm.pressButton("B") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.secondButton) }
                FilledTonalButton(
                    onClick = { vm.pressButton("C") },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.thirdButton) }
            }
        }

        Column(
            Modifier
                .background(color = Color.Black)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                FilledTonalButton(
                    onClick = { vm.pressButton("1") },
                    modifier = Modifier.size(width = HSIZE, height = VSIZE),
                    contentPadding = PaddingValues(0.dp)
                ) { Text(text = vm.buildButtonText("1", "SRCH1")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("2") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("2", "SRCH2")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("3") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("3", "SRCH3")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("volup") },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE),
                ) { Text(text = "Vol Up") }
            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                FilledTonalButton(
                    onClick = { vm.pressButton("4") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("4", "ATT")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("5") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("5", "DIM")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("6") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("6", "WX")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("voldn") },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Vol Dn") }
            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                FilledTonalButton(
                    onClick = { vm.pressButton("7") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("7", "IFX")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("8") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("8", "REV")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("9") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("9", "DISP")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("squp") },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Sq Up") }
            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                FilledTonalButton(
                    onClick = { vm.pressButton(".") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText(".", "No/PRI")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("0") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("0", "LVL")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("E") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = vm.buildButtonText("E", "Yes/0.SRCH")) }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("sqdn") },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Sq Dn") }
            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                FilledTonalButton(
                    onClick = { vm.pressButton("L") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Avoid") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("Y") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Record") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("Z") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "ZIP") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton(">") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Func Up") }
            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                FilledTonalButton(
                    onClick = { vm.pressButton("V") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Dim") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("M") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Menu") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("F") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Function") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("<") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Func Dn") }

            }
        }
    }
}


@Preview(showSystemUi = true,
    device = "spec:width=1348dp,height=2992dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun Preview() {

    HDisplay()
}
