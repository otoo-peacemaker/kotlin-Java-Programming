import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.security.spec.InvalidParameterSpecException


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

suspend fun anotherMethod(){
    val exceptionHandler = CoroutineExceptionHandler{_, ex ->
        println("Caught : $ex.message")
    }
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Default +job)
    val launch = scope.launch(exceptionHandler){
        throwExLaunch("Err", true)
    }

    job.join()

}

suspend fun throwExLaunch(message: String, throwEX: Boolean = false) {
delay(500)
    if (throwEX){
        throw InvalidParameterSpecException("I am exception")
    }else{
        println(message)}
}
