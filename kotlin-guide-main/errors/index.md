# Error Handling

In many object-oriented programming languages, run-time errors are handled
using **exceptions**. You saw last year how this idea works in Python. Here,
we take a deeper dive into the concept, using Kotlin.

You'll notice many similarities between Kotlin's approach and Python's
approach, but Kotlin provides some additional features that make it easy
to create and intercept exceptions.

Another idea that you encountered last year, in both Python and C, is the
idea of [**assertions**][asrt]. Assertions are a feature of unit tests, but
they can also be used directly in production code (e.g., to help with
debugging, or as a defensive programming technique).

After completing this section you will have a solid understanding of how
exceptions work in Kotlin. You will not only be able to create them within
your own code, but also intercept those that occur in other code that
you are using. You will also be able to write Kotlin code containing
assertions.


[asrt]: https://en.wikipedia.org/wiki/Assertion_(software_development)
