fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val box = Box<ArrayList<Int>>()
    box.put(arrayListOf(3, 5))
    val boolBox = Box<Boolean>()
    boolBox.put(true)
    boolBox.isEmpty()

    println(box.retrieve())


        val node1 = Node(value = 1)
        val node2 = Node(value = 2)
        val node3 = Node(value = 3)
        node1.next = node2
        node2.next = node3
        println("NODE :::::::::::::: $node1")

    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)
    println(list)

    val list2 = LinkedList<Int>()
    list.append(1)
    list.append(2)
    list.append(3)
    println(list)


}