import kotlinx.coroutines.*

/**
 * Coroutines builders: launch async and runBlocking
 * The launch coroutines builder launches a new coroutines without blocking the current thread. It inherit the its immediate
 * parent thread and returns a job object.
 *
 * GlobalScope coroutines are top-level coroutines and can survive the entire life of the application
 * unlike the local launch function where is alive on an active launch.
 *when do we use global and local coroutines: we create global coroutine when operation of one activity
 * wouldn't affect or depend on the operation of the other activity. eg...file downloading, music play etc.
 * we also use the local launch only when there is dependant operations. such as screen navs and simple calcs
 *
 * */


/**
 * @param []launch : Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a Job
 * @return [Job]: Suspends the coroutine until this job is complete.
 * And this helps us to avoid hard-code with delay method and an option to cancel the thread
 * */
fun launchBuilder(time:Long)= runBlocking{
  val job: Job = launch {
      println("routines thread start: ${Thread.currentThread().name}")
      delay(time)//blocking the main thread in 1s
      println("routines thread end : ${Thread.currentThread().name}")
  }
    job.join()//Suspends the coroutine until this job is complete.
    println("Main thread end: ${Thread.currentThread().name}")

}


/**
* @return [Deferred]: Deferred value is a non-blocking cancellable future — it is a Job with a result.
*It is in active state while the value is being computed. Deferred has the same state machine as the Job with
*additional convenience methods to retrieve the successful or failed result of the computation that was carried out
* */
fun asyncBuilder(time:Long)= runBlocking{
    val jobDeferred: Deferred<String> = async {
        println("async routines thread start: ${Thread.currentThread().name}")
        delay(time)//blocking the main thread in 1s
        println("async routines thread end : ${Thread.currentThread().name}")
        "Success"
    }
    jobDeferred.await()
    println("Main thread end: ${Thread.currentThread().name}")

}