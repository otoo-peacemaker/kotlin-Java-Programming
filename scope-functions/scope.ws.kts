data class SomeVals(
    var a: Int = 4,
    val b: Int = 6,
    val c: Int = 10
)

with(SomeVals()) {
    print(c).run {
       println("I am favored")
    }.also {
        print("$c is printed")
    }.let {
        a = 8
        print("new value for $a is printed")
    }
}