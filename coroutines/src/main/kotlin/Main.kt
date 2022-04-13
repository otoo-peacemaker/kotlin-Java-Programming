fun main() {

    println("The thread name ${Thread.currentThread().name}")

    val let = LetScope

    println(let.doWithNonNullAsserted(78))

    //println(LetClass(400))


    //dispatcher()
}