# Rich Assertions

In Part 1, you learned how to write [assertions in Kotest][asrt] using a
**matcher**, `shouldBe`.

You can do a lot with `shouldBe`, but Kotest provides a large set of matchers
that perform other kinds of assertion. Using these can simplify unit tests
and make them much easier to read.

We present examples of some of these below, but for full details you should
consult the Kotest documentation for [Core Matchers][core].

## Floating-Point Values

When testing the result of a floating-point expression, `shouldBe` won't
suffice on its own, due to the inexactness of floating-point arithmetic.
Instead, you need to test whether the result of the expression is *close
enough* to the expected value.

you can do this by combining `shouldBe` with another matcher, `plusOrMinus`.
This allows you to specify how much deviation above or below the expected
value is acceptable.

For example, to test whether variable `result` is close enough to 3.0,
you could use

```kotlin
result shouldBe (3.0 plusOrMinus 1e-6)
```

Note that the parentheses are needed here, to ensure that the `plusOrMinus`
matcher takes priority over `shouldBe`.

This assertion would succeed if the value of `result` was between 2.999999
and 3.000001.

:::{note}
If you are making an assertion about a `Double` value, you'll need to use the
version of `plusOrMinus` from the package `io.kotest.matchers.doubles`. You
will need an import statement like this in order to use it:

```kotlin
import io.kotest.matchers.doubles.plusOrMinus
```

There is an equivalent matcher for `Float` values in `io.kotest.matchers.floats`.
:::

## Comparisons

Consider how you might assert that the result of some computation is a value
less than 10. You could do this using `shouldBe` like so:

```kotlin
result < 10 shouldBe true
```

Alternatively, you could write

```kotlin
result shouldBeLessThan 10
```

This reads more naturally and is easier to understand.

Kotest also provides the other comparison matchers that you would expect:

    shouldBeGreaterThan
    shouldBeLessThanOrEqualTo
    shouldBeGreaterThanOrEqualTo

These can be used to test values of any type for which the standard
comparison operators are defined.

:::{note}
These matchers are provided in the package `io.kotest.matchers.comparables`.
You will need the appropriate import statement to use one of them, e.g.,

```kotlin
import io.kotest.matchers.comparables.shouldBeLessThan
```
:::

## Testing Strings

Consider how you might test whether the string resulting from some text
manipulation was blank, or was empty, or started with a particular sequence
of characters. You could make the necessary assertions like this:

```kotlin
str.isEmpty() shouldBe true
str.isBlank() shouldBe true
str.startsWith("Foo") shouldBe true
```

Alternatively, you could use Kotest's string matchers:

```kotlin
str.shouldBeEmpty()
str.shouldBeBlank()
str shouldStartWith "Foo"
```

Again, the benefit here is improved readability.

Notice the change in syntax here. Asserting that a string should be empty,
for example, doesn't require an additional argument, therefore infix notation
cannot be used for the call.

Some of the string matchers are particularly powerful. For example, if you
need to assert that a string should contain at least one decimal digit,
or that it contains only decimal digits, or that it is valid representation
of an integer in a particular number base, you can use

```kotlin
str.shouldContainADigit()
str.shouldContainOnlyDigits()
str.shouldBeInteger()           // decimal representations
str.shouldBeInteger(radix=16)   // hexadecimal representations
str.shouldBeInteger(radix=2)    // binary representations
```

There are many other string matchers. [See the documentation][core] for
details of these.

:::{note}
These matchers are provided in the package `io.kotest.matchers.string`.
You will need the appropriate import statement to use one of them, e.g.,

```kotlin
import io.kotest.matchers.string.shouldBeEmpty
```
:::

## Testing Collections

If the result of computation is a collection of some kind, then you could
make assertions about emptiness, size and contents using `shouldBe`, like so:

```kotlin
result.isEmpty() shouldBe true
result.size shouldBe 10
42 in result shouldBe true
```

Alternatively, you could use others matchers to make these assertions in a
clearer way:

```kotlin
result.shouldBeEmpty()
result shouldHaveSize 10
result shouldContain 42
```

You can also make more sophisticated assertions about collection contents:

