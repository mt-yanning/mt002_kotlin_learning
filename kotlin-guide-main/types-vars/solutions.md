# Exercise Solutions

```{solution} ex-num-ints
:label: sol-num-ints
Kotlin has **eight** built-in types for representing integers: four to
represent signed values, plus another four to represent unsigned values.
```

```{solution} ex-range-ushort
:label: sol-range-ushort
The minimum value of a `UShort` is 0, as is the case for all unsigned types.

A `UShort` occupies 2 bytes, i.e., 16 bits. There are thus 2{sup}`16` =
65,536 possible values. If 0 is the smallest of these values then 65,535 must
be the largest.
```

```{solution} ex-float-type
:label: sol-float-type
**Option 2** is correct: `Float` is the Kotlin type used to represent 32-bit
floating-point values.

`Double` is a valid Kotlin type, but it uses 64 bits to represent a value,
not 32 bits.

The other types represent floating-point values in other languages.
(`float` and `double` can be used in C, C++ and Java; `f32` is the 32-bit
floating-point type in Rust.)
```

```{solution} ex-literal-str
:label: sol-literal-str
Use of the double-quote delimiter character indicates that `"H"` is of type
`String`. With a single-quote delimiter (i.e., `'H'`) it would be a `Char`.
```

```{solution} ex-char-comp
:label: sol-char-comp
**Statement 3 is true**: Kotlin `Char` values require more storage than
C `char` values.

This is because Unicode characters need to be represented, and there are
far too many of these to be distinguishable using a single byte.
```

```{solution} ex-type-infer
:label: sol-type-infer
We could fix the problem using Alice, Charlie or Ellie's suggestion.

Alice's suggestion would work because it would allow a type of `Double` to
be inferred for `pi`. This is probably the best suggestion of the three.

Charlie's suggestion and Ellie's suggestion would both work because they
would both make the type of the floating-point literal match the declared
type of `pi`.
```

```{solution} ex-val-var
:label: sol-val-var
1. This should be a `val`. If this is the final result of a calculation then
   there is presumably no need to make any further alterations to the
   variable's value from this point onwards.

2. This should be a `var`. The variable will probably be updated within a loop
   that iterates over the array, meaning that reassignment will be necessary.

3. This should probably be a `val`. Input from a program's user should
   normally be preserved as-is after it has been captured, and using a `val`
   helps to ensure this.

   If a `var` was used here, the value might be changed accidentally and it
   would then no longer represent the input that the user had supplied.
```

```{solution} ex-code-style
:label: sol-code-style
- Line 1 should be changed to use screaming snake case, i.e., `MAX_SIZE`.

- The function name on Line 3 should use lower camel case, i.e., `createFile`.
```
