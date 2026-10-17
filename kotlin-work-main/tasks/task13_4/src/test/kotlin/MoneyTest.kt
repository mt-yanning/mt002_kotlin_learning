// Task 13.4: unit tests for Money class

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class MoneyTest : FreeSpec({
    // Write your unit tests here
    "Can create a Money" {
        val m = Money(1, 50)
    }
    "Can create a Money" {
        val m = Money(1, 50)
        withClue("euros") { m.euros shouldBe 1 }
        withClue("cents") { m.cents shouldBe 50 }
    }
    "Can create a different Money" {
        val m = Money(2, 99)
        withClue("euros") { m.euros shouldBe 2 }
        withClue("cents") { m.cents shouldBe 99 }
    }
    "Nevigative euros are invalid" {
        shouldThrow<IllegalArgumentException> {
            Money(-1, 5)
        }
    }
    "Nevigative cents are invalid" {
        shouldThrow<IllegalArgumentException> {
            Money(3, -5)
        }
    }
    "Cents must be less than or equal to 99" {
        shouldThrow<IllegalArgumentException> {
            Money(3, 100)
        }
    }
    "€1.50 + €1.00 is €2.50" {
        Money(1, 50) + Money(1, 0) shouldBe Money(2, 50)
    }
//   代码中写 a + b 时，Kotlin 编译器会自动将其转换为 a.plus(b)
    "€1.50 + €2.00 is €3.50" {
        Money(1, 50) + Money(2, 0) shouldBe Money(3, 50)
    }
    "€1.50 + €0.01 is €1.51" {
        Money(1, 50) + Money(0, 1) shouldBe Money(1, 51)
    }
    "€2.99 + €0.01 is €3.00" {
        Money(2, 99) + Money(0, 1) shouldBe Money(3, 0)
    }
})
