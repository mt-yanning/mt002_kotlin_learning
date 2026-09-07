# Scope Functions

Scope functions are a set of useful higher-order functions provided in the
Kotlin standard library. Each of them is typically used with a lambda that
defines a new scope for computation. Each scope function supplies a
**context object** to that scope in a particular way. Scope functions also
vary in terms of what they return to their caller.

:::{note}
Scope functions can feel confusing at first, and it can be hard to know which
one should be used in a given situation.

The Kotlin language documentation provides a [good overview][docs], including
a useful table that [summarizes scope function differences][diff].
:::

## `with()`

We encountered this when discussing [`System.out.printf()`][sop]. Invoking
this method multiple times results in repeated references to `System.out`, the
receiver of the method calls:

```kotlin
System.out.printf("Rectangle colour = (%d,%d,%d)\n", r, g, b)
System.out.printf("Perimeter = %.3f\n", 2.0 * (width + height))
System.out.printf("Area = %.3f\n", width * height)
```

These repeated references to the receiver are tedious to type and clutter the
source code. Using `with()` allows us to reference the receiver just once:

```kotlin
with(System.out) {
    printf("Rectangle colour = (%d,%d,%d)\n", r, g, b)
    printf("Perimeter = %.3f\n", 2.0 * (width + height))
    printf("Area = %.3f\n", width * height)
}
```

In this example, two arguments are passed to `with()`: the context object,
`System.out`, and a lambda expression. Within the lambda, the context object
is used as the receiver of any method calls that don't have an explicit
receiver themselves.

Note also that `with()` returns whatever is returned by the lambda. In the
example above, this will be the `PrintStream` object that `printf()` returns.

## `run()`

`run()` is quite similar to `with()`. The only real difference is that it is
an extension function rather than a top-level function.

The previous example rewritten to use `run()` would look like this:

```kotlin
System.out.run {
    printf("Rectangle colour = (%d,%d,%d)\n", r, g, b)
    printf("Perimeter = %.3f\n", 2.0 * (width + height))
    printf("Area = %.3f\n", width * height)
}
```

Because it is an extension function that can be invoked on anything, `run()`
can be inserted into call chains to perform a custom operation on an object.
You can't do that using `with()`.

For example, suppose you need to take a string, remove occurrences of "The"
from the start of that string, enclose the string in single quotes, and then
transform the characters to uppercase[^ill]. The first and last of these
operations are supported by existing functions of the standard library, but
the second operation is not.

Prefix removal can be accomplished with the `removePrefix()` function. This
returns a string, on which you can invoke `run()`. Within the lambda passed to
`run()`, that returned string becomes the implicit receiver of method calls.
It can also be referenced explicitly as `this`. Hence you can add the required
single quotes with the following lambda:

```kotlin
{ "'$this'" }
```

The result of evaluating this lambda is itself a string, on which you can
then invoke `uppercase()` to produce the final result.

The entire three-stage operation looks like this:

```kotlin
val newTitle = title
    .removePrefix("The ")   // returns string without prefix
    .run { "'$this'" }      // returns string enclosed in single quotes
    .uppercase()            // returns uppercased string
```

## `let()`

`let()` is similar to `run()`. Again it is an extension function, but it
passes the context object to the lambda _via the lambda's parameter list_,
instead of making it the implicit receiver of method calls within the lambda.
We typically refer to the context object as `it` inside the lambda, just as
you've seen in previous examples of lambda expressions.

The `printf()` example could be rewritten to use `let()` like so:

```kotlin
System.out.let {
    it.printf("Rectangle colour = (%d,%d,%d)\n", r, g, b)
    it.printf("Perimeter = %.3f\n", 2.0 * (width + height))
    it.printf("Area = %.3f\n", width * height)
}
```

The string transformation example would look like this if based on `let()`:

```kotlin
val newTitle = title
    .removePrefix("The ")
    .let { "'$it'" }
    .uppercase()
```

Note the use of `it` rather than `this` here.

:::{note}
You can see from these examples that different scope functions can often
be used to accomplish the same task.

The `printf()` example can be abbreviated using `with()`, `run()` or `let()`.
Of these, `with()` is the most natural and convenient option, whereas
`let()` is the least convenient.

The string transformation example can be implemented using `run()` or `let()`,
with equal ease. The `let()` implementation is perhaps the more natural of
the two. You can read it as

> "Take the title, then remove the specified prefix, then let it be enclosed
> in single quotes, then uppercase it."
:::

## `also()`

`also()` resembles `let()`, in that it accepts the context object via the
lambda's parameter list, but it differs from `let()` in one respect: whereas
`let()` returns the result of the lambda expression, `also()` returns the
context object instead. This makes it useful for performing an additional
operation 'on the side', as part of a call chain involving the context object.

For example, consider the following code:

```kotlin
val lines = filePath.readLines()
    .also { log("${it.size} lines loaded from $filePath") }
    .filter { it.length < 80 }
    .also { log("${it.size} lines are shorter than 80 chars") }
```

After this code executes, `lines` will contain all of lines from the file
that are shorter than 80 characters in length, but in addition to reading and
filtering the lines, the code performs two other operations:

1. After reading from the file, but before filtering the list, a function
   named `log()` is called, to report on the number of lines read from
   the file.

1. After filtering the list, `log()` is called a second time, to report on
   the number of lines that survived the filter.

In each case, `also()` returns the context object, which is a list of strings.
This permits the chaining of operations on the list. The lambdas execute on
the side, without affecting the list.

## `apply()`

`apply()` represents the context object as an implicit receiver, like `run()`,
and it returns this context object, like `also()`. This makes it useful as a
tool for combining object creation with object customization.

For example, the Java standard library includes a GUI framework called
Swing, which can be used easily from Kotlin. In Swing, we represent the main
window of an application as a `JFrame` object. Typically, we create the
object and then perform separate operations to make it visible on screen or
define what happens when the window closes:

```kotlin
val frame = JFrame("Window Title")
frame.isVisible = true
frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
```

These customizations can be bundled together using `with()` or `run()`, e.g.,

```kotlin
val frame = JFrame("Window Title")
frame.run {
    isVisible = true
    defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}
```

However, the nicest solution is to combine the customizations with object
creation:

```kotlin
val frame = JFrame("Window Title").apply {
    isVisible = true
    defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}
```


[^ill]: This example is taken from Chapter 11 of the book *Kotlin: An
Illustrated Guide*, by Dave Leeds.

[docs]: https://kotlinlang.org/docs/scope-functions.html
[diff]: https://kotlinlang.org/docs/scope-functions.html#function-selection
[sop]: ../io-intro/output.md#printf
