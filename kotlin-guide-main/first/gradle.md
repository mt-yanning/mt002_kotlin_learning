---
short_title: "Gradle"
---

# Using Gradle

This task revisits the fancy "Hello World" application [seen previously][prev],
this time organizing it as a [Gradle][grad] project. This project is provided
for you in the `tasks/task1_4` subdirectory of your repository.

## Project Organization

Take a few minutes to look at the files in `task1_4` and its various
subdirectories.

Open the file `build.gradle.kts` in your editor and examine it. This is
the **Gradle build script**[^script] for the application. The top part of
the build script looks like this:

```{code} kotlin
:filename: build.gradle.kts
:linenos:
plugins {
    kotlin("jvm") version "2.3.21"
    application
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "MainKt"
}
```

The code seen here serves the following purposes:

- Line 2 installs the Kotlin plugin and indicates that we are targeting
  the JVM, using version 2.3.21 of the language & compiler; line 3 installs
  the application plugin, indicating that we are building an application
  rather than a library.

- Lines 6&ndash;8 configure the Kotlin plugin to indicate that we want to use
  version 21 of the Java compiler and virtual machine.

- Lines 10&ndash;12 configure the application plugin to indicate where the
  `main()` function of the application can be found. (Note: there is no
  default for this in Gradle, so it will always need to be provided.)

The bottom part of the build script looks like this:

```{code} kotlin
:filename: build.gradle.kts
:lineno-start: 14
repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.datetime.jvm)
    implementation(libs.mordant)
}
```

The code seen here serves the following purposes:

- Lines 14&ndash;16 specify the source of the application's dependencies
  ('Maven Central', in this case). If a dependency is not already present on
  the system, it will be downloaded from this location and will then be
  cached locally for future use.

- Lines 18&ndash;21 specify the dependencies themselves. `libs.datetime.jvm`
  and `libs.mordant` are references to the library catalog, which is identical
  to the one seen previously (apart from it being located in the `gradle`
  subdirectory).

## Build Tasks

To see the tasks that can be performed by the build script, go to a terminal
window, move into the `task1_4` subdirectory and enter

    ./gradlew tasks

This command runs the **Gradle wrapper**. It will work as above on Linux
or macOS. If you see a 'Permission denied' error on these systems, you can
fix this with

    chmod u+x gradlew

Note: If you are using Windows and your command prompt is provided by
`cmd.exe`, you'll need to omit the leading `./` from the command to run it.
If your command prompt is provided by Windows Powershell then you'll need
to use `.\gradlew` to run it.

:::{warning} Warning
**The Gradle wrapper will be VERY slow the first time it runs on your PC.**

This is because it will need to download the code for Gradle itself,
the Kotlin compiler and any application dependencies.

Subsequent tasks will run _much_ faster.
:::

## Running The Application

To run the application, do

    ./gradlew run

It should behave exactly as before.

By default, the `run` task runs an application without command line arguments.
You can supply arguments by expressing them as a quoted string and using the
`--args` option. For example, if the application required two arguments, you
could run it like this:

    ./gradlew run --args='arg1 arg2'

It's important not to forget the quotes here!

It is also possible to customize the `run` task in the build script so that
it provides a default set of arguments to the application.

## Other Tasks

Try packaging the application for distribution, using

    ./gradlew distZip

This will create a file named `task1_4.zip`, in the `build/distributions`
subdirectory of `task1_4`. This Zip archive contains JAR files for the
application and its dependencies, plus a shell script and batch file that can
be used to run the application on Linux, Mac or Windows systems.

If you like, copy this Zip archive to somewhere else on your system, unzip
it, then try running the application using the shell script (on Linux or
macOS) or batch file (on Windows).

When you're done, you can remove all of the build artifacts for the
project with

    ./gradlew clean

:::{tip}
If you are working on SoCS Linux machines, get into the habit of running the
`clean` task whenever you have finished working in a Gradle project.

This will ensure that you aren't using up your disk quota unnecessarily.
:::


[^script]: Note that this build script is itself written in Kotlin!

[prev]: toolchain.md
[grad]: https://gradle.org/
