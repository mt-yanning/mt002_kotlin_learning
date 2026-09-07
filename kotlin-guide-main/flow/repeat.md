# `repeat()`

Suppose you wish to execute a piece of code 5 times, and you don't need to
count out each iteration. In such a case, you don't have to use a `for` loop.
Instead, you can do this:

```kotlin
repeat(5) {
    println("Hello World!")
}
```

{button}`Run this code <https://pl.kotl.in/v36_CJXcg>`

The interesting thing about this example is that **it is a function call**
rather than a distinct looping structure like `for` or `while`.

The `repeat()` function expects to be given two arguments. The first is a
count of how many repetitions are required. The second argument is the code
to be executed repeatedly, typically provided in the form of a
**lambda expression**.

We will discuss lambda expressions in more detail [later][lam], but for now
you can think of them as 'anonymous functions'. In this example, the left and
right braces define the extent of the lambda expression. The lambda expression
is very simple; all it does is call the `println()` function.

What may be slightly confusing to you is that this piece of code appears
*outside* the parentheses, making it look like it isn't being passed in as
an argument of the function call!

In cases where a function expects to be supplied with a piece of code as the
final argument of a call to that function, and that code is written as a
lambda expression, Kotlin allows us to move the lambda expression outside
the parentheses, as we have done here.

:::{note}
This idea of a function argument being outside the parentheses of a
function call will look strange to you at first, but you will get used
to it.

It's an idiom that is used very frequently in Kotlin, and it does end up
making code look a little neater.
:::


[lam]: ../lambda/index.md
