# Catching Exceptions

You can regain control over a program in which an exception has been thrown
by **catching** that exception. Kotlin provides direct and indirect ways
of doing this.

## `try`...`catch`

The direct way of catching exceptions involves putting code that might
cause an exception inside a `try` block. This block is immediately followed
by one or more `catch` blocks:

```kotlin
try {
    // code that might cause an exception
}
catch (error: MyException) {
    // specific exception handling code
}
catch (error: Exception) {
    // more general exception handling code
}
```

Each `catch` block specifies the type of exception that it will intercept.
The code inside the block specifies your response to the matching exception.
The `catch` blocks are processed in the order in which they appear, with the
first matching block being executed and all subsequent blocks being ignored
(even if they would also match).

If no match is found, the exception will propagate further up the call stack,
eventually halting the program if there is no matching `catch` block found
in `main()`.

An important point to understand is that **a `catch` block written to
intercept an instance of a particular class of exception will also intercept
instances of subclasses**.

So, referring to @fig-except, a `catch` block that intercepts
`IllegalArgumentException` will also intercept `NumberFormatException`, and
a block that intercepts `Exception` will catch **all types of exception**.

### Task 9.4

Let's see how this works in practice.

1. Open the `tasks/task9_4` directory of your repository in your preferred
   editing environment. This is a Gradle project containing a small program
   that intercepts exceptions. Locate `Main.kt` and study the code of the
   program carefully.

1. Compile and run the program with

       ./gradlew run

   Try running it with a variety of inputs to see how it behaves. Use your
   observations to help you do the following exercises.

```{exercise}
:label: ex-catch-match
:enumerator: 9.4.1
What is printed when

1. {kbd}`Ctrl` + {kbd}`D` is pressed when prompted to enter a number
1. `0` is entered as the denominator
1. `three` is entered as the numerator

In each case, explain why the output is seen.
```

```{exercise}
:label: ex-catch-removal
:enumerator: 9.4.2
Which three lines could you remove from the program without changing its
behaviour? Why?
```

```{exercise}
:label: ex-catch-order
:enumerator: 9.4.3
What effect would swapping the order of the last two catch blocks have on
program behaviour?
```

## Exception Handling Tips

**1. Don't micromanage exceptions.**

Whilst it might seem tempting to give each potentially exception-causing
function call its own enclosing `try` and `catch`, this will quickly make
your code cluttered and hard to read.

**2. Organize `catch` blocks so that more specialized exception types are
caught first.**

Remember that a `catch` block that targets `Exception` will catch all
kinds of exception. If you have multiple `catch` blocks, it should be the
last one.

**3. Don't throw an exception yourself and then immediately catch it.**

Remember: throwing an exception at one point in your code signals that
you don't know how to deal with an error (or that you don't want to make
assumptions about how to deal with it). Catching the exception should
happen at a point where you are able to take on the responsibility for
handling the error.

The key thing to recognize is that _these two points should be at different
locations in your code_. If you find yourself tempted to signal an error and
handle it within the same context, then there really isn't any point in
throwing the exception in the first place!

**4. Avoid swallowing exceptions without doing anything about them.**

The compiler will allow you to write an empty `catch` block if you really
want to, but if you intercept all exceptions in this way, you will never
get any feedback about runtime errors in your code! In most cases, you should
do _something_. Note that this something doesn't have to be particularly
intrusive; you could just log the error to a file, for example.

## `finally`

You can optionally follow your `catch` blocks with a `finally` block. The
code in this block will always execute, regardless of whether an exception
has occurred in the `try` block. This can be useful when working with
files or network connections, as you can then ensure that these have been
properly closed.

```kotlin
try {
    // code that might cause an exception
}
catch (error: MyException) {
    // code that runs if MyException occurs
}
finally {
    // code that always runs, with or without an exception
}
```

It's also possible to pair `try` directly with `finally`, i.e., have no
`catch` blocks at all. The `try`...`finally` structure is useful if you
don't want to catch any exceptions but want to guarantee the clean-up of
resources[^use].

:::{caution}
You can never have a `try` block on its own. It must always be followed
immediately by a `catch` block, or a `finally` block.
:::

## `runCatching()`

The [`runCatching()`][run] function runs the given block of code, catching
any exceptions that occur within that block. It returns a [`Result`][res]
object that encapsulates any value returned from that block of code, and
details of the caught exception (if one was thrown).

You can access the `isSuccess` or `isFailure` boolean properties to see
whether the block of code ran successfully, or failed with an exception.

You can retrieve the value returned from the block of code[^unit] using the
method `getOrNull()`. This value will be `null` if execution of the block
failed and an exception was caught.

You can retrieve the caught exception using the method `exceptionOrNull()`.
This will yield `null` if execution succeeded and no exception was caught.

Here's an example of how you might use `runCatching()`:

```kotlin
val result = runCatching {
    // code that could produce exceptions
}

if (result.isFailure) {
    println("Task failed with an exception:")
    println(result.exceptionOrNull())
}
```

In this example, the message associated with the caught exception is
printed, but you could do something more sophisticated here&mdash;e.g.,
retrieve the full stack trace and write it to a log file.

:::{note}
`runCatching()` doesn't do anything you couldn't do already using `try` and
`catch` blocks. The main benefit it offers is improved readability.
:::


[^use]: It should be noted that Kotlin has some neater ways of ensuring
that files or other open resources are always closed properly&mdash;e.g.,
the `use()` extension function.

[^unit]: This value could be `Unit`&mdash;meaning that execution succeeded,
but didn't produce a value that can be used in other computation.

[run]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/run-catching.html
[res]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-result/
