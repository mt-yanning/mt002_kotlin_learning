# Exercise Solutions

```{solution} ex-no-args
:label: sol-no-args
The array `args` will be empty if the program is run without supplying any
command line arguments. An array index of 0 will therefore be 'out of bounds'.

Kotlin handles this situation in a similar way to Python, by throwing an
exception (specifically, an `IndexOutOfBoundsException`). This exception will
halt the program if it isn't intercepted by an appropriate exception
handler.
```

```{solution} ex-args-no-array
:label: sol-args-no-array
**Option 4 is correct**: the program prints "Hello World!", ignoring the
command line argument.

Command line arguments can be supplied to a program even if it hasn't been
equipped to process them. Giving `main()` a parameter of type `Array<String>`
gives you access to those arguments if you need them, but you don't have to
declare a parameter if you don't need to use them in your program.
```

```{solution} ex-no-conv
:label: sol-no-conv
The programmer sees 27 printed because `arg[0]` and `arg[1]` are both of
type `String`. The `+` operator performs string concatenation when the values
either side of it are strings, so if the arguments are "2" and "7", "27" will
be printed.

To fix the problem, replace `arg[0]` with `arg[0].toInt()` and likewise for
`arg[1]`. Use the `toFloat()` or `toDouble()` conversion function instead if
floating-point values need to be supported.
```

```{solution} ex-str-interp-1
:label: sol-str-interp-1
`f` should not be used as a prefix to the string.

This prefix is used in Python. String templates in Kotlin don't begin with
a special prefix (although a special prefix, `$`, is needed *inside* the
string to trigger interpolation).
```

```{solution} ex-str-interp-2
:label: sol-str-interp-2
This prints `{word.uppercase()}`.

The string is printed as-is; interpolation doesn't happen here, because
the `$` prefix has not been included in the string.

(We did warn you to look at the code carefully! 😉)
```
