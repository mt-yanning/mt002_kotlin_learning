# Throwing Exceptions

To signal an error from your own code, you must create an exception object
and then **throw** it. Kotlin allows you do to this directly, via the `throw`
statement, or indirectly, using one of its [precondition functions][pre].

## Direct Use of `throw`

Consider the `variance()` function discussed earlier. Instead of returning
`-1.0` to signal an error, you could make the changes highlighted below:

```{code} kotlin
:linenos:
:emphasize-lines: 2-4
fun variance(dataset: List<Double>): Double {
    if (dataset.size < 2) {
        throw IllegalArgumentException("not enough data")
    }

    val mean = dataset.average()
    val sumSquaredDev = dataset.map { it - mean }.sumOf { it * it }
    return sumSquaredDev / (dataset.size - 1)
}
```

If this new version of `variance()` is called on an empty list, or a list
containing only a single number, an `IllegalArgumentException` will be
thrown, containing the message "not enough data".

:::{danger} Important
Remember that exceptions change flow of control completely. **Lines 6&ndash;8
of this function will not execute if the exception is thrown.**

Remember that flow of control will be affected in the code that called
`variance()`, too. Statements appearing after the function call will not
execute, unless the exception is intercepted successfully before those
statements occur.
:::

<!-- TODO: task or exercise here? -->

## Precondition Functions

You can also throw certain exceptions using the functions `require()`,
`check()` and `error()`. These typically require a little less code than
throwing the exception directly yourself, but their main benefit is that
they can make the intention of your code a bit clearer.

### `require()`

`require()` will throw `IllegalArgumentException` if the boolean expression
specified as its first argument evaluates to `false`. The message associated
with the exception is provided by an optional second argument, which is
a lambda expression.

For example, the error handling for `variance()` could be written like
this:

```{code} kotlin
:linenos:
:emphasize-lines: 2
fun variance(dataset: List<Double>): Double {
    require(dataset.size > 1) { "not enough data" }
    ...
}
```

Notice how concise and clear this is.

### `check()`

`check()` will throw an `IllegalStateException` if the boolean expression
supplied as its first argument evaluates to `false`. The message associated
with the exception is provided by an optional second argument which is,
again, a lambda expression.

:::{note}
`require()` is a neat way of verifying the prerequisites needed for a
successful function call, before any further computation is attempted,
whereas `check()` is intended for use later on in a function's code, for
the purpose of checking whether computation is proceeding as expected.

Using `require()` and `check()` feels a bit like using `assert` statements
in C or Python, but there are some important differences to keep in mind,
as we discuss [shortly][ass].
:::

### `error()`

`error()` is the simplest of the three functions. It takes an error message
as its sole argument and throws an `IllegalStateException` with that message.

You can use `error()` combined with an `if` or `when` expression as an
alternative to `check()`. For example, these two pieces of code are
functionally identical:

```kotlin
check(result >= 0.0) { "Something's wrong" }

if (result < 0.0) {
    error("Something's wrong")
}
```

[pre]: https://kotlinlang.org/docs/exceptions.html#throw-exceptions-with-precondition-functions
[ass]: assert.md
