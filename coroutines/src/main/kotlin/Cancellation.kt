import kotlinx.coroutines.*
/**
 * @param [cancelAndJoin()]
 * Cancels the job and suspends the invoking coroutine until the cancelled job is complete.
This suspending function is cancellable and always checks for a cancellation of the invoking coroutine's Job
 * */
fun cancellable() = runBlocking{
    val  job: Job = launch {
        while (isActive){
            for (i in 0..500){
                //  if (!isActive){return@launch}//break
                print("$i. ")
                delay(100)// print the value for every second
                //yield()//run the routine very fast
            }
        }
    }
    delay(5000)//within 5min cancel the job and print the thread name
    job.cancelAndJoin()
    println("The thread name : ${Thread.currentThread().name}")
}