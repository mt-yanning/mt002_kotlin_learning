# Null Checks

Fixing the compiler error in Task 10.1 requires that we add a null check.
We could do this using an `if` or `when` expression (although there are
better ways, which we will consider shortly).

Let's look at two different ways of using `when`.

## Task 10.3.1

The most obvious fix to the Task 10.1 error is to do a null check before
calling `display()`.

Open the `tasks/task10_3_1` directory of your repository. This is a copy of
the Task 10.1 code, with the call to `display()` uncommented. Replace this
call with the following code:

```kotlin
when (result) {
    null -> println("?")
    else -> display(result)
}
```

The program should now compile successfully. Try running it, with a word
that is in the CSV file and with a word that is not. You should see `?`
printed as the translation for the latter.

Think about what is happening here. The `when` expression creates two
execution paths for the program: in the first, `result` is known to have the
value `null`; in the second, we have established that `result` is NOT equal
to `null`, so it must therefore be a valid string.

In this second branch, the compiler is happy to treat the `String?` object
as if it had been a `String` object all along, and we are free to use it as
an argument to `display()`. This is sometimes referred to as
**smart casting**.

Note that there is no conversion of one object into another taking place
here, and therefore no runtime cost, aside from the cost of doing the null
check in the first place. You can think of nullability as a kind of label
attached to an object, indicating uncertainty. Examining the object via
a null check removes the uncertainty and erases that label.

## Task 10.3.2

An alternative approach to fixing the Task 10.1 error is to leave the main
program as it is and instead change the definition of `display()` so that it
can handle nullable strings.

Open the `tasks/task10_3_2` directory of your repository. This is another
copy of the Task 10.1 code, with the call to `display()` uncommented.

Now modify the `display()` function so that it looks like this:

```kotlin
fun display(text: String?) {
    when (text) {
        null -> println("?")
        else -> println(text.uppercase())
    }
}
```

Notice what has changed here. The function parameter now has the type
`String?` instead of `String`. As a consequence of this, we have to do the
null check inside the function, before attempting to uppercase the supplied
string.

The program should now compile successfully. Try running it, with a word
that is in the CSV file and with a word that is not. It should behave
exactly the same as the program in Task 10.3.1.

## Comparison of Solutions

The solutions explored in Tasks 10.3.1 and 10.3.2 perform the same null check;
they differ only in where that check takes place. The null check cannot be
avoided, but we have some control over where it happens.

One issue with doing null checks using `if` or `when` expressions is that
code can quickly become cluttered and harder to read. In the next two
sections, we will look at how Kotlin addresses this issue.
