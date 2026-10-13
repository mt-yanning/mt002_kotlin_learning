// Task 8.3: functions for temperature analysis

import javax.xml.xpath.XPath
import kotlin.io.path.Path
import kotlin.io.path.forEachLine

typealias Record = Pair<String,Double>

fun fetchData(filename: String) = buildList {
    // Finish the implementation of this function
    Path(filename).forEachLine {
        val a = it.split(",")
        add(Pair(a[0].trim(), a[1].toDouble()))
    }
}

// Challenge: compute average temperature with one line of code
// in main(), instead of using the function below!

fun averageTemp(data: List<Record>): Double {
    var sum = 0.0
    for (record in data) {
        sum += record.second
    }
    return sum / data.size
}
