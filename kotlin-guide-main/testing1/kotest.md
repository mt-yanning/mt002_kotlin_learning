# Using Kotest

[Kotest][kot] is a powerful testing framework for Kotlin applications, with
many useful features. It supports multiple test styles, allowing you to
organize tests in a way that suits you and your team. It also offers a richer
and more expressive set of assertions that make tests easier to read.

## Configuring a KT Project

To use Kotest, you'll need a library catalog like this:

```{code} toml
:filename: libs.versions.toml
[versions]
kotest = "6.2.2"

[libraries]
kotest-assertions = { module = "io.kotest:kotest-assertions-core", version.ref = "kotest" }
kotest-framework = { module = "io.kotest:kotest-framework-engine", version.ref = "kotest" }
kotest-runner = { module = "io.kotest:kotest-runner-junit5", version.ref = "kotest" }
```

You will also need to add a `test-dependencies` section to `module.yaml`:

```{code} yaml
:filename: module.yaml
test-dependencies:
  - $libs.kotest.assertions
  - $libs.kotest.framework
  - $libs.kotest.runner: runtime-only
```

Notice how the third dependency is tagged `runtime-only`, indicating that it
is required to run tests but not to compile them.

:::{caution}
This is _not_ the same as the `dependencies` section that we saw
[earlier][dep], so don't get confused between the two.

Test dependencies are specified separately from application dependencies to
ensure that they are not bundled with an application when we package it
for deployment or distribution.
:::

## Configuring a Gradle Project

The library catalog should be configured as for KT projects. Remember,
though, that this file should go in the `gradle` subdirectory rather than the
top-level directory of the project.

The dependencies section of `build.gradle.kts` will need to include the
following:

```{code} kotlin
:filename: build.gradle.kts
dependencies {
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.framework)
    testRuntimeOnly(libs.kotest.runner)
}
```

## Test Organization

Kotest supports multiple [testing styles][sty]. These styles represent
different ways of organizing and writing tests, drawing inspiration from a
range of testing approaches and testing frameworks. You can use whichever
style suits you best, but we will focus here on the `FreeSpec` style, which
is perhaps the simplest.

In this style, tests are collected together into classes that inherit from
`FreeSpec`. Each test consists of a descriptive string, followed by a
lambda expression containing the code for the test. (We will cover lambda
expressions properly [later](../lambda/index.md); for now, just think of them as blocks of
code, enclosed in braces.)

Here's an example:

```{code} kotlin
:linenos:
import io.kotest.core.spec.style.FreeSpec

class GradeTest : FreeSpec({
    "Mark of 55 gives a Pass" {
        // Implement test here
    }
})
```

## Expressive Assertions

Kotest supports a particularly nice and expressive syntax for making
assertions, involving **matchers**. We will explore matchers more thoroughly
in [][pt2]. Here, we focus on the most useful of them, `shouldBe`.

`shouldBe` is an [infix function][ifx]. It asserts that the expression on its
left yields the value provided by the expression on its right. For example,
we expect that the function call `grade(55)` should return the string "Pass".
We can make this assertion with

```kotlin
grade(55) shouldBe "Pass"
```

Compare this with the equivalent assertion in `kotlin.test`:

```kotlin
assertEquals("Pass", grade(55))
```

Notice how much clearer and easier to read the Kotest assertion is.

:::{note}
The `shouldBe` matcher is defined in the package `io.kotest.matchers`. To use
it, you'll need to add a suitable import statement to the top of the source
file:

```kotlin
import io.kotest.matchers.shouldBe
```
:::

## Clues & Soft Assertions

Recall from our earlier discussion of [test granularity][gran] that one of the
issues with bundling multiple assertions into a single test is that it often
isn't clear which assertion caused that test to fail.

The solution to this issue when using `kotlin.test` assertions is to include
additional information about the test case as an extra argument:

```kotlin
assertEquals("Distinction", grade(85), "Mark=85")
```

The equivalent in Kotest is to provide a **clue**. This is done using the
`withClue()` function. This accepts a string as its first argument and
a lambda expression as its second argument, containing the assertions to
which the clue applies.

For example, we could test whether a grade of "Distinction" is correctly
generated like so:

```kotlin
"Mark between 70 and 100 gives a Distinction" {
    withClue("Mark=70") { grade(70) shouldBe "Distinction" }
    withClue("Mark=85") { grade(85) shouldBe "Distinction" }
    withClue("Mark=109") { grade(100) shouldBe "Distinction" }
}
```

