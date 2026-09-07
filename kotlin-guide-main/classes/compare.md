---
short_title: "Python Comparison"
---

# Comparison with Python

::::{tab-set}
:::{tab-item} Kotlin
```{code} kotlin
:linenos:
import kotlin.math.hypot

class Point(var x: Double, var y: Double) {
    fun distance() = hypot(x, y)
    fun distanceTo(p: Point) = hypot(x - p.x, y - p.y)
}
```
:::
:::{tab-item} Python
```{code} python
:linenos:
from math import hypot

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def distance(self):
        return hypot(self.x, self.y)

    def distanceTo(self, p):
        return hypot(self.x - p.x, self.y - p.y)
```
:::
::::

Before progressing any further, spend a few minutes comparing the two
implementations of `Point` that you created in Task 12.1. You should have
code resembling that shown above.

## Obvious Differences

It is immediately obvious that the Kotlin class requires fewer lines of code.
This is due to its very compact way of defining properties and a primary
constructor, plus the use of expression body syntax when defining the methods.

The Kotlin class is more verbose than the Python class in one respect, namely
its requirement that types be specified for the properties. This is not
required for Python, with the result that you could create a nonsensical
`Point` object where `x` and `y` are strings, for example. Attempts to invoke
`distance()` or `distanceTo()` on such an object would result in a `TypeError`
exception at run time.

If you wanted, you could specify that `x` and `y` ought to be of type `float`
using Python's [type hinting][hint] feature, but this is very much optional.
Thus the Kotlin version is more type-safe.

## Role of `self` & `this`

The Python constructor has an explicit parameter `self`, appearing before
the parameters representing point coordinates, which refers to the object
being created. Similarly, the `distance()` and `distanceTo()` methods both
have a `self` parameter to represent the object on which the method is
being called.

When referring to the fields `x` and `y` within methods of the Python class,
you are required to use `self` as a prefix: i.e, you must refer to the fields
as `self.x` and `self.y`.

In contrast, methods of Kotlin classes do *not* use an explicit parameter in
the parameter list to represent the object on which a method is being called.
Thus there is no need to use a special prefix when referring to the properties
of the class within those methods.

Note, however, that you are free to use the special variable `this` as an
_implicit_ reference to the object if you wish. For example, you could refer
to the properties of `Point` as `this.x` and `this.y` within the `distance()`
and `distanceTo()` methods if you really wanted to.

## Accessing The Classes

One final difference can be seen in how the different versions of `Point`
are accessed.

The Kotlin program in `Main.kt` doesn't need to do anything special in order
to access the `Point` class. This is because `Point` and `main()` are
regarded as being part of the same logical package, despite being defined in
different source files.

In the case of Python, `point.py` and `main.py` are considered to be separate
modules. The program in `main.py` cannot make use of something defined in
`point.py` unless that thing is imported from the module, or the entire
module is imported.


[hint]: https://mypy.readthedocs.io/en/stable/cheat_sheet_py3.html
