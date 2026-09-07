# `when` Expressions

At its simplest, Kotlin's `when` expression can operate much like C's
`switch` statement. Here's an example. (Note: we've omitted some of the code
below because you don't need to see all of it to understand the syntax
differences.)

::::{tab-set}
:::{tab-item} Kotlin
```kotlin
when (day) {
    1 -> println("Monday")
    2 -> println("Tuesday")
    3 -> println("Wednesday")
    ...
}
```
:::
:::{tab-item} C
```c
switch (day) {
    case 1:
        printf("Monday\n");
        break;
    case 2:
        printf("Tuesday\n");
        break;
    case 3:
        printf("Wednesday\n");
        break;
    ...
}
```
:::
::::

Clearly, the Kotlin syntax is simpler and more compact. C requires that
each case be terminated with a `break` statement, otherwise execution will
'fall through' to the following case[^fall]. Kotlin doesn't require this
(although the code after `->` will need to be enclosed in braces if it is
more than just a single expression or statement).

However, `when` in Kotlin is also more powerful than `switch` in C. For one
thing, you are not limited to matching to a single value in each branch.
You can match the subject of the expression to a comma-separated list of
options:

```kotlin
when (day) {
    1, 3, 5 -> println("Take a walk")
    2, 4    -> println("Go to the gym")
    6, 7    -> println("Rest")
}
```

You can also match the subject of the expression to ranges. For example,
imagine you are writing a program to transform numerical exam marks, on a
0 to 100 integer scale, into one of three possible grades: `"Fail"` (mark
between 0 and 39), `"Pass"` (mark between 40 and 69), `"Distinction"` (mark
between 70 and 100). If the mark lies outside the 0 to 100 range, a grade
of `"?"` should be returned.

The required Kotlin code could be written like this:

```kotlin
val grade = when (mark) {
    in 0..39   -> "Fail"
    in 40..69  -> "Pass"
    in 70..100 -> "Distinction"
    else       -> "?"
}
```

Notice how clear and readable this is[^align].

As with `if` expressions, an `else` branch is needed here, because we are
assigning the result of the `when` expression to a variable and therefore
need to ensure that it always yields a value.

As a final example, consider the following code:

```kotlin
when {
    isPrime(x) -> println("x is a prime number")
    x % 2 != 0 -> println("x is odd")
    else       -> println("x is even")
}
```

This demonstrates that you don't always have to provide a subject whose value
is then matched against the provided options. In this more flexible form of
`when` expression, each branch performs its own independent test, using a
boolean expression. The first branch for which the expression evaluates to
`true` is the one that executes. If none of the expressions are `true` then
the `else` branch, if present, will execute.

## Task 4.3

A university module sets three assignments, each of which is awarded an
integer mark between 0 and 100. A grade for this module is determined from
the equally-weighted average of these three marks.

In the `tasks/task4_3` directory of your repository, write a Kotlin program
that determines a module grade, given three marks that have been provided on
the command line.

Your program should first check that three command line arguments have been
supplied and exit with a suitable error if this is not the case.

After determining the rounded average of the three marks, the program should
turn this into a grade of Distinction (70 to 100), Pass (40 to 69) or
Fail (0 to 39), using the `when` expression shown above. It should then print
both the rounded average mark and the grade.

Test your program carefully with different inputs to make sure that it
behaves correctly.

:::{tip}
Kotlin has a [handy extension function for rounding to an integer][round].
To access this function, add the following import statement to the top
of your `.kt` file:

```kotlin
import kotlin.math.roundToInt
```
:::


[^fall]: C programmers sometimes use this fall-through behaviour deliberately,
to write code that performs the same action for multiple matching cases.
This is often regarded as poor programming practice.

[^align]: There's no significance here to the alignment of the `->` in
each branch. The compiler doesn't care about this, but it does make the code
look a little neater for the human beings who have to read it!

[round]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.math/round-to-int.html
