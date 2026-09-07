# Test-Driven Development

In **test-driven development** (TDD), tests are written *before* the code
that needs to be tested.

Why might you choose to do this?

One reason is that it forces you to implement the test. With a 'test last'
approach, there can sometimes be a temptation to skip one or more tests.
(When you are under pressure to deliver working code quickly, it is
surprisingly easy to delude yourself that a piece of code is "bound to be
correct", and therefore doesn't require tests.)

But there is much more to TDD than that. By writing a test first, you are
forced to think carefully about how a method should be called and what
the precise outcome of calling it should be; in essence, TDD clarifies the
detailed requirements for your code.

TDD also helps to define a clear endpoint for implementation of a feature;
when you have tests that cover each aspect of the behaviour required of that
feature, and they all pass, you've finished. Advocates of TDD argue that this
approach leads to cleaner and simpler code that does no more than what
is required.

:::{note}
The remainder of this section is an extended task in which you will use TDD
to create a class named `Money`, representing an amount of money as euros
and cents.

**You will need to work through it right to the end** in order to get a
proper idea of what TDD feels like in practice.
:::

## Getting Started

The `tasks/task13_4` directory of your repository is a Gradle project in
which you will develop the `Money` class. The class will be implemented in
`Money.kt`, in the `src/main/kotlin` subdirectory of the project. Tests will
be written in the file `MoneyTest.kt`, in the `src/tests/kotlin` subdirectory.
Open these two files now.

### First Creation Test

The starting point of the TDD cycle is to **write a test that fails**, so
let's do that now. Write the following in the `MoneyTest` class:

```kotlin
"Can create a Money" {
    val m = Money(1, 50)
}
```

This code encapsulates two design decisions: one is that there should be a
class named `Money`; the other is that two integer values (representing
number of euros and number of cents) must be supplied in order to create a
`Money` object.

If you have written this code using an IDE such as IntelliJ, you will see
that it is highlighted as an error. If you try to run the test manually,
e.g., with `./gradlew test`, you will see a compiler error reporting that
`Money` is an 'unresolved reference'.

You have written a test that fails[^fail], as required in TDD; it is this
failure that motivates creation of the `Money` class.

Create the following minimal definition of `Money`:

```kotlin
class Money(euros: Int, cents: Int)
```

Run the tests again. They should now pass.

**You should not implement anything more at this stage.** A central
principle of TDD is that you write only the minimum of code needed to get
a test to pass. The need for a more complex implementation has to be
driven by the existence of additional failing tests.

### Adding Assertions

The test could be improved by making it actually assert something, so modify
it to look like this:

```kotlin
"Can create a Money" {
    val m = Money(1, 50)
    withClue("euros") { m.euros shouldBe 1 }
    withClue("cents") { m.cents shouldBe 50 }
}
```

The modified test captures another design decision; namely that the `Money`
class has properties named `euros` and `cents`. Since these properties don't
yet exist, the test fails to compile.

Modify the `Money` class so that it looks like this:

```kotlin
class Money(euros: Int, cents: Int) {
    val euros = 1
    val cents = 50
}
```

**Don't be tempted to do anything else at this stage**; as yet, our test
requires only that the `euros` and `cents` properties have values of 1 and
50, respectively.

If you rerun the tests, they should now pass.

:::{note}
It is obvious that these properties shouldn't really have hard-coded values,
but you need to bear in mind that TDD encourages implementation of the
**simplest possible solution that makes the tests pass**.

Only when we have a failing test that identifies the need for something
more complicated should we add that complexity. This is a powerful way of
preventing 'over-engineering' of the code.
:::

### Second Creation Test

Add a test to see whether a different amount of money can be created properly:

```kotlin
"Can create a different Money" {
    val m = Money(2, 99)
    withClue("euros") { m.euros shouldBe 2 }
    withClue("cents") { m.cents shouldBe 99 }
}
```

Rerun the tests and you'll see a failure. The test report will provide
further details:

```
io.kotest.assertions.MultiAssertionError: The following 2 assertions failed:
1) euros
expected:<2> but was:<1>
   at MoneyTest$1$2.invokeSuspend(MoneyTest.kt:18)
2) cents
expected:<99> but was:<50>
   at MoneyTest$1$2.invokeSuspend(MoneyTest.kt:19)
```

The only sensible way of getting this failed test to pass as well as the
previous one is to have the properties store the values that were supplied
to the constructor:

```kotlin
class Money(euros: Int, cents: Int) {
    val euros = euros
    val cents = cents
}
```

Make these changes, then rerun the tests. They should now pass.

## A Refactoring Opportunity

The `Money` class as it stands can be implemented in a more concise way,
without changing its functionality. Making such a change, in which structure
is improved with affecting behaviour, is known as [refactoring][ref].

Having good unit tests available is an essential prerequisite for refactoring.
If you have good tests, you can make structural improvements with confidence,
safe in the knowledge that any mistakes will cause a test to fail.

Try this out now. Refactor the `Money` class into a compact, single line
implementation:

```kotlin
class Money(val euros: Int, val cents: Int)
```

Then run the tests again. They should still pass.

One other thing we can do is turn `Money` into a [data class][data]:

```kotlin
data class Money(val euros: Int, val cents: Int)
```

Make this change, then run the tests again. They should still pass.

## Improving Robustness

Another required feature is that is shouldn't be possible to create a `Money`
object with an invalid number of euros or cents. Any attempt to do so should
result in an `IllegalArgumentException` thrown by the constructor.

1. Identity a minimal number of tests that will be required to verify that
   that this feature has been implemented correctly.

1. For each of these, write the test first, run all the tests to make sure
   that it fails, then make changes to `Money` that are sufficient for
   the test to pass.

:::{hint .dropdown} Hints
You can drive implementation of this feature with as few as three tests:

* It shouldn't be possible to create a `Money` with a negative value for euros
* It shouldn't be possible to create a `Money` with a negative value for cents
* It shouldn't be possible to create a `Money` in which cents has a value
  larger than 99

See the section on [rich assertions](assertions.md) for details of how to
assert that a particular exception is thrown by code.
:::

## Further Steps

Let's use TDD to implement one more feature in `Money`: the ability to add
two amounts together, yielding a new `Money` object representing the
total amount.

### First Addition Test

Add the following test to `MoneyTest`:

```kotlin
"€1.50 + €1.00 is €2.50" {
    Money(1, 50) + Money(1, 0) shouldBe Money(2, 50) 
}
```

This won't compile, because we've not defined how the addition operator
should work for `Money` objects.

To fix the error, add the following minimal implementation of the addition
operator to `Money`:

```kotlin
operator fun plus(other: Money) = Money(2, 50)
```

This is obviously wrong, but is sufficient for the test to compile and pass.

:::{note}
This is an example of **operator overloading**.

We've not covered operator overloading formally, but if you are curious
about it, see the [official language documentation][op] for more details.
:::

### Second Addition Test

Write another test, one that adds a different number of euros:

```kotlin
"€1.50 + €2.00 is €3.50" {
    Money(1, 50) + Money(2, 0) shouldBe Money(3, 50)
}
```

This will fail when executed, with an error like that shown in @fig-money-fail.

```{figure} money-fail.png
:label: fig-money-fail
:enumerator: 13.4
:alt: Screenshot of a Gradle test report showing a failed unit test
:width: 80%

Test failure for the addition of two `Money` objects
```

<!--TODO: update this screenshot, or replace with text -->

The failed test motivates a change to the implementation of the addition
operator, so that it adds the `euros` properties of the two `Money` objects:

```kotlin
operator fun plus(other: Money) = Money(euros + other.euros, 50)
```

Make this change and rerun the tests. They should now all pass.

### Third Addition Test

Verify that cents are added correctly with a test like this:

```kotlin
"€1.50 + €0.01 is €1.51" {
    Money(1, 50) + Money(0, 1) shouldBe Money(1, 51)
}
```

This should fail when you rerun the tests.

Make the minimal changes necessary for the failing test to pass:

```kotlin
operator fun plus(other: Money) = Money(euros + other.euros, cents + other.cents)
```

### Final Addition Test

There is one further aspect of adding together two `Money` amounts that we
need to test. If the sum of the `cents` values equals or exceeds 100, we need
to represent this as an additional euro plus zero or more remaining cents.

Add this test to `MoneyTest`:

```kotlin
"€2.99 + €0.01 is €3.00" {
    Money(2, 99) + Money(0, 1) shouldBe Money(3, 0)
}
```

This should fail when you run the tests.

Make the changes to the addition operator that are needed for this final test
to pass. We'll let you figure those out for yourself!

:::{note}
Compare the sizes of the `Money` and `MoneyTest` classes.

What do you notice?
:::

## Other Things To Try

You've hopefully done enough now to understand the TDD cycle, but if you
want to carry on and gain more experience of it, here are a few optional
tasks:

* Override `toString()` so that it returns strings like `€0.01` and `€10.50`
* Implement comparisons using the `<`, `<=`, `>`, `>=` operators
* Add support for subtraction of one amount of money from another

**In each case, be sure to follow a test-driven approach**: write a test to
verify one aspect of the desired behaviour, make sure it fails, then write
the minimum amount of code needed for the test to pass. Repeat these steps
until all aspects of the desired behaviour are covered by tests that pass.

You could also try refactoring the tests&mdash;e.g., introducing a
[test fixture](fixtures.md).

## Final Thoughts

You may still be unconvinced by TDD. Newcomers to the concept often feel
that it is slow and unnecessarily cautious, forcing you to take small steps
towards the desired implementation when the nature of that implementation is
'obvious'&mdash;but this is missing the point.

Take a close look at the tests you have written in `MoneyTest`. Effectively,
these document the required behaviour of the `Money` class:

- There are two tests that document the requirement that euros and cents
  values are stored in, and can be retrieved from, a `Money` object.

- There are three tests that document the constraints that should be
  enforced on the values of euros and cents (euros not negative, cents in
  the range `0..99`).

- There are four tests that collectively encode the rules governing how two
  sums of money should be added together.

Use of TDD has resulted in the
development of a complete *and executable* requirements specification for
the `Money` class.  Without using TDD, we might not have arrived at such a
complete specification.

Because the requirements are specified so comprehensively by tests, you can
have more confidence in the correctness of your implementation. You can also
be more confident about refactoring the class in future development work,
knowing that unintended changes in its behaviour will be flagged by failing
tests.

Finally, TDD's emphasis on not implementing more code than is necessary to
make the tests pass means that you haven't wasted any time over-engineering
the `Money` class by adding features that aren't yet needed.


[^fail]: Failure to compile counts as a failed test here.

[ref]: https://en.wikipedia.org/wiki/Code_refactoring
[data]: ../classes/data.md
[op]: https://kotlinlang.org/docs/operator-overloading.html
