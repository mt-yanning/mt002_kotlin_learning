// Task 5.1.1: anagrams() function

fun anagrams(first: String, second: String): Boolean {
    if (first.length != second.length) {
        return false
    }
    val firstChar = first.lowercase().toList().sorted()
    val secondChar = second.lowercase().toList().sorted()
    return firstChar == secondChar
}
