# Portfolio: Week 2

## Relevant Preparation

This work draws upon Sections 4 to 7 of the [Kotlin Programming Guide][guide],
and on your solution to the Week 1 portfolio task.

Subsections [4.8][sec48], [5.1][sec51], [5.2][sec52] and [7.1][sec71] are of
particular relevance, along with any programming tasks in those subsections.

## Instructions

There are *two* separate parts to this assignment - see the subsections below
for full details.

In each part, you will be writing small Kotlin functions. Tests are provided
for you to verify the correctness of your implementations. **All of the tests
must pass for you to achieve a grade of Pass on this assignment!**

You can run the tests for both parts with

    ./kotlin test

Note that this will only work once you have compiling code written for
both parts.

### Triangles

Edit `triangles/src/Triangles.kt`. Notice how it defines a data structure
named `Triangle`, which is simply an alias for `Triple<Double,Double,Double>`.
The purpose of this triple is to bundle together three values that represent
the lengths of a triangle's three sides.

Your task is to add functions named `isValidTriangle()` and `triangleArea()`
to this file. Each of these functions should have a single parameter, of
type `Triangle`. The return type of `isValidTriangle()` should be `Boolean`,
and the return type of `triangleArea()` should be `Double`.

Start by creating suitable **stubs** (i.e., empty implementations) for these
functions. Your stub for `isValidTriangle()` should return `false`, and your
stub for `triangleArea()` should return `0.0`.

Next, run the tests for the two functions by entering the following command,
from within the `week2` subdirectory:

    ./kotlin test -m triangles

Some of the tests will pass, but most should fail.

Your goal now is to replace the stubs with valid implementations of the two
functions, so that the tests all pass. If all of these tests pass, you
have completed this part of the assignment successfully.

Your implementation of `isValidTriangle()` should return `true` if the three
sides lengths in the provided `Triangle` object represent a valid triangle,
`false` if they do not. **The test for validity is that each side length
should be smaller than the sum of the other two side lengths.**

Your implementation of `triangleArea()` should reuse the code you wrote
for your Week 1 portfolio solution. **Note that your function should not print
anything on screen!** It should simply return the computed triangle area to
the caller.

### Redaction

[Redaction][red] is the process of removing sensitive information from a
document, typically by 'blanking out' that information in some way. Your task
here is to write a function that performs this operation on a string.

This function should be implemented in the file `redact/src/Redaction.kt`.
It should be named `redact()`, and it should have three parameters:

1. The input document, in the form of a `String` object
2. The text to be redacted from the document, also a `String` object
3. The character used in the redaction (a `Char`, defaulting to `'X'`)

The function should return the redacted document as a `String` object. It
should not print anything on screen.

For example, invoking the function with

```kotlin
redact("Hello World!", "World")
```

should return `"Hello XXXXX!"`, whereas invoking it with

```kotlin
redact("Hello World!", "World", '-')
```

should return `"Hello -----!"`.

As with the triangle geometry functions, the first step is to write the
function as a stub that simply returns a fixed string. This will allow you
to run the tests:

    ./kotlin test -m redact

These tests should compile and run but fail. At this point, you can proceed
with rewriting the function so that it behaves in the manner described above.

You will find it useful to consult the [API documentation for `String`][api].
Select the 'Members & Extensions' tab, then scroll down the list of extension
functions to see if there is anything there that can help...

When all of the tests pass, you will have completed this part of the
assignment successfully.

Optionally, you can perform some extra manual testing by running the small
program provided in `redact/src/Main.kt`. This expects three command line
arguments: the text to be redacted (enclosed in quotes if it contains spaces),
the filename of the input document, and a filename for the output document.
You can run the program like this:

    ./kotlin run -m redact "Hello World" document.txt redacted.txt


[guide]: https://comp2850.github.io/kotlin-guide/
[sec48]: https://comp2850.github.io/kotlin-guide/flow/str-iter
[sec51]: https://comp2850.github.io/kotlin-guide/funcs/block
[sec52]: https://comp2850.github.io/kotlin-guide/funcs/expression
[sec71]: https://comp2850.github.io/kotlin-guide/collect/pair-trip
[red]: https://en.wikipedia.org/wiki/Redaction
[api]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/
