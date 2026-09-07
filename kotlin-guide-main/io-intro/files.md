# File Handling

Working with files in Kotlin can feel complicated, because there is often
more than one way to accomplish your goals. When running on the JVM, you have
direct access to two different Java APIs (`java.io` and `java.nio`), and
Kotlin layers its own API on top of this underlying code. To keep things
simple, we will focus on the Kotlin API here.

## `Path` Objects

Kotlin's functions for working with filesystem paths rely on Java's `Path`
class. The `kotlin.io.path` package provides a special function, also
named `Path`, for creating an instance of this class:

```kotlin
val path = Path("test.txt")
```

The package also provides various [extension functions][ext] that add simple
file reading and file writing capabilities to `Path`. We discuss how to
invoke these functions on `Path` objects below.

To use `Path` and its extension functions, you will need to add one or more
`import` statements to the top of a source file. The laziest approach is
to do a single 'starred import':

```kotlin
import kotlin.io.path.*
```

Alternatively, you can be more explicit and have separate `import` statements
for `Path` and each extension function that your program uses[^imp], e.g.,

```kotlin
import kotlin.io.path.Path
import kotlin.io.path.readText
```

## Reading From Text Files

You can read the entire contents of a text file by creating a `Path` object
and invoking the `readText()` extension function on it:

```kotlin
val filePath = Path("test.txt")
val fileContents = filePath.readText()
```

Here, `fileContents` is a `String` object containing the text read from
the file.

If you need that text to be broken up into separate lines, you could invoke
the `lines()` function on the string `fileContents`, but a more direct
approach is to do

```kotlin
val fileLines = filePath.readLines()
```

Here, `fileLines` is an object of type `List<String>`, i.e., a list of
strings, with each string representing a single line from the file. We cover
lists extensively [later][lst].

:::{danger} Important
**These approaches read the entire contents of the file into memory.**

This can be wasteful and inefficient when working with large files. Often,
processing a text file a line at a time is a better option. We will consider
how to do this in [][iter], after we have looked at iteration using `for` loops.
:::

## Writing To Text Files

You can write a string of characters to a text file using the `writeText()`
extension function:

```kotlin
val filePath = Path("test.txt")
filePath.writeText(content)
```

By default, this will overwrite the current contents of `test.txt`, should
that file already exist in the filesystem.  If you wish to preserve the
current contents and append the new text to those contents, use `appendText()`
instead of `writeText()`.

If you have a list of strings representing the lines you want to appear in
the file, you can write these to a file using the `writeLines()` extension
function:

```kotlin
filePath.writeLines(lines)
```

An appropriate line separator will be output when each of the strings is
written, so there is no need to add this yourself to the ends of the strings
when you create the list.

Like `writeText()`, this will overwrite file contents. If you wish to preserve
existing lines and append new lines to the file, use `appendLines()` instead
of `writeLines()`.

## Binary Files

If a file consists of [binary data][bin] rather than text data, you can read
its contents by invoking the `readBytes()` function on a `Path` object. This
will return a `ByteArray` object containing all the bytes of the file.

You can write binary data by invoking the `writeBytes()` function on a
`Path` object. This needs to be supplied with a `ByteArray` object containing
the bytes to be written. You can append to existing binary data rather than
overwriting it by using `appendBytes()` instead of `writeBytes()`.

**The efficiency considerations that apply to text files also apply to binary
files.** It will often be more appropriate to read small blocks of binary data
iteratively rather than attempting to read the whole file into memory in
one go.

## Task 3.5

Open `tasks/task3_5` in your preferred code editing environment. In `Main.kt`,
write a program that uses `Path` and `writeText()` to write some text to a
file named `test.txt`.

Run the program and examine the contents of the file to verify that the text
is written correctly.

Next, add a line that uses `writeText()` to write a different piece of text
to `test.txt`. Run the program and check that the file contains only this
second piece of text.

Now change the second function call from `writeText()` to `appendText()`.
Run the program and check that the file now contains both pieces of text.

Finally, add two more lines of code to `main()`. The first should use
`readText()` to read the contents of `test.txt`; the second should print the
string returned by this function call. Run the program and check that the
printed string matches the file's contents.


[^imp]: Explicit imports are more inconvenient but also safer. If you use
starred imports with multiple packages, there is a risk of name clashes
between things defined in those different packages.

[ext]: ../funcs/extension.md
[lst]: ../collect/lists.md
[iter]: ../flow/file-iter.md
[bin]: https://en.wikipedia.org/wiki/Binary_file
