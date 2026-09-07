# Constants

We saw in the [previous section][prev] that when you use `val`, you are a
creating a fixed association between a name and an object. You won't be
allowed to associate your chosen name with a different object. This doesn't
necessarily mean that the object remains in the same state. So `val`
variables are not true constants.

Kotlin does, however, allow us to create true **compile-time constants** for
certain types, by applying the `const` modifier to a `val`:

```kotlin
const val SPEED_OF_LIGHT = 2.99792e8

const val VERSION = "v1.0"
```

When you define a constant in this way, the compiler will **inline** any
usage of that constant that it finds&mdash;meaning that it will replace all
occurrences of the constant with its literal value.

In your own code, you can use `const val` to give names to simple values that
are fixed for all time and known in advance.

:::{caution}
Note that there are some restrictions that apply to constants:

* A constant can only be an instance of `String`, `Char` or a numeric type

* A constant has to be defined either at the top level of a file (i.e.,
  not inside a function), or inside the companion object of a class
  (see later)

A constant defined at the top level of a file will, by default, be visible
to code in other files that are part of the same package. You can limit a
constant's visibility to just the file in which it is defined by including
`private` as a prefix.
:::


[prev]: val-var.md
