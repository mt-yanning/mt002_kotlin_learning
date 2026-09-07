# Command Line Input

If you want your Kotlin program to use command line arguments, you must
modify the definition of `main()` so that it has an **array of strings** as
its sole parameter. The type of this parameter must be declared as
`Array<String>`. The name given to it has no special significance, but
`args` or `argv` are common and sensible choices.

Individual arguments are accessed by array indexing, which uses square
brackets and a zero-based integer index, just like the C arrays and Python
lists that you used last year.

Before accessing arguments, you will typically need to check that the correct
number of arguments have been supplied to the program. Arrays in Kotlin have
a `size` property that you can examine in order to check this. If the required
number of arguments haven't been provided, it will be necessary to terminate
the program prematurely, with a suitable error message.

For example, if a program requires a filename as a single command line
argument, you could check the command line using code like that shown below.
(Equivalent code in C and Python is also provided, for comparison.)

::::{tab-set}
:::{tab-item} Kotlin
```{code} kotlin
:linenos:
:emphasize-lines: 1,6
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Error: filename required as sole argument")
        exitProcess(1)
    }

    // required argument available here, as args[0]
}
```
:::
::: {tab-item} C
```{code} c
:linenos:
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
  if (argc != 2) {
    printf("Error: filename required as sole argument\n");
    exit(1);
  }

  // required argument available here, as argv[1]
}
```
:::
::: {tab-item} Python
```{code} python
:linenos:
import sys

if len(sys.argv) != 2:
    sys.exit("Error: filename required as sole argument")

# required argument available here, as sys.argv[1]
```
:::
::::

:::{caution} Caution
**Kotlin stores arguments differently from C and Python.**

In C, the first element of the argument array is the path to the
executable file containing the program. The _second_ element of this
array contains the first argument supplied to the program. Hence, in
the C example, we terminate the program if `argc` is not equal to 2.

Python is like C. The first element in `sys.argv`, the list of command
line arguments, is the name of the Python program. The second list element
is the first command line argument. Hence, in the Python example, we
terminate the program if list length is not equal to 2.

**In Kotlin, the program name/file path is NOT stored in the array**.
Thus, in the example above, `args[0]` will be the first argument supplied
to the program, not `args[1]`. We terminate the program if the size of
`args` is not equal to 1.
:::

We've highlighted two lines in the Kotlin example above. The first of these
imports the function `exitProcess()` from the `kotlin.system` package of the
Kotlin standard library. Technically, you don't need this statement, but if
it were omitted you would need to refer to the function by its
**fully-qualified name**, `kotlin.system.exitProcess`. Kotlin source files
often begin with multiple `import` statements so that we can refer to
functions, classes, etc, without having to use their fully-qualified names.

The second highlighted line is the call to `exitProcess()`. Calling this
function will halt program execution, setting the program's **exit status**
to the value supplied as an argument.

:::{note}
By convention, an operating system will assume that a program has
terminated normally if it has an exit status of zero[^exit], and that it
has terminated abnormally if the exit status has any non-zero integer
value. It is good practice to signal program failure to the OS in
this fashion.

Having the exit status available can sometimes be useful, e.g., if you
are running the program from a shell script and need to halt the script
if that program fails to run properly.
:::

## Task 3.1

### Accessing Arguments

Go to the `tasks/task3_1` directory of your repository and edit the file
`Main.kt`.
 
In this file, write a small Kotlin program that accepts command line
arguments. Your `main()` function should contain only two lines of code,
which should print out the first and second command line arguments.
**Do not add anything else at this stage.**

Compile the program, then run it without supplying any arguments on the
command line. What do you see?

Now run the program with two command line arguments, e.g.,

    ./kotlin run arg1 arg2

Then try running it like this:

    ./kotlin run 'arg1 arg2'

What happens, and why do you see this behaviour?

### Checking Arguments

Modify the program in `Main.kt` so that it tests for the existence of the two
required command line arguments _before_ attempting to print them. Use the
code example above as a guide.

**If the required arguments are not present, your code should call
`exitProcess()`, with a non-zero exit status.**

Recompile the program, then use the following commands to run it with
varying numbers of command line arguments, displaying exit status
each time:

    ./kotlin run arg1
    echo $?
    ./kotlin run arg1 arg2
    echo $?
    ./kotlin run arg1 arg2 arg3
    echo $?

Here, `$?` is a **shell variable** that holds the exit status of the last
command. This is what you would use in shell scripts to decide whether
script execution should continue.

:::{note}
This shell variable is different on Windows systems. If `cmd.exe` is your
command shell, then you'll need to use `%ERRORLEVEL%` instead of `$?`.
If you are running Powershell, you'll need to use `$LastExitCode`.
:::

## Exercises

````{exercise}
:label: ex-no-args
:enumerator: 3.1.1
What happens when the following Kotlin program is run without specifying
any command line arguments?

```kotlin
fun main(args: Array<String>) {
    println(args[0])
}
```
````

````{exercise}
:label: ex-args-no-array
:enumerator: 3.1.2
Consider the following Kotlin program:

```kotlin
fun main() {
    println("Hello World!")
}
```

Imagine that this is part of a Kotlin Toolchain project. What happens
if we try to run the program like this?

```
./kotlin run xxx
```

1. The program prints "Hello World! xxx"
1. The program fails to compile because the parameter list of `main()` is empty
1. The program fails with a runtime error because the parameter list of `main()` is empty
1. The program prints "Hello World!", ignoring the command line argument
````


[^exit]: An exit status of 0 is the default. Thus you do _not_ need to
have an explicit `exitProcess(0)` at the end of your program. You'd only need
to use this if you wanted to halt the program before the end of `main()`
and signal that this was a normal termination rather than an error of some
kind.
