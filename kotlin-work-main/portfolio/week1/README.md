# Portfolio: Week 1

## Relevant Preparation

This work draws upon Sections 1-3 of the [Kotlin Programming Guide][guide].

Subsections [3.1][sec31], [3.2][sec32] and [3.4][sec34], and any programming tasks
in those subsections, are of particular relevance.

## Instructions

In `src/Main.kt`, write a program that uses [Heron's formula][heron] to compute
the area of a triangle, given the lengths of its three sides.

The three side lengths must be provided as command line arguments. You can
assume that any arguments supplied on the command line are valid floating-point
numbers, but you should _not_ assume that three values are always supplied on
the command line.

If there are fewer than three arguments, your program should use `println()`
to print the _exact_ message below:

    Error: values for a, b, c required on command line

After printing this message, your program should terminate immediately, with
an exit status code of 1.

If all side lengths have been specified on the command line, your program
should compute area and then use `println()` to print the string "Area = ",
followed by the area value. The latter should be formatted to 5 decimal
places of accuracy.

For example, if the program is invoked with

    ./kotlin run 3.0 4.0 5.0

then the output should look _exactly_ like this:

    Area = 6.00000

Note: do not add anything else to the program, or generate any output other
than what is specified above! For example, you should not add any code to
check whether the side lengths represent a valid triangle.

**If you add anything anything else, or deviate from the instructions above
in any way, checking of your solution may fail and you will not be awarded a
pass for this portfolio assignment!**

## Checking Your Solution

On Linux or macOS, you can check whether your solution performs as required
by running the Python program `check.py` from a terminal window:

    python check.py

(You may need to use `python3` instead of `python`, depending on where you
are doing this and how Python has been installed on that system.)

If you see no failures, your solution is good enough to earn a pass.


[guide]: https://comp2850.github.io/kotlin-guide/
[sec31]: https://comp2850.github.io/kotlin-guide/io-intro/cmdline
[sec32]: https://comp2850.github.io/kotlin-guide/io-intro/conversion
[sec34]: https://comp2850.github.io/kotlin-guide/io-intro/output
[heron]: https://en.wikipedia.org/wiki/Heron%27s_formula
