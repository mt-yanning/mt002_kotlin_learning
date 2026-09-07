# `if` Expressions

You can use `if`, in combination with `else if` and `else` where required,
to choose between different execution paths in a Kotlin program. The syntax
is basically the same as in C:

::::{tab-set}
:::{tab-item} Kotlin
```kotlin
if (number < 0) {
    println("Number too low")
}
else if (number > 100) {
    println("Number too high")
}
else {
    println("Number OK")
}
```
:::
:::{tab-item} C
```c
if (number < 0) {
    printf("Number too low\n");
}
else if (number > 100) {
    printf("Number too high\n");
}
else {
    printf("Number OK\n");
}
```
:::
:::{tab-item} Python
```python
if number < 0:
    print("Number too low")
elif number > 100:
    print("Number too high")
else:
    print("Number OK")
```
:::
::::

However, note that the Kotlin compiler is stricter than a C compiler. It
will require the test performed by `if` or `else if` to be a proper boolean
expression.

You can construct more complex boolean expressions in Kotlin using the logical
operators `&&` (AND) and `||` (inclusive OR), just as in C:

```kotlin
if (number < 0 || number > 100) {
    println("Number is out of range")
}
```

One important difference between Kotlin and C or Python is that, in Kotlin,
**selection structures are expressions, not statements**. This means that
a selection structure can be regarded as having a value, which can be
assigned to a variable. The value will be determined by the chosen execution
path, so each path should yield a result of the appropriate type.

Thus you could rewrite the example above like this:

```kotlin
val message = if (number < 0) {
    "Number too low"
}
else if (number > 100) {
    "Number too high"
}
else {
    "Number OK"
}

println(message)
```

Each branch yields a string, so the compiler will infer the type of
`message` to be `String`.

:::{caution}
In the original version of this example, we could have omitted the `else`
branch if we didn't want to print a message when `number` is in range.

We cannot do that here; **this new version requires an `else` branch**.

The reason is that we are assigning the result of the `if` expression to
a variable. The expression must therefore always yield a result. The `else`
branch guarantees this by providing a value that can be used in cases where
none of the tests in the other branches evaluate to `true`.

An `else` branch is only needed if you are using the result of the
expression in some way.
:::

Whilst C and Python don't allow you to duplicate an example like the one
above, they do support a simpler form of conditional expression. Here's an
example of that simpler form, written for all three languages so you can
compare the syntax:

::::{tab-set}
:::{tab-item} Kotlin
```kotlin
val sign = if (number < 0) '-' else '+'
```
:::
:::{tab-item} C
```c
char sign = (number < 0) ? '-' : '+';
```
:::
:::{tab-item} Python
```python
sign = '-' if number < 0 else '+'
```
:::
::::

This is often described as a **ternary expression**, because it has three
parts: a boolean expression; some code to evaluate if that expression has the
value `true`; and some code to evaluate if that expression has the value
`false`.

In the examples above, Python arguably has most natural and readable syntax
for ternary expressions, with C being the least readable in this regard.

## Exercises

````{exercise}
:label: ex-if-expr
:enumerator: 4.1
Some students are arguing over the following piece of Kotlin code, which is
supposed to read an integer from the user but limit the value to a maximum
of 100:

```kotlin
print("Enter a value for x: ")
var x = readln().toInt()
x = if (x > 100) 100
```

Alice says the code will behave as required. Bob disagrees, saying it won’t
compile, although he’s not sure how to fix it.

Charlie thinks that line 3 needs `else x` added to the end in order for it
to work, whereas Diane suggests changing line 3 to `if (x > 100) x = 100`.

Ellie thinks that the code won’t work because line 3 will give `x` a value of
0 if it is intially less than or equal to 100.

Which of the following statements is accurate?

1. Alice is right: there is no problem here.
1. Bob is correct, and Charlie & Diane's suggestions will both fix the issue.
1. Ellie is correct, and Diane's suggestion would fix the issue.
1. Bob is correct, and Diane's suggestion is the only suitable fix.
1. Ellie is right, and Charlie's idea is the only fix that will work.
````
