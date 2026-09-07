# Error Handling Approaches

## Example 1

Consider a function that writes lines of text to a file. The function is
supposed to prevent the overwriting of a file that already exists:

```{code} kotlin
:linenos:
import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.system.exitProcess

fun writeToFile(lines: List<String>, filePath: Path) {
    if (filePath.exists()) {
        println("Error: $filePath already exists!")
        exitProcess(1)
    }

    // rest of function not shown
}
```

**What do you think about this approach to error handling? Is it generally
applicable? What drawbacks might there be?**

If you're in class, discuss these questions with the people sitting near you.
If you are on your own, think carefully about these questions yourself. When
you've finished discussing or thinking about them, click on the 'Discussion'
heading below to reveal our take on these issues.

:::{note .dropdown} Discussion
On occasion this approach might be acceptable, but it isn't generally
applicable to all situations, because it makes specific assumptions about
how the error is handled.

The most obvious issue is the assumption that the program should be
terminated immediately (line 8). In some situations, it may be possible
to ask the user to specify a different filename, avoiding the need to
terminate the program.

A more subtle issue is the assumption that there is somewhere for the
error message to appear! When you run a program from a terminal, you will
see output appear in that terminal, but where does the message printed
by line 7 go when code like this is executed by a server that was not
started manually from a terminal?
:::

## Example 2

Now consider this function, which computes the [variance][var] for a
numerical dataset:

```{code} kotlin
:linenos:
fun variance(dataset: List<Double>): Double {
    if (dataset.size < 2) {
        return -1.0
    }

    val mean = dataset.average()
    val sumSquaredDev = dataset.map { it - mean }.sumOf { it * it }
    return sumSquaredDev / (dataset.size - 1)
}
```

Notice how the function returns prematurely on line 3 when there aren't
enough data points to compute variance. The special value of `-1.0` is used
to signal this.

**Is this better / more flexible than the approach followed in the previous
example? What drawbacks might there be, in general?**

As before, discuss these questions with the people sitting near you, or think
about them yourself. After discussing / thinking about them, click on the
'Discussion' heading for our take.

:::{note .dropdown} Discussion
This approach improves on the previous example in some respects, because
it makes no assumptions about how the error should be handled, but it
has problems of its own.

One issue is that it won't always be possible to find a special value that
signals an error. In this case, returning a negative value works because
variance can never be negative, but there will be some functions for which
every representable return value is potentially a genuine result
of computation.

One option here would be to return a `Pair`, containing the result of
computation plus an error code that indicates whether that result is valid
or not, but that feels clumsy. Alternatively, we could return the result of
computation or the special value `null`. You'll learn more about this idea
when we discuss [null safety][null], but for now we can simply note that
returning `null` doesn't provide any information about why computation failed.

A general problem with the 'returning a value' approach is that the caller
of a function can simply ignore any kind of error code that the function
returns.
:::

[var]: https://en.wikipedia.org/wiki/Variance
[null]: ../nulls/index.md
