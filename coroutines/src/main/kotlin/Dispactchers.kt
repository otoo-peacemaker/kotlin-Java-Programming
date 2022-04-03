import kotlinx.coroutines.*

/**
 * @param Dispatcher determines the working thread
 * @param io : that is designed for offloading blocking IO tasks to a shared pool of threads.
 *
 * @param main : A coroutine dispatcher that is confined to the Main thread operating with UI objects. This dispatcher can be used either directly or via MainScope factory.
 * Usually such dispatcher is single-threaded.
 *
 * @param default : The default CoroutineDispatcher that is used by all standard builders like launch, async, etc.
 * if no dispatcher nor any other ContinuationInterceptor is specified in their context.
 *
 * @param unconfined : A coroutine dispatcher that is not confined to any specific thread.
 * It executes initial continuation of the coroutine in the current call-frame and lets the coroutine resume in whatever thread
 * that is used by the corresponding suspending function, without mandating any specific threading policy.
 * Nested coroutines launched in this dispatcher form an event-loop to avoid stack overflows
 * */


fun dispatcher() = runBlocking {
    launch(Dispatchers.Unconfined) {
        println("What's the thread name? ${Thread.currentThread().name} ")
        delay(1000)
        println("What's the thread name? ${Thread.currentThread().name} ")
        println("What's the thread name? ${Thread.currentThread().name} ")
        delay(1000)
        println("What's the thread name? ${Thread.currentThread().name} ")
    }


}


suspend fun context(){
    withContext(Dispatchers.IO){

    }
}