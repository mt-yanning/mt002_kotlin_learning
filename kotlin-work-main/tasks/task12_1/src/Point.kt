// Task 12.1: Point class
import kotlin.math.hypot

class Point {
    var x = 0.0
    var y = 0.0
}

class Point2 constructor(x: Double, y: Double) {
    val attri_x = x
    val attri_y = y
}

class Point3 constructor(var x: Double, var y: Double) {
    var attri_x = x
    var attri_y = y
}

class Point4(x: Double, y: Double) {
    var attri_x = x
    var attri_y = y
}
class Point5(var x: Double, var y: Double) {
    fun distance() = hypot(x, y)
    fun distanceTo(other: Point5) = hypot(other.x - x, other.y - y)
}
