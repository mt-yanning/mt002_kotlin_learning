# Writing Unit Tests

This section is an extended task introducing the simplest way of writing
unit tests in KT projects, using the [`kotlin.test`][ktest] package from the
standard library. We consider how to do this in Gradle projects in the
[next section][next].

## Getting Started

Open `tasks/task6_3` in your preferred code editing environment. If you
examine `module.yaml` you will see the usual setting to specify the JVM as
the target, and _nothing else_. KT projects are preconfigured to use
`kotlin.test`, so there is no need to add any explicit dependencies here[^dep].

In the `src` subdirectory you will find files `Grades.kt` and `Main.kt`. The
former contains a definition of the `grade()` function discussed in earlier
sections; the latter contains a small program that demonstrates the function.

Unit tests must be kept separate from production code. KT looks for them
in a `test` subdirectory of the project. If you look in this subdirectory,
you'll find `GradeTest.kt`:

```{code} kotlin
:filename: GradeTest.kt
:linenos:
:lineno-start: 3
import kotlin.test.Test
import kotlin.test.assertEquals

class GradeTest {
    // Write tests here
}
```

Line 3 of this file imports the `Test` **annotation**. This is used to label
each individual test, so the testing framework can find it.

Line 4 imports one of the assertion functions provided by `kotlin.test`.
Note that these assertion functions are different from the built-in `assert()`
function provided by Kotlin. You shouldn't use the latter in your tests!

When using `kotlin.test`, unit tests must be organized as **classes**. We've
provided an empty class for you here (lines 6&ndash;8). Each test will be
implemented as a member function (method) of this class, annotated with
`@Test`.

:::{note}
We haven't covered classes yet, but don't worry: you don't need to understand
anything about this topic in order to write simple tests and run them.

For now, you can just think of the class as a container or 'namespace' for
the tests.
:::

## Your First Test

For your first unit test, let's verify that the `grade()` function produces
a grade of "Pass" when called with an exam mark of 55 as its argument.

Edit `GradeTest.kt` and add the following to the body of the class:

```{code} kotlin
:linenos:
@Test
fun `Mark of 55 gives a Pass`() {
    assertEquals("Pass", grade(55))
}
```

There are several noteworthy things about this code, discussed in the
subsections below.

### Function Definition Differences

There are two key differences between this function definition and those
you've seen earlier. First, the function is annotated with `@Test` to
indicate that it is a test. If you forget this, the testing framework will
ignore the function!

Second, the function is named differently, using a string of natural language,
with backticks as delimiters. You can use this technique to give any Kotlin
variable or function a more natural-looking name, containing spaces or other
characters that aren't normally allowed in identifiers.

We haven't used this technique before now because it can make code a lot
harder to read if it isn't used sparingly. When writing unit tests, however,
it proves to be very useful. The reason is that testing frameworks use the
name of the function when displaying test results. These results are clearer
and easier to read when the test names are strings of natural language rather
than normal function names[^java].

If you don't like this approach to naming tests, then you can just use normal
function naming conventions instead:

```{code} kotlin
:linenos:
@Test
fun markOfFiftyFiveGivesPass() {
    assertEquals("Pass", grade(55))
}
```

### Arrange, Act, Assert?

The body of the test is a single line of code, so use of the Arrange-Act-Assert
pattern isn't immediately obvious. We could have made it more obvious with
code like this:

```kotlin
val mark = 55                  // Arrange
val result = grade(mark)       // Act
assertEquals("Pass", result)   // Assert
```

However, this is needlessly verbose. The test can be condensed into a single
line without any loss of clarity. You should always aim to make your tests
concise but clear.

### `assertEquals()`

The `assertEquals()` function asserts that the expression passed in as the
_second_ argument has the expected value represented by the first argument.
Hence this test asserts on line 3 that the string "Pass" will be returned when
`grade()` is called with 55 as its argument.

If the assertion fails, `assertEquals()` will throw an exception, and the
testing framework will report this as a failed test. If no exception is
thrown, the testing framework will report this as a passing test.

:::{caution}
Take care to pass arguments to `assertEquals()` in the correct order, with
expected value first. If you don't get this right, the reporting of test
results might be confusing.
:::

## Running Tests

To run all of the tests in the project, enter the following command:

    ./kotlin test

Try it now. You should see output like this:

```
Started GradeTest
Started Mark of 55 gives a Pass()
Passed Mark of 55 gives a Pass()
Completed GradeTest

Test run finished after 107 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         1 tests found           ]
[         0 tests skipped         ]
[         1 tests started         ]
[         0 tests aborted         ]
[         1 tests successful      ]
[         0 tests failed          ]
```

You can see here that the name of the test and its status are logged, and
that the output ends with a summary of how many tests were found, how many
succeeded and how many failed.

### Using IntelliJ

