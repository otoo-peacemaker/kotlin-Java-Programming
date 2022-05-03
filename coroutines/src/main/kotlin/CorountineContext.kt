import kotlinx.coroutines.*



val scopeJob = Job()//reference
val scope = CoroutineScope(Dispatchers.Default + scopeJob)//type


//the builder
val coroutineJob = scope.launch {
delay(1000)//suspend for 1000mls
    println("A custom coroutine scope")
}


fun main(){
    println(coroutineJob)
    /*runBlocking {
       print("this is my custom $coroutineJob")
    }*/
}