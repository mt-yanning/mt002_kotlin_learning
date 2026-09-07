# Sets & Maps

A **map** is an associative container for values. Rather than indexing
values by their position in a sequence, as is the case for arrays and lists,
a map associates each value with a **key**. This key is then used to look up
the corresponding value. Keys must therefore be unique.

A **set** is a little like a map with keys but no associated values.
In effect, the keys *are* the values we wish to store! Sets are essentially
unordered collections in which items are guaranteed not to be duplicated.

C has no equivalents to sets and maps in its standard library, but Python
provides both of these data structures. (A Python dictionary is equivalent to
a Kotlin map.)

## Creation

Sets and maps are created in a similar way to arrays and lists, by invoking
the constructors for `Set<>` and `Map<>`, or the creation functions `setOf()`
and `mapOf()`. The latter is often used in conjunction with the `to()`
function, to construct maps in a very readable way.

```kotlin
val fixedOptions = setOf("Save", "Load", "Quit")

val fixedPrices = mapOf(
    "Apple" to 32,
    "Orange" to 55,
    "Kiwi" to 20
)
```

**The same mutability considerations that apply to lists also apply to sets
and maps.** Thus the `Set<>` constructor and `setOf()` will give you an
immutable set, whereas the `Map<>` constructor and `mapOf()` will give you
an immutable map.

If you want mutability, you must request it explicitly:

```kotlin
val options = mutableSetOf("Save", "Load", "Quit")

val prices = mutableMapOf<String,Int>()   // key & value types needed!
```

As with mutable lists, it is necessary to specify the type(s) of the
collection's contents when creating an empty mutable set or an empty mutable
map.

## Set Manipulation

To add an item to or remove an item from a mutable set, use the `add()`
or `remove()` method, as appropriate.

These methods expect a single argument: the item to be added/removed. Both
of them return `true` if the operation succeeded, `false` if it did not.

Here's an example of how we might check for successful addition to a set:

```kotlin linenums="1"
val names = mutableSetOf("Joe", "Sarah", "Nicole")

print("Enter your name: ")
val name = readln()

when (names.add(name)) {
    true -> println("$name added")
    false -> println("I'm sorry, we already have a $name")
}
```

If you want to add or remove multiple items at the same time, you can use
`addAll()` or `removeAll()`. Both of these methods accept a collection
of the values to be added or removed as the only argument. Both return
`true` if at least one value was added or removed, or `false` if there was
no modification of the set.

You can empty a mutable set of all its contents using the `clear()` method.

<!-- TODO: add a task here? -->

## Looking Up Values in a Map

You can look up the value associated with a key using the `[]` operator
or the `get()` method:

```{code} kotlin
:linenos:
val prices = mutableMapOf(
    "Apple" to 32,
    "Orange" to 55,
    "Kiwi" to 20
)

println(prices["Apple"])   // prints 32
println(prices["Pear"])    // ?
```

:::{attention .simple icon=false} **Question**
What do you think happens when the print statement on line 8 is executed?

If you are unsure, [try running this code][run]...
:::

:::{attention .simple .dropdown icon=false} **Answer**
The statement executes as normal, printing the value `null`
:::

It's important to understand that the object returned when `[]` or `get()`
is used on a map is an instance of a **nullable type**, which we will cover
later.

If you don't want this behaviour, you can use `getOrElse()` instead:

```kotlin
prices.getOrElse(item) { 15 }

prices.getOrElse(item) {
    throw NoSuchElementException("No price for $item")
}
```

As its name suggests, this returns the associated value if the given key
is present, *or else* it performs the action specified by the given lambda
expression. In the first of the examples above, this lambda expression
simply returns a default value. The second example throws an exception in
cases where the key is missing.

## Modifying a Map

You can modify an existing value or add a new key-value pair to a mutable
map using the `[]` operator in an assignment operation, or by calling
the `put()` method. For example, given the mutable map described earlier,
you could do this:

```kotlin
prices["Apple"] = 40    // updates price of Apple
prices["Banana"] = 65   // introduces new item & price
```

You can remove a key-value pair from a mutable map by invoking the `remove()`
method on it, with the key as the argument.

You can empty a mutable map of all its contents using the `clear()` method.

<!-- TODO: add a task here? -->


[run]: https://pl.kotl.in/-JGjOhK0z
