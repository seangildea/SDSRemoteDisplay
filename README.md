# SDSRemoteDisplay

SDSRemoteDisplay is an Android app to connect to specific models of Uniden scanners and display the data of what it is scanning.
It will work with SDS200, SDS100, BCD536, and BCD436*.

\* I do not have a BCD436 to develop/test with so some features may not work correctly.

# Why

This was created mainly as a method for myself to learn the basics of Kotlin and Android app programming.

Contact: W1SPG.Radio at gmail.com

# What It Does

When connected to a scanner by either direct USB or by Wifi to a network connected scanner it will display the on screen data in two possible formats depending on the screen orientation of the phone/tablet.

Vertical display example:

<img src="https://github.com/seangildea/SDSRemoteDisplay/blob/master/Vertical1.jpg" width="200" height="400">

Horizontal display example (SDS Scanner):

<img src="https://github.com/seangildea/SDSRemoteDisplay/blob/master/Horizontal1.jpg" width="400" height="200">

When connected by Wifi to a Wifi capable scanner, it will attempt to stream the audio from the scanner to the phone.
Audio is not transfered over a USB connection.

At this point it will not show the waterfall display.


# GPS

If the phone/tablet has built in GPS, the current latitude and longitude of the phone will be sent to the scanner.
This allows location based scanning without a GPS "puck".
GPS data will only be fed to the scanner when the app is the active app on the phone.

# Usage

Should work on any Android phone or tablet with Android version 7 or later, although no guarantees.

USB Connection:
Connect a USB cable directly between the phone and the scanner using the same scanner's USB port that Uniden's Sentinel program uses.
You may want to use a OTG (on the go) USB cable which will allow you to charge the phone while in use, especially if you connect to an SDS100 which seems to pull power from the phone at a higher rate.

Run the app before connecting the USB cable or disconnect and reconnect after the app is started for the phone to discover the scanner connection.

Wifi Connection:
Enter the IP address (IPv4) of the scanner in the text entry field and click connect for a network connected scanner only

When in vertical display, touching the text of the system, department, or channel will hold the scanner on the selected item.  The color will invert to indicate the state (there may be a short delay before the app gets the hold state from the scanner).  To resume scanning touch the text again to release the hold.

# How to sideload apps

To install this Android APK, you will need to sideload it.  Here are a couple of websites for how to do it.

If you would prefer to install via Google Play, send me an email and I will add you to the beta tester list and send a link to install it.
W1SPG.Radio at gmail.com

https://www.xda-developers.com/how-to-sideload-install-android-app-apk/

https://www.howtogeek.com/313433/how-to-sideload-apps-on-android/
