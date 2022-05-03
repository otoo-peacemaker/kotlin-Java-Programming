/**
 * @author Peaceamaker
 * The [Generics1] class accepts only one generic type T
 * @param[T] a generic parameter to accept any type
 * @param [value] accept any value of a type T
 * @constructor accepts a value with type T
 * */

class Generics1<T>(var value: T) {

    init {
        println(value)
    }
}


/**
 * The [Generics2] class accepts multiple generic type T,S with corresponding
 * parameters data and type
 * @param[T] a generic parameter to accept any type
 * @param [data] accept any value of a type T
 * @param [data] accept any value of a type
 * @constructor accepts a parameters with type T and S
 * */
class Generics2<T, S>(var data: T, var type: S) {

    init {
        println("data : $data  type: $type")
    }
}


fun <T>GenericFunction(data: ()->T){
    println(data)
}