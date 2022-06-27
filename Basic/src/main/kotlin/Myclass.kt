


fun  main(){


    val a : Int = 8
    val b : Int = 8

    a.times(10)
    b.times(10)


    val total = a.plus(b)

    println(total)

    val firstName: String = "Nadi"
    val lastName: String = "Bentum"
    val fullName: String = firstName+" "+ lastName


    val list = listOf(Items(1,"Kwesi","0245518918","nana@gmail"))
    val items = listOf(ListItems("Nadi",list))



    items.map{itemList ->
        println("::::::::::::::::::::::::::::::::::\n  \n${itemList.list}")
    }

    val num:Int? = null
    println(num)

    println(fullName)
}