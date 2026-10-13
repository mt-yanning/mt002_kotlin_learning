import java.sql.DriverManager.println

// Task 7.3.1: list element access
fun main() {
    val numbers = listOf(9, 3, 6, 2, 8, 5)
    println(numbers)

    println(numbers[0])
//    println(numbers[10])
    println(numbers.slice(2..4))
    println(numbers.first())
    println(numbers.last())
//    numbers[0] = 10
//    println(numbers[0])

    val numbers2 = mutableListOf(9, 3, 6, 2, 8, 5)
    numbers2[0] = 10
    numbers2.add(1)
    println(numbers2[0])
    println(numbers2)

    numbers2.addAll(listOf(12, 13, 14, 15))
    println(numbers2)

    numbers2.remove(10)
    println(numbers2)

    numbers2.removeAt(0)
    println(numbers2)

    numbers2.clear()
    println(numbers2)
}

