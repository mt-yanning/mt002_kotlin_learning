// Task 15.3: program demonstrating dynamic binding
fun main() {
    val p = Person()
    println(p.speak())

    val s = Student("Computer Science")
    println(s.speak())

    val p2: Person = Student("Maths")
    println(p2.speak())
//    p2 的声明类型是 Person, 但它实际引用的对象类型是 Student
//    调用 p2.speak() 时，JVM 看的是对象的实际类型，而不是变量的声明类型, 所以调用的是 Student.speak()
}
