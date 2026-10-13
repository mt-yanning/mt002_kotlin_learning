// Task 8.5: example of using a higher-order function

fun main() {
    // Add your testing code here
    val text = "mt hello hihihi hhh omg"

    val vowelCount = text.howMany { it.lowercase() in "aeiou" }
    println("$vowelCount")

    fun isEnglishVowel(c: Char) = c.lowercase() in "aeiou"
    val vowelCount2 = text.howMany(::isEnglishVowel)
    println("$vowelCount2")

    val spaces = text.howMany { it.lowercase() == " " }
    println("$spaces")

    val m = text.howMany { it.lowercase() == "m" }
}
