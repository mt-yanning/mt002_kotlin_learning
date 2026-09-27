// Task 4.7: finding the longest line in a file
import kotlin.io.path.Path
import kotlin.system.exitProcess
import kotlin.io.path.forEachLine

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: one file path required")
        exitProcess(1)
    }
    val filePath = args[0]

    var currentLineNumber = 0
    var currentLength = 0
    var maxLength = 0
    var longestLineNumber = 0

    Path(filePath).forEachLine {
        currentLineNumber += 1
        currentLength = it.length
        if (currentLength > maxLength) {
            maxLength = currentLength
            longestLineNumber = currentLineNumber
        }
    }

    println("Line $longestLineNumber is the longest (length = $maxLength)")

}
