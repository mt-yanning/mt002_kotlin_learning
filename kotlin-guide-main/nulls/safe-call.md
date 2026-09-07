---
short_title: "Safe Call Operator"
---

# The Safe Call Operator

## Basic Concept

Kotlin makes writing null-safe code a lot easier than Java does, first by
controlling the locations where null checks ought to be done, and second by
giving us **more elegant tools for doing those checks**.

The first of these tools is the **safe call operator**, `?.`

You are already familiar with `.`, the regular call operator. We use this
whenever we need to invoke a method or extension function on an object,
or access one of its properties. The Kotlin compiler won't allow use of `.`
on an instance of a nullable type, unless a null check has been done&mdash;but
it will allow `?.` to be used with nullable values.

In effect, the safe call operator **does the null check for us**, as well as
invoking the specified method or function if the null check establishes that
the value is not null.

For example, if `text` is a variable of type `String?`, we can invoke the
`reversed()` extension function on this variable like so:

```kotlin
val result = text?.reversed()
```

If `text` is null, nothing else occurs and null will be assigned to
`result`. If `text` is not null, the function will be called and the value
of `result` will be whatever the function returns&mdash;in this case, the
contents of `text`, in reverse order.

## Call Chains

If you have a call chain, you will need to repeat the use of safe call
along the chain:

```kotlin
val result = text?.reversed()?.uppercase()
```

If later calls made in the chain are to functions that don't themselves
return nullable values, this can needlessly repeat the null checks&mdash;in
which case, a more efficient approach is to use safe call once, in
combination with the `let()` scope function:

```kotlin
val result = text?.let { it.reversed().uppercase() }
```

You can read this code as "Is `text` an actual string? If so, let it be
reversed and then uppercased to produce a value for `result`; if not, then
give `result` the value `null`."

## Task 10.4

Open the `tasks/task10_4` directory of your repository in your preferred
code editing environment. This is a copy of the solution to Task 10.3.2.

Edit `Main.kt` and replace the `when` expression in `display()` with a
single line of code that uses the safe call operator to handle null values.

Compile and run the program to check that it behaves sensibly.

:::{note}
The behaviour will change slightly; the program will now display `null`
when no translation is available, rather than `?`. We will fix that
issue in the [next section](elvis.md).
:::
