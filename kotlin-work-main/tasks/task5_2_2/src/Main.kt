// Task 5.2.2: main program

fun main(args: Array<String>) {
    for (arg in args) {
        val mark = arg.toInt()
        val result = grade(mark)
        println("$mark is a $result")
    }
}