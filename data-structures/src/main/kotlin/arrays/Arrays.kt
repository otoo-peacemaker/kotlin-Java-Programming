package arrays

class ArraysClass {

    /**
     *
     * */

    fun reverseArrayInGroup(arr:ArrayList<Int>, groupSize:Int){
        val length = arr.size
        var groupPos=0

        for(i in 0..length){
            groupPos = i+groupSize//0+groupSize

            var start = i
            var end = Math.min(groupPos-1, length-1)//solving out of bound exception and finding min arr size and group size

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
    val arr = arrayListOf<Int>(3,6,7,8,1,28,9,53,7,4,2,9,2,9,0)
    val k = 5
    val reverseArrayInGroup = ArraysClass()
    reverseArrayInGroup.reverseArrayInGroup(arr, k)

    for (value in arr) print("$value ")
}