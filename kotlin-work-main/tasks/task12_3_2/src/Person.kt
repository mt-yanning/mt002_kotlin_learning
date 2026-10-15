// Task 12.3.2: Person class with secondary constructor
import kotlinx.datetime.LocalDate

class Person(var name: String, val birth: LocalDate) {
    var isMarried = false
    constructor(name: String, birth: String): this(name, LocalDate.parse(birth))
}