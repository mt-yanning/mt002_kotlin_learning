# Nullable Types

## Basic Concept

To understand the compilation error in the translation program from Task 10.1,
you must first understand that the data types we normally use in a Kotlin
program are **non-nullable**. This means that an `Int` variable or a `String`
variable, for example, cannot have the value `null`.

This is quite useful. It means we can invoke a method or extension function
on an `Int`, a `String` or an instance of some other data type with complete
confidence, knowing that there is definitely an object there, on which the
requested operation can be performed.

However, nulls can also be useful. For example, if a function is not able
to generate a sensible return value, it can be useful to signal this by
returning a null, rather than by throwing an exception. This is precisely
what happens when we use a key to look up a value in a map, but that key
is not present in the map.

Besides, it is actually _necessary_ for Kotlin to support the use of nulls,
because Kotlin is supposed to integrate seamlessly with Java, and object
references in Java can implicitly be null.

Kotlin addresses this need to support nulls by having a **parallel type
system**, consisting of nullable variants of the standard non-nullable types.
These nullable variants have the same name as their non-nullable counterparts,
but with a `?` appended. Thus Kotlin has `Int?` to represent nullable
integers, `String?` to represent nullable strings, and so on.

The use of `?` as a suffix is a clever mnemonic device. The `Int?` type poses
the question "is this an integer or is it null?", whereas `Int` means "no
question that this is an integer".

:::{caution}
It's important to understand that there is **no extra code required
here**. The Kotlin standard library doesn't have code to implement the
`String` class, plus additional code to implement the `String?` class,
for example.

Instead, you should think of nullability as a 'label' attached to a
type declaration to indicate a degree of uncertainty. It means that
"a variable of this type might represent the value `null` instead of a
useful value".
:::

Now we can finally explain why the compilation error in Task 10.1 occurs.
When we use `[]` to look up a value in the map, the return type of this
operation is `String?`&mdash;meaning it might return a `String` object or it
might return a null. Thus the type of variable `result` is also `String?`.
The error occurs because `display()` is expecting a non-nullable string as
an argument, but it is being given a nullable string.

The Kotlin compiler is strict about attempts to use a value of a nullable
type in places where a value of a non-nullable type is expected. **It will
allow us to do this only if we explicitly check the value first, to make sure
that it is not null.**

## Benefits of Strictness

To understand why Kotlin's strict requirement for null checks is a good
idea, consider what happens in the Java language.

In Java programs, there is always the possibility that an object reference
might be null, but the compiler doesn't require references to be checked
before use. If a program attempts to invoke a method on an object but the
object reference is `null`, the result is a runtime error&mdash;specifically,
a `NullPointerException` (NPE).

NPEs can always be avoided through more careful programming, but Java
developers often aren't careful enough. Sometimes, an NPE 'leaks out' of an
application and is embarrassingly visible to users&mdash;as in the example
of @fig-npe, showing an NPE from a banking app.

```{figure} npe.png
:label: fig-npe
:enumerator: 10.2
:alt: Example of a NullPointerException in a real Java application (TSB's banking app)
:width: 420px
:align: center

An NPE in a Java application
```

This problem is much less common in Kotlin applications, thanks to the
strictness of the Kotlin compiler and the ease with which null checks can
be performed.
