# Object Declarations

A class definition is effectively a reusable 'template' that specifies how
to create and use a particular kind of object. Object creation is handled
in a separate step, with code that invokes a constructor of the class, and
there are no limitations on how many objects we can create in this way.

However, Kotlin also allows us to combine class definition and instance
creation into a single step, using the `object` keyword. This can be useful
in scenarios where we want to restrict object creation, such that only a
single instance of a class can ever exist.

## Singletons

A **singleton** is an instance of a class that is guaranteed to be the only
instance of that class. This means that singletons are good ways of
representing anything that can exist only once in an application.

For example, imagine that a library has a Kotlin application allowing users
to search for books they are interested in. Details of those books are stored
in a database. Interaction with that database could be managed with a
`Database` class, but that would allow multiple instances to be created.
This wouldn't make sense, as there is only one database.

A better solution is to represent the database as a singleton, using `object`:

```kotlin
object Database {
    val connection = DriverManager.getConnection(...)

    val bookTitleQuery = "select * from Books where title like ?"

    fun findBooks(searchTerm: String): ResultSet {
        val statement = connection.prepareStatement(bookTitleQuery)
        statement.setString(1, searchTerm)
        return statement.executeQuery()
    }
    ...
}
```

Note the syntax here. The `object` keyword is followed by a name. Alhough
we would normally reference objects by names that begin with a lowercase
letter, the convention for singletons is to use an initial uppercase letter,
just as we would for classes.

After the name we have the body of the object, which can define both
properties and methods. In this case, we have properties representing the
connection to the database and an SQL query, and a method that runs the
query to find books whose titles match the given search term.

We would use the singleton like this:

```kotlin
val bookDetails = Database.findBooks("%Kotlin%")
```

:::{note}
'[Singleton][sng]' is an example of a [**design pattern**][pat]: a
well-understood, documented solution to a problem commonly encountered in
software development.

Implementing the Singleton pattern can be complicated in some object-oriented
languages, but the `object` keyword makes this trivial in Kotlin.
:::

## Companion Objects

A **companion object** is a special type of singleton defined within a class.
It provides a home for class-level properties and methods[^cls].

Here's a simple example:

```{code} kotlin
:linenos:
class Location(val longitude: Double, val latitude: Double) {
    companion object {
        const val MIN_LONGITUDE = -180.0
        const val MAX_LONGITUDE = 180.0
        const val MIN_LATITUDE = -90.0
        const val MAX_LATITUDE = 90.0
    }

    init {
        require(longitude in MIN_LONGITUDE..MAX_LONGITUDE) { ... }
        require(latitude in MIN_LATITUDE..MAX_LATITUDE) { ... }
    }
}
```

This class represents a location on the Earth by its longitude & latitude.
An initializer block (lines 9&ndash;12) is used to ensure that these
properties are within valid ranges.

We define the limits on longitude and latitude using named compile-time
constants (lines 3&ndash;6), to make the code clearer. These constants are
defined **at the class level**, within the companion object of `Location`[^alt].

Other code is able to refer to these properties in a couple of different
ways:

```kotlin
Location.MIN_LATITUDE
Location.Companion.MIN_LATITUDE
```

This first of these is shorthand for the second, and `Companion` appears
in the second example because we didn't give the companion object an
explicit name.

Companion objects can be named easily enough:

```kotlin
class Location(val longitude: Double, val latitude: Double) {
    companion object Limits {
        ...
    }
    ...
}
```

With this change, a full reference to a member of the companion object
looks like this:

```kotlin
Location.Limits.MIN_LATITUDE
```

:::{note}
Keep in mind that these constants are effectively associated with the *class*,
not with instances of the class. If you create a `Location` object and try to
access `MIN_LATITUDE` as if it were a property of that object, you will get
a compiler error.

In general, companion objects are a good place for properties that can shared
by all instances of a class. It would be a waste of memory to give each
instance its own copy of those properties.
:::

## Another Example

Let's return to our old friend, the `Person` class, for a more complex
example of how companion objects can be useful.

Imagine that you want to impose some limitations on the creation of `Person`
objects. In particular, you want to ensure that every `Person` object has
a unique name. Attempts to create a `Person` object with the same name as
another should fail.

The only way of achieving this is to prevent users of the class from invoking
the constructor of `Person`, and then take control of instance creation
yourself, by writing your own method to do so. This method has to be a
class-level method, defined in the companion object of `Person`:

```{code} kotlin
:linenos:
class Person private constructor(val name: String, val birth: LocalDate) {
    companion object Factory {
        private val names = mutableSetOf<String>()

        fun create(name: String, birth: LocalDate): Person {
            require(name !in names) { "Name must be unique!" }
            names.add(name)
            return Person(name, birth)
        }
    }
}
```

Notice here that the primary constructor is defined more verbosely, with
explicit use of the `constructor` keyword (line 1). We need to do this in
order to declare it as `private`. Doing this will prevent users of the class
from invoking the constructor themselves.

A user of the class must request creation of a `Person` object by using the
public method `create()`, defined inside `Person`'s companion object
(lines 5&ndash;9):

```kotlin
val p = Person.create("Sarah", LocalDate(2005, 7, 16))
```

The `create()` method is able to impose restrictions on object creation.
In this case, we keep track of the names given to people in a set of
strings called `names`. **Notice that this set is defined as a private
class-level property, within the companion object** (line 3).

The `create()` method checks whether the desired name is in this set,
aborting object creation with an exception if the name is found (line 6).
If the name isn't found, the set is updated with the desired name (line 7),
then the method invokes the primary constructor to create the `Person` object,
before returning the object to the caller (line 8).

Although users of `Person` cannot access the private constructor, the
`create()` method, being part of the class, is able to do so (after first
checking that creation should be permitted).

:::{note}
The choice of name for `Person`'s companion object is not accidental. Its
purpose here is to act as a 'factory' for creating `Person` objects.

This is an example of another design pattern, **Static Factory Method**.
:::

### Task 12.9

1. Open the `tasks/task12_9` directory of your repository in your preferred
   code editing environment. Edit `Person.kt` and modify the basic `Person`
   class definition in this file to match the example given above.

1. Edit `Main.kt`. In the `main()` function, write a line of code that
   attempts to create a `Person` in the normal way, by invoking the primary
   constructor. Verify that this line produces a compiler error.

1. Replace that line with a different line that creates a `Person` object
   by invoking the class-level `create()` method. Compile and run the
   program to make sure that there are no errors.

1. Add a line that attempts to create another `Person` object with the same
   value for the `name` property as the first. Verify that this leads to
   a run-time error.


[^cls]: Kotlin's approach to supporting features at the class level is
different from that of Java, C# or C++. In those languages, you can label
a member of a class with the keyword `static` to indicate that it is
associated with the class rather than any particular instance.

[^alt]: An alternative approach would be to define these constants at the
top level, outside the class. This would work fine, but defining them within
the class is a nicer solution.

[sng]: https://refactoring.guru/design-patterns/singleton
[pat]: https://refactoring.guru/design-patterns/what-is-pattern
