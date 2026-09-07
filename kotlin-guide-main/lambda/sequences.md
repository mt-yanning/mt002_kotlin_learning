# Sequences

In the [previous section][prev], we saw how filtering and mapping operations
can be chained together:

```kotlin
numbers.filter { it % 2 != 0 }.map { it * it }
```

In the example above, the initial filtering operation generates a temporary
collection, which is then transformed by the subsequent mapping operation.
After that point, the temporary collection is no longer required, and the
memory it uses it will be reclaimed.

This isn't much of a problem for the small examples that we've considered so
far, but there could be significant performance implications to the creation
of these intermediate collections if we are working with longer chains of
operations, or if we are working with very large amounts of data (e.g.,
lists with millions of elements).

To cater for these scenarios, Kotlin provides the `Sequence` type[^gen].

A `Sequence` object represents a sequence of values, over which we can
iterate. A `Sequence` object knows how to retrieve the next value in this
sequence of values, **without actually having to store all of the values
from the sequence itself**. This means that a potentially infinite series of
values can be represented as a `Sequence`.

Because sequences are iterable, operations such as `filter()` and `map()`
can be performed on sequences just as easily as on actual collections.
In this way, we can avoid creation of intermediate collections when we
chain operations together.

:::{danger} Important
 The functional programming approach involves using `filter`, `map` and
 a few other functions as small, simple 'building blocks' that can be
 combined in order to implement very complex manipulations of data in a
 collection.

 This approach is much more powerful than one in which we have to write
 a large, complicated function every time we are faced with a data
 manipulation task. Complex manipulations are easier to understand when
 expressed as combinations of simple operations. Using sequences ensures
 that we can benefit from this expressiveness without sacrificing
 performance.

 See the Kotlin language documentation for more information on
 [how to use sequences][seq].
:::

## Task 8.4.1

Open the `tasks/task8_4_1` directory of your repository and edit `Main.kt`.
You should see the following code:

```{code} kotlin
:linenos:
:emphasize-lines: 4
fun main() {
    val numbers = listOf(1, 4, 7, 2, 9, 3, 8)

    val result = numbers

    println(result)
}
```

Run the program. You should see the contents of `numbers` displayed.

Now make the following changes, one after the other. After making each change,
rerun the program to see how output is affected:

1. Add `.asSequence()` to the end of the highlighted line in `main()`,
   after `numbers`.

1. Add `.filter { it % 2 != 0 }` to the end of that line.

1. Add `.map { it * it }` to the end of the line.

You will observe that printing the `Sequence` object returned by
`asSequence()` did not display any of the numbers in the list. Neither did
printing the result of the filtering and mapping operations added in the
subsequent steps; all they did was to extend the sequence with additional
stages of processing. To retrieve usable values from a sequence, you must
add a **terminal operation** to it.

Try this now. Add `.toList()` to the end of the line, then run the program
again. This time, you should see a list of the squares of the odd integers
from `numbers`.

## Task 8.4.2

The `tasks/task8_4_2` directory of your repository is a KT project that
explores the performance impact of using sequences. Open `Benchmark.kt` in
the `src` subdirectory of the project and study the code in this file.

The operation performed by this program involves reading lines of text from
a file, filtering out the blank lines, then filtering out all the lines
containing at least 10 characters, then converting the lines that remain into
all lowercase.

This operation is implemented in two different ways, with and without the use
of `Sequence`. The execution time of each implementation is measured and
displayed.

Also in `tasks/task8_4_2` is a large text file, containing the entire text of
Leo Tolstoy's notoriously long novel *War And Peace*. The file is over 3 MB
in size and contains over 66,000 lines. Take a moment to examine its contents.

Now run the program with

    ./kotlin run war-and-peace.txt

```{exercise}
:label: ex-seq-lists
:enumerator: 8.4.1
How many lists are created by these two versions of the operation?
```

```{exercise}
:label: ex-seq-perf
:enumerator: 8.4.2
Which version of the operation executes faster?
```


[^gen]: The equivalent feature in Python is the [generator][gen].

[prev]: collections.md#filtering-mapping
[seq]: https://kotlinlang.org/docs/sequences.html
[gen]: https://docs.python.org/3/tutorial/classes.html#generators
