# Portfolio: Week 3

## Relevant Preparation

This work draws upon Sections 6 to 9 of the [Kotlin Programming Guide][guide],
and on your solution to a Week 2 portfolio task.

Subsections [6.5][sec65], [7.3][sec73], [7.6][sec76] and [9.3][sec93] are of
particular relevance, as are any programming tasks in those subsections.

Part of this work involves adapting your solution to [Task 7.7.1][task], so
make sure you have completed that before you begin.

## Instructions

There are *two* separate parts to this assignment - see the subsections below
for full details.

In each part, you will be working with small Kotlin functions. In the first
part of the assignment, a full set of tests is provided for you to verify the
correctness of your work; in the second part, you will need to write most of
the tests yourself. **All of these tests must pass for you to achieve a
grade of Pass on this assignment!**

You can run the tests for both parts with

    ./kotlin test

Note that this will only work once you have compiling implementations of
the relevant functions.

### Triangles

Copy the file `Triangles.kt` from your Week 2 solution into the directory
`triangles/src`. This will give you most of the code you need for this part
of the assignment.

Study the source files in the `test` subdirectory, so that you understand
how your code will be tested. See [Section 6.5][sec65] for explanations.

Run the tests with

    ./kotlin test -m triangles

If your implementation from Week 2 was successful, there should be 8 passing
tests and 2 failing tests.

Modify `triangleArea()` so that it throws the required exception if a triangle
is invalid. You should obviously use the existing function `isValidTriangle()`
to help you determine this.

Rerun the tests. If all ten of them pass, you have completed this part of
the assignment successfully.

### Computing a Median

Copy the file `Stats.kt` from your [Task 7.7.1][task] solution into the
directory `median/src`. This will give you most of the code you need for this
part of the assignment.

Study the file `MedianTest.kt`, in the `test` subdirectory. This is an
incomplete set of unit tests for the `median()` function. Currently, it tests
only that the function throws an exception when invoked on an empty list.

Run the tests with

    ./kotlin test -m median

A single failed test should be reported.

Modify your implementation of `median()` so that it throws the required
exception. Rerun the tests and make sure that the failing test now passes.

To complete the assignment, add **four** more tests to `MedianTest.kt`.
These should check that the function behaves correctly when invoked on lists
containing 1, 2, 3 and 4 elements. Be sure to use appropriate Kotest matchers
in your tests (see [Section 6.5][sec65]), and choose each test case carefully
so that the test will fail if a median isn't being calculated correctly.

If the tests reveal any problems with your implementation of `median()`, fix
them. When you have five well-chosen tests that all pass, you will have
completed this part of the assignment sucessfully.


[guide]: https://comp2850.github.io/kotlin-guide/
[sec65]: https://comp2850.github.io/kotlin-guide/testing1/kotest
[sec73]: https://comp2850.github.io/kotlin-guide/collect/lists
[sec76]: https://comp2850.github.io/kotlin-guide/collect/other
[sec93]: https://comp2850.github.io/kotlin-guide/errors/throwing
[task]: https://comp2850.github.io/kotlin-guide/collect/tasks/#task-7-7-1
