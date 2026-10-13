// Task 7.7.2: phone book simulator

const val CSV_FILENAME = "phone.csv"

fun main() {
    // Implement the main program here
    // (You can add other functions to this file if you wish)
    val filename = "phone.csv"
    val database = createDatabase()
    database.load(filename)

    while (true) {
        println("Enter name (or 'quit' to exit):")
        val input = readln()
        if (input == "quit") break
        if (input in database) {
            println("$input's phone: ${database[input]}")
        } else {
            println("Enter $input's phone")
            val phone = readln()
            database[input] = phone
            database.save(filename)
        }
        database.save("phone.csv")
    }
}
