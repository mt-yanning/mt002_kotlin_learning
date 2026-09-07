# Key Concepts

## What Do We Test?

Sometimes, a function or method will be so small and simple that it will
not need to be tested directly. For example, when implementing a class in
Java or C++, we would typically write 'getter' methods for the fields of
the class that merely return the current value of the field. Code like this
is too simple to need testing itself. In general, however, you will need
unit tests for the majority of code that you write.

Note also that you shouldn't necessarily be writing one test for each
function or method in your code. Instead, think about how each function or
class is expected to behave, and write a set of tests that verify each
aspect of that behaviour.

When verifying behaviour, it is important to **test at the boundaries where
behaviour changes**. It is quite common for mistakes to be made at these
boundaries. An example of this is the classic '[off by one][obo]' error that
crops up when writing loops, indexing arrays, etc.

It is also crucial to **test that errors occur when expected**. Programmers
have a tendency to focus on the 'happy path', when inputs are all valid, but
behaviour also needs to be correct in cases where invalid input has been
supplied, or computation fails for some other reason.

## Equivalence Partitions

Is it feasible to test using all possible inputs to a piece of code?

Consider these hypothetical C functions (bodies are not shown as they are
not relevant):

```c
int func1(char c) { ... }

int func2(int n) { ... }

int func3(int x, int y) { ... }
```

**How many possible inputs are there to each of these three functions?**

(If you are in a lab class, discuss your answers with the people sitting
near you.)

:::{note .dropdown} Discussion
A `char` in C occupies 8 bits, so there are 2{sup}`8` = 256 possible
inputs to `func1()`.

An `int` is normally represented using 32 bits, so there are 2{sup}`32`
= 4,294,967,296 possible inputs to `func2()`.

Since `func3()` has two `int` parameters there are (2{sup}`32`){sup}`2`
= 18,446,744,073,709,551,616 possible combinations of input value.

We can conclude from this that, whilst exhaustive testing might be possible
in a few cases, it will be infeasible most of the time. There will simply
be far too many values to test.
:::

An alternative to exhaustive testing is to identity ranges of input within
which code behaviour is expected to be the same. It is important to consider
ranges of invalid input here, as well as ranges of valid input.

For each of these **equivalence partitions**, we test using at least one
typical value, plus the boundary values. If tests pass for these few values,
it is reasonable to assume that they would pass for all values in the
partition.

For example, consider this function:

```kotlin
fun tooShort(str: String) = str.length < 8
```

There are two obvious equivalence partitions here, one containing all strings
whose length is less than 8 characters, the other strings whose length
is greater than or equal to 8 characters.

For the first of these partitions, `"xxxxxxx"` could be used as a boundary
value (or any other string containing 7 characters), and `"xxxx"` would
be suitable as a typical value. We would expect the function to return
`true` for each of these values.

For the second of these partitions, `"xxxxxxxx"` could be used as a boundary
value (or any other string containing 8 characters), and `"xxxxxxxxxxx"`
would be suitable as a typical value. We would expect the function to
return `false` for each of these values.

Each of these values represents a distinct **test case** for the `tooShort()`
function.

### Exercises

These exercises are based on the following Kotlin function:

```kotlin
fun grade(mark: Int) = when (mark) {
    in 0..39   -> "Fail"
    in 40..69  -> "Pass"
    in 70..100 -> "Distinction"
    else       -> "?"
}
```

```{exercise}
:label: ex-test-part
:enumerator: 6.2.1
How many equivalence partitions are there for the `grade()` function?
```

```{exercise}
:label: ex-test-cases
:enumerator: 6.2.2
Assuming that we test using a typical value and the boundary values of each
equivalence partition, how many different exam marks would be used in the unit
tests for the `grade()` function?
```

## Desirable Properties of Unit Tests

The acronym 'FIRST' is a useful way of remembering that unit tests should be

* **F**ast
* **I**solated
* **R**epeatable
* **S**elf-validating
* **T**imely

**Fast** means that a single unit test should require only a few milliseconds
to run. This is necessary because well-tested code will have a large number
of unit tests, and developers will want to run those tests frequently.

**Isolated** means that a test shouldn't have external dependencies. For
example, tests should not depend on a database that is used by other code,
otherwise there is a risk of that database changing and affecting results.
Test isolation also means that tests should be isolated *from each other*,
so that the outcome of running a test will not depend on the order in which
tests are executed.

Isolation makes it easier for tests to be **Repeatable**, meaning that they
produce the same outcome each time they run, provided there have been no
changes to the code being tested. Repeatability can be a challenge if you have
code that interacts with something that changes (e.g., the system clock). In
such cases, it may be necessary to replace the thing that changes with a
'dummy object' that generates fixed output.

**Self-validating** tests determine success or failure for themselves,
without requiring human intervention to check results. In practice, this
means that tests should make assertions, which either succeed or fail. The
testing framework should be able to count the failures and provide you
with details that help you track down the reason for the failure.

Tests should be written in a **Timely** fashion, at the point when you are
writing code that needs testing rather than much later. Timely testing means
that you shouldn't be writing large amounts of code before you turn your
attention to the tests that you need for that code. For example, if you
create a function to perform a task, you should write tests for that function,
and run them to make sure they all pass, *before* moving on to next piece
of code.

In the most extreme form of timely testing, [test-driven development][tdd],
unit tests will actually be written before the code that needs to be tested!

## Arrange, Act, Assert

Unit tests generally follow a standard pattern, consisting of three steps:

1. Arrange
1. Act
1. Assert

The Arrange step involves setting up the conditions needed for the test
to be performed. This might mean defining variables to represent a particular
set of values that will be passed to a function, or creating an instance of
a class and configuring it to be in a particular state.

The Act step involves carrying out the operation we wish to test. This
typically means invoking a single function or method.

The Assert step involves verifying that the operation produced the expected
outcome, using a function that makes an **assertion** about that outcome.

## Unit Testing Frameworks

Whilst it is certainly possible to write unit tests using only the native
features of a language (e.g., the `assert` statement in C and Python), it
is generally preferable to use a dedicated unit testing framework.
Frameworks provide a number of benefits, including

* Automated discovery of tests
* Simpler and more expressive ways of writing tests
* Control over which tests are run, and how they are run
* Support for various ways of reporting test results
* Integration with build systems

The Kotlin standard library includes some support for writing unit tests
using various backends (e.g., [JUnit][jun], in JVM-based projects).

More advanced frameworks such as [Kotest][kot] are also available.


[obo]: https://en.wikipedia.org/wiki/Off-by-one_error
[tdd]: https://en.wikipedia.org/wiki/Test-driven_development
[jun]: https://junit.org/
[kot]: https://kotest.io/
