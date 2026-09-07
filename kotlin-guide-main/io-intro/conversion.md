---
short_title: "Type Conversion"
---

# Conversion to Other Types

Command line arguments are provided to a program as strings, but what if you
need to treat these arguments as numbers?

In such cases, you can use one of the many numeric conversion functions
associated with the `String` class. For example, to parse a string into an
integer value, you can invoke `toInt()` or `toLong()`. To parse the string
into a floating-point value you can use `toFloat()` or `toDouble()`.

(Equivalent conversion functions are associated with the numeric types,
too&mdash;so you can use `toDouble()` to convert an `Int` value to a `Double`
value, for example.)

Let's imagine that you are writing a program that computes the square of an
integer value supplied on the command line. A suitable program to do this is
shown below. The equivalent code in C and Python is provided for comparison:

::::{tab-set}
:::{tab-item} Kotlin
```{code} kotlin
:linenos:
:emphasize-lines: 9
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: integer required on command line")
        exitProcess(1)
    }

    val number = args[0].toInt()
    println(number * number)
}
```
:::
:::{tab-item} C
```{code} c
:linenos:
:emphasize-lines: 10
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    if (argc != 2) {
        printf("Error: integer required on command line\n");
        return 1;
    }

    int number = atoi(argv[1]);
    printf("%d\n", number * number);
}
```
:::
:::{tab-item} Python
```{code} python
:linenos:
:emphasize-lines: 7
import sys

if len(sys.argv) != 2:
    print("Error: integer required on command line")
    sys.exit(1)

number = int(sys.argv[1])
print(number * number)
```
:::
::::

Note: in parallel with `toInt()`, `toDouble()`, etc, Kotlin provides extension
functions named `toIntOrNull()`, `toDoubleOrNull()`, etc. These deal with
invalid conversions in a different way. We will discuss these functions
later, when we cover [null safety][null].

## Task 3.2

The Kotlin program shown above can be found in the `tasks/task3_2` directory
of your repository. Try running the program like so:

    ./kotlin run 19

Run the program a few more times, with the following as inputs:

    19.5
    nineteen
    92682

Do you understand why the first two of these inputs crash the program
but the third does not?

Do you understand why the third input produces a nonsensical result?

:::{hint .dropdown}
What is the type of variable `number`?

Are there any restrictions associated with this type?
:::

:::{attention .simple icon=false} **Optional**
How would the behaviour of the C and Python versions of this program
differ if you supplied them with the arguments described above?

If you are not sure, use the code examples above to investigate this.
:::

## Exercises

````{exercise}
:label: ex-no-conv
:enumerator: 3.2
Consider this Kotlin program, intended to sum two numbers supplied on the
command line:

```kotlin
fun main(args: Array<String>) {
    val sum = args[0] + args[1]
    println(sum)
}
```

The programmer runs this program like so:

```
./kotlin run 2 7
```

They expect to see 9 printed, but instead they see 27.

Why do they see this output, and what changes should they make to get
the desired behaviour?
````


[null]: ../nulls/index.md
