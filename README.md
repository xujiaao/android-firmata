# Android-Firmata (IN DEVELOPING ...)

**IoT Library for Android Developers. Inspired by [Johnny-Five].**

Android-Firmata is a client library of [Firmata] written in Kotlin. It allows controlling
Arduino (or other boards, such as [NodeMcu]...) which runs Firmata Protocol from your Android
application.


## Getting Start

This piece of code shows how to "Blink an LED" with Android-Firmata:

````
class BlinkLedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Connect board through Bluetooth transport.
         *
         * NOTICE: Make sure the name of Bluetooth device is "HC-06",
         * and the device has already been bonded with your Android phone!!!
         */
        connectBoardWithLifecycle("bt://HC-06".toTransport(), lifecycle, {
            onConnecting { toast("Connecting...") }

            onConnected { board ->
                toast("Connected")

                val led = board.Led(13) // Create an Led on pin 13
                led.blink(500) // Blink every half second
            }

            onDisconnected { error ->
                if (error != null) {
                    toast("Disconnected: ${error.message}")
                }
            }
        })
    }
}
````

![](https://github.com/rwaldron/johnny-five/raw/master/assets/led-blink.gif)


### Setup Bluetooth Device

Check out the [Johnny-Five Bluetooth Guide] to setup your Bluetooth Serial Port Module.


### Setup Arduino

- Download Arduino IDE

- Plug in your Arduino or Arduino compatible microcontroller via USB

- Open the Arduino IDE, select: File > Examples > Firmata > StandardFirmataPlus

  - StandardFirmataPlus is available in Firmata v2.5.0 or greater

- Click the "Upload" button

If the upload was successful, the board is now prepared and you can close the Arduino IDE.


## NodeMcu




## Installation

In your build.gradle:

````
dependencies {
    implementation 'com.xujiaao.android:android-firmata:???????' // coming soon
}
````

## Sample Application

**All images in the sample application are copied from [Johnny-Five Examples Page].**


## License

Android-Firmata is distributed under the terms of the MIT License. See the [LICENSE] file.


[Johnny-Five]: https://github.com/rwaldron/johnny-five
[Johnny-Five Bluetooth Guide]: https://github.com/rwaldron/johnny-five/wiki/Getting-Started-with-Johnny-Five-and-JY-MCU-Bluetooth-Serial-Port-Module
[Johnny-Five Examples Page]: http://johnny-five.io/examples
[Firmata]: https://github.com/firmata/protocol
[NodeMcu]: http://nodemcu.com
[LICENSE]:

