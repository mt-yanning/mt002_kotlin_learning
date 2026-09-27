// Task 4.5: summing odd integers with a for loop

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    // Add your code here
    if (args.size != 1) {
        println("Error: One arguments required")
        exitProcess(1)
    }

    val limit = args[0].toInt()
    var sum = 0
    for (i in 1..limit step 2) {
        sum += i
    }
    println("Sum of odd numbers from 1 to $limit is: $sum")

}
