# `val` & `var`

The `val` and `var` keywords both introduce named variables into a program,
but it's important to understand the difference between these two kinds
of variable.

When you define a variable with `val`, you are allowed to assign a value to
it **once, and once only**. Any subsequent attempt at assignment will cause a
compile-time error.

When you define a variable with `var`, you can assign a value to it as many
times as you like.

Here's an example:

```kotlin
val name = "Nick"
name = "Joe"       // compiler error

var age = 42
age = 43           // OK
```

## Task 2.4

Open the `tasks/task2_4` directory of your repository and edit `src/Main.kt`.

In this file, write a Kotlin program containing four lines of code. The first
line should create a `val` variable and assign an integer value to it; the
second line should print the value of the variable; the third line should
attempt to assign a new value to the variable; the fourth line should attempt
to print the value of the variable again.

Try compiling this program so that you understand exactly how the compiler
reacts to the error on the third line.

Fix the error by changing `val` to `var`, then recompile and run the program.

## Why Bother With `val`?

Wouldn't it be easier to just define everything as a `var`?

In practice there are many situations in programming where we give a name
to a value purely so that we can use it later in a program, and not because
we need to update that value. Then there are other situations where we do
need to update the value. Kotlin gives us syntax to distinguish explicitly
between these two different ways of using variables.

The advantage of being explicit is that the compiler can then help us catch
some programming errors. For example, suppose you have a program that
defines a variable `amount` as a `val` and another variable `newAmount` as
a `var`. At a later point in the code, you intend to update the value of
`newAmount` but accidentally type the variable's name as `amount`.

This mistake will lead to a compiler error, because you are attempting to
assign a new value to a `val`. If you had defined both variables using `var`,
the program would compile but would now have a bug in it&mdash;one that might
be hard to find and fix.

:::{tip}
The recommended approach in Kotlin is to **define variables using `val`
wherever you can**. Use `var` only in places where a variable will need to
be reassigned a value after it has initially been created.
:::

:::{caution} Caution
**Don't think that using `val` is the same as defining a constant in
your code!**

When you use `val`, all you are doing is establishing a permanent link
between an object and the name that you want to use to refer to that object.
The compiler will stop you associating the name with a different object,
**but it won't stop that object from changing its state**, should that be
possible for the object in question.

For example, you might have a `val` that is referencing an array of numbers.
The compiler will be happy for you to replace the contents of that array with
new values. What it won't let you do is reuse that variable to refer to
a *different* array of numbers, stored at some other location in memory.
:::

## Exercises

```{exercise}
:label: ex-val-var
:enumerator: 2.4
Here are three different situations in which we would need to use a variable.
In each case, decide whether the variable should be defined as a `val` or
as a `var`.

1. Representing the final result of a calculation
2. Representing a sum that we compute for values in an array
3. Representing input captured from the program user
```
