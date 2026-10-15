// Task 12.5.2: demo program for class with custom getter
import kotlinx.datetime.LocalDate

fun main() {
    val date = LocalDate(2006, 3, 26)
    val person = Person("Mt", date)
    println(person.name)
}
