// Task 5.1.1: anagrams() function

infix fun String.anagramOf(first: String) =
    this.lowercase().toList().sorted() == first.lowercase().toList().sorted()

