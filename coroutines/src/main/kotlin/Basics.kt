import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main(args: Array<String>) {

    thread()
    coroutines(2000)





}









fun thread(){
    /**@Author Peacemaker
     *
     * @sample[Thread]
     * the code below runs parallel that's in-concurrency occurs in lines 10,11 and 18 before the
     * thread block also run. No matter what, the application waits for the thread to finish its execution before exit
     * */
    println(":::::::::::::::::WORKING WITH COROUTINES::::::::::::::::::::")
    println("Main thread start: ${Thread.currentThread().name}")

    thread {//normal main background thread
        println("block thread start: ${Thread.currentThread().name}")
        Thread.sleep(1000)//blocking the main thread in 1s
        println("block thread end : ${Thread.currentThread().name}\n\n")
    }
    println("Main thread : ${Thread.currentThread().name}")//blocking the main thread in 1s
}


/**
 * @sample [1]coroutines
 * unlike threads, for coroutines, the application by
 * default does not wait for it finish its block execution and to solve this,
 * we need to instruct the app to wait for the coroutines to finish as line:38
 */

fun coroutines(sleep: Int){
     GlobalScope.launch {// coroutines background thread
         println("routines thread start: ${Thread.currentThread().name}")
         Thread.sleep(1000)//blocking the main thread in 1s
         println("routines thread end : ${Thread.currentThread().name}")
     }
     Thread.sleep(2000)//blocking the main thread in 2s
     println("Main thread end: ${Thread.currentThread().name}")
}


/**
 * @sample [2]coroutines
 * @param delay()
 * using Thread.sleep() in violates the principle of coroutines in the sense that,
 * it blocks the main thread from working, and also prevent the parallel executions.
 * But using delay() actually delays or suspends and resumes a particular coroutines its executions without blocking others from
 * executions.
 */

fun coroutines(time: Long){
    GlobalScope.launch {// coroutines background thread
        println("routines thread start: ${Thread.currentThread().name}")
        routineDelay(time)//blocking the main thread in 1s
        println("routines thread end : ${Thread.currentThread().name}")
    }

    runBlocking {//blocking the current main thread for 2s
        routineDelay(2000)
    }
    println("Main thread end: ${Thread.currentThread().name}")

}

suspend fun routineDelay(time:Long){
    delay(time)
}