**[Skip this bit](writing.md#finishing-the-test-suite) if you are not
using IntelliJ as your IDE!**

When you open a KT project in IntelliJ, it will generate for you a 'run
configuration' that executes KT's `run` command. It will not do the same for
the `test` command, so you'll need to do this yourself manually.

1. Select the menu item *Run > Edit Configurations...* This will bring up
   the 'Run/Debug Configurations' dialog (@fig-ij-config).

1. Select the default configuration, entitled "Run Module task6_3", then
   click the *Copy Configuration* button. (Alternatively, press
   {kbd}`Cmd`+{kbd}`D` on a Mac, or {kbd}`Ctrl`+{kbd}`D` on Linux & Windows.)

1. Rename the duplicated configuration to "Run tests", then change the entry
   for 'Execute Kotlin CLI Command' from `run` to `test`. Then click *OK*
   to save the new configuration.

   ```{figure} ij-config.png
   :label: fig-ij-config
   :enumerator: 6.3.1
   :alt: Screenshot of the Run/Debug Configurations dialog in the IntelliJ IDE
   :width: 80%

   Creating a run configuration for tests in IntelliJ
   ```

1. To run the tests, select "Run tests" from the Run Configurations drop-down
   menu in the top-right of the toolbar, then click the button with the
   green triangle icon.

   Test results should appear in a new panel at the bottom of the IDE
   window. Passing tests will be annotated with a green tick. You can rerun
   the tests using the buttons on this dialog.

   ```{figure} ij-results.png
   :label: fig-ij-results
   :enumerator: 6.3.2
   :alt: Screenshot of the Test Results panel in the IntelliJ IDE
   :width: 80%

   Results of running tests in IntelliJ
   ```

## Finishing The Test Suite

So far, you've tested `grade()` with a typical value from one equivalence
partition. A number of other test cases need to be added before you can
be confident that `grade()` is working correctly.

Add these now. You should know what is needed here if you've completed
[](#ex-test-part) and [](#ex-test-cases). Write a separate function for each test case, following
the same format as the one you've already written.

Run the test suite again. The output should report 13 tests found, with 12
successes and 1 failure. The information provided on the failed test should
include text similar to this:

```
Failed Mark of 70 gives a Distinction()
           => Exception: org.opentest4j.AssertionFailedError: expected: <Distinction> but was: <Pass>
```

Fix the problem in `grade()`, then rerun the tests. They should all now pass.

## Test Granularity

A written specification for the `grade()` function might look like this:

    A mark between 70 and 100 gives a grade of "Distinction"
    A mark between 40 and 69 gives a grade of "Pass"
    A mark between 0 and 39 gives a grade of "Fail"
    A mark below 0 gives a grade of "?"
    A mark above 100 gives a grade of "?"

Each line of the specification describes the behaviour expected in a single
equivalence partition, but the tests you've written so far are more
fine-grained than this; each of them represents _one test case_ from an
equivalence partition. Each line of the specification therefore maps onto
two or three actual tests.

The tests could be written in a more coarse-grained way, such that there is a
one-to-one mapping from specification to test function, e.g.,

```kotlin
@Test
fun `Mark between 70 and 100 gives grade of Distinction`() {
    assertEquals("Distinction", grade(70))
    assertEquals("Distinction", grade(85))
    assertEquals("Distinction", grade(100))
}
```

This approach will also lead to fewer tests&mdash;which means the test suite
will run a little faster, because the overhead associated with finding and
running a test is incurred a smaller number of times.

However, a coarse-grained approach also means that each test will be a bit
larger and more complex than it could be. Remember that tests should
ideally be small and simple!

There are other problems with a coarse-grained approach. One is that reporting
of test results won't necessarily tell you which assertion caused the test to
fail. You will always see a line number, which you can check against the test
source code, but it would nice to see instantly what the issue was, without
having to make that check.

A solution to this problem is to use the optional message argument of the
assertion function:

```kotlin
@Test
fun `Mark between 70 and 100 gives grade of Distinction`() {
    assertEquals("Distinction", grade(70), "mark=70")
    assertEquals("Distinction", grade(85), "mark=85")
    assertEquals("Distinction", grade(100), "mark=100")
}
```

If any of these assertions fails, the associated message string will be
included in the report generated by the test framework, making it easier to
see what the issue is.

However, another problem remains. A test fails _as soon as an assertion fails_.
Hence you don't get any useful information from the subsequent assertions of
a multi-assertion test until you've fixed the issue that caused the first
failure.

This second problem can't be solved easily using `kotlin.test`, but we will
see a nice solution when we explore the use of the Kotest framework
[later][kot].


[^dep]: Note that dependencies still exist, and they will need to be
downloaded the first time that you run your tests. It's just that these
dependencies do not need to be specified explicitly.

[^java]: Java doesn't allow you to write your test names in natural language
like this, but you can specify a natural language description of a test using
the `@Test` annotation instead.

[ktest]: https://kotlinlang.org/api/core/kotlin-test/
[next]: gradle.md
[kot]: kotest.md
