// Task 12.8.1: demo program for Day enum class
fun main() {
    print("Enter a day: ")
    val input = readln()
    try {
        val day = Day.valueOf(input)
        println("your choice: $day")
    } catch(error: IllegalArgumentException) {
        println(error)
        println("your choice: $input")
    }
}
