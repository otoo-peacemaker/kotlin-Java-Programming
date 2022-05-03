import javax.naming.Context
import kotlinx.coroutines.*
class MyCoroutineWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    suspend fun doWork() {
// work to be done on the coroutine
        return Result.success()
    }
}

interface WorkerParameters {

}
