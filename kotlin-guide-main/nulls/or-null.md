# ...`OrNull()`

We saw in [a previous example][prev] that Kotlin provides functions `getOrNull()`
and `toIntOrNull()` that return null instead of throwing an exception.

There are many other such functions available in Kotlin. For example, there
are ...`OrNull()` versions of `toLong()`, `toFloat()`, `toDouble()` and
all of the other conversion functions. Each of them returns null if parsing
of the string fails, rather than throwing an exception.

There is also a `readlnOrNull()` function to go alongside `readln()`. It does
the same things as `readln()`, except that it returns null if the input
stream is closed rather than throwing an exception.

When we considered collection types, we saw that there are extension
functions like `min()`, `max()`, `minBy()` and `maxBy()`, for finding
minimum and maximum values. Each of these throws an exception if invoked
on an empty collection, but there is also an ...`OrNull()` version of
each of them that returns null for empty collections.

You can see a pattern emerging here.

There are functions that each throw an exception if an operation can't be
carried out successfully; alongside those functions are counterparts with
`OrNull` appended to their names, each of which returns null if the operation
cannot be carried out.

:::{note}
Having two distinct options for handling failed operations in our code gives
us flexibility.

Using the ...`OrNull()` functions in conjunction with the safe call and elvis
operators is often the most convenient approach to use when there is a clear
default result to fall back on when computation fails.

In other situations, allowing exceptions to happen and then writing
appropriate exception handling code will make more sense.
:::


[prev]: elvis.md#another-example
