import TimeStampConverter.getDateString
import java.util.*
import kotlin.math.roundToLong

fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    // println("Program arguments: ${args.joinToString()}")
    // println(Calendar.getInstance().time)

    val sysTime = Calendar.getInstance().time.toString()

    // println(".................... current time :$sysTime")

    val decimalValue = 1.65312602E9
    val wholeNum = decimalValue.roundToLong()

    println("The conversion : $wholeNum")
    println(getDateString(wholeNum))

    val printWeather = updateWeather(20).toString()
   // println(printWeather)

  //  println(isValidIdentifier("0kofile"))

    val matches = "" subStringMatcher "".toRegex()
}

