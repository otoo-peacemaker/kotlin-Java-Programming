import TimeStampConverter.getDateString
import java.util.*
import kotlin.math.nextDown
import kotlin.math.nextUp
import kotlin.math.roundToInt
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


  //  if (validator("nanakwesi@gmail.com","12345k690")) print("Successful") else{print("Fail")}

  //  if(validator("nana@gmai.lcom","12345k690"))print("Successful") else{print("Fail")}

    validator("nanakwesi@gmail.com","12345989")


    val doubleValue = 29.4847472
    val roundup = doubleValue.roundTo(2)
    val dropme = doubleValue.nextDown()

    println("The conversion value ::::::::::::::::::::::::: $roundup")
    println("The conversion value : $dropme")
  //  println(isValidIdentifier("0kofile"))

    val timeCon = Long.secondsToHHMM(73791)

    println(buildString {
        append("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
    })

    println("::::::::::::::::::::::::: time "+timeCon)



    val matches = "" subStringMatcher "".toRegex()

    val sol = mutableListOf(5,3,5,6)
    println(solution(sol))
}

