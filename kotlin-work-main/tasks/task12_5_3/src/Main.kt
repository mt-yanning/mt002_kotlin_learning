// Task 12.5.3: demo program for class with custom setter
import kotlinx.datetime.LocalDate

fun main() {
    val date = LocalDate(2006, 3, 26)
    val person = Person("Mt", date)
    person.name = "ll"
    println(person.name)
    person.name = " "
}