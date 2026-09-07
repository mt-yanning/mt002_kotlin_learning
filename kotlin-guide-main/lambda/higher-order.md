# Writing Higher-Order Functions

A higher-order function is a function that operates on another function.
For example, it might accept a function as one of its parameters, or it might
return a function to the caller.

In either case, it will be necessary to specify the type of the function that
is passed in or returned, but how do we do this?

## Specifying Function Types

The general syntax for specifying a function type is

`(` *parameter-types* `)` `->` *return-type*

For example, consider this predicate:

```kotlin
fun isEnglishVowel(c: Char) = c.lowercase() in "aeiou"
```

The type of this function is

```kotlin
(Char) -> Boolean
```

As another example, consider the `anagrams()` function [discussed previously][prev].
Its type is

```kotlin
(String, String) -> Boolean
```

## Functions as Parameters

Now let's write a higher-order function that takes another function as its
sole parameter.

Imagine that we need a function `howMany()` to count the number of
characters in a string that satisfy a given predicate. (There is actually
no need for such a function, as strings support the `count()` operation
already, but let's pretend that this doesn't exist...)

We could implement `howMany()` like so:

```{code} kotlin
:linenos:
fun String.howMany(include: (Char) -> Boolean): Int {
    var count = 0
    for (character in this) {
        if (include(character)) {
            count += 1
        }
    }
    return count
}
```

Note the following points:

* Use of the `String.` prefix to the function name on line 1 means that this
  is an extension function of the `String` class. This will allow us to invoke
  it as if it were a method of that class.

* The sole parameter, `include`, has a type of `(Char) -> Boolean`. This means
  that when we call `howMany()`, we have to pass it a function that takes a
  single `Char` and returns a `Boolean`. (This could be a named function, or
  a lambda.)

* The `for` loop (lines 3&ndash;7) iterates over the characters of the receiver,
  i.e., the string on which `howMany()` has been invoked. We refer to the
  receiver using the special name `this`.

* The function passed to `howMany()` is applied to each character on line 4.

You could use `howMany()` in conjunction with the `isEnglishVowel()` predicate
to count the number of vowels in a string:

```kotlin
val text = readln()
val vowelCount = text.howMany(::isEnglishVowel)
println("$vowelCount vowels found")
```

However, note that it is more common to specify predicates as lambdas:

```kotlin
val vowelCount = text.howMany { it.lowercase() in "aeiou" }
```

As usual, we write the lambda in the most compact way possible. In this case,
we can omit the parentheses of the call, because the lambda is the only
parameter of `howMany()`.

## Task 8.5

Try using the `howMany()` function described above.

1. Open the `tasks/task8_5` directory of your repository, edit `Count.kt`
   and add the definition of `howMany()` shown above. Use `./kotlin build` to
   check that the code compiles before proceeding further.

1. Now edit `Main.kt` and modify `main()` so that it creates a string and
   then calls `howMany()` on it in various ways, using different lambdas as
   the argument.


[prev]: ../funcs/block.md#with-a-return-statement
