# Lambda Expressions

In Kotlin, functions are **first-class objects**. This means they have an
equivalent status to the different types of data that we manipulate in a
program. Thus it is possible to write **higher-order functions** that expect
to receive another function as an argument, or that return another function
to the caller. This is a key aspect of the [functional programming][fun]
paradigm.

These functions that we pass in as arguments are typically quite short. Also,
it is common for them to be needed only at one point in the source code.
Kotlin therefore allows us to define these functions at the point of use,
in a more compact way, as **lambda expressions**.

The format of a lambda expression (or 'lambda', for short) is

`{` *parameter-list* `->` *body* `}`

This is like a function with no name, and with the parameter list moved
inside the braces. An arrow (`->`) separates the parameter list from the
body. No return type is declared, as this will be inferred by the compiler.

After completing this section, you will understand how to create your own
lambdas, using the recommended syntax. You will also recognize the important
role played by lambdas in helping to manipulate collections and sequences.


[fun]: https://en.wikipedia.org/wiki/Functional_programming
