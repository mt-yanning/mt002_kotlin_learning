import com.sun.org.apache.bcel.internal.util.Args.require

// Task 15.2.3: SavingsAccount class

class SavingsAccount(holder: String, val rate: Double): BankAccount(holder) {
    init {
        require(rate > 0.0) { "Rate must be greater than zero." }
    }
    fun applyInterest() {
        val interest = (balance * rate / 100).toInt()
        deposit(interest)
    }
}
