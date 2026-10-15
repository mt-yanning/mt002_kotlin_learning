// Task 12.1: program that uses a Point object

fun main() {
    val p = Point()
    println(p.x)
    println(p.y)
    p.x = 1.0

    val p2 = Point2(1.3, 4.3)
    println(p2.attri_x)
    println(p2.attri_y)

    val p3 = Point3(1.2, 4.4)
    println(p3.attri_x)
    println(p3.attri_y)

    val p4 = Point4(1.3, 4.5)
    println(p4.attri_x)
    println(p4.attri_y)

    val p5 = Point5(1.4, 4.6)
    val q = Point5(4.5, 7.0)
    println(p5.x)
    println(p5.y)
    println("%.2f".format(p5.distance()))
    println(p5.distanceTo(q))
}