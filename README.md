# Android-Firmata

[![Build Status](https://travis-ci.org/xujiaao/android-firmata.svg?branch=master)](https://travis-ci.org/xujiaao/android-firmata)
[![Download](https://api.bintray.com/packages/xujiaao/android/android-firmata/images/download.svg)](https://bintray.com/xujiaao/android/android-firmata/_latestVersion)


**IoT Library for Android Developers. Inspired by [Johnny-Five].**

Android-Firmata is a client library of [Firmata] written in **Kotlin**.
It allows controlling Arduino (or other boards, such as [NodeMcu]...)
which runs Firmata Protocol from your Android Application.


## Installation

In your build.gradle:

````
dependencies {
    implementation 'com.xujiaao.android:android-firmata:${android_firmata_version}'
}
````

[![Download](https://api.bintray.com/packages/xujiaao/android/android-firmata/images/download.svg)](https://bintray.com/xujiaao/android/android-firmata/_latestVersion)


## Get Started

This piece of code shows how to "Blink an LED" with Android-Firmata:

````kotlin
class GetStartedActivity : AppCompatActivity() {

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

![](assets/images/led-blink.gif)


## Guidance

Before programing with the Android Firmata Library, you need to select
in which way your Android Device and the Arduino Board being connected.

Currently, these communication modes are supported:

- [Connect via Bluetooth](#connect-via-bluetooth)

- [Connect via WiFi](#connect-via-wifi)


### Connect via Bluetooth

#### Requirements

- StandardFirmataPlus v2.5.0 or greater

  - Arduino IDE > Examples > Firmata > StandardFirmataPlus

- A Bluetooth Serial Port Module

  ![](module-jy-mcu.jpg)


#### Setup the Bluetooth Serial Port Module

Since Firmata runs at `57600` baud, you'll need to configure the module
before making a connection with Android Firmata.

Check out the [Johnny-Five Bluetooth Guide] for more information.


#### Android Programing

Update the [Transport URI](#transport) in your Android Application
to let it know which device should be connected.

For Bluetooth Connection, the URI can be either of:

- `bt:<bluetooth_name>`

- `bt:<bluetooth_mac_address>`

For example:

````
/**
 * if the name of your Bluetooth device is "HC-06", then the URI should be:
 *
 *   "bt://HC-06"
 */
connectBoard("bt://HC-06".toTransport(), ...)
````

> NOTICE: When using the Bluetooth device name, make sure the Bluetooth
device has already been bonded with your Android phone.


### Connect via WiFi

#### Requirements

- StandardFirmata**WiFi** v2.5.0 or greater

  - Arduino IDE > Examples > Firmata > StandardFirmata**WiFi**

- A [NodeMcu] (ESP8266) Board


#### Setup NodeMcu

Check out the [NodeMcu Guide] to learn about how to install
StandardFirmataWiFi on the board.


#### Update your Android Program

For WiFi Connection, the [Transport URI](#transport) is:

- `tcp:<board_ip_address>:<board_port>`

For example:

````
/**
 * If the ip address is '192.168.4.1', and the port is '3030', then the URI should be:
 *
 *   "tcp://192.168.4.1"
 */
connectBoard("tcp://192.168.4.1".toTransport(), ...)
````


## Documentation

### Transport

Android Firmata Library uses a Transport URI to identify how devices are
being connected:

- Bluetooth:

    - `bt:<bluetooth_name>`

      - Make sure the Bluetooth device has been bonded before connecting.

    - `bt:<bluetooth_mac_address>`

- WiFi:

    - `tcp:<board_ip_address>:<board_port>`


## Sample Application (:link: [Link](https://github.com/xujiaao/android-firmata/releases/latest))

> **Note: Images in the sample application are copied from [Johnny-Five Examples Page].**

![](assets/images/sample-app.jpg)

- To edit the Transport URI, open the Settings Menu, select: Transport

- To Connect/Disconnect the board, click the action button in the top right corner


## TODOs

- [ ] Support USB connection (USBTransport)
- [ ] Support BLE connection (BLETransport)


## License

Android-Firmata is distributed under the terms of the MIT License. See the [LICENSE] file.


[Johnny-Five]: https://github.com/rwaldron/johnny-five
[Johnny-Five Bluetooth Guide]: https://github.com/rwaldron/johnny-five/wiki/Getting-Started-with-Johnny-Five-and-JY-MCU-Bluetooth-Serial-Port-Module
[Johnny-Five Examples Page]: http://johnny-five.io/examples
[Firmata]: https://github.com/firmata/protocol
[NodeMcu]: http://nodemcu.com
[NodeMcu Guide]: https://github.com/xujiaao/android-firmata/wiki/Getting-Started-with-Android-Firmata-and-NodeMcu-Board
[LICENSE]: LICENSE

