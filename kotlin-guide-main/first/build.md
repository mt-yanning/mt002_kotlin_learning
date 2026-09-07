# Build Systems

The Kotlin Toolchain combines a compiler with other tools, providing a
sophisticated **build system** for managing Kotlin software.

Build management systems are common in software development and offer many
benefits. Typically, they can

* Download **dependencies**: libraries of code used by an application
* Work efficiently, compiling only the files that have changed
  since the last compilation
* Run tests and report on the results
* Perform checks on code style or code quality
* Generate API documentation from **doc comments**
* Package a library or application for deployment and distribution

You actually encountered a build management system named **Make** last year,
when working with C programs. Make is very basic, designed to support
efficient compilation and not much else.

The Kotlin Toolchain can do much more than Make, but it isn't the only option
available for managing the development of Kotlin applications. It is also
possible to use more established tools such as [Gradle][grad] or [Maven][mav].
You'll use the former for some of the work in this module.


[grad]: https://gradle.org/
[mav]: https://maven.apache.org/
