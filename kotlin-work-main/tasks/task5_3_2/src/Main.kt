// Task 5.3.2: main program

fun main(args:Array<String>) {
    rollDice(20, 5)
    rollDice(sides = 11)
    rollDice(sides = 10)
    rollDice(dice = 10)
    rollDice()
    val sides = args[0].substringBefore("d").toInt()
    val dice = args[0].substringAfter("d").toInt()
    rollDice(dice, sides)
}
