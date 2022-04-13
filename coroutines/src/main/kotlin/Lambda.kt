
/**
 * Lambda with no parameters
 * */

val noParamLam : () -> Unit ={
    println("initiated")
}

/**
 * Lambda with single parameters
 * */
val singleParamLam : (Int) -> Unit ={
    println(it*it)
}


/**
 * Lambda with multiple parameters
 * */
val lam: (a:Int, b:String)->(Any)  = {
    a, b ->  println("$a $b")
}


fun main(){

    println(noParamLam())
    println(singleParamLam(9))
    val value = lam(4,"Str")
   println(value.toString())
}