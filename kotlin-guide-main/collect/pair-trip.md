# Pairs & Triples

## Creation

You can create a `Pair` or `Triple` by invoking their **constructor**
directly. For example, you could pair up someone's name and age, or bundle
together name, age and height, like this:

```kotlin
val person = Pair("Sarah", 37)

val person2 = Triple("Joe", 24, 1.75f)
```

Note that this code is technically shorthand for

```kotlin
val person = Pair<String,Int>("Sarah", 37)

val person2 = Triple<String,Int,Float>("Joe", 24, 1.75f)
```

`Pair` & `Triple`, like all Kotlin collections, are **generic types**. You
can think of a generic type as an 'incomplete type', with parameters that
are themselves types. Values for these parameters are provided inside angle
brackets to complete the type specification. In the case of collections,
these type parameters are used to specify the type(s) of the contents of
the collection.

In the examples above, the type parameters of the `Pair` have values of
`String` and `Int`, whereas the type parameters of the `Triple` have values
of `String`, `Int` and `Float`.

In some cases, you won't need to specify what these type parameters are when
you use collections, because the compiler will use type inference to figure
this out. In other cases, you will need to provide a full specification
(e.g., when creating an empty collection, or when writing a function with a
parameter that is a collection).

## `to()` Function

An alternative way of creating a `Pair` is to use the `to()` extension
function:

```kotlin
val person = "Sarah" to 37
```

This doesn't look like a regular function or method call, because it is
using [infix notation](../funcs/infix.md).

`to()` is a nice way of pairing keys with values for use in a map.

## Element Access

The elements of a `Pair` are referenced as `first` and `second`. The
elements of a `Triple` are, unsurprisingly, referenced as `first`, `second`
and `third`.

```kotlin
val person = Pair("Sarah", 37)

println(person.first)    // prints Sarah
println(person.second)   // prints 37
```

Obviously `first` and `second` are not ideal as ways of referring to a
person's name or age. You can use a technique known as **destructuring** to
assign the elements of a pair to individual variables with better names.
This is the equivalent of tuple unpacking in Python.

::::{tab-set}
:::{tab-item} Kotlin
```kotlin
val person = Pair("Sarah", 37)
...
val (name, age) = person
println("Name is $name, age is $age")
```
:::
:::{tab-item} Python
```python
person = ("Sarah", 37)
...
name, age = person
print(f"Name is {name}, age is {age}")
```
:::
::::

{button}`Run Kotlin example <https://pl.kotl.in/Nb2e1aIQZ>`

:::{tip}
`Pair` and `Triple` are intended for situations in which you need to bundle
two or three values together temporarily, for a very specific purpose.

Often, it will be more useful to represent a group of two or three related
values using a class. This is certainly the recommended approach
when you are dealing with the important entities in a software application.

For example, in a graphics application you could represent points in a
two-dimensional coordinate system using `Pair<Double,Double>`, but a better
option would be to create a proper `Point` class, with dedicated `x` and `y`
properties.
:::
