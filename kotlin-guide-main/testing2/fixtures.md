# Test Fixtures

A **test fixture** is a known, fixed environment to use as a baseline when
running tests. Typically it consists of a set of objects that are created
and initialized to a known state, and then made available to every test.

Test fixtures help to eliminate code duplication by defining the objects that
are used by multiple tests in a single place.

Test fixtures can also speed up test execution by ensuring that
time-consuming object configuration is performed once, instead of being
repeated unnecessarily in multiple tests.

## Task 13.2

Let's work through an example of using a fixture in Kotest. This will also
serve as an illustration of how we test classes.

Start by opening the `tasks/task13_2` directory of your repository in your
preferred code editing environment. This is a Gradle project that implements
a class representing time on a 24-hour clock.

The class is defined in `Time.kt` and a small demo program is in `Main.kt`.
Examine these files carefully, then run the demo with

    ./gradlew run

The unit tests are in the file `TimeTest.kt`. Run them with

    ./gradlew test

They should all pass.

Now examine the tests in `TimeTest.kt` carefully. Focus in particular on any
examples of code duplication that you can see.

For example, in the portion of the code shown below, you can see that a `Time`
object representing a time of midnight is defined more than once.

```{code} kotlin
:linenos:
:emphasize-lines: 3,10
class TimeTest : FreeSpec({
   "Hours stored correctly" {
       val midnight = Time(0, 0, 0)
       val noon = Time(12, 0, 0)
       withClue("00:00:00") { midnight.hours shouldBe 0 }
       withClue("12:00:00") { noon.hours shouldBe 12 }
   }

   "Minutes stored correctly" {
       val midnight = Time(0, 0, 0)
       val thirtyMin = Time(0, 30, 0)
       withClue("00:00:00") { midnight.minutes shouldBe 0 }
       withClue("00:30:00") { thirtyMin.minutes shouldBe 30 }
   }
   ...
})
```

Redefine `midnight` *outside* the tests, at the start of `TimeTest`. Remove
any definitions that are made inside the tests:

```{code} kotlin
:linenos:
:emphasize-lines: 3
class TimeTest : FreeSpec({

   val midnight = Time(0, 0, 0)

   "Hours stored correctly" {
       val noon = Time(12, 0, 0)
       withClue("00:00:00") { midnight.hours shouldBe 0 }
       withClue("12:00:00") { noon.hours shouldBe 12 }
   }

   "Minutes stored correctly" {
       val thirtyMin = Time(0, 30, 0)
       withClue("00:00:00") { midnight.minutes shouldBe 0 }
       withClue("00:30:00") { thirtyMin.minutes shouldBe 30 }
   }
   ...
})
```

The variable `midnight` is now part of a **test fixture**. The creation of
the object happens only once in this case (though see the discussion below),
and the variable can be used by any of the tests defined inside `TimeTest`.

Repeat this process for any other `Time` objects that are used more than once
by the tests. **After moving an object into the fixture, rerun the tests to
check that they all still pass.**

You should end up with a test fixture consisting of six `Time` objects.
This new version of `TimeTest` should be around eight lines shorter than
the original.

## Isolation Modes

In the example above, Kotest creates a single instance of the `TimeTest` class
and reuses it to run each of the specified tests. This means that fixture
creation happens only once.

This is ideal for situations like this one, where the class under test has
no methods that change object state. But what if we were testing a class
in which changes in state were possible? For example, what if `Time` had been
defined with `var` properties, allowing hours, minutes and seconds to be
altered after object creation?

This creates a potential problem. When objects are mutable, it is possible
for a test to modify an object in the test fixture so that it is no longer in
the state expected by other tests!

The solution to this problem is to switch Kotest's **isolation mode** from
'single instance' (the default) to 'instance per root'. In this mode, Kotest
creates a new instance of the test spec before running each top-level test.
This means that the fixture will be recreated before one of those tests runs,
so it no longer matters if a test modifies the objects in the fixture.

Here's an example of how to switch to 'instance per root' in a single set
of tests:

```kotlin
import io.kotest.core.spec.IsolationMode
...

class MyTests : FreeSpec({
    isolationMode = IsolationMode.InstancePerRoot
    ...
})
```

If you want to change isolation mode to 'instance per root' across the whole
project, one way of doing this is via system properties. This involves
creating a `kotest.properties` file, containing the following setting:

```
kotest.framework.isolation.mode=InstancePerRoot
```

See the [earlier discussion of soft assertions][soft] for details of where
this configuration file should be placed in KT and Gradle projects.

The Kotest documentation has [more information on isolation modes][iso].


[soft]: ../testing1/kotest.md#soft-assertions-as-a-default
[iso]: https://kotest.io/docs/framework/isolation-mode.html
