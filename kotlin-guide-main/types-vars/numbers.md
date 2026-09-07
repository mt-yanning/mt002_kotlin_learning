# Representing Numbers

[Kotlin's numeric types][num] are similar to those found in other languages.
There is generally a close correspondence to types in Java, which shouldn't
be a surprise given that we typically compile Kotlin code down to Java
bytecode, for execution on a JVM. However, there are some differences too.

## Integer Types

The four most commonly used integer types supported by Kotlin are shown in
the table below. The table includes the size in bytes of a value of each type.
It also shows what the equivalent types are in Java and C.

| Size | Kotlin type | Java equivalent  | C equivalent  |
|------|-------------|------------------|---------------|
| 1    | `Byte`      | `byte`, `Byte`   | `signed char` |
| 2    | `Short`     | `short`, `Short` | `short`       |
| 4    | `Int`       | `int`, `Integer` | `int`         |
| 8    | `Long`      | `long`, `Long`   | `long`        |

:::{note}
The names of Kotlin's built-in types all start with a capital letter.
:::

Notice that _two_ equivalent types exist in Java for each of the four common
Kotlin integer types. The same is true for the floating-point types. We
explain the reason for this [below][dup].

Kotlin also has a way of representing _unsigned_ integers, just like C does:

| Kotlin type | C equivalent     |
|-------------|------------------|
| `UByte`     | `unsigned char`  |
| `UShort`    | `unsigned short` |
| `UInt`      | `unsigned int`   |
| `ULong`     | `unsigned long`  |

An unsigned integer uses the same storage space as its signed counterpart but
shifts the range of representable values so that only positive values are
represented. Thus a `Byte` can be a value in the range -128 to 127, whereas
a `UByte` can be in the range 0&ndash;255.

Java does not have unsigned integer types.

### Integer Literals

A sequence of digits, optionally preceded by a `+` or `-` character, is
taken to represent an `Int` value. Integer literals can also include the
underscore character, `_`, between digits. This is ignored by the compiler
but can be used to separate groups of digits, in the same way that a comma
is used when writing numbers down in text documents.

If you want a literal to be treated as an unsigned value, you can add the
`u` or `U` suffix to it. If you want a literal to be treated as a long 
nteger you can add the `l` or `L` suffix to it[^pref]. These suffixes can be
combined if required:

```kotlin
-42       // Int
250U      // UInt
8094L     // Long
32767uL   // ULong
```

There is no special syntax for represent `Byte` or `Short` values in literal
form.

## Floating-Point Types

Kotlin provides two ways of representing floating-point values:

| Size | Kotlin type | Java equivalent    | C equivalent |
|------|-------------|--------------------|--------------|
| 4    | `Float`     | `float`, `Float`   | `float`      |
| 8    | `Double`    | `double`, `Double` | `double`     |

As in Java & C, the `Double` type can represent a wider range of values,
to higher degree of precision, than the `Float` type.

### Floating-Point Literals

Floating-point literals are distinguished from integer literals either by
inclusion of a decimal point or use of [scientific notation][sci]:

```kotlin
2500      // Int
2500.0    // Double
2.5e3     // Double
```

If you want the literal to be treated as `Float` value rather than a `Double`
value, you can add the `f` or `F` suffix to it:

```kotlin
3.14159   // Double
3.14159f  // Float
```

## Type Duplication in Java

We saw in the tables earlier a curious duplication of types, with Java
providing two types that correspond to each of Kotlin's numeric types. This is
because Java can represent numbers either as instances of a **primitive type**
(e.g., `int` or `float`) or as instances of a **wrapper class** (e.g.,
`Integer` or `Float`).

The primitive types are used for most things in Java, because they are more
efficient, but in some cases the class-based representations are required.
For example, if you wish to create a list of integer values in Java, you must
represent those values as `Integer` objects instead of using `int`.

Normally, you won't have to worry about any of this as a Kotlin programmer[^arr].
When you compile Kotlin code for execution on the JVM, the compiler will
decide whether your `Int` values can be represented using Java's primitive
`int` type, or whether the `Integer` wrapper class needs to be used instead.

## Comparison With Python

When it comes to representing numbers, Python offers fewer choices than any
of the other languages mentioned here.

In Python, all integers are represented using the `int` type, and all
floating-point values are represented using the `float` type.

Python's `float` is equivalent to Kotlin's `Double`, but Python's `int` has no
direct equivalent in Kotlin, Java or C. This is because, unlike those other
languages, a Python `int` is not restricted to occupying a maximum of 8 bytes.

## Exercises

```{exercise}
:label: ex-num-ints
:enumerator: 2.1.1
How many types does Kotlin have for representing integer values?
```

```{exercise}
:label: ex-range-ushort
:enumerator: 2.1.2
What are the minimum and maximum values represented by `UShort`?
```

```{exercise}
:label: ex-float-type
:enumerator: 2.1.3
Which Kotlin type would you use to represent a 32-bit floating-point number?

1. `float`
2. `Float`
3. `double`
4. `Double`
5. `f32`
```


[^pref]: You should definitely prefer `L` to `l` as a suffix on long integer
literals. An `L` is more easily distinguished visually from the digit `1`.

[^arr]: The only time that you are likely to see this type duplication
exposed to you in Kotlin is when you create [arrays of numbers, characters
or boolean values][arr].

[num]: https://kotlinlang.org/docs/numbers.html
[sci]: https://en.wikipedia.org/wiki/Scientific_notation
[dup]: numbers.md#type-duplication-in-java
[arr]: ../collect/arrays.md#primitive-arrays
