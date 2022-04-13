import LetScope.letExpression
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object LetScope {
    /**
     * The null safety code below works fine but its more prone to errors
     * since the [anyValue] is global variable, its value can be set to null
     * by any other scope which might affect the value set to null and also
     * not null check(!!) would also throw an exception since the value is set back to null by
     * other operation or scope function
     * */

    private var anyValue: Int? = null

    /**
     * @method [doWithoutNonNullAsserted]
     * @exception [ add non-null asserted!! call] Smart cast to 'Int' is impossible, because 'number' is a mutable
     * property that could have been changed by this time
     *
     * @reason : Since the [anyValue] is a global variable, its value could be set to null at anytime
     * by any other scope which could affect the [anyValue] or set the [anyValue] to back to null
     *
     * @solution : Place non null asserted(!!) call to the [anyValue]
     * */
    fun doWithoutNonNullAsserted() {
        if (anyValue != null) {
//            val num = anyValue +1
//            print(num)
        }
    }


    /**
     * @method [doWithNonNullAsserted]
     * @exception [NullPointerException]
     * @reason : Since the [anyValue] is a global variable, its value could be set to null at anytime
     * by any other scope[thread] which could affect the [anyValue] or set the [anyValue] to back to null, and
     * the non-null asserted call would throw an exception since the value is null
     * */
    fun <T> doWithNonNullAsserted(value: T? = null) {
        if (anyValue != null) {
            val newValue = anyValue!! + (value as Int) //checking for non-null
            println(newValue)
        } else println("This function has a null value")

    }

    /**
     * Calls the specified function block with this value as its argument
     * and returns its result.
     * */

    val letExpression: (Int) -> Unit = { num ->
        anyValue  = 10
        anyValue?.let {
            anyValue = (num + it)
        }
        println(anyValue)
    }
}

/**
 * The function below contains multiple threads calling the [doWithNonNullAsserted()]
 * One thread set the [value] to null which makes all threads return null since they're all
 * accessing the value at same time
 * */

val runAsserted: () -> Unit = {
    runBlocking {
        launch {
            println("The launch thread: ${Thread.currentThread().name}")
            println(LetScope.doWithNonNullAsserted(200))
            delay(1000L)
        }

        async {
            println(LetScope.doWithNonNullAsserted(null))
        }

        async {
            println(LetScope.doWithNonNullAsserted(90))
        }

        async {
            println(LetScope.doWithNonNullAsserted(45))
        }
    }

}


fun main(): Unit = runBlocking {
    //println(runAsserted.invoke())
    println(letExpression.invoke(80))
}