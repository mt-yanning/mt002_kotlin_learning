# Testing in Gradle

In this task, you will see what the example from the [previous section][prev]
looks like as a Gradle project.

Start by opening the `tasks/task6_4` directory of your repository in your
preferred code editing environment. Project structure should be largely
familiar from earlier Gradle-based tasks. The main new thing is the presence
of unit tests. Gradle expects to find these under `src/test/kotlin`.

## Configuration

Unlike KT, some configuration is required to do unit testing with Gradle.

Examine `gradle/libs.versions.toml`. This is another example of a library
catalog, similar to the one used in Task 1.4:

```{code} toml
:filename: lib.versions.toml
[versions]
junit = "6.1.2"

[libraries]
junit-jupiter-engine = { module = "org.junit.jupiter:junit-jupiter-engine", version.ref = "junit" }
junit-platform-launcher = { module = "org.junit.platform:junit-platform-launcher" }
```

This particular catalog specifies some [JUnit][jun] libraries that will be
needed for testing. Although `kotlin.test` provides the API that we are
using to write unit tests, the underlying infrastructure needed to run them
is provided by JUnit.

Now examine `build.gradle.kts`:

```{code} kotlin
:filename: build.gradle.kts
:linenos:
:emphasize-lines: 10-14, 24-29
plugins {
    kotlin("jvm") version "2.3.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "MainKt"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "failed", "skipped")
    }
}
```

The highlighted sections show what is new, compared with Task 1.4.

Line 11 specifies that Kotlin's testing API will be used to write the tests,
whereas lines 12 & 13 indicate runtime dependencies on the JUnit libraries
identified in the version catalog.

Notice the use of `testImplementation()` and `testRuntimeOnly()` on lines
11&ndash;13. These indicate dependencies that apply only to tests and not to
the application itself. Compare this with Task 1.4, where we specified
application dependencies using `implementation()`.

Lines 24&ndash;29 configure Gradle's `test` task, indicating that JUnit
will be used to run the tests. Logging is configured to maximize the amount
of information shown when tests are run. With this configuration, you will
see tests that have passed, tests that have failed and tests that have been
skipped for some reason.

## Running Tests

You can run the tests in the terminal with `./gradlew test`. Try this now.

You should see output like this:

```
> Task :test FAILED

GradeTest > Mark of -1 isn't a valid grade() PASSED

GradeTest > Mark of 40 gives a Pass() PASSED

GradeTest > Mark of 85 gives a Distinction() PASSED

GradeTest > Mark of -5 isn't a valid grade() PASSED

GradeTest > Mark of 101 isn't a valid grade() PASSED

GradeTest > Mark of 100 gives a Distinction() PASSED

GradeTest > Mark of 70 gives a Distinction() FAILED
    org.opentest4j.AssertionFailedError at GradeTest.kt:39

GradeTest > Mark of 20 gives a Fail() PASSED

GradeTest > Mark of 105 isn't a valid grade() PASSED

GradeTest > Mark of 39 gives a Fail() PASSED

GradeTest > Mark of 69 gives a Pass() PASSED

GradeTest > Mark of 0 gives a Fail() PASSED

GradeTest > Mark of 55 gives a Pass() PASSED

13 tests completed, 1 failed

FAILURE: Build failed with an exception.
```

In Task 6.4 we've provided all the required tests for `grade()` but haven't
fixed the bug, hence the single failed test. Don't fix this just yet!

Notice that the output displayed in the terminal by Gradle has a somewhat
nicer and more readable format than that produced by KT.

### Using IntelliJ

**[Skip this bit](gradle.md#test-reporting) if you are not using IntelliJ as your IDE!**

To run tests via IntelliJ's GUI, activate the Gradle tool window (@fig-ij-gradle)
by clicking on the button with the elephant icon in the toolbar on the
right-hand side of the IDE.

Drill down to *Tasks > verification > test* and double-click on this item.

```{figure} ij-gradle.png
:label: fig-ij-gradle
:enumerator: 6.4.1
:alt: Screenshot showing where to find the test task in the Gradle tool window of the IntelliJ IDE
:width: 50%

Accessing the Gradle `test` task in IntelliJ
```

IntelliJ will create a run configuration for the `test` task in the drop-down
menu in the toolbar, which you can use to select and run tests in future.
Test output will be collected and displayed in a separate panel, in the same
way as for KT projects.

## Test Reporting

One nice feature offered by Gradle is the formatting of test results as a
set of web pages. If you examine closely the output displayed in the terminal
after running tests, you'll see mention of this, along with a URL for the
test report.

Visit this URL in your browser now[^url]. You should see a page like that
shown in @fig-gradle-rep1.

```{figure} gradle-rep1.png
:label: fig-gradle-rep1
:enumerator: 6.4.2
:alt: Screenshot of a web page of test results generated by Gradle

Test results in an HTML report generated by Gradle
```

When there are test failures, the report defaults to showing only these, but
you can select the 'All' tab to see results for all tests. Try this now.


Click on the link to the failed test to access details of why it failed.
You should a page resembling that shown in @fig-gradle-rep2.

```{figure} gradle-rep2.png
:label: fig-gradle-rep2
:enumerator: 6.4.3
:alt: Screenshot of a web page generated by Gradle, showing details of a failed test

Details of a failed test in an HTML report generated by Gradle
```

Return to the home page of the test results, then fix the bug in `grade()`,
just as you did in the KT project. Rerun the tests, then refresh the page in
the browser so you can see what 100% success looks like in the test report.

## Rerunning Tests

To finish this task, go back to the terminal and try rerunning the tests
with

    ./gradlew test

You should see Gradle jump straight to the 'BUILD SUCCESSFUL' message, without
actually running each test. It recognizes that there is no need to do this,
because the tests all passed previously and no code has changed.

You can force Gradle to rerun the tests with

    ./gradlew test --rerun

You can force Gradle to recompile everything and then rerun the tests with

    ./gradlew --rerun-tasks test


[^url]: You can copy the link and paste it into the browser address bar, or
use {kbd}`Ctrl` + {kbd}`O` ({kbd}`Cmd` + {kbd}`O` on a Mac) to open a file
dialog and navigate to where the main HTML file is. You should find it in
`build/reports/tests/test`.

[prev]: writing.md
[jun]: https://junit.org/
