# Iteration & Files

We noted in the section on [][files] that Kotlin provides a `Path` class and a
selection of extension functions for reading from and writing to the file
represented by a `Path` object. Those extension functions are convenient but
are not well suited to working with large files. Fortunately, more efficient
functions are available that operate in an iterative manner.

## Reading Lines

Given an instance of `Path` named `filePath`, representing a text file, you
could read file contents one line at a time using the `forEachLine()`
extension function:

```kotlin
filePath.forEachLine {
    println(it)
}
```

This operates in a similar way to the `repeat()` function discussed
[previously][prev]. The code in the braces is a lambda expression, specifying
what we want to do with each line obtained from the file. Within this lambda
expression, a line can be referenced as a `String` object named `it`. In this
example, we simply print the line.

Another option is the `useLines()` extension function, which could be
used like this:

```kotlin
filePath.useLines {
    for (line in it) {
        println(line)
    }
}
```

Like `forEachLine()`, this must be given a lambda expression. However, within
this lambda expression the variable `it` refers to a **sequence** that can
then be iterated over using a `for` loop. Although this approach is more
verbose than using `forEachLine()`, it is also more flexible. We will discuss
exactly what sequences are and why they are useful [later][seq].

## Writing Text Iteratively

If you need more control over writing than that provided by the `writeText()`,
`appendText()` and `writeLines()` extension functions discussed earlier, you
can obtain a `Writer` object from a `Path` object and interact with that.

For example, imagine you need to generate a file containing the integers from
1 up to some maximum value. You could first create a list of strings
corresponding to those numbers and then use `writeLines()` to write these
strings to the file, but a neater approach would be to do this:

```kotlin
filePath.writer().use {
    for (number in 1..maxValue) {
        it.write("$number\n")
    }
}
```

Invoking `writer()` on the `Path` object provides a `Writer` object capable
of writing to the file. We then immediately call `use()` on this `Writer`
object, passing to it a lambda expression containing the code that uses the
`Writer` object. **This ensures that the file will always be closed correctly,
regardless of whether an I/O error occurs.**

:::{danger} Important
Due to buffering of data, failing to close a file properly can lead to
missing output. The `use()` function allows you to easily avoid such
mishaps.
:::

Within the lambda expression passed to `use()`, we refer to the `Writer`
object as `it`. We can invoke the `write()` method on this object to write
a string to the file. This string should include the newline character, if we
want the strings to appear on separate lines of the file.

## Task 4.7

Open the `tasks/task4_7` directory of your repository, then edit `Main.kt`
and write a program that finds the longest line in a text file.

The path to this file should be supplied as a command line argument. The
program should print both the line number and the length of the longest line.

For example, given the file

```
xxx
xx
xxxxx
xxx
```

the program should print

```
Line 3 is the longest (length = 5)
```

Use one of the techniques discussed above to read from the file.


[files]: ../io-intro/files.md
[prev]: repeat.md
[seq]: ../lambda/sequences.md
