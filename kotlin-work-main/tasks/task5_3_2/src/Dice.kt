// Task 5.3.2: rollDice() function

import kotlin.random.Random

fun rollDice(sides: Int = 6, dice: Int = 1) {
    var total = 0
    if (sides !in setOf(4, 6, 8, 10, 12, 20)) {
        println("Error: cannot have a $sides-sided die")
        return
    }

    repeat(dice) {
        total += Random.nextInt(1, sides + 1)
    }
    println("Rolling $dice d$sides... Total: $total")
}
