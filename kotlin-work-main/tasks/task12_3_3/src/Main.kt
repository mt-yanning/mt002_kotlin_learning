// Task 12.3.2: demo of Person class with secondary constructor
import kotlinx.datetime.LocalDate

fun main() {
    val date = LocalDate(1997, 8, 23)
    val person = Person("Mt", date)
    println("Name: ${person.name}")
    println("Birth: ${person.birth}")
    println("Married: ${person.isMarried}")

    val person2 = Person("Mt", "1997-08-23")

    val person3 = Person(" ", "1997-08-23")
}