However, we still have the problem that failure of the first of these
assertions means that the other two won't even run. It would be better if
we could run all of them and see all of the failures, if any, in the test
results.

We can achieve this in Kotest by 'softening' the assertions. If we wrap all
three of them up as a lambda expression and pass this to the `assertSoftly()`
function, then Kotest will always make all three assertions when the test
runs, collecting information about any failures:

```kotlin
"Mark between 70 and 100 gives a Distinction" {
    assertSoftly {
        withClue("Mark=70") { grade(70) shouldBe "Distinction" }
        withClue("Mark=85") { grade(85) shouldBe "Distinction" }
        withClue("Mark=109") { grade(100) shouldBe "Distinction" }
    }
}
```

:::{note}
`assertSoftly()` and `withClue()` are defined in the `io.kotest.assertions`
package. To use them, you'll need to add suitable import statments to the
top of your source file:

```kotlin
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
```
:::

### Soft Assertions as a Default

Using `assertSoftly()` in every test can get a little tedious. Fortunately,
you can make soft assertions the default across the entire project if you
wish.

There are a couple of different ways to achieve this: one is to create a
project configuration object (see [Part 2][pt2]); the other is to set a
system property in a configuration file.

To do the latter in a KT project, you will need to create a subdirectory
named `testResources`, alongside the `test` subdirectory. Inside this new
subdirectory, you will need to create a file named `kotest.properties`,
containing this line:

```
kotest.framework.assertion.globalassertsoftly=true
```

After setting this up, you can remove any occurrences of `assertSoftly()`
from your tests.

:::{note}
In a Gradle project, you should create the same `kotest.properties` file,
but it should be located in a subdirectory of `src/test/kotlin` named
`resources`.
:::

## Task 6.5

Open the `tasks/task6_5` directory of your repository in your preferred
code editing environment. This is a KT project, already configured to use
Kotest. Spend a minute or two examining how the project is laid out and
configured. In particular, examine the contents of `libs.versions.toml` and
`module.yaml`.

Next, edit `GradeTest.kt` and write a set of unit tests for the `grade()`
function, using Kotest. Use the tests from Task 6.3 as your reference point.
You should have 13 tests in your solution to this task. You will need to
write the Kotest equivalent of each of these. Be sure to make the assertions
using `shouldBe`.

Run the tests, e.g., using `./kotlin test` in the terminal. They should
all pass.

Now refactor `GradeTest.kt` so that the tests are more coarse-grained. You
should aim to have 5 tests, one for each equivalence partition. Each test
should make either two or three assertions. Use `assertSoftly()` and
`withClue()` here, as described above. (That earlier dicussion actually
gives you the code for one of the tests in its entirety!)

Run the tests again. They should still all pass.

Now investigate the effect of using soft assertions. Edit `Grade.kt` and
modify the `grade()` function so that it will cause two assertions to fail:

```{code} kotlin
:linenos:
:emphasize-lines: 4
fun grade(mark: Int) = when (mark) {
    in 0..39 -> "Fail"
    in 40..69 -> "Pass"
    in 71..99 -> "Distinction"
    else -> "?"
}

```

Run the tests again, and examine the output carefully. You should see a single
failed test containing information similar to this:

```
Failures (1):
  Kotest:GradeTest:Marks between 70 and 100 give a Distinction
    MethodSource [className = 'GradeTest', methodName = 'Marks between 70 and 100 give a Distinction', methodParameterTypes = null]
    => io.kotest.assertions.MultiAssertionError: The following 2 assertions failed:
1) Mark=70
expected:<Distinction> but was:<?>
   at GradeTest$1$1.invokeSuspend(GradeTest.kt:13)
2) Mark=100
expected:<Distinction> but was:<?>
   at GradeTest$1$1.invokeSuspend(GradeTest.kt:15)
```

Next, investigate how to configure soft assertions globally. Remove all
occurrences of `assertSoftly()` from the tests, then create a suitable
`kotest.properties` file, [as discussed earlier][soft]. If you run the tests
again, you should see the same test failure, identifying two failed assertions.
If you change the setting in `kotest.properties` from `true` to `false`, the
test will still fail but it will now indicate only a single fail assertion.

Finally, revert the changes you made to `grade()` and `kotest.properties`,
then run the tests one last time. They should now all pass.


[kot]: https://kotest.io/
[dep]: ../first/toolchain.md#dependencies
[sty]: https://kotest.io/docs/framework/testing-styles.html
[pt2]: ../testing2/index.md
[ifx]: ../funcs/infix.md
[gran]: writing.md#test-granularity
[soft]: kotest.md#soft-assertions-as-a-default
