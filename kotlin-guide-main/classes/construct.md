(constructors)=
# Constructors

Constructors are responsible for creating instances of a class. Kotlin
classes are generally defined with a **primary constructor**, providing the
main way of creating an object:

```kotlin
class Point(var x: Double, var y: Double)
```

In this example, the primary constructor specifies that two values of type
`Double`, representing a point's x and y coordinates, need to be provided
whenever we try to create a `Point`. These values will be used to initialize
properties named `x` and `y`.

But what if we want to create objects of this class in other ways? For
example, what if we want there to be a 'default point' with coordinates (0,0),
created when no values have been provided for `x` and `y`?

This could achieved by specifying default arguments of `0.0` in the
constructor:

```kotlin
class Point(var x: Double = 0.0, var y: Double = 0.0)
```

However, this isn't entirely satisfactory. It allows us to create a `Point`
by supplying a value for only one of the two coordinates, and this feels
unintuitive.

## Secondary Constructors

A better solution to this problem is to define an additional constructor
with an empty parameter list. This **secondary constructor** is specified
within the body of the class, using the `constructor` keyword:

```kotlin
class Point(var x: Double, var y: Double) {
    constructor(): this(0.0, 0.0)
}
```

The `constructor` keyword is followed by a parameter list, then a colon,
then an invocation of another constructor of the class, using `this`.

In this particular case, the parameter list of the secondary constructor
is empty, because this secondary constructor is intended to support situations
in which we are not supplying values for `x` and `y`. Instead, we want to
construct a `Point` object in which `x` and `y` are both zero. This is
achieved by invoking the primary constructor, via `this(0.0, 0.0)`.

:::{danger} Important
A secondary constructor must always delegate either to the primary
constructor or to another secondary constructor, using the `this()` syntax
shown above.

If required, the secondary constructor can have a body, enclosed in `{}`,
which can do other work, but it always has to delegate.

This ensures that the primary constructor is ultimately always used as part
of the process of initializing an object.
:::

### Task 12.3.1

Open the `tasks/task12_3_1` directory of your repository in your preferred
code editing environment. You should see the following code in `src`:

```{code} kotlin
:filename: Point.kt
class Point(var x: Double, var y: Double)
```

```{code} kotlin
:filename: Main.kt
fun main() {
    val p = Point(4.5, 7.0)
    println("(${p.x}, ${p.y})")
}
```

Make sure that this code compiles and runs, with

    ./kotlin run

Then change `main()` so that it attempts to create the `Point` object without
specifying values for `x` and `y`. Try recompiling with

    ./kotlin build

What errors do you see?

Now modify the class definition so that it includes the secondary constructor
described above. Compile and run the program to verify that this fixes the
issue.

Next, add these two lines to `main()`:

```kotlin
val q = Point(4, 7)
println("(${q.x}, ${q.y})")
```

Try recompiling the code. What happens?

Finally, add another secondary constructor to `Point` that fixes the issue.
Verify that the program compiles and runs successfully after making this
change.

:::{hint .dropdown} Hints
* Your secondary constructor will need parameters. What type do they
  need to be?

* Note that these parameters should NOT be prefixed with `val` or `var`.
  Those keywords are used to specify properties, and you are only allowed
  to do that in the primary constructor parameter list or separately in
  the body of the class.

* Remember that Kotlin has extension functions to support conversion of
  values to numeric types. These can be invoked on numeric values as well
  as strings...
:::

## Another Example

Consider this class definition:

```kotlin
import kotlinx.datetime.LocalDate

class Person(var name: String, val birth: LocalDate) {
    var isMarried = false
}
```

This represents a person using three properties: their name, their date of
birth[^date], and their marital status. Notice that the latter is defined and
initialized in the body of the class, rather than being specified as
part of the primary constructor.

We are free to mix these different styles of property definition and
initialization as we see fit. The design decision made here was that when
we represent a person, their name and date of birth must always be provided,
but it most cases it can be assumed that they are unmarried. If an object
representing a married person is required, the `apply()` scope function can
be used to create it in a reasonably convenient way:

```kotlin
val marriedPerson = Person("Joe", birthDate).apply {
    isMarried = true
}
```

Notice also that properties `name` and `isMarried` are defined using `var`,
but `birth` is defined using `val`. This is because a person's date of
birth is a fixed value that can never change, whereas their name and marital
status can both change during their lifetime.

### Task 12.3.2

Open the `tasks/task12_3_2` directory of your repository. Edit `Person.kt`
and add to this file the `Person` class definition shown above. Be sure to
include the `import` statement as well.

Next, edit `Main.kt` and write a program that creates a `Person` object and
then prints the person's name, date of birth and marital status.

You can create a `LocalDate` object to represent date of birth with code
like this:

```kotlin
val date = LocalDate(1997, 8, 23)
```

Note the ordering of arguments here: year first, then month, then day.

Check that your program compiles and runs successfully.

Dates are often manipulated as strings, in [ISO 8601 format][fmt]
(e.g., `"1997-08-23"`). Add a secondary constructor to `Person` that allows
date of birth to be supplied as such a string.

:::{hint .dropdown}
Your secondary constructor will need to convert a string to a `LocalDate`
object before delegating to the primary constructor. You can do this by
passing the string to the `LocalDate.parse()` function.
:::

Finally, modify `main()` so that when the `Person` object is created, date of
birth is now specified as a string. Recompile and run the program to verify
that it behaves as expected.

## Initializer Blocks

There's a problem with the `Person` class. In the implementation shown above,
it is possible to create a `Person` object using *any string* as the person's
name. It would be better if we could impose some restrictions such as
preventing use of an empty string, or use of a string consisting solely of
spaces. However, there is no way do to this currently, because the primary
constructor just assigns values to properties.

There are a couple of different ways of solving this problem. One of them
is to give the `Person` class an **initializer block**.

Initializer blocks are blocks of code enclosed in braces, marked with the
`init` prefix. Any blocks marked in this way will be injected into the object
construction process. You can do almost anything you like inside an
initializer block, but a common thing to do is **property validation**:

```{code} kotlin
:linenos:
:emphasize-lines: 6-8
import kotlinx.datetime.LocalDate

class Person(var name: String, val birth: LocalDate) {
    var isMarried = false

    init {
        require(name.isNotBlank()) { "Name cannot be blank" }
    }
}
```

In the example above, the `name` parameter is checked to make sure that
it is not blank, i.e., that its length is greater than zero and its contents
do not consist solely of whitespace characters. If this is not true, an
`IllegalArgumentException` will be thrown and object construction will fail.

Note that initializer blocks are effectively incorporated into the primary
constructor, even in cases where that primary constructor is implicit rather
than explicit. Thus they always execute when an instance of the class is
created.

### Task 12.3.3

Copy the files `Person.kt` and `Main.kt` from the `task12_3_2/src` directory
of your repository to `task12_3_3/src`.

Edit the new copy of `Main.kt` in `task12_3_3/src` and modify `main()` so
that it creates a `Person` object with a zero-length name, or a name
consisting only of spaces. Verify that the program compiles and runs
successfully.

Finally, modify the class so that it has the initializer block shown above.
Recompile the program and try running it again to verify that invalid names
trigger an exception.


[^date]: We represent date of birth using the `LocalDate` class provided
by the `kotlinx.datetime` library.

[fmt]: https://en.wikipedia.org/wiki/ISO_8601
