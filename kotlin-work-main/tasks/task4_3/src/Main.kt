import kotlin.math.roundToInt
import kotlin.system.exitProcess

// Task 4.3: grade calculation using a when expression
fun main(args:Array<String>) {
    if (args.size != 3) {
        println("Error: three marks required")
        exitProcess(1)
    }

    val mark1 = args[0].toInt()
    val mark2 = args[1].toInt()
    val mark3 = args[2].toInt()

    val averageMark = ((mark1 + mark2 + mark3) / 3.0).roundToInt()

    val grade = when (averageMark) {
        in 0..39 -> "Fail"
        in 40..69 -> "Pass"
        in 70..100 -> "Distinction"
        else -> "Unknown"
    }
    println("Average mark: $averageMark")
    println("Grade $grade")
}