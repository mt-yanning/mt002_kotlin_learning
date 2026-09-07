---
short_title: "Basics"
---

# The Basics

Let's begin with an example of the simplest possible class definition
in Kotlin:

```kotlin
class Point
```

We can create an **instance** of this class (i.e., a `Point` object)
like so:

```kotlin
val p = Point()
```

This invokes the **default constructor** of the `Point` class.

In this case, the default constructor does nothing; indeed, the class itself
is essentially useless. To make it useful, we need to add some Kotlin code
to `Point` that represents the attributes or behaviour associated with points.

Let's assume that `Point` is supposed to represent a point in 2D space.
Its attributes will therefore be the x and y coordinates of that point. We
represent these as **properties** of the class. We can define and initialize
these properties like so:

```kotlin
class Point {
    val x = 0.0
    val y = 0.0
}
```

You can see here that properties look just like variables, but defined
inside a class.

The code to initialize properties `x` and `y` to zero is executed as part of
the object construction process. After creating a `Point` object, we can access
its `x` and `y` properties with the 'dot' operator:

```kotlin
val p = Point()

println(p.x)   // prints 0.0
println(p.y)   // prints 0.0
```

## Task 12.1

Let's test this simple class.

Open the `tasks/task12_1` directory of your repository and edit the file
`Point.kt`. Add to this file the `Point` class definition shown above.

Next, edit `Main.kt` and add a program that will create a `Point` object
named `p` and then print the values of its `x` and `y` properties. Compile
and run the program.

Now add the following to `main()`, immediately after the line that creates
the `Point` object:

```kotlin
p.x = 1.0
```

What happens when you try to recompile?

Finally, change the property definitions in the `Point` class to use `var`
rather than `val`. Verify that this fixes the compiler error.

:::{danger} Important
`val` is used to define read-only properties in a class.

If you need the ability to assign a new value to a property after an
object has been created, you need to define that property using `var`.
:::

## Writing a Constructor

You should now have a class definition like this:

```kotlin
class Point {
    var x = 0.0
    var y = 0.0
}
```

This is the first genuinely useful version of the `Point` class. However,
the process of representing a point at any location other than the origin is
inconvenient:

```kotlin
val p = Point()
p.x = 4.5
p.y = 7.0
```

It would be better if we could supply the desired values for `x` and `y` as
part of the construction process, so that the desired object is created with
a single line of code. We achieve this by adding an explicit **primary
constructor** to the class:

```kotlin
class Point constructor(x: Double, y: Double) {
    var x = x
    var y = y
}
```

The `constructor` keyword introduces this explicit constructor. It is followed
by a parameter list, specifying that two `Double` values must be provided to
create a `Point` object. These values are then assigned to the `x` and `y`
properties.

:::{note}
The compiler isn't bothered by the repeated use of `x` and `y` here.
It knows that the usage of `x` and `y` on the right-hand side of the
assignments refers to the constructor parameters and not the properties.

If you find it confusing, you can use different names for the constructor
parameters.
:::

Let's try this out now.

Return to `Point.kt`. Modify the definition of the `Point` class so that it
looks like the one above, but don't change `main()`. What happens when you
try to compile the code?

To fix the issue, modify `main()` so that coordinate values are supplied when
creating the object. Check that the program now compiles and runs as expected.

From this experiment, you can see that if you write a primary constructor,
**this becomes the expected way of creating an instance of the class**. We'll
consider how you can support different ways of creating an object in the
section on [][con].

## Compact Syntax

The need to define some class properties and a constructor that assigns
values to those properties is so frequent that Kotlin provides a much more
compact syntax for doing this.

The class definition

```kotlin
class Point constructor(x: Double, y: Double) {
    var x = x
    var y = y
}
```

Can be written more concisely as

```kotlin
class Point(var x: Double, var y: Double)
```

:::{tip}
When you see a class definition with a primary constructor, take a close
look at its parameter list. Each item in the list represents a value that
must be provided when creating an object. If you see `val` or `var` in
front of an item then that item also specifies a property of the class.
:::

Let's try this out.

Return to `Point.kt`. Modify the class definition by removing the keyword
`constructor`:

