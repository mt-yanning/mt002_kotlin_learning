# Error Handling Task

Open the `tasks/task9_6` directory of your repository. This is a KT project
for a program to compute variance in a numeric dataset. Source code files
for this program are in the `src` subdirectory.

## `variance()`

Edit `Stats.kt` and add the `variance()` function discussed earlier. Your
implementation should test whether the provided list has enough values using
`require()`.

To check that you've implemented the function correctly, run the unit tests
for the project, e.g., with

    ./kotlin test

Make sure the code compiles and the tests all pass before proceeding
any further.

:::{note}
The tests are in `test/VarianceTest.kt`. Take a moment to study this code.

(This is a 'sneak peak' at some of the [][asrt] that are possible in Kotest.)
:::

## `readData()`

Edit `Data.kt` and add a function that will read numeric data from a file
and return it as a list of `Double` values. Use a similar implementation to
that of [Task 7.7.1][task], i.e.,

```kotlin
import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun readData(filename: String) = buildList {
   Path(filename).forEachLine {
       add(it.toDouble())
   }
}
```

Check that the new code compiles with

    ./kotlin build

## Main Program

Now open `Main.kt`. In this file, write a `main()` function that expects a
filename to be supplied on the command line. Your program should use the
`readData()` and `variance()` functions to read a dataset from that file
and then compute its variance. Your program should display both the size of
the dataset and the variance, formatting the latter to five decimal places.

Use `try` and `catch` blocks or `runCatching()` to intercept exceptions
that might occur. Display some information about the caught exception
and then terminate the program with a non-zero status code.

Once again, check that the code compiles with

    ./kotlin build

Try running the program without a command line argument, and then with the
name of a file that doesn't exist:

    ./kotlin run
    ./kotlin run file-that-does-not-exist

Both of these should result in errors. The second of them should be dealt
with by your exception handling code.

Finally, create the following test files:

* An empty file
* A file containing a single number
* A valid file, containing only numbers (one per line)
* A file that mixes numeric & non-numeric data

Run the program on each of these, to check that it behaves as expected. Any
exceptions should be handled; you shouldn't be seeing the stack trace of
an unintercepted exception in any of these cases.


[asrt]: ../testing2/assertions.md
[task]: ../collect/tasks.md#task-7-7-1
