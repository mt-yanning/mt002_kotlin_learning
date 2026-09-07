# Exercise Solutions

````{solution} ex-lambda-square
:label: sol-lambda-square
```kotlin
{ x: Double -> x * x }
```

Note: we will consider later how this can be written more compactly.
````

````{solution} ex-lambda-lessthan
:label: sol-lambda-lessthan
```kotlin
{ a: Int, b: Int -> a < b }
```
````

````{solution} ex-count-three
:label: sol-count-three
To count occurrences of a specific value, the predicate needs to test
whether its parameter is equal to that value. If the value is 3, then the
predicate required is `{ it == 3 }`, thus the required code is

```kotlin
numbers.count { it == 3 }
```

Note: implicit parameter `it` _must_ be used here because the exercise asks
for the most compact lambda expression possible!
````

````{solution} ex-count-oddint
:label: sol-count-oddint
To count odd integers, the predicate needs to test whether its parameter is
odd, and the obvious way of doing that is to check whether the remainder
after dividing by 2 is non-zero. Thus, the required code is

```kotlin
numbers.count { it % 2 != 0 }
```
````

````{solution} ex-lines-filter-map
:label: sol-lines-filter-map
The predicate needed for the filtering operation is `isNotBlank()`. The
transformer function needed for the mapping operation is `lowercase()`.
Thus the combined operation looks like this:

```kotlin
lines.filter { it.isNotBlank() }.map { it.lowercase() }
```
````

```{solution} ex-seq-lists
:label: sol-seq-lists
The 'without sequences' version creates a list with `readLines()`, then
creates additional lists for each use of `filter()` and the final use of
`map()`&mdash;a total of **four lists**.

In the 'with sequences' version **one list** is created, by invocation
of the terminal operation `toList()`. Operations prior to this are part of
a 'pipeline' that can process lines retrieved from the file, one at a time,
without the need to create any intermediate lists.
```

```{solution} ex-seq-perf
:label: sol-seq-perf
You should have observed that the sequence-based version of the task is
noticeably faster.

The overhead of creating intermediate lists means that the version without
sequences should execute more slowly.
```
