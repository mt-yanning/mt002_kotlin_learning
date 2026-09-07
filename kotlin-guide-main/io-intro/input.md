# Console Input

Kotlin's standard library provides the function `readln()` to read a line
of input from the console. This line of input is returned as a `String`
object.

Here's an example, with the equivalent C and Python code provided for
comparison:

::::{tab-set}
:::{tab-item} Kotlin
```kotlin
print("Enter your name: ")
val name = readln()
```
:::
:::{tab-item} C
```c
char name[80];
printf("Enter your name: ");
fgets(name, sizeof name, stdin);
```
:::
:::{tab-item} Python
```python
name = input("Enter your name: ")
```
:::
::::

You can see that Python's approach is the most concise and C's the most
verbose, with Kotlin somewhere in between.

Note the use of `print()` rather than `println()` in the Kotlin example.
This is done so that input takes place on the same line as the printed
prompt.

If you need to read a number from the user, the call to the relevant
conversion function can be chained onto the call to `readln()`:

```kotlin
print("Enter your age: ")
val age = readln().toInt()
```

It's common to see chained calls like this in object-oriented languages.

:::{caution}
Reading from standard input using `readln()` can intefere with build
systems when they try to run a program.

Gradle can be configured to avoid the problem, but this is not currently
possible in projects built using the Kotlin Toolchain. For such projects,
the workaround is to [package the application as an executable JAR][pkg]
and run it directly on the JVM.
:::


[pkg]: ../first/toolchain.md#deployment
