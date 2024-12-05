package com.W1SPG.sdsremotedisplay.ui.theme

// Lookup values of Uniden font chars to replace with ascii characters
// https://info.uniden.com/twiki/pub/UnidenMan4/SDS200FirmwareUpdate/SDS200_FontData_V1_00.pdf
val subValues = mapOf(
    181 to 71, //0xB5 GPS to G
    182 to 80, //0xB6 GPS to P
    183 to 83, //0xB7 GPS to S
    137 to 67, //0x89 Normal Close Call to C
    138 to 67, //0x8A Normal Close Call to C
    14 to 70, //0x0E FM to F
    15 to 77, //0x0F FM to M
    12 to 32, //0x0C FM/AM to space
    8 to 65, //0x08 AM to A
    11 to 77, //0x0B AM to M
    166 to 32, //0xa6 signal level bars to space
    167 to 32, //0xa7 signal level bars to space
    168 to 32, //0xA8 signal level bars to space
    169 to 32, //0xA9 signal level bars to space
    170 to 32, //0xAA signal level bars to space
    171 to 32, //0xAB signal level bars to space
    172 to 32, //0xAC signal level bars to space
    173 to 32, //0xAD signal level bars to space
    156 to 72, //0x9C Hold to H
    157 to 79, //0x9D Hold to O
    158 to 76, //0x9e Hold to L
    159 to 68, //0x9F Hold to D
    160 to 112, //0xA0 Normal Priority to p
    161 to 114, //0xA1 Normal Priority to r
    162 to 105, //0xA2 Normal Priority to i
    163 to 80, //0xA3 Highlighted Priority to P
    164 to 82, //0xA4 Highlighted Priority to R
    165 to 73, //0xA5 Highlighted Priority to I
    148 to 97, //0x94 Normal Avoid to a
    149 to 118, //0x95 Normal Avoid to v
    150 to 100, //0x96 Normal Avoid to d
    151 to 32, //0x97 Normal Avoid to space
    152 to 65, //0x98 Highlighted Avoid to A
    153 to 86, //0x99 Highlighted Avoid to V
    154 to 68, //0x9A Highlighted Avoid to D
    155 to 32, //0x9B Highlighted Avoid to space
    139 to 114, //0x8B Normal Rec to r
    140 to 101, //0x8C Normal Rec to e
    141 to 99, //0x8D Normal Rec to c
    129 to 99, //0x81 Highlighted Close Call to c
    130 to 99, //0x82 Highlighted Close Call to c
    146 to 80, //0x92 Highlighted "P" to P
    147 to 32, //0x93 Highlighted "P" to space
    177 to 73, //0xB1 IFX to I
    178 to 70, //0xB2 IFX to F
    179 to 88, //0xB3 IFX to X
    200 to 83, //0xC8 SCR to S
    201 to 67, //0xC9 SCR to C
    202 to 82, //0xCA SCR to R
    1 to 35 //0x01 Black Box to #
)
