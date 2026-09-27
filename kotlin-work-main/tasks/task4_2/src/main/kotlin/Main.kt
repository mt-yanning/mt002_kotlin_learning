// Task 4.2: use of if and ranges

fun main() {
    // Add your code here
    println("PIZZA MENU\n")
    println("(a) Margherita")
    println("(b) Quattro Stagioni")
    println("(c) Seafood")
    println("(d) Hawaiian")
    print("Choose your pizza (a-d):")
    val pizzaChoice = readln().lowercase()

    if (pizzaChoice.length == 1 && pizzaChoice[0] in 'a'..'d') {
        println("Order accepted")
    } else {
        println("Invalid choice!")
    }

}
