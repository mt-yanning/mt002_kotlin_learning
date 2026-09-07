---
short_title: "Building Strings"
---

# Building Strings Iteratively

So far, we've considered strings that are literal character sequences and
strings that come from sources such as the command line, standard input or a
file on disk. But how do we construct a `String` object from smaller
components programmatically?

If the components are few and fixed in number, we can write a small expression
that concatenates those components with `+`, or we can use interpolation:

```kotlin
// Concatenation
val greeting = "Hello " + name + "!"

// Interpolation
val greeting = "Hello $name!"
```

In other situations, e.g., where the number of components isn't known until
run time, we may need to follow an iterative approach. However, there are
good and bad ways of doing this.

## Bad Approach

Imagine you need a string consisting of the integers from 1 up to some limit,
separated by commas. For example, if the limit is 5, the resulting string
should be `"1,2,3,4,5"`.

One approach to constructing this string would be to do this:

```{code} kotlin
:linenos:
var result = ""
for (n in 1..limit) {
    result += n
    if (n < limit) {
        result += ','
    }
}
```

This builds up the string `result` a piece at a time. On line 3, the current
value of a loop counter variable will be concatenated with `result`. (The
value will be converted to a string automatically prior to concatenation.)
A second concatenation of `result` with a comma (line 5) will take place on
every iteration of the loop apart from the last.

:::{warning}
**This approach is very inefficient**, because each concatenation will create
a new `String` object as an intermediate result of the string building
operation. These strings are needed temporarily and will be discarded after
the desired string has been built.

For example, when `limit` is 5, the first iteration creates temporary
strings `"1"` and `"1,"`, the second iteration creates temporary strings
`"1,2"` and `"1,2,"`, etc. In total, 9 temporary objects are created before
we arrive at the required value of `result`.

There is an overhead associated with object creation, so ideally we want
to minimize creating these intermediate results if we can.
:::

## Good Approach

Many programming languages provide a string building capability that avoids
the inefficiencies discussed above. For example, Kotlin provides the class
`StringBuilder`, plus the function `buildString()` that simplifies use of
this class.

Here's how we could reimplement our example using `buildString()`:

```{code} kotlin
:linenos:
val result = buildString {
    for (n in 1..limit) {
        append(n)
        if (n < limit) {
            append(',')
        }
    }
}
```

Notice that the code to build up the string is supplied to `buildString()`
in the form of a lambda expression. Within this block of code, we add things
to the string by invoking the `append()` method of a hidden `StringBuilder`
object that is created for us by the function.

The important thing to note here is that no intermediate `String` objects are
created. The `StringBuilder` object collects the pieces together in memory
before creating a _single_ `String` object to use as the return value of the
function call.

## Task 4.8

Open the `tasks/task4.8` directory of your repository in your preferred code
editing environment. This is a KT project containing demo applications that
benchmark the approaches described above. You can see the code in
`bad/src/Main.kt` and `good/src/Main.kt`.

Run a demo of the bad approach with a value for `limit` of 20, like so:

    ./kotlin run -m bad 20

Then run a demo of the good approach with

    ./kotlin run -m good 20

Compare the execution times displayed by these two demos. You should see a
significant difference between the two.

(Note that `ms` signifies milliseconds, `us` signifies microseconds.)
