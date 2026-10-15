// Task 12.3.1: demo of secondary constructors

fun main() {
    val p = Point(4.5, 7.0)
    println("(${p.x}, ${p.y})")

    val p1 = Point()
    println("(${p1.x}, ${p1.y})")

    val q = Point(4, 7)
    println("(${q.x}, ${q.y})")
}
