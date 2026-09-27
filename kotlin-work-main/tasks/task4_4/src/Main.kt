// Task 4.4: temperature conversion using a while loop

import kotlin.system.exitProcess

import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal

fun main(args: Array<String>) {
    // Add your code here
    if(args.size != 3) {
        println("Error: three arguments required")
        exitProcess(1)
    }

    val initialTemperature = args[0].toDouble()
    val maxTemperature = args[1].toDouble()
    val increment = args[2].toDouble()

    var celsius = initialTemperature
    while (celsius <= maxTemperature) {
        val fahrenheit = (celsius * 9.0 / 5.0) + 32.0
        celsius += increment
        println("%.1f°C  %.1f°F".format(celsius, fahrenheit))
    }
}
