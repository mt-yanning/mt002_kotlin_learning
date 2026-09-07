# Exercise Solutions

```{solution} ex-catch-match
:label: sol-catch-match
1. Pressing {kbd}`Ctrl` + {kbd}`D` closes the input stream, causing
   `EOFException` to occur. This exception doesn't match the first two catch
   blocks but does match the third, so the message "Some sort of error
   occurred" is printed.

1. Entering `0` as the denominator means that the program will attempt
   division by 0, which causes an `ArithmeticException`. This exception
   doesn't match the first two catch blocks but does match the third, so
   "Some sort of error occurred" is printed.

1. Entering `three` as the numerator causes the `toInt()` conversion
   function to throw a `NumberFormatException` exception. This is intercepted
   by the first catch block in the program, so "You didn't enter  a valid
   number" is printed.
```

```{solution} ex-catch-removal
:label: sol-catch-removal
You could remove the three lines of the final catch block. They don't have
any effect because any occurrence of `ArithmeticException` will be intercepted
by the previous catch block.
```

```{solution} ex-catch-order
:label: sol-catch-order
The practical effect of swapping the order of the last two catch blocks would
be that entering 0 would now lead to the message "You tried to divide by zero"
being printed.
```
