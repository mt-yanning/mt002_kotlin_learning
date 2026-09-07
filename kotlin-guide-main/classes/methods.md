# Methods

**Methods**, also known as **member functions**, are functions defined
inside the body of a class. They have implicit access to the properties of
a class, by virtue of being defined within the same scope as those
properties.

The syntax for defining a method is basically the same as that for standalone
functions. You can write a method with a block body or expression body, as
you prefer. The method can accept input via its parameter list and return
values to the caller in the same way as a standalone function.

## Task 12.4.1

This task will give you more practice at implementing methods.

Start by opening the `tasks/task12_4_1` directory of your repository. Examine
the implementation of the `Point` class in `Point.kt`.

Next, edit `Circle.kt`. In this file, implement a new class named `Circle`.
Give your class two `val` properties:

* `centre`, a `Point` object representing coordinates of the circle's centre
* `radius`, a `Double` value representing the circle's radius

Check that the project's code compiles with

    ./kotlin build

### `init` Block

Add an `init` block to `Circle` that guarantees `radius` will be greater than
zero when a `Circle` object is created. Use `require()` to help with this.

Then edit `Main.kt`. In this file, write a program to verify that creation
of a `Circle` with a negative radius is prevented.

Compile and run the program. It should throw an `IllegalArgumentException.`

### Adding Methods

Add methods `area()` and `perimeter()` to `Circle`. These methods should have
no parameters. They should return the area and perimeter, respectively, of
the circle.

Area and perimeter can be computed using the formulae $\pi r^2$ and
$2\pi r$, respectively. The constant $\pi$ can be imported as the name
`PI`, using

```kotlin
import kotlin.math.PI
```

Next, add a method `contains()` to `Circle`. This should have a single
parameter, of type `Point`. It should return `true` if the given point is
inside the circle, `false` otherwise. The point should be considered inside
if the distance from it to the centre of the circle is less than or equal to
the radius.

If you like, use `infix` when defining the method, so that it can be invoked
like this on objects named `circle` and `point`:

```kotlin
if (circle contains point) {
    ...
}
```

Finally, return to `Main.kt`. Replace the program in this file with one that
creates a `Circle` object and then demonstrates the use of its three methods.

Compile and run the program to make sure it behaves as expected.

## Overriding

One feature of methods that they don't share with standalone functions is
their ability to **override** (i.e., substitute for) other methods, or
be overridden themselves. We will consider a simple example of this here,
deferring more detailed discussion to when we cover [inheritance][inh].

All classes in Kotlin inherit implicitly from a superclass named `Any`. This
provides all Kotlin classes with certain capabilities, one of them being
the ability to generate a string representation of an instance of the class,
via the `toString()` method. The default representation produced by this
method is generic and not particularly useful, so it is common to override
`toString()` with a version that yields something better[^py].

Note that `toString()` is called automatically on any object you pass to
`print()` and `println()`, so overriding it can be very useful for tasks
like debugging.

### Task 12.4.2

1. Open the `tasks/task12_4_2` directory of your repository and examine the
   source code files `Point.kt` and `Main.kt`.

1. Add code to `main()` that creates a `Point` object and calls `println()`
   on that object.

1. Compile and run the program. What do you see?

1. Now add a new method to the `Point` class in `Point.kt`:

   ```kotlin
   override fun toString() = "($x, $y)"
   ```

   Recompile the program and run it again. What has changed?

:::{danger} Important
You **must** use the `override` keyword when overriding a method, and the
new version must have the same signature as the version being overridden.

Also, note that methods must grant explicit permission to be overridden
in subclasses. The example above works because the `Any` superclass grants
that permission for `toString()`. We will discuss this further when we
cover inheritance.
:::

## Method or Extension?

Almost all of the methods we've seen so far could have been written instead
as [extension functions](../funcs/extension.md). For example, `Point`
could have been implemented like this:

```kotlin
class Point(var x: Double, var y: Double)

fun Point.distance() = hypot(x, y)
fun Point.distanceTo(p: Point) = hypot(x - p.x, y - p.y)
```

From the perspective of users of `Point`, there is no discernable difference
between this version of the class and the version with methods rather than
extension functions.

But methods have some unique advantages. As we've seen already, they support
overrriding, whereas extension functions do not.

Another advantage is that methods have more privileged access to members of
a class. A method can make use of anything defined in its class, including
private members. By contrast, an extension function only has access to the
public API of the class.

Extension functions have their uses, though. If you don't have access to
the source code of a class, and that class prevents inheritance, then
extension is the only way in which you can add new functions to that class.


[^py]: You may remember doing something similar for Python. Python classes
have inherited 'dunder' methods, much like Kotlin classes. Overriding the
`__str__` method in a Python class is the equivalent of overriding
`toString()` in a Kotlin class.

[inh]: ../inherit/index.md
