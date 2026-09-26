// Task 3.5: simple file I/O

import kotlin.io.path.Path
import kotlin.io.path.appendText
import kotlin.io.path.readText
import kotlin.io.path.writeText

fun main() {
    // Add your code here
    val path = Path("text.txt")
    path.writeText("text1text1text1text1text1text1text1text1")

    path.writeText("text2text2text2text2")

    path.appendText("text3text3text3text3")

    val file = path.readText()
    println(file)
}
