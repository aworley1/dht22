import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.RaspiPin
import com.pi4j.wiringpi.GpioUtil

val DHT_22 = RaspiPin.GPIO_04

fun main() {
    GpioUtil.enableNonPrivilegedAccess()
    val gpio = GpioFactory.getInstance()

    val output = gpio.provisionDigitalOutputPin(DHT_22)

    output.low()
    Thread.sleep(18)
    output.high()
    output.unexport()

    val input = gpio.provisionDigitalInputPin(DHT_22)

    while (true){
        println(input.state)
    }
}