// Task 12.4.1: Circle class
import kotlin.math.PI

class Circle(val centre: Point, val radius: Double) {
    init {
        require(radius > 0.0) { "Radius must be positive" }
    }
    fun area() =  PI * radius * radius
    fun perimeter() = 2 * PI * radius
//    fun area(): Double {
//        return  PI * radius * radius
//    }
    fun contains(point: Point) = point.distanceTo(centre) <= radius
}
