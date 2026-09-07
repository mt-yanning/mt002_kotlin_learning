# Exercise Solutions

```{solution} ex-if-expr
:label: sol-if-expr
Bob is correct, and Charlie & Diane's suggestions will both fix the issue.

This code won't compile because `if` is being treated as an expression here,
which means that it must always yield an `Int` value. The expression doesn't
provide a value for the case where `x` is not greater than 100.

Adding the `else`, as Charlie suggests, will ensure that the expression
always yields a value. An alternative fix is to not treat the `if` as an
expression, as Diane suggests.
```

```{solution} ex-int-range
:label: sol-int-range
This prints `true` on one line, then `false` on the next line.

`true` is printed on the first line `0..5` is a closed range that includes 5;
`false` is printed on the second line because `0..<5` is an **open range**
that doesn't include 5.
```
