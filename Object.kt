import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }
    
    fun printDeviceInfo(){
        
        println("Device name: $name, Category: $category, Type: $deviceType")
        
    }
    
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2,
                                                minValue = 0,
                                                maxValue = 100,
                                                lowerInputMessage = "Minimum volume reached.",
                                                higherInputMessage = "Maximum volume reached" )

    private var channelNumber by RangeRegulator(initialValue = 1,
                                                minValue = 0,
                                                maxValue = 200,
                                                lowerInputMessage = "Minimum channel reached.",
                                                higherInputMessage = "Maximum channel reached")

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }
    
    fun decreaseSpeakerVolume() {
        speakerVolume--
        println("Speaker volume decreased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }
    
    fun previousChannel() {
        channelNumber--
        println("Channel number decreased to $channelNumber.")
    }

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    internal var brightnessLevel by RangeRegulator(initialValue = 2,
                                                  minValue = 0,
                                                  maxValue = 100,
                                                  lowerInputMessage = "Minimum brightness reached, the lamp is now off.",
                                                  higherInputMessage = "Minimum brightness reached.")

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }
    
    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness decreased to $brightnessLevel.")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("$name turned off")
    }
}

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        if(smartTvDevice.deviceStatus == "on"){
        	smartTvDevice.increaseSpeakerVolume()
        } else {
            println("The tv is off.")
        }
    }
    
    fun decreaseTvVolume(){
        if(smartTvDevice.deviceStatus == "on"){
        smartTvDevice.decreaseSpeakerVolume()
        } else {
            println("The tv is off.")
        }
    }

    fun changeTvChannelToNext() {
        if(smartTvDevice.deviceStatus == "on"){
        smartTvDevice.nextChannel()
        } else {
            println("The tv is off.")
        }
    }
    
    fun changeTvChannelToPrevious() {
        if(smartTvDevice.deviceStatus == "on"){
        smartTvDevice.previousChannel()
        } else {
            println("The tv is off.")
        }
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        if (smartLightDevice.deviceStatus.equals("on", true)){
        	smartLightDevice.increaseBrightness()
        } else {
            println("The light is off.")
        }
    }
    
    fun decreaseLightBrightness() {
        if (smartLightDevice.deviceStatus.equals("on", true)){
        	smartLightDevice.decreaseBrightness()
        	if (smartLightDevice.brightnessLevel == 0) {            
				turnOffLight()
        	}
        } else {
          println("The light is off.")  
        }
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
    
}

class RangeRegulator(
    initialValue: Int,
    private val lowerInputMessage: String,
    private val higherInputMessage: String,
    private val minValue: Int,
    private val maxValue: Int,
	) : ReadWriteProperty<Any?, Int> {

    private var fieldData = initialValue

    init {
        require(initialValue in minValue..maxValue) { "Initial value must be within the range [$minValue, $maxValue]" }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value < minValue) {
            println(lowerInputMessage)
            fieldData = minValue
        } else if (value > maxValue) {
            println(higherInputMessage)
            fieldData = maxValue
        } else {
            fieldData = value
        }
    }
}

fun main() {
    val smartDevice1: SmartTvDevice = SmartTvDevice("Android TV", "Entertainment")
    
    val smartDevice2: SmartLightDevice = SmartLightDevice("Google Light", "Utility")
    
    val smartHome: SmartHome = SmartHome(smartDevice1, smartDevice2)
    smartHome.turnOnTv()
    smartHome.turnOnLight()
    smartHome.changeTvChannelToNext()
    smartHome.changeTvChannelToPrevious()
    smartHome.decreaseTvVolume()
    smartHome.decreaseLightBrightness()
    println(smartHome.deviceTurnOnCount)
}