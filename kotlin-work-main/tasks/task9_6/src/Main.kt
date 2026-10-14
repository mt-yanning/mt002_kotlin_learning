// Task 9.6: application to compute dataset variance

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    // Implement main program here
    if (args.size != 1) {
        println("please provide a filename")
        exitProcess(1)
    }
    try {
        val f = readData(args[0])
        val variance = variance(f)
        println("Dataset size: ${f.size}")
        println("Variance: %.5f".format(variance))
    } catch(error: Exception) {
        println("Error: $error")
        exitProcess(1)
    }
}
