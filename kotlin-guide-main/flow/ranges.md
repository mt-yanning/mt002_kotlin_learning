# Range Checking

If you wish to check that a variable's value is in range, you could do it
like this:

```kotlin
if (number >= 0 && number <= 100) {
    // use number
}
else {
    // do something else
}
```

An alternative approach in Kotlin is to define a **range** and check for
membership of that. The above example could be rewritten to use a range like
this:

```kotlin
if (number in 0..100) {
    // use number
}
else {
    // do something else
}
```

Here, `0..100` defines a **closed integer range**, with 0 and 100 included
as endpoints.

Ranges are not limited to integers. You can also define floating point
ranges, ranges of characters, even ranges of strings:

```kotlin
0.0..100.0
'a'..'z'
"aaa".."zzz"
```

To understand that last example, think of an alphabetical ordering of
strings, much like words in an English dictionary. Testing whether a string
is in the range `"aaa".."zzz"` will yield a result of `false` if that string
would come before `aaa` or after `zzz` in such a dictionary; otherwise,
it will yield a result of `true`.

In each of the examples above, literals have been used as the endpoints, but
keep in mind that you are also allowed to use variables as endpoints of a
range.

````{exercise}
:label: ex-int-range
:enumerator: 4.2
What is printed by the following Kotlin code?

```kotlin
val x = 5
println(x in 0..5)
println(x in 0..<5)
```

:::{hint .dropdown}
Closed ranges are not the only type of range available in Kotlin...
:::
````

## Task 4.2

The `tasks/task4_2` directory of your repository contains a pre-configured
Gradle project. Open this directory in your preferred code editing environment
and edit `Main.kt`. In this file, write a program to simulate ordering
a pizza.

* Your program should present a menu to the user consisting of four pizza
  options. It should label these options `a`, `b`, `c` and `d`.

* Your program should use `readln().lowercase()` to read the user's input
  and convert it to a lowercase string.

* Your program should use an `if` expression to check that the length of the
  input string is 1, and that the first (and only) character of the string
  is one of the four available pizza options. **It should use a `Char` range
  for the latter.**

* If the input is valid, your program should print "Order accepted";
  otherwise, it should print "Invalid choice!"

You can run the program from the command line with

    ./gradlew run

Here's an example of program output and user input:

    PIZZA MENU

    (a) Margherita
    (b) Quattro Stagioni
    (c) Seafood
    (d) Hawaiian

    Choose your pizza (a-d): b
    Order accepted
