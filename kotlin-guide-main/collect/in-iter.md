# Membership & Iteration

## Membership Tests

You can test whether an item is in an array, list or set using `in`, or
the `contains()` method. The former is more elegant:

```kotlin
if (item in collection) {
    // item is in the collection!
}
```

You can also use `in` with maps, but in this case you need to test for
membership of either the map's keys or its values:

```kotlin
if (item in map.keys) {
    // item is a key of the map
}

if (item in map.values) {
    // item is a value of the map
}
```

An alternative to the above approach is to use the `containsKey()` or
`containsValue()` method of the map.

You can check whether multiple values are all contained within an array,
list or set using the `containsAll()` method. For example

```kotlin
numbers.containsAll(setOf(1, 2, 3))
```

will return `true` if the list `numbers` contains the values 1, 2 and 3.
It will return `false` if any of them are missing from the list.

## Iteration

You can iterate over the contents of an array, list, set or map using a
`for` loop. With arrays, lists and sets, the syntax is

```kotlin
for (item in collection) {
    // do something with item
}
```

Iteration is slightly more complex with maps, because the `for` loop will
yield pairings of key and value[^entry]. Typically we use destructuring
within the loop to extract key and value into separate variables:

```kotlin
for ((key, value) in map) {
    // do something with key and value
}
```

You can also iterate over keys or values separately, by using the `keys` or
`values` properties of the map in a `for` loop:

```kotlin
for (key in map.keys) { ... }

for (value in map.values) { ... }
```

{button}`Try this out <https://pl.kotl.in/8NsB77oCr>`

### Functional Style

Arrays, lists, sets and maps also allow iteration over their contents in a
more functional style, via the `forEach` extension function.

This function expects to be supplied either with a function reference or
with a lambda expression. The supplied function or lambda should take a
single parameter, which represents an item from the collection. The function
or lambda will be executed once for each item.

For example, to print out each item of a collection on a line by itself,
you could use either of the following:

```kotlin
collection.forEach(::println)

collection.forEach { println(it) }
```


[^entry]: You might think that a Kotlin `Pair` is always used for this, but
on the JVM a Java collection class, `LinkedHashMap`, is used to implement
maps, and its inner class `Entry` is used to implement the pairing.
