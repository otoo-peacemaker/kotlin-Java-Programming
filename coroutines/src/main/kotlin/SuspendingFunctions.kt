import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

/**
 * @author Peacemaker Nana Kwesi Otoo
 * How to compose a suspending functions such a sequential, concurrent and lazy exe
 *
 * */


/**
 * @param sequential: by default coroutines are executed sequentially: One after the other
 *
 * */
fun sequentialRoutine() = runBlocking {
    val time = measureTimeMillis {//checking the used to exe the entire program
        val msgOne = getMessageOne()
        val msgTwo = getMessageTwo()

        println("The entire message : ${msgOne + msgTwo}")
    }
    println("Time completed : $time")
}


/**
 * @param concurrent: This is how executes coroutines in parallel using async builder, though launch would
 * also do but only when you don't want to return a value.
 *
 * */

fun concurrentRoutine() = runBlocking {
    val time = measureTimeMillis {//checking the used to exe the entire program
        val msgOne: Deferred<String> = async {
            //..more code
            getMessageOne()//return type
        }
        val msgTwo: Deferred<String> = async {
            //..more code
            getMessageTwo()
        }

        println("The entire message : ${msgOne.await() + msgTwo.await()}")
    }
    println("Time completed : $time")
}

suspend fun getMessageTwo(): String {
    delay(1000)
    return "Hello "
}

suspend fun getMessageOne(): String {
    delay(1000L)
    return "world"
}