```kotlin
result.shouldContainAll("a", "b", "c")
result.shouldContainExactly("a", "b", "c")
tesult.shouldContainExactlyInAnyOrder("a", "b", "c")
result.shouldBeSorted()
```

The first of these examples would succeed for any collection containing these
three strings and would allow other strings to be present. For example, it
would succeed for `["a", "b", "c"]`, `["b", "a", "c"]` and
`["a", "b", "c", "d"]`, but fail for `["a", "b", "d"]`.

The second example would succeed only for collections containing exactly
`["a", "b", "c"]`. It would fail for `["a", "b", "c", "d"]` or
`["b", "a", "c"]`.

The third example relaxes the ordering requirement, so would succeed for
both `["a", "b", "c"]` and `["b", "a", "c"]`.

The final example is used specifically with lists, and would succeed for any
list whose elements were sorted into their natural ascending order.

There are many other matchers that can be used with collections. [See the
documentation][core] for details of these.

:::{note}
These matchers are provided in the package `io.kotest.matchers.collections`.
You will need the appropriate import statement to use one of them, e.g.,

```kotlin
import io.kotest.matchers.collections.shouldHaveSize
```
:::

## Inspectors

Kotest inspectors allow for more detailed assertions to be made about the
contents of a collection. For example, suppose you need to verify that
a particular collection of strings contains at least two strings that are
at least 10 characters long. You can do this using the `forAtLeast()`
inspector, with a lambda expression containing the `shouldHaveMinLength`
matcher:

```kotlin
result.forAtLeast(2) {
  it shouldHaveMinLength 10
}
```

See [the Inspectors documentation][ins] for more details.

## Logical Negation

The matchers discussed above have involved 'positive' assertions that
something is true about the value being tested, but it important to note
that Kotest also provides matchers representing the logical negation of
these assertions.

For example, you can use `shouldNotBe` to assert that the result of
computation should *not* have a particular value.

Other examples include

* `shouldNotBeEmpty` and `shouldNotBeBlank`, for strings
* `shouldNotBeLessThan`, `shouldNotBeGreaterThan`, etc, for comparisons
* `shouldNotHaveSize` and `shouldNotContain`, for collections

## Testing For Exceptions

:::{danger} Important
If your code throws exceptions, it's *essential* to have some of your unit
tests check whether these exceptions are thrown when expected.
:::

Kotest provides the following functions for testing exceptions. Each of
them accepts a lambda expression as an argument, containing the code that is
supposed to trigger the exception. The first two are generic functions that
also require an exception type to be specified.

    shouldThrow
    shouldThrowExactly
    shouldThrowAny

For example, suppose you have a class `Money`, representing an amount of
money as euros and cents. Attempting to create a `Money` object with a
negative number of euros should trigger an `IllegalArgumentException`.

You could test for this behaviour using `shouldThrow()` like so:

```kotlin
"Exception when creating Money with negative euros" {
    shouldThrow<IllegalArgumentException> {
        Money(-1, 0)
    }
}
```

This test will pass if creating a `Money` object with a negative value for
euros throws an instance of `IllegalArgumentException` *or any of its
subclasses*. If you want to exclude subclasses, use `shouldThrowExactly`
instead.

If you don't care about the exception type and simply want to verify that
an exception occurs, you can use simpler code to make that assertion:

```kotlin
shouldThrowAny { Money(-1, 0) }
```

Note that all three of these 'should throw' functions return the exception
object that was thrown. You are free to ignore this, or you can use it to test
the error message associated with the exception, using the `shouldHaveMessage`
matcher:

```kotlin
"Exception when creating Money with negative euros" {
    val exception = shouldThrow<IllegalArgumentException> {
        Money(-1, 0)
    }

    exception shouldHaveMessage "Invalid euros"
}
```

:::{note}
These matchers are provided in the package `io.kotest.assertions.throwables`.
You will need the appropriate import statement to use one of them, e.g.,

```kotlin
import io.kotest.assertions.throwables.shouldThrow
```
:::

<!-- TODO: add a task here that uses these richer assertions? -->


[asrt]: ../testing1/kotest.md#expressive-assertions
[core]: https://kotest.io/docs/assertions/core-matchers.html
[ins]: https://kotest.io/docs/assertions/inspectors.html
