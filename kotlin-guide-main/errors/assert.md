# Assertions

You can make assertions using the `assert()` function, from the Kotlin
standard library. This fulfils a similar purpose to Python's `assert`
statement, or C's `assert` macro.

Here's an example, with the equivalent code in Python & C provided for
comparison:

::::{tab-set}
:::{tab-item} Kotlin
```kotlin
fun processText(text: String) {
    assert(text.length > 0)
    ...
}
```
:::
:::{tab-item} Python
```python
def process_text(text):
    assert len(text) > 0
    ...
```
:::
:::{tab-item} C
```c
#include <assert.h>
#include <string.h>

void process_text(const char* text)
{
  assert(strlen(text) > 0);
  ...
}
```
:::
::::

:::{caution}
Don't get confused here.

Native assertions in Kotlin are different from the more specialized assertion
functions that we use in unit testing. The latter only appear in unit tests
and not in production code.
:::

## `require()` vs `assert()`

The example above looks very similar to the precondition functions that
we saw [earlier][pre]. We could easily replace the use of `assert()` with
a call to `require()`:

```kotlin
require(text.length > 0)
```

But these two approaches are subtly different.

`require()` is 'always on'. It will always throw an `IllegalArgumentException`
if its argument evaluates to `false`.

Uses of `assert()`, on the other hand, can be enabled or disabled at runtime,
via options passed to the JVM. If assertions are enabled and the argument
of `assert()` evaluates to `false`, then `AssertionError` will be thrown.

Thus `require()` is better suited to situations where run-time errors might
be expected to occur from time to time, whereas `assert()` is better suited
to situations where we are not expecting problems but want to have a 'sanity
check' that can be enabled at run time when needed.

## Controlling Assertions

### Kotlin Toolchain

When you run a Kotlin Toolchain project, assertions are enabled by default.
You can disable them by running the application like this:

    ./kotlin run --jvm-args="-disableassertions"

You can abbreviate `-disableassertions` to `-da` if you like.

### Executable JARs

If you have packaged an application as an executable JAR and are running it
directly on the JVM, assertions will be disabled by default. You can enable
them by using the `-enableassertions` option:

    java -enableassertions -jar app.jar

You can abbreviate `-enableassertions` to `-ea`.

### Python & C Comparison

In Python and C, assertions are 'on by default', and need to be disabled
manually.

In Python, you would do this by running the `python` command with the `-O`
command line argument. In C, you would do it by defining a special
preprocessor symbol, `NDEBUG`, prior to the point where `assert.h` gets
included into the source code. This could be done via the compiler option
`-DNDEBUG`, for example.


[pre]: throwing.md#precondition-functions
