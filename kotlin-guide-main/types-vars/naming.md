# Naming of Things

Before proceeding further, let's consider how variables, constants and other
program elements should be named in Kotlin.

## Naming Styles

There are several different naming styles commonly used in programming.

- In **lower camel case**, we join words together to create the name, using
  all lowercase for the first word and then starting each subsequent word with
  an uppercase letter&mdash;e.g., `writeToFile`

- **Upper camel case** is similar to lower camel case, except that all words
  start with an uppercase letter&mdash;e.g., `ConfigurationFile`

- In **snake case**, we render the words in lowercase and join them with
  underscores&mdash;e.g., `write_to_file`

- In **screaming snake case** (aka const case or macro case), we render all
  the words in uppercase and join them with underscores&mdash;e.g., `FILE_PATH`

You should be familiar with some of these from last year. For example, you
will have seen C and Python code in which variables and functions are named
using snake case.

The convention in Kotlin is to use

* Lower camel case for names of variables, functions and methods
* Upper camel case for class names
* Screaming snake case for names of constants

We expect you to follow this convention rigorously in COMP2850.

````{exercise}
:label: ex-code-style
:enumerator: 2.6
What two changes should be made to the following code to improve its style?

```{code} kotlin
:linenos:
const val max_size = 100

fun CreateFile(name: String) {
    ...
}
```
````

## Meaningful Names

It is extremely important that variables and other program elements are
given names that are meaningful. A variable's name should describe what that
variable represents.

For example, in software that handles an election of some kind, `n` or `num`
would not be good names for a variable that represents the number of votes
that were cast in that election; `numVotes` or `numberOfVotes` would be
much better choices here.

In certain situations, short or single-character variable names are OK. For
example, if you are using a `for` loop to index the characters of a string
or the elements of an array, it is common to use `i`, `j` or `k` as the name
of the indexing variable. This is acceptable because the variable is used
within the body of that loop and nowhere else, and because these particular
names are widely understood as subscripts in algorithms and mathematical
formulae.

Generally, the names of variables and classes should be nouns or noun
phrases, whereas the names of functions and methods should be verbs or verb
phrases. This is because variables and classes represent things, whereas
functions and methods represent actions to be performed on or using those
things.
