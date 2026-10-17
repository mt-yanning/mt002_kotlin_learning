import kotlin.Double

// Task 15.2.3: demo program

fun main() {
    // Write code to demonstrate the classes here
    val SavingsAccount = SavingsAccount(holder = "Mt", rate = 1.8)
    SavingsAccount.deposit(1250)
    println(SavingsAccount.balance)
    for (i in 1..5) {
        SavingsAccount.applyInterest()
    }
//    repeat(5) {
//        account.applyInterest()
//    }
    println(SavingsAccount.balance)
    SavingsAccount.withdraw(50)
    println(SavingsAccount.balance)

}
