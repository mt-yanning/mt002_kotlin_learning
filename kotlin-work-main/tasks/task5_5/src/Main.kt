// Task 5.1.1: main program

fun main(args: Array<String>) {
    if(args.size != 2) {
        println("Error: two words required")
        return
    }

    if (args[1] anagramOf args[0]) {
        println("${args[0]} and ${args[1]} are anagrams")
    }
    else {
        println("${args[0]} and ${args[1]} are not anagrams")
    }
}
