// Task 6.5: unit tests for grade()

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe


@Suppress("unused")
class GradeTest : FreeSpec({
    // Write your tests in here
    // 0 20 39  40 55 69  70 85 100  -1 101 200

    // 细粒度
    "Mark of 0 gives a Fail" {
        grade(0) shouldBe "Fail"
    }
    "Mark of 20 gives a Fail" {
        grade(20) shouldBe "Fail"
    }
    "Mark of 39 gives a Fail" {
        grade(39) shouldBe "Fail"
    }
    "Mark of 40 gives a Pass" {
        grade(40) shouldBe "Pass"
    }
    "Mark of 55 gives a Pass" {
        grade(55) shouldBe "Pass"
    }
    "Mark of 69 gives a Pass" {
        grade(69) shouldBe "Pass"
    }
    "Mark of 70 gives a Distinction" {
        grade(70) shouldBe "Distinction"
    }
    "Mark of 85 gives a Distinction" {
        grade(85) shouldBe "Distinction"
    }
    "Mark of 100 gives a Distinction" {
        grade(100) shouldBe "Distinction"
    }
    "Mark of -1 gives a ?" {
        grade(-1) shouldBe "?"
    }
    "Mark of 101 gives a ?" {
        grade(101) shouldBe "?"
    }
    "Mark of 200 gives a ?" {
        grade(200) shouldBe "?"
    }

    // 粗粒度
    "Mark between 0 and 39 gives a Fail" {
        assertSoftly {
            withClue("Mark = 0") {grade(0) shouldBe "Fail"}
            withClue("Mark = 20") {grade(20) shouldBe "Fail"}
            withClue("Mark = 39") {grade(39) shouldBe "Fail"}
        }
    }
    "Mark between 40 and 69 gives a Pass" {
        assertSoftly {
            withClue("Mark = 40") {grade(40) shouldBe "Pass"}
            withClue("Mark = 55") {grade(55) shouldBe "Pass"}
            withClue("Mark = 69") {grade(69) shouldBe "Pass"}
        }
    }
    "Mark between 70 and 100 gives a Distinction" {
        assertSoftly {
            withClue("Mark = 70") {grade(70) shouldBe "Distinction"}
            withClue("Mark = 85") {grade(85) shouldBe "Distinction"}
            withClue("Mark = 100") {grade(100) shouldBe "Distinction"}
        }
    }
    "Marks below 0 give a ?" {
        assertSoftly {
            withClue("Mark = -1") {grade(-1) shouldBe "?"}
            withClue("Mark = -100") {grade(-100) shouldBe "?"}
        }
    }
    "Marks above 100 give a ?" {
        assertSoftly {
            withClue("Mark = 101") {grade(101) shouldBe "?"}
            withClue("Mark = 200") {grade(200) shouldBe "?"}
        }
    }
})
