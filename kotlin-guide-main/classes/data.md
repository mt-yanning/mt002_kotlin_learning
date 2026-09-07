# Data Classes

Some of the classes we create in a software application are there to
represent the fundamental 'business entities' of a use case. For example,
in an e-commerce scenario, we might have classes named `Customer` and
`Order`, involved in placing an order for a product.

These **entity classes** are data-focused. Instances of them may represent
the results of a database query, or data that needs to be stored in a
database. We may need to manage multiple instances using one of Kotlin's
collection types. We may need to test whether two instances contain the same
data. We may even need the ability to generate strings giving details of
the data stored inside an instance (for testing and debugging purposes,
if nothing else).

Kotlin classes have methods named `equals()`, `hashCode()` and `toString()`
that support these tasks, but the default implementations generally won't
provide us with what we require. They need to be overridden in order to
be useful.

This is where Kotlin's **data classes** come in. When we designate a class
as a data class, the compiler will synthesize useful versions of `equals()`,
`hashCode()` and `toString()` for us[^other]. We can use the data class
feature to implement entity classes more quickly, with less code.

## Task 12.7

Let's investigate the behaviour of a regular class, and how that behaviour
changes when you designate it as a data class.

### Regular Class

Start by opening the `tasks/task12_7` directory of your repository in your
preferred code editing environment. Examine the source code files `Person.kt`
and `Main.kt`.

The former defines a class `Person`, whose implementation should be familiar
to you by now; the latter is a program that creates and manipulates two
instances of `Person`.

Compile and run the program, with `./kotlin run`. Study the output carefully.
This shows you the behaviour of the 'default' versions of `toString()`,
`hashCode()` and `equals()` that regular Kotlin classes all have.

You should see something similar to this:

    p1 == p1?     : true
    p1 == p2?     : false
    p1.hashCode() : 504bae78
    p2.hashCode() : 53e25b76
    p1.toString() : Person@504bae78
    p2.toString() : Person@53e25b76

Although objects `p1` and `p2` contain exactly the same data, comparing
them with `==` returns `false`, indicating that they are not considered to
be equal.

This is because using `==` invokes the `equals()` method, and the default
implementation of this method tests for equality of *identity* (do the
variables reference the same object?) rather than equality of *state* (do
the variables represent objects containing the same data?)

`p1` and `p2` also have different hash codes and different string
representations. The hash codes differ because the default implementation
of `hashCode()` is based on the memory address at which an object is
located. The string representations differ because the default behaviour
of `toString()` is to include an object's hash code in the generated string.

### Data Class

Now make one small change to the `Person` class. Add the keyword `data` to
the start of the definition. The class should now look like this:

```kotlin
data class Person(var name: String, val birth: LocalDate) {
   var isMarried = false
}
```

Recompile the program and run it. Notice how the output changes:

    p1 == p1?     : true
    p1 == p2?     : true
    p1.hashCode() : 042a878b
    p2.hashCode() : 042a878b
    p1.toString() : Person(name=Dave, birth=1997-08-23)
    p2.toString() : Person(name=Dave, birth=1997-08-23)

Now, `p1` and `p2` are considered equal, and they have the same hash codes.
They also have the same string representation, and that representation is
much more useful, showing us property values (although one property
is missing).

### Property Inclusion

The last five lines of `Main.kt` are currently commented out. Uncomment these
lines, then recompile the program and run it. The program will generate the
following additional lines of output:

    p1 marries
    p1 == p2?     : true
    p1.hashCode() : 042a878b
    p1.toString() : Person(name=Dave, birth=1997-08-23)

Think about what is happening here. the `isMarried` properties of `p1` and
`p2` now have different values, but the objects are still being reported as
equal, and they still have the same hash codes and string representations.

You may have already guessed the reason for this. The `isMarried` property
is currently ignored by the implementations of `equals()`, `hashCode()` and
`toString()` that were generated for us when we made `Person` a data class.

To fix the problem, modify the `Person` class so that `isMarried` is now
defined as part of the primary constructor. The class should now look
like this:

```kotlin
data class Person(
   var name: String,
   val birth: LocalDate,
   var isMarried: Boolean = false
)
```

If you recompile and run the program, it should now behave as expected.

:::{caution}
Data classes are a handy way of creating fully functional entities without
having to write lots of 'boilerplate' code yourself.

Just remember that any properties that are important in determining object
equality, hash code or string representation **need to be defined within
the primary constructor**.
:::

## Should All Classes Be Data Classes?

The short answer is No.

Entity classes are not the only kind of class that we find when we analyze a
use case or user story. Some classes are **boundary classes** that represent
points of interaction with a user of a system. For example, an `OrderForm`
class might represent the UI with which a customer places an order.

Other classes turn out to be **control classes**, which orchestrate the flow
of events in a use case. For example, an `OrderPlacement` class might be
used to capture the logic of checking stock levels, taking payment from a
customer and then placing the order for an item.

Boundary classes and control classes are not data-focused like entity classes
are. We won't need to compare instances of them for equality, store instances
of them in a collection, or display the state of an instance. Hence there is
no benefit in adding to these classes the extra code that supports such
activities.


[^other]: Designating a class as a data class also adds functions to
support object copying and destructuring operations, but we won't discuss
these further here.
