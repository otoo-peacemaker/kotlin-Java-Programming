import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.jvm.internal.Intrinsics.Kotlin


var map = mapOf(
    "value1" to 3,
    "value2" to 5,
    "value3" to 6,
)



fun main(){
            for ((k, v) in map.entries){
                println("$k to $v")
        }
}




/**
 * Apply
 * Calls the specified function block with this value as its receiver and returns this value.
 *
 * Also
 * Calls the specified function block with this value as its argument and returns this value.
 *
 * run
 * Calls the specified function block with this value as its receiver and returns its result.
 *
 *
 * with
 * Calls the specified function block with the given receiver as its receiver and returns its result.
 *
 * let
 * Calls the specified function block with this value as its argument and returns its result.
 *
 *
 * takeUnless
 * Returns this value if it does not satisfy the given predicate or null, if it does.
 *
 * */


//public inline fun <T>    T.also(block: (T) -> Unit)               Calls the specified function block with this value as its argument and returns this value.
//public inline fun <T, R> T.let(block: (T) -> R): R                Calls the specified function block with this value as its argument and returns its result.

//public inline fun <T> T.apply( block: T.() -> Unit ): T           Calls the specified function block with this value as its receiver and returns this value.
//public inline fun <T, R> T.run(block: T.() -> R) {}               Calls the specified function block with this value as its receiver and returns its result.


//public inline fun <T, R> with( receiver: T, block: T.() -> R ): R   Calls the specified function block with the given receiver as its receiver and returns its result.



inline fun <T> anything(block: T.()->T): Unit = runBlocking{

}


