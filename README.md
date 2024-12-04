# SDSRemoteDisplay

SDSRemoteDisplay is an Android app to connect to specific models of Uniden scanners and display the data of what it is scanning.
It will work with SDS200, SDS100, BCD536, and (with some limitations) BCD436.  The reason for the limitations on the 436 is I do not possess one to develop/test with.

# Why

This was created mainly as a method for myself to learn the basics of Kotlin and Android app programming.

# What It Does

When connected by a USB cable to the scanner it will display the on screen data in two possible formats depending on the screen orientation of the phone/tablet.

Vertical display example:


Horizontal display:


# GPS

If the phone/tablet has built in GPS, the current latitude and longitude of the phone will be sent to the scanner.
This allows location based scanning without a GPS "puck".
GPS data will only be fed to the scanner when the app is the active app on the phone.

# Usage

Connect a USB cable directly between the phone and the scanner using the same scanner's USB port that Uniden's Sentinel program uses.
You may want to use a OTG (on the go) USB cable which will allow you to charge the phone while in use, especially if you connect to an SDS100 which seems to pull power from the phone at a higher rate.

Run the app before connecting the USB cable or disconnect and reconnect after the app is started for the phone to discover the scanner connection.

When in vertical display, touching the system, department, of channel will hold the scanner on the selected item.  The color will invert to indicate the state (there may be a short delay before the app gets the hold state from the scanner).  To resume scanning touch the text again to release the hold.
