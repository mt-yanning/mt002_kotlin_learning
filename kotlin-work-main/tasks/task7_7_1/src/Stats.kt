import kotlin.Float

// Task 7.7.1: statistics functions

fun computeMedian(numbers: List<Float>): Float {
    val sortedNumbers = numbers.sorted()
    val numberSize = sortedNumbers.size
    if (numberSize % 2 == 0) {
        return (sortedNumbers[numberSize / 2] + sortedNumbers[numberSize / 2 - 1]) / 2.0f
    } else {
        return sortedNumbers[(numberSize - 1) / 2]
    }
}

fun displayStatistics(numbers: List<Float>) {
    println("Min: ${numbers.min()}")
    println("Max: ${numbers.max()}")
    println("Mean: %.2f".format(numbers.average()))
    println("Median: ${computeMedian(numbers)}")
}