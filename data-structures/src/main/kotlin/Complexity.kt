
/**
 * TIME COMPLEXITY
 * []Time complexity] is a measure of the time required to run an algorithm as the input
size increases.
 * **/

//A constant time algorithm is one that has the same running time regardless of the
//size of the input
fun checkFirst(names: List<String>) {
    if (names.firstOrNull() != null) {
        println(names.first())
    } else {
        println("no names")
    }
}

//The size of names does not affect the running time of this function. Whether names
//has 10 items or 10 million items, this function only checks the first element of the
//list.


