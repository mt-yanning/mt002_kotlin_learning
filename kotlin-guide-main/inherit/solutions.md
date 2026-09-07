# Exercise Solutions

```{solution} ex-member-vis
:label: sol-member-vis
Lines 15, 22 and 23 will cause compiler errors.

Lines 15 and 22 are both compiler errors, for the same reason: `method1()`
is private, so neither `Child` nor the main program have access to it.

Line 23 is a compiler error because `method2()` has protected visibility,
so the main program doesn't have access to it. Using this method on Line 16
is OK because `Child` is a subclass of `Parent`.
```