```kotlin
class Point(x: Double, y: Double) {
   var x = x
   var y = y
}
```

Recompile the program and run it, to verify that behaviour is unchanged.

Now merge the property definitions into the constructor parameter list so that
the entire class definition is a single line of code, as shown above.

Recompile and run, to verify that behaviour is unchanged.

:::{danger} Important
It is common to see classes defined in this very concise way, so you
need to get used to it! It may feel quite strange at first, particularly
if you are used to the more verbose class definitions of other
programming languages.

The best way of adjusting to this style is to get lots of practice at
writing small classes.
:::

## Mutable or Immutable?

Consider these two different versions of the `Point` class:

```kotlin
class Point(var x: Double, var y: Double)   // mutable

class Point(val x: Double, val y: Double)   // immutable
```

One is **mutable**, meaning that it is possible to give a `Point` object a
different value for `x` or `y` after creation. The other is **immutable**,
meaning that the property values are fixed when the `Point` object is
created and cannot change thereafter.[^imm]

Despite the restrictions, you shouldn't think of the immutable version as
being necessarily inferior. Anything you can do with the mutable version can
still be achieved with the immutable version, albeit at the cost of creating
an additional object.

For example, here's how you could create a point and then translate it by
10 units along the x axis, using both versions of the `Point` class:

```kotlin
// If Point is mutable:
val p = Point(x, y)
p.x += 10.0

// If Point is immutable:
var p = Point(x, y)
p = Point(p.x + 10.0, p.y)
```

Besides, immutable types have some advantages over mutable types. For one
thing, they are safer to use in multi-threaded applications[^race].

## Adding a Method

It would be useful if a `Point` had a way of telling us how far it was from
the origin. We can implement this as a  **method** or **member function**
of the class.

Following Pythagoras, the distance of a point $(x, y)$ from the origin is the
length of the hypotenuse of the right-angled triangle whose other two sides
lie along the x and y axes:

$$ d = \sqrt{x^2 + y^2} $$

We can use the [`hypot()` function from the standard library][hyp] to perform
this calculation. Our new method simply needs to call `hypot()`.

Here is the complete implementation of the class:

```kotlin
import kotlin.math.hypot

class Point(var x: Double, var y: Double) {
    fun distance() = hypot(x, y)
}
```

Notice that the method is defined in just the same way as a regular
standalone function. In this case, we've used an expression body, but we
could have also written it with a block body.

The body of the method has direct access to properties `x` and `y`, which
are all it needs to do its work. Hence the method has an empty parameter list.

## Finishing Task 12.1

There are three more things that you need to do to complete this extended
task. The first is to modify the definition of `Point` in `Point.kt` so that
it has a `distance()` method, as shown above. Then add a new method,
`distanceTo()`. This method should accept a `Point` object as its sole
parameter. It should return the distance between the receiver of the method
call and the specified `Point` object.

The distance between two points $p$ and $q$ is given by
$$ d = \sqrt{(x_p - x_q)^2 + (y_p - y_q)^2} $$

You should be able to implement this in a straightforward way using the
`hypot()` function from the standard library, as you did for the
`distance()` method.

The second thing is to modify the main program so that it

* Creates a `Point` object using coordinates of your choosing
* Prints the x and y coordinates by accessing properties of this object
* Computes and prints distance of the point from the origin
* Computes and prints the distance of the point from a new point at (4.5, 7.0)

The final part of the task is to implement **an equivalent class definition
and program in Python**, in files named `point.py` and `main.py`. You should
make sure that the class and program behave the same as the Kotlin
implementation.

Looking at these pieces of code side-by-side will help you to understand more
clearly how Kotlin classes differ from Python classes.


[^imm]: The immutability of this second version of the `Point` class stems
from the fact that `val` is used to define both properties _and_ the fact
that both properties are of type `Double`, which is an immutable type. 

[^race]: [Race conditions][race] can occur when two threads share write access
to the same variable. These problems don't occur if we only have read access
to the variable, as is the case for immutable types.

[con]: construct.md
[race]: https://en.wikipedia.org/wiki/Race_condition#In_software
[hyp]: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.math/hypot.html
