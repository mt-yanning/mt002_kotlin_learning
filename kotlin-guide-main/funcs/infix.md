# Infix Functions

[Infix notation][inf] is a particular way of calling an extension function
or member function, in which the call operator (the dot) and the parentheses
are omitted. This can help to make the code more readable.

You've already seen an example of this:

```kotlin
for (n in 10 downTo 1) {
    ...
}
```

Here, `10 downTo 1` is infix notation for the call `10.downTo(1)`, which
invokes the `downTo()` extension function on the value 10, passing it an
argument of 1. This function call returns an integer progression representing
the sequence 10, 9, 8, ... 2, 1.

To create a function that supports infix notation, begin its definition with
the keyword `infix`. Note also that your function must

* Be an extension function or member function
* Have a single parameter, for which an argument is always supplied

## Example

Consider an extension function for strings, `longerThan()`. This function
returns `true` if the receiver contains more characters than the string
supplied as an argument:

```kotlin
fun String.longerThan(str: String) = this.length > str.length
```

This function could be used on two strings `a` and `b` like so:

```kotlin
if (a.longerThan(b)) {
    ...
}
```

To enable the use of infix notation here, all we need to do is add the
`infix` keyword to the start of the function definition:

```kotlin
infix fun String.longerThan(str: String) = this.length > str.length
```

Now the function call can be simplified to

```kotlin
if (a longerThan b) {
    ...
}
```

{button}`Try this out <https://pl.kotl.in/oLNwGRIw3>`

:::{note}
Obviously, this is not really necessary.

We could just compare the string lengths directly with
`a.length > b.length`.

The benefit here is the increase in clarity. `a longerThan b` is a bit
clearer and more readable than the explicit comparison of properties.

You shouldn't overuse infix functions or extension functions, but
sometimes it may be worth introducing them to make code more readable.
:::

## Task 5.5

Copy the two source files from `task5_1_1/src` to `task5_5/src`.

Edit the new copy of `Anagram.kt` in `task5_5/src` and modify the anagram
checking function so that it is an extension function of `String`, callable
using infix notation. Change the function's name to `anagramOf`.

Edit `Main.kt` and modify `main()` so that it uses this new version of the
function. If you've done everything correctly, it will be possible to test
whether one command line argument is an anagram of the other using very
readable code like this:

```kotlin
if (args[1] anagramOf args[0]) {
   println("${args[0]} and ${args[1]} are anagrams!")
}
```

Build the program and run it to check that it behaves as expected.


[inf]: https://kotlinlang.org/docs/functions.html#infix-notation
