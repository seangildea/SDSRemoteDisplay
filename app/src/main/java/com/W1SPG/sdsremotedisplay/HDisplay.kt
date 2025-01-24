package com.W1SPG.sdsremotedisplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HDisplay() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    val HSIZE = (screenWidth / 11).dp
    val VSIZE = (screenHeight / 9.2).dp
    val HBUTTONSPACER = 10.dp

    val NUMBUTTONFONTSIZE = 33.sp
    val TEXTBUTTONFONTSIZE = 18.sp

    val SYSDEPTCHAN_HSIZE = (screenWidth / 7).dp

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
                showLine(1)

            }
            showLine(2)
            showLine(3)
            showLine(4)
            showLine(5)
            showLine(6)
            showLine(7)
            showLine(8)
            showLine(9)
            showLine(10)
            showLine(11)
            showLine(12)
            showLine(13)
            showLine(14)
            showLine(15)
            showLine(16)



            Row(
                Modifier
                    .height(HSIZE)
                    .weight(.1f)
                    .fillMaxWidth()
                    .background(vm.backGroundColor),
                Arrangement.SpaceEvenly,
                Alignment.Bottom
            ) {
                showRectangleButton(vm.firstButton, "A", SYSDEPTCHAN_HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
                showRectangleButton(vm.secondButton, "B", SYSDEPTCHAN_HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
                showRectangleButton(vm.thirdButton, "C", SYSDEPTCHAN_HSIZE, VSIZE, TEXTBUTTONFONTSIZE)

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
                showKeyButton("1", "1", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "srch1")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("2", "2", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "SRCH2")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("3", "3", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "SRCH3")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Vol Up", "volup", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)

            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                showKeyButton("4", "4", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "ATT")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("5", "5", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "DIM")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("6", "6", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "WX")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Vol Dn", "voldn", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)

            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                showKeyButton("7", "7", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "IFX")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("8", "8", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "REV")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("9", "9", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "DISP")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Sq Up", "squp", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)

            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                showKeyButton(".", ".", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "No/PRI")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("0", "0", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "LVL")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("E", "E", HSIZE, VSIZE, NUMBUTTONFONTSIZE, subText = "Yes/Q.SRCH")
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Sq Dn", "sqdn", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)

            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                showKeyButton("Avoid", "L", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Record", "Y", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("ZIP", "Z", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Func Up", ">", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
            }
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(10.dp),
                Arrangement.SpaceEvenly
            ) {
                showKeyButton("Dim", "V", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Menu", "M", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Function", "F", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)
                Spacer(modifier = Modifier.width(HBUTTONSPACER))

                showKeyButton("Func Dn", "<", HSIZE, VSIZE, TEXTBUTTONFONTSIZE)

            }
        }
    }
}

@Composable
fun showLine(lineNum: Int) {
    // text to add to string to make underline and highlight fill entire space
    val textExtender = "                                 "

    if (vm.displayLinesLength >= lineNum) {
        var color: Color = vm.colorArray[lineNum]
        var bcolor: Color = vm.BACKGROUNDCOLOR
        if (vm.highlightArray[lineNum]) {
            color = vm.backGroundColor
            bcolor = vm.defaultSystemTextColor
            vm.HDisplay[lineNum] += textExtender
        }
        var underlined = TextDecoration.None
        if (vm.underlineArray[lineNum]) {
            underlined = TextDecoration.Underline
            vm.HDisplay[lineNum] += textExtender
        }
        Text(
            text = vm.HDisplay[lineNum],
            fontSize = vm.getFontSize(lineNum),
            color = color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.background(bcolor),
            textDecoration = underlined
        )
    }
    if (lineNum == 1) {
        // Signal strength bars
        val SIGBARSIZE = 15.dp
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
            else -> Image(
                painter = painterResource(R.drawable._0bars),
                contentDescription = "Signal Level 0",
                Modifier.size(SIGBARSIZE)
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "", Modifier  //Uniden LED notification
                //blue, red magenta, green, cyan yellow, white
                .background(vm.displayLED)
                .fillMaxWidth()
                .height(15.dp)
        )

    }
}

@Composable
fun showRectangleButton(buttonText: String, onClick: String, width: Dp,
               height: Dp, fontSize: TextUnit) {
    FilledTonalButton(
        onClick = { vm.pressButton(onClick) },
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.size(width = width, height = height)
    ) { Text(text = buttonText,  fontSize = fontSize ) }
}

@Composable
fun showKeyButton(buttonText: String, onClick: String, width: Dp,
                        height: Dp, fontSize: TextUnit, subText: String = "" ) {
    FilledTonalButton(
        onClick = { vm.pressButton(onClick) },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.size(width = width, height = height)
    ) { Text(text = vm.buildButtonText(buttonText, subText),  fontSize = fontSize ) }
}

/*
@Preview(showSystemUi = true,
    device = "spec:width=1348dp,height=2992dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun Preview() {

    HDisplay()
} */
