import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull


/**
 * @param timeOut
 * Runs a given suspending block of code inside a coroutine with a specified timeout
 * and returns null if this timeout was exceeded and print finish if operation exe successfully
 *
 * @sample I have instructed the program to print 500 numbers in a second
 * */
fun timeOut()= runBlocking{
    println("The thread name: ${Thread.currentThread().name}")

    val result : String? = withTimeoutOrNull(1000){
        for (i in 0..500){
           print("$i. ")
           //delay(10)
        }

        "finish"
    }
    println("\n"+result)
    println("The thread name: ${Thread.currentThread().name}")
}