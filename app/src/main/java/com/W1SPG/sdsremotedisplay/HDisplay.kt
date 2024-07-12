package com.W1SPG.sdsremotedisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


var HSIZE = 67.dp
var VSIZE = 36.dp
val HBUTTONSPACER = 10.dp

@Composable
fun HDisplay() {
    //val configuration = LocalConfiguration.current
    //val screenHeight = configuration.screenHeightDp.dp
    //val screenWidth = configuration.screenWidthDp.dp

    Row (
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
            if (vm.displayLinesLength >= 1) {
                var color: Color = vm.colorArray[1]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[1]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine1,
                    fontSize = vm.getFontSize(1),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 2) {
                var color: Color = vm.colorArray[2]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[2]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine2,
                    fontSize = vm.getFontSize(2),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 3) {
                var color: Color = vm.colorArray[3]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[3]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine3,
                    fontSize = vm.getFontSize(3),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 4) {
                var color: Color = vm.colorArray[4]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[4]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine4,
                    fontSize = vm.getFontSize(4),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 5) {
                var color: Color = vm.colorArray[5]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[5]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine5,
                    fontSize = vm.getFontSize(5),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 6) {
                var color: Color = vm.colorArray[6]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[6]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine6,
                    fontSize = vm.getFontSize(6),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 7) {
                var color: Color = vm.colorArray[7]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[7]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine7,
                    fontSize = vm.getFontSize(7),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 8) {
                var color: Color = vm.colorArray[8]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[8]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine8,
                    fontSize = vm.getFontSize(8),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 9) {
                var color: Color = vm.colorArray[9]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[9]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine9,
                    fontSize = vm.getFontSize(9),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 10) {
                var color: Color = vm.colorArray[10]
                var bcolor: Color = vm.BACKGROUNDCOLOR
                if (vm.highlightArray[10]) {
                    color = vm.backGroundColor
                    bcolor = vm.defaultSystemTextColor
                }
                Text(
                    text = vm.HDisplayLine10,
                    fontSize = vm.getFontSize(10),
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(bcolor)
                )
            }
            if (vm.displayLinesLength >= 11) {
                Text(
                    text = vm.HDisplayLine11,
                    fontSize = vm.getFontSize(11),
                    color = vm.colorArray[11],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace
                )
            }
            if (vm.displayLinesLength >= 12) {
                Text(
                    text = vm.HDisplayLine12,
                    fontSize = vm.getFontSize(12),
                    color = vm.colorArray[12],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace
                )
            }
            if (vm.displayLinesLength >= 13) {
                Text(
                    text = vm.HDisplayLine13,
                    fontSize = vm.getFontSize(13),
                    color = vm.colorArray[13],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace
                )
            }
            if (vm.displayLinesLength >= 14) {
                Text(
                    text = vm.HDisplayLine14,
                    fontSize = vm.getFontSize(14),
                    color = vm.colorArray[14],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace
                )
            }
            if (vm.displayLinesLength >= 15) {
                Text(
                    text = vm.HDisplayLine15,
                    fontSize = vm.getFontSize(15),
                    color = vm.colorArray[15],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace
                )
            }
            if (vm.displayLinesLength >= 16) {
                Text(
                    text = vm.HDisplayLine16,
                    fontSize = vm.getFontSize(16),
                    color = vm.colorArray[16],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace
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

        Column(Modifier.background(color = Color.Black)
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
                    onClick = { vm.pressButton("1") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "1") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("2") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "2") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("3") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "3") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("volup") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
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
                ) { Text(text = "4") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("5") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "5") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("6") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "6") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("voldn") }, contentPadding = PaddingValues(0.dp),
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
                ) { Text(text = "7") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("8") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "8") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("9") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "9") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("squp") }, contentPadding = PaddingValues(0.dp),
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
                ) { Text(text = ".") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("0") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "0") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("E") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "E") }
                Spacer(modifier = Modifier.width(HBUTTONSPACER))
                FilledTonalButton(
                    onClick = { vm.pressButton("sqdn") }, contentPadding = PaddingValues(0.dp),
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
                    onClick = { vm.pressButton("<") }, contentPadding = PaddingValues(0.dp),
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
                    onClick = { vm.pressButton(">") }, contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = HSIZE, height = VSIZE)
                ) { Text(text = "Func Dn") }
            }
        }


    }
}


@Preview()
@Composable
fun Preview() {
    HDisplay()
}
