---
short_title: "Variables in Kotlin"
---

# Defining Variables in Kotlin

You introduce a variable into Kotlin code by using either the `val` or `var`
keyword, followed by the name of the variable, followed by an assignment
operation that gives it a value:

```kotlin
val name = "Nick"
var age = 42
```

:::{attention .simple icon=false} **Question**
What are the types of `name` and `age` in this code?
:::

:::{tip .simple .dropdown icon=false} **Answer**
`name` is a `String`, and `age` is an `Int`.

Note: you must use this exact spelling for the type names. Kotlin is
case-sensitive, like most programming languages.
:::

The code example above raises some important questions:

1. Why do we not need to specify types for either of these variables?
1. Why is the type of the `age` variable not ambiguous?
1. Why is one of the variables defined with `val` and the other with `var`?

We address the first two of these questions below, and cover the third
in the [next section][next].

## Type Inference

Like C, Kotlin requires that the type of a variable is known at compile time.
However, unlike C, **Kotlin can often infer what that type should be**,
freeing us from the need to specify it explicitly. This gives Kotlin some of
the lightweight feel of a dynamically-typed language like Python.

Consider the two examples from earlier. These could be written more
explicitly as

```kotlin
val name: String = "Nick"
var age: Int = 42
```

**However, we do not need to be this explicit.** Kotlin can infer that `name`
is a `String` because the value we are assigning to `name` consists of
characters enclosed in double quotes.

Similarly, Kotlin can infer that `age` should be of type `Int` because we
are assigning to it a value that consists solely of digits.

Note that type inference in the second example is *not* ambiguous, despite
the fact that Kotlin has a number of different integer types. This is because
integer literals consisting only of digits are always regarded as `Int`
values. Floating-point literals are also not ambiguous: a value like `3.5`
is always treated as a `Double`.

If you want a different integer or floating point type to be inferred when
assigning to a variable, you can use the special suffixes [described earlier][lit].

:::{caution} Caution
**Sometimes, you have to specify types explicitly.**

For example, Kotlin doesn't provide special syntax to indicate that an
integer literal should be represented as a `Short` or `Byte` value, so in
those cases you will need to indicate explicitly the type of the variable
that will hold such a value.

Another example is [function definitions][func]. When defining a function
that has parameters, you must always specify the types of those parameters.
:::

## Task 2.3

Open the `tasks/task2_3` directory of your repository and edit `src/Main.kt`.

Add to the `main()` function the following lines of code:

```kotlin
val myAge = 29u
val universeAge = 13_800_000_000L
val status = 'M'
val name = "Sarah"
val height = 1.78f
val root2 = Math.sqrt(2.0)
```

Check that your program compiles, with

    ./kotlin build

Then see if you can predict the type of each of these variables. Make a note
of your predictions.

To check whether you have predicted the type of variable `myAge` correctly,
add the following print statement to the program:

```kotlin
println(myAge::class)
```

Add a similar print statement for each of the other variables, then run the
program with

    ./kotlin run

How many of your predictions were correct?

## Exercises

````{exercise}
:label: ex-type-infer
:enumerator: 2.3
Some students are arguing over the following piece of Kotlin code:

```kotlin
val pi: Float = 3.14159
```

Alice says that this will trigger a compiler error, which could be fixed by
removing the type declaration. Bob disagrees, saying that this code will
trigger a warning, one that we can simply ignore.

Charlie agrees with Alice but suggests an alternative fix of appending `f`
to the line. Diane also thinks that something should be appended, but she
insists that a semicolon is needed at the end of the line. Ellie suggests
that `Float` could be replaced with `Double`.

Who has made valid suggestions here?

(Note: more than one of these suggestions may be valid!)
````


[next]: val-var.md
[lit]: numbers.md#integer-literals
[func]: ../funcs/index.md
