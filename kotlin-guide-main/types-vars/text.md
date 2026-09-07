# Representing Text

## Individual Characters

Kotlin provides the [`Char`][chr] type to represent an individual
[Unicode][uni] character.

The equivalents to this type in Java are the `char` primitive type and the
`Character` wrapper class. (We have [already discussed][num] the reason why
Java offers two options here.)

C's `char` type occupies a single byte of storage space, which limits it to
representing characters from the ASCII character set[^wide]. Characters in
Java and Kotlin require more storage space, but can represent letters from
multiple alphabets, and a wider range of symbols.

Python doesn't have a type to represent individual characters. In Python,
you would represent such a thing using a string of length 1.

### Character Literals

As in C, the single quote is the delimiter for literal `Char` values.
It is a compiler error to put more than one character inside single quotes,
or have an empty pair of single quotes.

As in C, you can represent special non-printable characters via
**escape sequences** that begin with a backslash. For example, `'\n'`
represents the newline character.

Unlike C, you can represent Unicode characters using escape sequences
consisting of `\u` followed by the Unicode [code point][cpt] of the character,
expressed with four hexdecimal digits. For example, the Euro currency
symbol can be represented literally as `'€'` or as `'\u20ac'`.

## Strings

Kotlin provides the [`String`][str] type, to represent a sequence of Unicode
characters.

Whereas C strings are very primitive, being nothing more than a sequence of
bytes in memory ending in a terminating null byte, Kotlin strings are much
more like strings in Python.

A Kotlin string is an object, with a `length` property that tells us the
size of the string. Kotlin strings also support a rich variety of operations,
much like those available in Python. For example, given the `String` variable
`name`, you can generate a lowercased version with `name.lowercase()`, or
test whether it consists solely of whitespace characters with `name.isBlank()`.

### String Literals

As in C, the double quote is the usual delimiter for literal string values,
e.g., `"Hello"`. An empty pair of double quote characters represents a string
of length 0.

Like Python, Kotlin also supports triple-quoted string literals, for easy
representation of [multiline text][mult]:

```kotlin
"""This is a string
that spans a total of
three lines."""
```

## Exercises

```{exercise}
:label: ex-literal-str
:enumerator: 2.2.1
What is the type of the literal `"H"` in Kotlin?
```

```{exercise}
:label: ex-char-comp
:enumerator: 2.2.2
Which of the following statements is true?

1. C and Kotlin both represent indvidual characters using two bytes of storage
2. C's `char` represents a single character, Kotlin's `Char` can
   represent multiple characters
3. Kotlin `Char` values occupy more storage space than C `char` values
4. C and Kotlin both represent individual characters with a single byte
   of storage
```


[^wide]: C does offer a separate 'wide character' type, but working with
wide or multibyte characters and strings in C is a lot more fiddly than
using Unicode in Kotlin, Java or Python.

[num]: numbers.md#type-duplication-in-java
[chr]: https://kotlinlang.org/docs/characters.html
[uni]: https://en.wikipedia.org/wiki/Unicode
[cpt]: https://en.wikipedia.org/wiki/Unicode#Codespace_and_code_points
[str]: https://kotlinlang.org/docs/strings.html
[mult]: https://kotlinlang.org/docs/strings.html#multiline-strings
