# Interfaces

We've seen in earlier sections how classes can form a hierarchy, in which
subclasses are specialized versions of more general superclasses. In Kotlin
and some other languages (e.g., Java and C#), a particular restriction
applies to this class hierarchy: each class cannot have more than one
superclass.

We've also seen that these superclasses can be **abstract**, with 'missing'
method implementations that have to be provided in subclasses.

**Interfaces** are similar to abstract classes in this respect, but are not
subject to the same limitations. A class can have at most one abstract class
as its superclass, but it can 'implement' multiple interfaces.

After completing this section, you will understand why interfaces are useful
in their own right, and how they complement abstract classes. You will also
know how to use them in Kotlin code.
