# Lambdas & Collections

Kotlin is extremely good at manipulating collections in a wide variety of
ways, with a minimum of code&mdash;largely because many operations can be
controlled using lambda expressions.

Consider, for example, the `first()` and `last()` functions that give us the
first and last items in an array or list. What if you wanted the first
even value in an array of integers, or the last non-blank string in a list
of strings?

To achieve this, all you need to do is supply `first()` or `last()` with
the appropriate predicate, written as a lambda. The returned value will be
the first or last item in the collection for which the predicate returns
`true`.

```kotlin
val numbers = intArrayOf(9, 3, 6, 2, 8, 5)
val firstEven = numbers.first { it % 2 == 0 }

val words = listOf("Hello", "Goodbye", "Ciao", "", "Hi", "")
val lastNonBlank = words.last { it.isNotBlank() }
```

{button}`Try this code <https://pl.kotl.in/Wxre20bKi>`

As another example, consider the `count()` operation. You can invoke this
with no arguments, to get the number of items in a collection[^size], but
you can also supply it with a predicate, and it will then count the number
of items for which that predicate is `true`.

## Exercises

For each of the following exercises, give your answer in the form of a
lambda expression, and make it as compact as possible.

```{exercise}
:label: ex-count-three
:enumerator: 8.3.1
Given a list of integers named `numbers`, how would you count occurrences
of the value 3?
```

```{exercise}
:label: ex-count-oddint
:enumerator: 8.3.2
Give a list of integers named `numbers`, how would you count the odd values?
```

## Numerical Operations

We saw [earlier][coll] that it is possible to perform numerical operations
such as finding the minimum or maximum, or computing a sum, for a collection
of numbers. There are variants of these operations that use lambdas.

For example, suppose you have a function that fetches measurements of
temperature from a series of weather stations. This dataset is returned to
you as a list of `Pair<String,Double>` objects. The `String` in a pair is
the name of the weather station and the `Double` is the temperature measured
at that station.

How would you find the lowest temperature in this dataset, and the station
that recorded that temperature?

You could write some code that iterates over the list of pairs, comparing
each of them with the pair that is your current 'best guess' at the
measurement having the lowest temperature. If the pair you are currently
looking at has a lower temperature than the current best guess, then it
becomes your new best guess:

```kotlin
var lowest = dataset[0]
for (measurement in dataset) {
    if (measurement.second < lowest.second) {
        lowest = measurement
    }
}
```

Alternatively, you could replace these six lines of code with a single line:

```kotlin
val lowest = dataset.minBy { it.second }
```

Here, `{ it.second }` acts as a **selector function**, picking out the value
to use when seeking a minimum. In this case, it simply picks out the second
value of each `Pair` object, which is the temperature. `minBy()` performs
all of the comparisons necessary to find the list element for which the
selector yields a minimum value, returning that element. The variable
`lowest` is therefore a `Pair` object, containing the lowest temperature in
the dataset and the name of the station that recorded that temperature[^minof].

## Task 8.3

The `tasks/task8_3` directory of your repository is a KT project containing
code for the analysis of weather station temperature data. Examine the files
in the `src` subdirectory.

Notice that `Funcs.kt` introduces `Record` as a **type alias**: a more
convenient way of referring to `Pair<String,Double>`. The `String` component
of this pair is a weather station's name and the `Double` component is the
temperature recorded at the station.

`Funcs.kt` also contains an empty definition of a function `fetchData()` that
is supposed to read temperature data from a CSV file and return the data in
the form of a list of `Record` objects. The first step of this task is to
complete the implementation of this function.

:::{hint .dropdown}
Refer back to the discussion of [reading lines][lin] for help with reading
data from the file.

Note that you can also use the [`split()`][spl] extension function to split a
string on a comma. This will give you a list containing the weather station
name and temperature, both in the form of strings.
:::

### Coldest & Warmest Weather Station

After completing `fetchData()`, edit `Main.kt` and add code to `main()` that
will read a temperature dataset and then find the weather stations that
recorded the lowest & highest temperatures.

Your program should accept a filename on the command line. It should use the
single line of code discussed above to find the record with the lowest
temperature, and a similar line to find the record with the highest. It should
then print the station names and recorded temperatures.

