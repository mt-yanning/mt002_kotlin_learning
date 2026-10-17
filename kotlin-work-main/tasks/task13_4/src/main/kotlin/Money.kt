// Task 13.4: Money class

//class Money(euros: Int, cents: Int) {
//    val euros = euros
//    val cents = cents
//}

//class Money(val euros: Int, val cents: Int)

data class Money(val euros: Int, val cents: Int) {
    init {
        require(euros >= 0 ) { "Invalid euros: $euros" }
        require(cents >= 0) { "Invalid cents: $cents" }
        require(cents <= 99) { "Cents must be less than or equal to 99" }
    }
//  operator fun plus(other: Money) = Money(2, 50)
//  operator fun plus(other: Money) = Money(euros + other.euros, 50)
//  operator fun plus(other: Money) = Money(euros + other.euros, cents + other.cents)
    operator fun plus(other: Money): Money {
        val totalEuros = euros + other.euros
        val totalCents = cents + other.cents
        return Money(totalEuros + totalCents / 100, totalCents % 100)
    }
}