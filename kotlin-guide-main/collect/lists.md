# Lists

Lists are like arrays, but they are more flexible, because the sequence of
values can grow or shrink after it has been created. They work in a similar
way to Python lists.

## List Creation

Creating a list follows the same pattern that we saw for arrays. It can be
done by specifying element type, list size, and some code that generates an
initial value:

```kotlin
val numbers = List<Float>(100) { 0.0f }

val greetings = List<String>(5) { "Hello" }
```

Alternatively, you can provide the list contents as arguments to the
`listOf()` function:

```kotlin
val numbers = listOf(9, 3, 6, 2)

val names = listOf("Nine", "Three", "Six", "Two")
```

{button}`Run this code <https://pl.kotl.in/AHC2AiYik>`

There are a few differences between lists and arrays, however.

One difference is that you can pass a list to the `println()` function and
see the list contents displayed on the console. You can't do that with
arrays[^print].

Another difference is that there is no such thing as a more efficient 'list
of primitives'. So there is no `IntList` class and no `intListOf()` function,
for example.

## Element Access

Elements of lists are accessed in the same ways as elements of arrays.

You can use the `[]` operator or `get()` method to access an individual
list element. (Note that use of `[]` is considered better style.)

You can also use `slice()` to extract part of a list.

### Task 7.3.1

Open the `tasks/task7_3_1` directory of your repository. Edit `Main.kt`
and write a program that creates and displays a list of integers like this:

```kotlin
val numbers = listOf(9, 3, 6, 2, 8, 5)
println(numbers)
```

Check that the program compiles and runs successfully.

Now make the changes listed below. After making each change, try to predict
what will happen when you attempt to recompile and rerun the program. Then
perform those actions to see if you were right.

1. Add a line of code that prints the value of `numbers[0]`.

1. Add a line that attempts to print the value of `numbers[10]`.

1. Replace the line that you added in the previous step with a different
   line that prints the value of `numbers.slice(2..4)`.

1. Add a line that prints the value of `numbers.first()`. Then add a line
   that prints the value of `numbers.last()`.[^empty]

1. Add a line that assigns a new value to `numbers[0]`.

1. Replace the line that you added in the previous step with
   `numbers.add(1)`. This should, in theory, should append the value 1
   to the list.

**You should have seen that the last two of these changes fail to compile.**

:::{danger} Important
**Kotlin prefers immutability**.

Lists created using the `List<>` constructor or the `listOf()` function
are **immutable** by design. This means that you cannot add a new value
to such a list, remove any of its values, or replace any of its values.

However, note that **the objects in the list could still change state**,
if they are of a type that supports such operations.
:::

## Mutable Lists

If you want your list to change after creation, you need to be explicit
about this.

This means that you will need to use `MutableList<>` instead of `List<>`:

```kotlin
val greetings = MutableList<String>(5) { "Hello" }

greetings[0] = "Hi there"   // OK: greetings is mutable
```

Similarly, you will need to use the `mutableListOf()` function instead
of `listOf()`:

```kotlin
val numbers = mutableListOf(9, 3, 6, 2)

numbers[0] = 1   // OK: numbers is mutable
```

`mutableListOf()` is also the most convenient way of creating an empty
mutable list&mdash;although in this case you will need to supply an element
type in angle brackets, because there are no contents for type inference to
work on:

```kotlin
val data = mutableListOf<Double>()
```

:::{note}
Notice the use of `val` in the examples above.

Remember that `val` merely prevents reassignment of different list objects
to these variables; it doesn't prevent changes to the content of the
lists.
:::

## Adding & Removing Values

Mutable lists have a number of methods that can be used to modify the list:

| Method        | Description                                    |
|---------------|------------------------------------------------|
| `add()`       | Adds an item at the end, or a given position   |
| `addAll()`    | Adds all items from the given collection       |
| `remove()`    | Removes first occurrence of the given item     |
| `removeAll()` | Removes all occurrences of the specified items |
| `removeAt()`  | Removes the item at the given position         |
| `clear()`     | Empties the list of all its contents           |

Note that the `removeAll()` method expects to be given another collection,
specifying the items to be removed. You can also specify the items it should
remove by providing a predicate function that returns `True` for those items.

See the [API documentation for `MutableList`][mut] for further details.

### Task 7.3.2

1. Copy `Main.kt` from the `task7_3_1/src` to `task7_3_2/src`.

1. Modify this new copy of the program so that it uses the `mutableListOf()`
   function to create the list. Check that this fixes the compiler errors
   found in the previous task.

1. Add some code to the program that demonstrates the list modification
   methods in the table above. After calling each method, add a line that
   prints the list, so that you can see the effect of the call.

## `buildList()`

We saw earlier that you can [build a string iteratively][str] using the
`buildString()` function. Kotlin supports a similar approach to building a
list, using the `buildList()` function.

As an example of how this can simplify things, imagine that you have a text
file in which each line contains a single number, and that you need to read
all of those numbers into a list. This can be accomplished by creating an
empty mutable list, [reading the lines of the file][lin], parsing each line
as a number, and then appending that number to the mutable list:

```kotlin
val numbers = mutableListOf<Float>()
filePath.forEachLine {
    numbers.add(it.toFloat())
}
```

Alternatively, you could do this using `buildList()`, like so:

```kotlin
val numbers = buildList {
    filePath.forEachLine {
        add(it.toFloat())
    }
}
```

This is a little cleaner and more elegant.

Note how, in this approach, it isn't necessary to specify the type of value
that will be stored in the list. This will be inferred from the type of
value passed to the `add()` method.

As with `buildString()`, a hidden object is used internally to build the
thing that will be returned by the function. In this case, it is an object of
type `MutableList<Float>`.

:::{note}
The object returned by `buildList()` is actually a `List` rather than a
`MutableList`, in keeping with Kotlin's preference for immutability.

If you need to make further changes to the list after creating it, you can
obtain a mutable version of it by invoking `toMutableList()`, or you can just
avoid `buildList()` entirely and use the other approach described above.
:::


[^print]: This has to do with the underlying implementations of collections.
On the JVM, a Kotlin array is implemented as a Java array, which doesn't
support the automatic conversion to a string for printing. A Kotlin list, on
the other hand, is implemented by a Java class that does support automatic
conversion to a string.

[^empty]: What do you think would happen if you tried calling `first()` or
`last()` on an empty list? Try this out if you like. You can create that
empty list using `listOf<Int>()`.

[mut]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-mutable-list/
[str]: ../flow/str-iter.md#good-approach
[lin]: ../flow/file-iter.md#reading-lines
