# Exercise Solutions

```{solution} ex-test-part
:label: sol-test-part
There are **five** equivalence partitions here: one for each of the valid
grades (Fail, Pass, Distinction), plus one for marks less than zero,
plus another for marks greater than 100.
```

```{solution} ex-test-cases
:label: sol-test-cases
**13** different exam marks are sufficient to test all partitions.

The Fail, Pass and Distinction partitions can be tested using two boundary
values and one typical value (e.g., 40, 55, 69 in the case of a Pass grade).
Thus 3&times;3 = 9 different exam marks are sufficient to test these partitions.

The 'mark less than zero' partition has an upper bound (-1) but no lower bound,
so can be tested using 2 exam marks (-1 and a typical value, e.g., -10). The
'mark greater than 100' partition has a lower bound (101) but no upper bound,
so it can similarly be tested using 2 exam marks (101 and a typical value,
e.g., 105).
```
