import kotlinx.coroutines.*


fun exception() = runBlocking {

    val job: Job = launch {
        try {
            while (isActive) {
                for (i in 0..500) {
                    print("$i. ")
                    delay(100)
                }
            }
        } catch (ex: CancellationException) {
            println(ex.message)
        } finally {
            withContext(NonCancellable) {
                delay(200)
                println("something")
            }
        }
    }


    delay(5000)//within 5min cancel the job and print the thread name
    job.cancel("Job exceptions")
    job.join()
    println("The thread name : ${Thread.currentThread().name}")
}