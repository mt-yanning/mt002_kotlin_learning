---
short_title: "Unit Testing 2"
---

# Unit Testing (Part 2)

[][pt1] explored the basic principles of unit testing and introduced
[Kotest][kot], a powerful testing framework for Kotlin software.

In this follow-up to Part 1, you will learn about [richer and more powerful
assertions][asrt], with which you can check that floating-point
values, strings and collections have the expected form, or that exceptions
are thrown when expected.

You will also learn how to test classes, and how [test fixtures][fix] allow
you to set up a consistent environment for running tests.

You will apply what you've learned so far about Kotest in a scenario that
introduces you to the concept of [test-driven development][tdd], whereby
tests are written *before* the code that needs to be tested.

Finally, you'll learn how to isolate unit tests from external dependencies
using [test doubles][dbl].

At the end of all this, you should have a solid grounding in how to write
automated tests for Kotlin software using Kotest. We will return to testing
later in the module, when we look at how to test the HTTP responses from
web applications.


[pt1]: ../testing1/index.md
[kot]: https://kotest.io/
[asrt]: assertions.md
[fix]: fixtures.md
[tdd]: tdd.md
[dbl]: doubles.md
