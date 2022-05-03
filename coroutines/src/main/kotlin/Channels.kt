/*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel

*/
/**
 * ::::::::::::::::::::::::: DATA STREAMS :::::::::::::::::::::::::::
 * [1] Data may make itself available over time
 * [2] Channels stream data between coroutines
 * [3] Data is sent by the producer, and received by the consumer
 * [4] Hot streams constantly produce data, even if there is no consumer
 * [5] Cold streams are dormant until there is a consumer(Kotlin Flow)
 * *//*


val scope1 = CoroutineScope(Dispatchers.Default)
val channel = Channel<Int>()

scope1.launch{
     for (i in 1..10 ){
         channel.send(i)
     }
     }

scope1.launch{
    while(true){
        val j = channel.receive()
        println(j)
    }
}
 */
