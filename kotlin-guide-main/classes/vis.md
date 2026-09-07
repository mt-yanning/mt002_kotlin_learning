# Visibility

In all of the classes we've seen so far, the properties and methods have
had **public visibility**, meaning that any other code has access to them.
This level of visibility is the default, which is why we didn't use the
`public` keyword with any of the definitions.

But other levels of visibility are possible in classes. For example, we
can declare members of a class using the `private` keyword. If you make a
property or method private, then it can be used only within the class that
defines that property or method. Code outside the class will not be able
to access it.

The ability to hide class members by making them private is important and
useful. Let's consider an example where public visibility of a class member
causes problems, and examine how making that member private fixes things.

## An Example

Imagine that you have a class `Dataset`, representing numbers read from
a file:

```kotlin
class Dataset {
    val values = mutableListOf<Double>()

    fun loadData(filename: String) { ... }
}
```

The property that stores all the values is a mutable list because we need to
add values to it one at a time, as we read them from the file.

A `Dataset` object is initially empty. We populate it with values by
invoking the `loadData()` method, providing the name of the file containing
the data:

```kotlin
val dataset = Dataset()
dataset.loadData("data.txt")
```

After loading data, we can do things like query the size of the dataset,
test its first value, or iterate over the values in order to print them
all:

```kotlin
println(dataset.values.size)

if (dataset.values[0] < 0.0) {
    println("Dataset begins with negative value")
}

for (value in dataset.values) {
    println(value)
}
```

These operations are all useful, although having to access the `values`
property explicitly each time is a little inconvenient.

However, it is also possible to replace values, remove values, or add values:

```kotlin
dataset.values[1] = 0.0      // zeroes second value
dataset.values.removeAt(0)   // removes value at index 0
dataset.values.add(1.5)      // adds 1.5 to end
```

This is a big problem! A `Dataset` object is supposed to represent the data
read from a file. If we allow the contents of `values` to be modified in any
way, then it will no longer represent the contents of the file accurately.

## Fixing Things

The solution to the issues noted above is to declare `values` as private
and then provide controlled access to it via additional computed properties
and methods:

```{code} kotlin
:linenos:
class Dataset {
    private val values = mutableListOf<Double>()

    fun loadData(filename: String) { ... }

    val size get() = values.size

    operator fun get(index: Int) = values.get(index)

    operator fun iterator() = values.iterator()
}
```

This new version of `Dataset` has

* A computed property `size`, that simply returns the `size` property
  of `values`

* An element access function that gives read-only access to elements
  of `values`

* An iterator function that allows a `Dataset` object to be used as the
  subject of a `for` loop

Note the use of [operator overloading][op] for two of these three features.
The `operator` keyword is used on lines 8 & 10 to define member functions
that are not invoked in the regular way, using the name of the function.
For example, we invoke the `get()` function using `[]` and an integer index,
just as we would for arrays and lists.

The implementation of all three features is simple, because they all
**delegate** to corresponding features of the private `MutableList` object.

**No other parts of the list API are exposed to users of `Dataset`.** Thus
it is no longer possible for users of the class to replace anything in
`values`, remove anything in `values` or add anything to `values`.

It is still possible to query dataset size, read an individual value or
iterate over all values&mdash;with the added bonus that these things can now
be done with slightly simpler syntax:

```{code} kotlin
:linenos:
println(dataset.size)

if (dataset[0] < 0.0) {
    println("Dataset begins with negative value")
}

for (value in dataset) {
    println(value)
}
```

Lines 3 and 7 are where the overloaded operators are invoked. You can see
that the effect of operator overloading is to give the `Dataset` class
an API comparable with that of Kotlin's collection types. This makes the
class easier to understand and use.

## API vs Implementation

When you define the properties and methods of a class, think carefully
about whether you want those properties and methods to be part of the
**public API** of the class, or whether they should be part of the private
implementation.

Every class will need a public API of some sort, but **the public API becomes
hard to change as soon as other code starts using that class**, because
changing a public feature is likely to break that code. Private implementation
details, on the other hand, are free to change whenever you need them to,
without breaking any code that uses the class.

For example, the computed property `size` is part of the public API of
`Dataset`. If we changed its name to `length`, this would probably break
code that uses the class.

The `values` property, on the other hand, is no longer part of the public API.
We have made it an implementation detail. If we later decide that a different
type of collection would be better for storing the individual values of a
dataset, we can make that change to the `Dataset` class **without breaking
any code that is using the class**.


[op]: https://kotlinlang.org/docs/operator-overloading.html
