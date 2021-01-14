import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.RaspiPin
import com.pi4j.io.gpio.event.GpioPinListener
import com.pi4j.io.gpio.event.GpioPinListenerDigital
import com.pi4j.wiringpi.GpioUtil

val DHT_22 = RaspiPin.GPIO_04

fun main() {
    GpioUtil.enableNonPrivilegedAccess()
    val gpio = GpioFactory.getInstance()

    val output = gpio.provisionDigitalOutputPin(DHT_22)

    println("setting high")
    output.high()
    Thread.sleep(100)
    println("setting low")
    output.low()
    Thread.sleep(20)
    println("setting high")
    output.high()
    gpio.unprovisionPin(output)

    val input = gpio.provisionDigitalInputPin(DHT_22)

    println("Input starts off ${input.state}")

    input.addListener(
        GpioPinListenerDigital {
            println("${System.nanoTime()}: ${it.state}")
        }
    )

    while (true) {}
}