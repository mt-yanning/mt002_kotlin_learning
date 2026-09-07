# Block Body

This style of function definition begins with the keyword `fun`, followed by

1. The function's name
1. The parameter list, in parentheses (possibly empty)
1. A return type, if the function contains a `return` statement
1. The function's body, enclosed in braces

The parameter list is a comma-separated list of parameter declarations,
just as you have seen for functions in C and Python. A parameter declaration
must give both the parameter's name and its type, with a colon between the
two.

The return type, if present, is preceded by a colon.

## With a `return` Statement

Here is an example of a function with a block body and `return` statements:

```{code} kotlin
:linenos:
fun anagrams(first: String, second: String): Boolean {
    if (first.length != second.length) {
        return false
    }
    val firstChars = first.lowercase().toList().sorted()
    val secondChars = second.lowercase().toList().sorted()
    return firstChars == secondChars
}
```

{button}`Run this function <https://pl.kotl.in/PH-jQdS3f>`

This function requires two strings, represented by the parameters `first`
and `second`. It compares these two strings to see whether they are anagrams
of each other. Since this comparison is going to yield a `true` or `false`
result, the return type is declared explicitly as `Boolean`. Notice that
the return type comes between the parameter list and the function body, and
that it is immediately preceded by a colon.

The function has two `return` statements. The first of these allows it to
end computation early if the strings are of different lengths. Only if they
are of the same length will it proceed with a more detailed comparison. This
is done by converting each string to a sorted list of lowercase characters,
then comparing these two lists using the `==` operator. The boolean result of
the `==` expression is then returned.

## Without a `return` Statement

If you are writing a function that doesn't need to return a value to the
caller&mdash;e.g., because it will be printing the results of computation,
rather than returning those results for use elsewhere&mdash;then there is no
need to specify a return type.

As an example, suppose you wish to create a function that simulates rolling
any of the standard [polyhedral dice][dice] used in tabletop role-playing
games.

```{figure} dice.jpg
:label: fig-dice
:enumerator: 5.1
:alt: Six different polyhedral dice of different colours
:width: 350px
:align: center

Polyhedral dice used in TTRPGs
```

The function could be implemented like this:

```{code} kotlin
:linenos:
import kotlin.random.Random

fun rollDie(sides: Int) {
    if (sides in setOf(4, 6, 8, 10, 12, 20)) {
        println("Rolling a d$sides...")
        val result = Random.nextInt(1, sides + 1)
        println("You rolled $result")
    }
    else {
        println("Error: cannot have a $sides-sided die")
    }
}
```

{button}`Run this function <https://pl.kotl.in/g8vox_Z6a>`

Notice that there is nothing between the closing parenthesis of the
parameter list and the opening brace of the function body, i.e., no return
type has been specified.

This example includes one thing, sets, that we haven't discussed yet, but
it should nevertheless be fairly obvious what the code is doing. The caller
specifies the required die by providing the number of sides that the die has.
Rolls of d4, d6, d8, d10, d12 and d20 dice are supported; any other value
for number of sides results in an error message being printed.

For example, `rollDie(6)` simulates rolling a d6. This is achieved via a call
to Kotlin library function `Random.nextInt()`, with arguments of 1 and 7.
That call returns a pseudo-random integer in the range from 1 up to **but not
including** 7. This integer value is then printed; it isn't returned by the
`rollDie()` function.

Nevertheless, `rollDie()` still returns something: [a special value, named
`Unit`][unit].

**All Kotlin functions return something**&mdash;either an explicit value
provided by the function body, or `Unit`. This is somewhat analogous to
Python, in which functions that don't have a `return` statement nevertheless
still return the value `None`[^ret].

## Task 5.1.1

Open the `tasks/task5_1_1` directory of your repository in your preferred
editing environment. Two empty files of source code are provided in the `src`
subdirectory. Add to the file `Anagram.kt` the code for the `anagrams()`
function presented above.

Now edit `Main.kt`. Add to this file a `main()` function that

* Reads two words as command line arguments
* Compares those words using `anagrams()`
* Displays the result of the comparison to the user

Build the program, then run it a few times to check that it is working
correctly.

## Task 5.1.2

Open the `tasks/task5_1_2` directory in your preferred editing environment.
Add to the file `Die.kt` the code for the `rollDie()` function presented above.

Now edit `Main.kt`. Add to this file a `main()` function that calls `rollDie()`
a few times, with different values for the number of die sides. Build the
program, then run it a few times to check that the function is working
correctly.

:::{warning}
Remember to include the `import` statement for `Random` at the top
of the file:

```kotlin
import kotlin.random.Random
```

You will need to do this, or refer to `Random` by its fully-qualified
name within the function, in order for the code to compile.
:::

Now change `main()` so that it accepts the number of die sides as a command
line argument, calls `rollDie()` accordingly, then prints the resulting
value.

:::{hint .dropdown}
You will find `toInt()` useful here.

See the discussion of [converting strings to numeric types][conv] for
more help with this.
:::

Make sure that the program can handle failure to supply the required command
line argument. It should display a helpful message to the user if the
argument is missing.

Build the program, then run it a few times to check that it is working
as expected.


[^ret]: This is not quite the same thing as `void` in C, C++ or Java. Use
of `void` in those languages literally means 'nothing is returned'. But
having functions & methods always return something&mdash;even if it is just a
value that isn't useful&mdash;turns out to be a rather good idea. It makes
it much easier for Kotlin to support the functional and generic programming
styles, for example.

[dice]: https://en.wikipedia.org/wiki/Dice#Polyhedral_dice
[unit]: https://kotlinlang.org/docs/functions.html#unit-returning-functions
[conv]: ../io-intro/conversion.md
