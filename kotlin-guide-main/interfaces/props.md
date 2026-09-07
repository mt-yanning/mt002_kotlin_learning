# Properties in Interfaces

We noted previously that interfaces cannot contain members whose purpose is
to supply objects with some of their state. An interface can still specify
properties, but **those properties are implicitly abstract**.

Just like the abstract methods of an interface, those abstract properties will
need to be implemented in any class that implements the interface.

Here's a small example of an interface that represents users of a computer
system:

```kotlin
interface User {
    val username: String
}
```

The interface `User` does not actually provide a `username` property that
other classes can use. Instead, `User` imposes a contractual obligation on
any classes that implement it, requiring them to provide a property with this
name, of type `String`. That property could have backing storage, or it could
be a computed property, but it has to exist in order for a class that
implements `User` to compile successfully.

For example, we could have a class `LocalUser`, representing local users of
the system. In this case, `username` is overridden with a regular `String`
property that has backing storage:


```kotlin
class LocalUser(override val username: String) : User
```

This implementation follows the compact class definition syntax that you have
seen earlier, but note the use of the `override` keyword. This is required by
the compiler.

In addition to `LocalUser`, we could have another class `SubscribingUser`,
representing external users who have registered with the system using their
email address:

```kotlin
class SubscribingUser(val email: String) : User {
    override val username: String
        get() = email.substringBefore('@')
}
```

A `SubscribingUser` has an `email` property with backing storage, representing
the email address with which a user subscribed. It also has a `username`
property, as required by the `User` interface, but in this case that property
has no backing storage; instead, it is computed on demand from the email
address.

With the interface and classes shown above, tasks such as displaying the
usernames of all system users become very straightforward:

```kotlin
val users = mutableListOf<User>()
...
users.forEach {
    println(it.username)
}
```

This is polymorphic code, much like that in the graphics application
[discussed earlier][poly].


[poly]: ../inherit/case-study.md#polymorphic-solution
