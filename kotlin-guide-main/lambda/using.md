# Using a Lambda Expression

## An Example

Consider the following list of integers:

```kotlin
val numbers = listOf(1, 4, 7, 2, 9, 3, 8)
```

To extract the even numbers from this list, you can use the `filter()`
extension function that is provided for Kotlin collections. This expects to
receive a predicate function that returns `true` for values that should be
preserved, `false` for values that should be filtered out.

You could do the filtering using the `isEven()` function
[seen earlier](defining.md):

```kotlin
val evenNumbers = numbers.filter(::isEven)
```

(The `::` prefix is needed to create a **reference** to `isEven()`,
allowing you to pass that function to the `filter()` function.)

However, if this is only place in your code where it is necessary to
extract even numbers from a list, it seems wasteful to define a separate
named function to facilitate this. This is where lambdas, as 'anonymous
functions', come into their own. You could instead write the code to
extract the even numbers like this:

```kotlin
val evenNumbers = numbers.filter({ n: Int -> n % 2 == 0 })
```

{button}`Run this code <https://pl.kotl.in/VV0YaMFdi>`

Now the predicate is defined anonymously, at the point of use. There is no
need for its definition to occupy space elsewhere, cluttering the source
code.

## Simplifying The Syntax

You can simplify the syntax of using a lambda expression in up to four
distinct ways. Let's apply each of these, step by step, to the example of
filtering a list of numbers.

### Step 1

The first thing you can do is **omit the parameter type**, as it can be
inferred from the context in which the lambda is being used:

```kotlin
val evenNumbers = numbers.filter({ n -> n % 2 == 0 })
```

### Step 2

In cases like this one, where the lambda has a single parameter, the second
thing you can do is **make the parameter implicit**:

```kotlin
val evenNumbers = numbers.filter({ it % 2 == 0 })
```

Notice that the parameter list has gone entirely; all we have left is the
body of the expression, with the special name `it` now representing the
parameter.

### Step 3

The next thing is to **move the lambda outside of the parentheses**:

```kotlin
val evenNumbers = numbers.filter() { it % 2 == 0 }
```

Note: this can be done only in cases where the lambda is the final
argument of the function or method call, as is the case here[^final].

### Step 4

If the lambda is the *only* argument of the function or method call, then you
can make one further simplification and **remove the parentheses entirely**:

```kotlin
val evenNumbers = numbers.filter { it % 2 == 0 }
```

{button}`Run this code <https://pl.kotl.in/aO0CnLUtT>`

This is the final, most compact form, requiring ten fewer characters than
the original lambda.

::::{tab-set}
:::{tab-item} Final
```kotlin
val evenNumbers = numbers.filter { it % 2 == 0 }
```
:::
:::{tab-item} Original
```kotlin
val evenNumbers = numbers.filter({ n: Int -> n % 2 == 0 })
```
:::
::::

:::{danger} Important
In Kotlin, lambdas are usually written in the most compact way possible,
so take some time to study the simplifications outlined above and make
sure that you understand them.
:::


[^final]: Higher-order functions will often be written in such a way that
the expected function or lambda expression is the last parameter in the
parameter list, purely to facilitate this simplification.
