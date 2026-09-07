# Function Arguments

## Named Arguments

Imagine a function `applyInterest()` that calculates compound interest
accrued on the balance of a savings account held by the customer of a bank.

Consider how we might use such a function:

```kotlin
val balance = applyInterest(1200.0, 1.7, 3)
```

This function call demonstrates the use of **positional arguments**.
Whatever is in first position in the argument list becomes the value of the
parameter that occurs first in the function's parameter list. Then the
argument in second position becauses the value of the second parameter,
and so on.

Unfortunately, when you use literal values as positional arguments, as in
the example above, it's not always obvious what the numbers passed in as
arguments actually represent. You might need to refer to documentation, or
see the parameter list of the function implementation itself, before you can
understand code like this fully.

Fortunately, Kotlin gives us a solution to this problem, in the form of
**named arguments**. These are specified using the function parameter name,
followed by the `=` symbol, followed by the argument value. You may remember
this syntax from Python, which also supports the use of named arguments.

Thus the function call above could have been written like this:

```kotlin
val balance = applyInterest(amount=1200.0, rate=1.7, years=3)
```

Now it is much easier to understand what is going on: the function applies
interest to an initial amount of 1200.0, for a period of 3 years, with an
interest rate of 1.7%.

A bonus of using named arguments is that the ordering of the arguments no
longer needs to match the order of parameters in the function's parameter
list. Thus the following would also work:

```kotlin
val balance = applyInterest(rate=1.7, amount=1200.0, years=3)
```

:::{caution}
It is possible to mix the positional and named argument styles in a
single function call, but there are some limitations. Consult the
[language docs on named arguments][named] if you need more information
on this.

Note also that if you are using the JVM platform, you cannot use named
arguments when calling methods that are defined in Java classes. You
have to use positional arguments in those cases.
:::

## Default Arguments

It is possible in Kotlin to provide **default arguments** as part of a
function definition. These defaults will be used if no argument is provided
when the function is called. Here's a small example:

```kotlin
fun greet(target: String = "World") {
    println("Hello $target!")
}
```

This function can be called in two ways:

```kotlin
greet()        // prints Hello World!
greet("Joe")   // prints Hello Joe!
```

Default and named arguments are often used together to provide a function
with 'options'. Such a function will typically be called with a positional
argument that is always required, optionally followed by named arguments
that customize its behaviour in various ways.

For example, we might have a function to process data in a CSV file. Its
default behaviour might be to read all rows of data and treat the commas in
each row as the column separators. But we might have some cases where a
different character is used to separate columns. And we might sometimes need
to skip a number of rows to get to the data we need.

We can handle these situations by giving the function a `separator` parameter
that defaults to `','` and a `skip` parameter that defaults to `0`:

```kotlin
fun processCsvFile(filename: String, skip: Int = 0, separator: Char = ',') {
    ...
}
```

Here are some examples of different ways in which this function might
be called:

```kotlin
processCsvFile("data.csv")
processCsvFile("data.csv", skip=10)
processCsvFile("data.csv", separator='|')
processCsvFile("data.csv", skip=5, separator=':')
```

## Task 5.3.1

For this task, you will modify your solution to Task 5.1.2 slightly.

Start by copying the two source files from `task5_1_2/src` to `task5_3_1/src`.

Next, edit the new copy of `Die.kt` in `task5_3_1/src` and modify the
implementation of `rollDie()` so that the number of die sides defaults to 6.

Now modify the `main()` function in `Main.kt` so that it calls `rollDie()`
without an argument when the number of die sides hasn't been specified on the
command line.

Build the program, then run it a few times to check that it it is working
correctly.

## Task 5.3.2

Open the `tasks/task5_3_2` directory of your repository in your preferred
code editing environment. Edit the file `Dice.kt` and add to it a function
named `rollDice()`.

The `rollDice()` function should work in a similar way to `rollDie()` from the
previous task, except that **it should be able to roll multiple dice (all of
the same type), printing the total score across all of those dice**.

Your function will therefore need *two* parameters: one representing the
number of sides and the other the number of dice. Use a default of 6 for the
number of sides and a default of 1 for the number of dice.

Now edit `Main.kt` and add to this file a `main()` function that calls
`rollDice()` in various ways, using both the positional and named argument
styles, and omitting arguments so that you see the default values being used.
Build and run the program to check that it behaves as expected.

Finally, modify `main()` so that it rolls dice according to a 'dice
specification' provided as the program's only command line argument. This dice
specification includes the number of dice to roll and the number of sides of
each of those dice, separated by `d`.  For example, running the program with

    ./kotlin run 3d8

should invoke `rollDice()` with values of 3 for number of dice and 8 for
number of sides.

:::{hint .dropdown}
Strings support an [`indexOf()`][idx] operation, which may be useful here.
:::

As before, run the program several times to check that it is working
correctly.


[named]: https://kotlinlang.org/docs/functions.html#named-arguments
[idx]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/index-of.html
