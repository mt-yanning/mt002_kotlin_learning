---
short_title: "Elvis Operator"
---

# The Elvis Operator

## Basic Concept

A common requirement is to evaluate an expression and, if the result is null,
substitute a non-null 'default value' in its place. This can, of course, be
done using an explicit null check:

```kotlin
val result = when (text) {
    null -> "☹️"
    else -> text.reversed()
}
```

But Kotlin provides another simplifying tool for this, the
**elvis operator**, `?:`

**Note**: the technical term for this is the 'null coalescing operator', but
pretty much everyone calls it the elvis operator, because it looks like
Elvis Presley when rotated clockwise!

```{figure} elvis.jpg
:label: fig-elvis
:enumerator: 10.5
:alt: A photo of Elvis Presley with a rotated elvis operator superimposed on it
:width: 250px
:align: center

The elvis operator compared with Elvis Presley
```

The example above can be written much more concisely using a combination of
the safe call and elvis operators:

```kotlin
val result = text?.reversed() ?: "☹️"
```

The value of `result` will be determined by the expression to the left of
elvis if that expression evaluates to anything other than `null`; otherwise,
it will be determined by evaluating the expression to right of elvis.

The type of `text` in the example above is `String?`, but the type of
`result` is guaranteed to be `String`. This is because the elvis operator
replaces nulls with the value of the expression to its right, and that
expression is a `String` object in this case.

## Task 10.5

Open the `tasks/task10_5` directory of your repository in your preferred
code editing environment. This contains another version of the translation
program seen earlier.

Edit `Main.kt` and modify the `display()` function, replacing the `when`
expression with a single line of code that prints an uppercased translation
of the given word if such a translation exists, or the 'shrug' emoji if
it does not.

**Your modification should make use of both the safe call and elvis operators.**

Compile and run the program to make sure that it behaves as required.

## Back to Maps

When we looked at maps, we saw that you can access stored values using
`getOrElse()`. This returns the value associated with the given key if that
key is present, _or else_ the result of executing the given lambda
expression if it is not.

```kotlin
prices.getOrElse(item) { 15 }

prices.getOrElse(item) {
    throw NoSuchElementException("No price for $item")
}
```

You can achieve the same results more concisely, using `[]` and the
elvis operator:

```kotlin
prices[item] ?: 15

prices[item] ?: throw NoSuchElementException("No price for $item")
```

## Another Example

Here's a small program that prints a message one or more times on the screen.
The message can be specified as the first command line argument, and defaults
to "Hello!" if no arguments have been supplied. The message count can be
specified as a second command line argument, and defaults to 1 if no count
has been provided or the provided argument isn't a valid integer.

```{code} kotlin
:linenos:
:emphasize-lines: 2-3
fun main(args: Array<String>) {
    val message = args.getOrNull(0) ?: "Hello!"
    val count = args.getOrNull(1)?.toIntOrNull() ?: 1
    repeat(count) {
        println(message)
    }
}
```

Notice how parsing of the command line is done with **only two lines of code**.

Line 2 accesses the first command line argument using `getOrNull()` instead
of the usual `[]` operator. This function returns null instead of throwing an
exception when the array is empty, and the elvis operator replaces this
null with the default message of "Hello!"

Line 3 uses the same technique to access the second command line argument.
It then safe-calls the function `toIntOrNull()` to attempt conversion of that
argument into an integer. Use of the safe call operator ensures that the
conversion is only attempted if a second command line argument exists, and
the conversion function returns null if conversion fails. The elvis operator
replaces any null result with a 1, regardless of whether that null resulted
from a missing argument or a failed conversion.

The command line could have been parsed in several different ways, but those
other approaches would be more verbose than the very compact approach shown
here.
