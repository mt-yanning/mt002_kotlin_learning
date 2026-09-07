# Defining a Lambda Expression

Consider the following [predicate function][pred], which returns `true` if
the given argument is an even integer, `false` if it is odd:

```kotlin
fun isEven(n: Int): Boolean {
    return n % 2 == 0
}
```

Because this is so simple, it can be written more compactly, with an
[expression body][expr]. This involves removing the braces, the `return`
statement and the declaration of a return type:

```kotlin
fun isEven(n: Int) = n % 2 == 0
```

But we can go one step further, and remove the name&mdash;turning it into
a lambda expression:

```kotlin
{ n: Int -> n % 2 == 0 }
```

## Exercises

```{exercise}
:label: ex-lambda-square
:enumerator: 8.1.1
Write down a lambda expression that accepts a `Double` value and returns the
square of that value. Use `x` as the name of expression's only parameter.
```

```{exercise}
:label: ex-lambda-lessthan
:enumerator: 8.1.2
Write down a lambda expression for comparing two `Int` values named `a`
and `b`. Your expression should return `true` if `a` is less than `b`,
otherwise `false`.
```


[pred]: https://en.wikipedia.org/wiki/Boolean-valued_function
[expr]: ../funcs/expression.md
