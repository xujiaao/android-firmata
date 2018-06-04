# Android-Firmata

[![Build Status](https://travis-ci.org/xujiaao/android-firmata.svg?branch=master)](https://travis-ci.org/xujiaao/android-firmata)
[![Download](https://api.bintray.com/packages/xujiaao/android/android-firmata/images/download.svg)](https://bintray.com/xujiaao/android/android-firmata/_latestVersion)

基于 [Firmata] 协议的 Android 物联网开发库 (Android 版 [Johnny-Five] :trollface:),
使用 **Kotlin** 编写. 它允许您在 Android 应用中控制安装了 Firmata 协议的
**Arduino** 开发板.


## 依赖

在 build.gradle 中声明:

````
dependencies {
    implementation 'com.xujiaao.android:android-firmata:${android_firmata_version}'
}
````

[![Download](https://api.bintray.com/packages/xujiaao/android/android-firmata/images/download.svg)](https://bintray.com/xujiaao/android/android-firmata/_latestVersion)


## 快速上手

下面的示例展示如何通过 Android-Firmata 控制 LED 闪烁 (硬件版 "Hello World")

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

在使用 Android-Firmata 开发前, 您需要选择 Android 和 Arduino 设备间的连接方式:

- [通过蓝牙连接](#connect-via-bluetooth)

- [通过 WiFi 连接](#connect-via-wifi)


### 通过蓝牙连接

#### 准备

- StandardFirmataPlus v2.5.0 或更高版本

  - 打开 Arduino IDE > 示例 > Firmata > StandardFirmataPlus

- 蓝牙串口模块 (比如: HC-05, HC-06...):

  ![](assets/images/jy-mcu.jpg)


#### 配置蓝牙模块

Firmata 默认的波特率是 `57600`. 所以在连接到 Android-Firmata 之前, 需要进行一些配置.

关于如何配置蓝牙模块, 请参考 [Johnny-Five Bluetooth Guide]


#### Android 代码

为了让 Android 应用知道需要连接哪个设备, 您需要修改 Android 代码中的 [Transport URI](#transports):

可以使用下面两种 URI 进行蓝牙连接:

- `bt:<bluetooth_name>` (使用蓝牙名称进行匹配)

  - **使用蓝牙名称连接时, 请确保蓝牙设备已和您的手机配对**

- `bt:<bluetooth_mac_address>` (使用 Mac 地址进行匹配)

示例:

````kotlin
/**
 * If the name of your Bluetooth device is "HC-06", then the URI should be:
 *
 *   "bt://HC-06"
 */
connectBoard("bt://HC-06".toTransport(), ...)
````


### 通过 WiFi 连接

#### 准备

- StandardFirmata**WiFi** v2.5.0 或更高版本

  - 打开 Arduino IDE > 示例 > Firmata > StandardFirmata**WiFi**

- [NodeMcu] (ESP8266) 开发板

  ![](assets/images/nodemcu.jpg)


#### 配置 NodeMcu

关于如何 NodeMcu, 请参考  [Johnny-Five Bluetooth Guide]


#### Android 代码

使用 WiFi 连接的 [Transport URI](#transports) 如下:

- `tcp:<board_ip_address>:<board_port>`

示例:

````kotlin
/**
 * If the ip address is '192.168.4.1', and the port is '3030', then the URI should be:
 *
 *   "tcp://192.168.4.1"
 */
connectBoard("tcp://192.168.4.1".toTransport(), ...)
````


## 文档

### Transports

Android-Firmata 使用 Transport URI 来确定 Android 和 Arduino 的通信方式:

- Bluetooth:

    - `bt:<bluetooth_name>`

      - **使用蓝牙名称连接时, 请确保蓝牙设备已和您的手机配对**

    - `bt:<bluetooth_mac_address>`

- WiFi:

    - `tcp:<board_ip_address>:<board_port>`


## 示例应用 (:link: [下载链接](https://github.com/xujiaao/android-firmata/releases/latest))

![](assets/images/sample-app.jpg)

- 更改 Transport URI, 请点击 Settings 菜单中的 Transport 选项

- 连接/断开设备, 请点击右上角的 Connect/Disconnect 按钮


[Johnny-Five]: https://github.com/rwaldron/johnny-five
[Johnny-Five Bluetooth Guide]: https://github.com/rwaldron/johnny-five/wiki/Getting-Started-with-Johnny-Five-and-JY-MCU-Bluetooth-Serial-Port-Module
[Johnny-Five Examples Page]: http://johnny-five.io/examples
[Firmata]: https://github.com/firmata/protocol
[NodeMcu]: http://nodemcu.com
[NodeMcu Guide]: https://github.com/xujiaao/android-firmata/wiki/Getting-Started-with-Android-Firmata-and-NodeMcu-Board