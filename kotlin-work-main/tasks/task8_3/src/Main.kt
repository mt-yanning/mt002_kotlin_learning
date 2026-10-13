// Task 8.3: weather station temperature analysis program

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    // Add your code here
    val dataset = fetchData(args[0])
    val coldest = dataset.minBy {it.second}
    val hotest = dataset.maxBy {it.second}
    println("the coldest place: ${coldest.first}, with temperature: ${coldest.second}")
    println("the hotest place: ${hotest.first}, with temperature: ${hotest.second}")
}
