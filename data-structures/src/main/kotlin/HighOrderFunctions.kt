


//let

/**
 * Calls the specified function block with this value as
 * its argument and returns its result.
 * */
fun printCar(car: Car?) {
    val isCoupe = car?.let {
        (it.doors <= 2)
    }
    if (isCoupe == true) {
        println("Coupes are awesome")
    }
}

//run
/**Calls the specified function block with
 * this value as its receiver and returns its resul*/
fun printCar2(car: Car?) {
    val isCoupe = car?.run {
        (this.doors <= 2)
    }
    if (isCoupe == true) {
        println("Coupes are awesome")
    }
}

//also
fun printCar3(car: Car?) {
    car?.also {
        it.doors = 4
    }.let {
        if (it?.doors != null && it.doors <= 2) {
            println("Coupes are awesome")
        }
    }
}

//apply
fun printCar4(car: Car?) {
    car?.apply {
        doors = 4
    }.let {
        if (it?.doors != null && it.doors <= 2) {
            println("Coupes are awesome")
        }
    }
}


data class Car(var doors:Int)