Examine the sample data file that we've provided, `temps.csv`. Then
run the program:

    ./kotlin run temps.csv

Check that the output is what you expect to see.

### Average Temperature

Add a line of code to `main()` that invokes the `averageTemp()` function, in
order to to compute the average temperature across the entire dataset. Then
add another line that prints the average temperature, to two decimal places.

Run the program again and check that it produces sensible output.

Finally, modify `main()` so that it performs the calculation of average
temperature **entirely within `main()`, with a single line of code, avoiding
use of the `averageTemp()` function entirely**.

:::{hint .dropdown}
You'll need to find a suitable operation on collections, and provide it with
the appropriate lambda expression.

The documentation for [aggregate operations][agg] on collections will be
useful here.
:::

## Sorting

We encountered the `sorted()` and `sortedDescending()` operations
[earlier][coll]. These produce sorted versions of a collection, based on the
'natural ordering' of the items in the collection. In practice, this means
that meaningful comparison operations must exist for those items. This is
automatically the case for things like numbers, characters and strings, but
it won't be true if the items in a collection are more complex than that.

Kotlin provides more than one way of handling this. The approach we'll
consider here involves the `sortedBy()` and `sortedByDescending()`
operations.

Let's consider the temperature dataset example once again. Suppose you
want to display the entire dataset, but sorted alphabetically by weather
station name. `dataset.sorted()` won't work, because each item in the list
is a `Pair`, and there is no natural ordering defined for `Pair` objects.
Instead, you can use `sortedBy()`, with a selector that picks out the
station name, i.e., the first item in each pair:

```kotlin
dataset.sortedBy { it.first }.forEach { println(it) }
```

The rule of thumb here is that the selector used with `sortedBy` must always
return a value for which meaningful comparisons are possible. The code above
works because the selector yields a string, and Kotlin knows how to order
strings.

For further information on sorting operations, see the Kotlin documentation
on [ordering of collections][ord].

## Filtering & Mapping

Filtering and mapping are fundamental operations in purely functional
programming languages, but they are so useful that they are also supported
in hybrid languages like Kotlin.

As we've seen already, filtering creates a new collection by applying a
predicate to an existing collection. That predicate is used to determine
which of the existing values are included in the new collection and which
are discarded:

```kotlin
val words = listOf("Hello", "Hi", "Goodbye", "Bye")
val shortWords = words.filter { it.length < 5 }
```

Mapping, on the other hand, applies a **mapping function** to each item
of an existing collection, transforming it in some way. The values
returned by the mapping function are the contents of the new collection.

As an example, suppose you have a list of numbers, and you wish to create a
new list containing the squares of those numbers. This can be done with a
`map()` operation like so:

```kotlin
val numbers = listOf(1, 4, 7, 2, 9, 3, 8)
val squares = numbers.map { it * it }
```

Filtering and mapping operations can easily be **chained**. For example, if
you want the squares of only the odd numbers, this can be done with

```kotlin
numbers.filter { it % 2 != 0 }.map { it * it }
```

{button}`Try this code <https://pl.kotl.in/ZKepGhvKf>`

```{exercise}
:label: ex-lines-filter-map
:enumerator: 8.3.3
You have a list of strings named `lines`. You need a new list, in which
blank strings have been removed and the remaining non-blank strings are in
lowercase. A blank string is a string that is either empty or that consists
solely of whitespace characters.

Write a single line of code that uses `filter` and then `map` to achieve this.

:::{hint .dropdown}
You'll need to use two extension functions of the `String` class for this.

See the [documentation for `String`][str] for more details. (Select the
'Members & Extensions' tab and look under the 'Functions' heading.)
:::
```


[^size]: Though it would be easier just to access the `size` property in
this case.

[^minof]: Kotlin also supports the `minOf()` operation. This uses a selector
in the same way as `minBy()`, but it returns the **actual minimum value**,
rather than the element for which the selector returned a minimum.

[coll]: ../collect/other.md#extension-functions
[lin]: ../flow/file-iter.md#reading-lines
[spl]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/split.html
[agg]: https://kotlinlang.org/docs/collection-aggregate.html
[ord]: https://kotlinlang.org/docs/collection-ordering.html
[str]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-string/
