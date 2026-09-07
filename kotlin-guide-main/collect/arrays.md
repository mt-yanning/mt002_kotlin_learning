# Arrays

Arrays are useful when you need an ordered sequence of values, indexed by
their position in that sequence, and the length of that sequence doesn't
need to change after creation.

## Creation

You can create an array in two different ways. The first approach requires
you to specify the type of element to be stored in the array, the size of
the array, and some code to initialize the array elements:

```kotlin
val numbers = Array<Float>(100) { 0.0f }

val greetings = Array<String>(5) { "Hello" }
```

Here, `numbers` is created as an array of 100 `Float` values, each equal to
0.0, and `greetings` is created as an array of 5 strings, all `"Hello"`.

`{ 0.0f }` and `{ "Hello" }` are examples of lambda expressions that specify
how to compute an element's initial value. In these two simple cases a
constant value is used, but it is possible to have more complex expressions
that give the elements different values. For example, you could use the
lambda expression `{ Random.nextFloat() }` to initialize the array `numbers`
with pseudo-random values. ([Try this out!][rand])

The second approach involves specifying the desired initial values for each
array element individually, passing them all to the `arrayOf()` function:

```kotlin
val numbers = arrayOf(9, 3, 6, 2)

val names = arrayOf("Nine", "Three", "Six", "Two")
```

With this approach, there is no longer a need to specify element type or
array size: type inference is used to determine the former, and arguments
are simply counted to determine the latter.

## Task 7.2

There are some subtle aspects to using arrays of certain types in Kotlin. To
explore these subtleties, let's try a small example.

Open the `tasks/task7_2` directory of your repository and edit the file
`Main.kt`. Add the following `main()` function to this file:

```{code} kotlin
:linenos:
fun main() {
    val numbers = arrayOf(9, 6, 3, 2)

    val cls = numbers::class

    println(cls.qualifiedName)
    println(cls.java)
}
```

Then run the program and make a note of what it prints.

Now modify line 2 of `main()` so that the `intArrayOf()` function is called,
instead of the `arrayOf()` function. Run the program again. What has changed?

### Primitive Arrays

The change in the first line of program output tells us `arrayOf()` and
`intArrayOf()` create two different array types, but why is that? And what
does that strange-looking second line of output actually mean?

On the JVM, Kotlin uses Java as much as possible to provide the underlying
implementation of its classes. The second line of program output shows that
underlying Java representation, in a rather cryptic form[^desc]. The opening
`[` indicates that a Java array is being used. The rest of the string
indicates the type of value in the array. `Ljava.lang.Integer;` means that
`Integer` objects are stored in the array, whereas `I` indicates that the
array holds primitive `int` values.

This distinction matters because an array of `int` values can be handled more
efficiently than an array of `Integer` objects.

For example, suppose you need to double the integers stored in an array. If
that array holds `Integer` objects, it will be necessary to extract the
primitive `int` value from each object in order to multiply it by two. It
will also be necessary to 'box up' the resulting value as an object in order
to store it back in the array. [The compiler handles this boxing and
unboxing][box] for you, but the associated runtime cost could be avoided by
using a primitive array instead.

This is why Kotlin has an `IntArray` class and an `intArrayOf()` function: to
give us access to this more efficient representation of an array of integers
from our Kotlin programs.

:::{tip}
When you need an array of 'primitives' (numbers, characters or booleans),
it is more efficient to use the dedicated classes that Kotlin provides for
these types, rather than the generic `Array` class.

In other words, you should use `IntArray`, `FloatArray` or `CharArray`
rather than `Array<Int>`, `Array<Float>` or `Array<Char>`.

Similarly, if you are creating arrays of primitives from a known set of
values, you should prefer functions like `intArrayOf()`, `floatArrayOf()`,
`charArrayOf()` to the general-purpose `arrayOf()` function.
:::

## Element Access

The normal way of accessing an array element is to use `[]`, with a
zero-based integer index&mdash;just as you would in C. Thus you could use
`x[0]` to look up or modify the first element of array `x`.

Alternatively, you can use the `get()` method to retrieve an element, and
the `set()` method to modify an element. These expect a zero-based array
index, just like `[]`. The `set()` method also requires the new value for
the array element as a second argument. Note that use of `[]` is considered
better style than explicit use of `get()` or `set()`.

Last year, you will have seen that C doesn't stop you moving beyond the
bounds of an array, whereas Python will prevent you from doing that with a
list. Kotlin follows the same approach as Python, generating an
`ArrayIndexOutOfBoundsException` if an invalid array index is used.

You can use `slice()` to return part of the array. The desired portion is
specified using an integer range. Note however that the result of slicing is
returned **as a list**, not an array.

### Exercises

The following exercises all relate to this line of code:

```kotlin
val x = intArrayOf(9, 3, 6, 2, 8, 5)
```

If you are not sure of an answer to an exercise, try writing a small Kotlin
program to find out!

```{exercise}
:label: ex-array-zero-index
:enumerator: 7.2.1
What does `print(x[0])` do?
```

```{exercise}
:label: ex-array-oob
:enumerator: 7.2.2
What does `x[6] = 0` do?
```

```{exercise}
:label: ex-array-slice
:enumerator: 7.2.3
What does `x.slice(2..4)` produce?

1. An array containing 6 and 2
1. An array containing 6, 2 and 8
1. A list containing 6 and 2
1. A list containing 6, 2 and 8
```


[^desc]: These are type descriptors, used in Java bytecode. They are
written in a form that suits the JVM rather than a human reader.

[rand]: https://pl.kotl.in/HWFqLykEd
[box]: https://dev.java/learn/numbers-strings/autoboxing/
