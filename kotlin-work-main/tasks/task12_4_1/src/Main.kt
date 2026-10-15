// Task 12.4.1: main program

fun main() {
    val point = Point(0.0, 0.0)
//    val Circle1 = Circle(point, -5.0)
    val circle1 = Circle(point, 5.0)
    val point2 = Point(3.0, 4.0)
    val point3 = Point(3.0, 5.0)
    println(circle1.area())
    println(circle1.perimeter())
    println(circle1.contains(point2))
    println(circle1.contains(point3))






}
