import java.awt.Color
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object TimeStampConverter {
    private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)
    fun getDateString(time: Long): String = simpleDateFormat.format(time * 1000L)
    private fun getDateString(time: Int): String = simpleDateFormat.format(time * 1000L)
}


fun updateWeather(degrees: Int) {
    val (desc, color) = when {
        degrees < 10 -> "cold" to Color.BLUE
        degrees < 25 -> "mild" to Color.ORANGE
        else -> "hot" to Color.RED
    }
}

/**
 * Syntax of an Identifier
 * Syntax is a grammatical rule. Here is the syntax for valid Java identifiers:
 * Each identifier must have at least one character.
 * The first character must be picked from: alpha, underscore, or dollar sign. The first character can not be a digit.
 * The rest of the characters (besides the first) can be from: alpha, digit, underscore, or dollar sign. In other words, it can be any valid identifier character.
 * Put simply, an identifier is one or more characters selected from alpha, digit, underscore, or dollar sign. The only restriction is the first character can't be a digit.
 * */

fun isValidIdentifier(s: String): Boolean {
    fun isValidCharacter(char: Char) = char == '_' || char =='$' || char.isLetterOrDigit()//checking for char composition in the string
    if (s.isEmpty() || s[0].isDigit()) return false//check isEmpty string and should not start with a digit
    for (char in s) {
        if (isValidCharacter(char)) return true
        else throw Exception("$s is not valid identifier")
    }
    return true
}


/**
 * INFIX FUNCTION
 * Kotlin allows some functions to be called without using the period and brackets.
 * These are called infix methods, and their use can result in code that looks much more like a natural language.
 * */

/* An infix function to a String to pull out all the substrings that match a given regex:*/
infix fun String.subStringMatcher(r: Regex): List<String> {
    return r.findAll(this)
        .map { it.value }
        .toList()
}




/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            else -> {"No expression found"}
        }
    }
}


fun validator(email:String, password:String):Boolean{
    return when{
        !email.matches(("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)\$").toRegex()) ->{
            print("Invalid email")
            return false
        }
        !password.matches("^{1}(?:.){6,}$".toRegex()) ->{
            print("Invalid password")
            return false
        }
        else -> {true}
    }
}


fun Long.Companion.secondsToHHMM(seconds: Long): String {
    val hh = TimeUnit.SECONDS.toHours(seconds)
    val mm = TimeUnit.SECONDS.toMinutes(seconds)

    val hr = seconds.div(3600)
    val min = (seconds%3600)/60
    return String.format("%02d:%02d", hr, min)

}


fun Float.roundTo(n: Int): Float {
    //TODO locals eg:
    return "%.${n}f".format(Locale.ENGLISH,this).toFloat()
}

fun Double.roundTo(n: Int): Double {
    return "%.${n}f".format(this).toDouble()
}

fun solution(statues: MutableList<Int>): Int {
    return statues.sortedWith(compareBy { it }).first()
}


fun ArrComparator(statues: MutableList<Int>): Int {
    val min = statues.sortedWith(compareBy { it }).first()
    val max = statues.sortedWith(compareBy { it }).last()

    var counter = 0

    for(i in min..max){
        if(statues.indexOf(i)==-1)
            counter ++
    }

    return counter
}


