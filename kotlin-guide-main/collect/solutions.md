# Exercise Solutions

```{solution} ex-array-zero-index
:label: sol-array-zero-index
This prints the value at the start of the array, i.e., 9.

Arrays are zero-indexed, just like arrays in C or lists in Python.
```

```{solution} ex-array-oob
:label: sol-array-oob
This will trigger an exception.

Valid indices run from 0 to 5. 6 is out of bounds, so an exception will occur.
```

```{solution} ex-array-slice
:label: sol-array-slice
Option 4 is correct.

Slicing an array returns a list, and the range 2..4 means indices 2, 3 and 4,
so the list will contain the values at those indices.
```

```{solution} ex-list-propmeth
:label: sol-list-propmeth
This code prints the following lines:

    4
    false
    2
    -1

The list contains 4 elements, so the first line of output must be 4, and
the second line must be `false` (because list size is greater than 0).

The third line is 2 because the index of the last occurrence of "Apple" is 2.
The final line of output is -1 because "Banana" is not in the list.
(`indexOf()` returns -1 in such cases, rather than throwing an exception.)
```

```{solution} ex-list-chunk
:label: sol-list-chunk
This prints `[[9, 3, 6], [2, 8, 5], [1]]`.

Chunking with a size of 3 creates sublists of maximum size 3, which do not
overlap. The sublists do not have to contain exactly three elements. If list
size is not a multiple of three then the final sublist will contain one or
two elements (one, in this case).
```
