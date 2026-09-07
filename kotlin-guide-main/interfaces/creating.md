# Creating Interfaces

## Basic Syntax

A Kotlin interface is defined in a similar manner to a class, except that
we use the `interface` keyword instead of `class`.

Kotlin interfaces typically define abstract methods, in the same way that an
abstract class does. However, we don't use the `abstract` keyword as part
of the method definition.

Here's a simple example[^kia]. Imagine that you are building a GUI, and some
elements of that GUI can be clicked on using the mouse. You might represent
that capability by means of a `Clickable` interface:

```kotlin
interface Clickable {
    fun click()
}
```

In this example, `click()` is an abstract method, even though it isn't
specified using the `abstract` keyword. Any class that implements `Clickable`
will need to provide an implementation of the `click()` method, otherwise
it will not compile.

:::{note}
It is quite common for interfaces to have names ending in 'able'.

This is because they are often used to represent **capabilities** that are
shared by otherwise dissimiliar classes.
:::

<!-- TODO: Add quiz or task here? -->

## Default Implementations

Although the classic interface contains only abstract methods, it is possible
to include methods that have default implementations. The limitation is that
those method implementations are not allowed to reference state.

Unlike abstract classes, **Kotlin interfaces cannot contain any members that
are used to represent object state**.

For example, we could modify the `Clickable` interface to look like this:

```kotlin
interface Clickable {
    fun click()
    fun info() = println("I am clickable")
}
```

In this version of the interface, `click()` is an abstract method but
`info()` is not. Any class that implements `Clickable` will be able to make
use of this method, or override it with a new implementation.

Notice that there is no need to use the `open` modifier when defining
`info()`. The method is implicitly open for overriding, by virtue of being
defined within an interface.

<!-- TODO: Add quiz here? -->


[^kia]: This is adapted from an example in the book *Kotlin In Action*, as
are some of the other examples in this section.
