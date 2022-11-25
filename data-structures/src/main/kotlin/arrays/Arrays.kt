package arrays

class ArraysClass {

    /**
     *
     * */

    fun reverseArrayInGroup(arr:ArrayList<Int>, groupSize:Int){
        val length = arr.size

        for(i in 0..length){

            var start = i
            val  groupPos = i+groupSize//0+groupSize
            var end =
                (groupPos - 1).coerceAtMost(length - 1)//solving out of bound exception and finding min arr size and group size

            while (start<=end){
                val temp = arr[start]
                arr[start] = arr[end]
                arr[end] = temp
                start++
               end --
            }
        }
    }

//webwrite.com
}
fun main(){
    val arr = arrayListOf<Int>(3,6,7,8, 1,9,9,3 ,7,4,2,9, 2,9,0)
    val k = 3
    val reverseArrayInGroup = ArraysClass()
    reverseArrayInGroup.reverseArrayInGroup(arr, k)

    for (value in arr) print("$value ")
}