# Expression Body

If a function's body can be written as a single expression then you can omit
the braces and `return` statement. In this style of function definition, you
must use an `=` symbol between the function header and the expression. There
is typically no need to specify the return type explicitly, as the compiler
can usually infer this.

For example, here's how you could write a Kotlin function to compute the
area of a circle:

```kotlin
import kotlin.math.PI

fun circleArea(radius: Double) = PI * radius * radius
```

{button}`Run this function <https://pl.kotl.in/K4VhshhPZ>`

Notice the absence of an explicit return type. The compiler will infer
the return type to be `Double`, because the expression is a multiplication
of values that are all of type `Double`.

Notice also the absence of braces or the `return` keyword.

As another example, consider the `when` expression to compute exam grades
that we saw earlier. You could make this code more reusable by turning
it into a function with an expression body. Here's what such a function could
look like:

```{code} kotlin
:linenos:
fun grade(mark: Int) = when (mark) {
    in 0..39   -> "Fail"
    in 40..69  -> "Pass"
    in 70..100 -> "Distinction"
    else       -> "?"
}
```

{button}`Run this function <https://pl.kotl.in/V2YtgvseZ>`

If you wanted, you could write this with a block body instead:

```{code} kotlin
:linenos:
fun grade(mark: Int): String {
    when (mark) {
        in 0..39   -> return "Fail"
        in 40..69  -> return "Pass"
        in 70..100 -> return "Distinction"
        else       -> return "?"
    }
}
```

However, the version with an expression body is neater and more compact.

## Task 5.2.1

Open the `tasks/task5_2_1` directory of your repository in your preferred
code editing environment. Edit `Circle.kt` and copy the `circleArea()`
implementation shown above into this file.

Add a new function named `circlePerimeter()` to `Circle.kt`. This function
should compute and return the perimeter (circumference) of a circle with the
given radius. Like `circleArea()`, it should have an expression body.

Now edit `Main.kt`. In this file, add a `main()` function that reads a value
for circle radius from command line arguments and then uses the functions
from `Circle.kt` to compute area and perimeter. The program should then
display these quantities to four decimal places.

:::{hint .dropdown}
See the earlier discussion of [formatted output][fmt] if you need help
with displaying area and perimeter in the required way.
:::

Build the program, then run it a few times to check that it behaves
as expected.

## Task 5.2.2

Open the `tasks/task5_2_2` directory of your repository in your preferred
editing environment. Edit `Main.kt` and copy the expression form of the
`grade()` function shown above into this file.

Next, add to this file a `main()` function that

* Iterates over the program's command line arguments using a `for` loop
* Converts each argument to an `Int`, representing an exam mark
* Invokes `grade()` on the exam mark, to determine the corresponding grade
* Prints both the mark and the grade

Build the program, then test it by running it with different numeric arguments.
Here's an example of how you might run it from the command line, and what
the output might look like:

    $ ./kotlin run 45 72 29 63
    45 is a Pass
    72 is a Distinction
    29 is a Fail
    63 is a Pass


[fmt]: ../io-intro/output.md#format-function
