# `while` & `do`...`while`

Kotlin has a `while` loop that operates in the same way as the `while` loops
of C and Python:

::::{tab-set}
:::{tab-item} Kotlin
```kotlin
// Print a table of square roots

var x = 0.0
while (x <= 100.0) {
    val y = sqrt(x)
    println("%5.1f %6.3f".format(x, y))
    x += 2.5
}
```
:::
:::{tab-item} C
```c
/* Print a table of square roots */

double x = 0.0;
while (x <= 100.0) {
    double y = sqrt(x);
    printf("%5.1f %6.3f\n", x, y);
    x += 2.5;
}
```
:::
:::{tab-item} Python
```python
# Print a table of square roots

x = 0.0
while x <= 100.0:
    y = math.sqrt(x)
    print(f"{x:5.1f} {y:6.3f}")
    x += 2.5
```
:::
::::

{button}`Run Kotlin example <https://pl.kotl.in/8oWG4XsnW>`

Once again, Kotlin is stricter than C with regard to the test that follows the
`while` keyword. This test must yield a boolean result.

Like C (but unlike Python), Kotlin also has a `do`...`while` loop.

Remember that a `while` loop will not execute at all if the test associated
with the `while` evaluates to `false` immediately&mdash;whereas a
`do`...`while` loop is guaranteed to execute at least once, because the test
is done at the end rather than the start.

## Task 4.4

Open the `tasks/task4_4` directory of your repository in your preferred code
editing environment. This contains a Kotlin Toolchain project, pre-configured
with [Mordant][mor] as a dependency.

Write a temperature conversion program in `Main.kt`. This program should
require three floating-point values as command line arguments, representing:
an initial temperature in [Celsius][cel]; a maximum temperature in Celsius;
and a temperature increment.

Your program should use a `while` loop to generate a series of temperatures
on the Celsius scale, starting at the given initial temperature and stopping
when the temperature exceeds the given maximum. For each of the generated
temperatures, it should compute the corresponding temperature on the
[Fahrenheit][fah] scale.

Choose one of the two following options for how the temperatures are
displayed.

### Option 1

Your program should print the Celsius and Fahrenheit temperatures in two
right-aligned columns. Temperature values should be printed to one decimal
place of accuracy.

### Option 2

This is a bit trickier, but more interesting!

Use Mordant's [table builder][tab] to create a nicely-formatted temperature
conversion table. As with Option 1, temperatures should be right-aligned
within this table, and displayed with one decimal place of accuracy.

Be creative with the use of colour and style. Look at the code for the
examples in the Mordant documentation for ideas and guidance.

:::{hint .dropdown}
You can embed your `while` loop within the `body` component of the
table. Instead of printing the temperatures, the loop should format them
as strings and then pass these strings to the `row()` function.
:::


[mor]: https://ajalt.github.io/mordant/
[cel]: https://en.wikipedia.org/wiki/Celsius
[fah]: https://en.wikipedia.org/wiki/Fahrenheit
[tab]: https://ajalt.github.io/mordant/guide/#tables
