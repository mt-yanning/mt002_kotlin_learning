# Extension Functions

An **extension function** is a function that we 'plug in' to an existing data
type in order to extend its capabilities in some way.

In other object-oriented languages we would achieve this by using inheritance
to create a specialized version of an existing type. The desired behaviour
would be implemented as a member function (method) of this new type. Kotlin
supports this approach fully, but also offers the simpler alternative of
writing an extension function.

Extension functions are particularly useful in cases when all we want to do
is add a small piece of functionality to an existing type. In such cases,
it can feel like too much trouble to create a whole new type that inherits
from that existing type.

Also, some types will explicitly deny us permission to extend them via
inheritance. In such cases, extension functions are our only option for
adding functionality to the type.

## Example

Imagine that you are writing some code in which it is frequently necessary
to test whether an integer value is odd or not. This can be accomplished
easily enough by using the modulus operator to test whether dividing by 2
yields a non-zero remainder:

```kotlin
if (n % 2 != 0) {
    ...
}
```

The only drawback is that this code isn't as readable as it could be.

An obvious solution would be to write a dedicated function that tests for
oddness:

```kotlin
fun isOdd(n: Int) = n % 2 != 0
```

This function can then be used throughout your code like this:

```kotlin
if (isOdd(n)) {
    ...
}
```

But there is another approach we could follow here. We could think of testing
for oddness as part of the behaviour of the `Int` type. This suggests a
slightly different implementation, as an extension function of `Int`. This
is very easy to do:

```kotlin
fun Int.isOdd() = this % 2 != 0
```

Note the three changes that are needed to make this an extension function:

* The name of the type being extended must be used as a prefix to
  the function name, with the two names separated by the period character
  (i.e., `Int.isOdd`)

* The parameter list is now empty, because the integer value is no longer
  passed in as a function argument; instead it is the **receiver** of the
  function call

* Within the function body, the receiver of the call is referenced as `this`

The extension function is then used like so:

```kotlin
if (n.isOdd()) {
    ...
}
```

{button}`Try this out <https://pl.kotl.in/RI2fcFJdC>`

In effect, the `Int` type has been extended with a new capability. Anywhere
in your application, you are now able to 'ask' an integer value whether it is
odd or not.

## Extension Properties

Oddness is an intrinsic quality of integers, so it would be nice if it could
be implemented as a **property** of integers, rather than requiring an
explicit function call. This is also fairly easy to do:

```kotlin
val Int.isOdd: Boolean get() = this % 2 != 0
```

Note the differences between this and the extension function:

* We define it using `val` rather than `fun`

* We specify the property's type (`Boolean`, in this case) explicitly

* We use `get()` to define the 'getter function' that retrieves the
  value of the property

Making `isOdd` an extension property rather than an extension function
allows us to omit the parentheses, which feels simpler and more natural:

```kotlin
if (n.isOdd) {
    ...
}
```

{button}`Try this out <https://pl.kotl.in/K_b23tnJu>`

:::{note}
Extension properties like this are a useful abstraction, but keep in mind
that under the hood, a function is always being called. Properties are
merely a way of hiding these function calls, to improve the clarity and
readability of the code.

We will [discuss properties in detail later][prop], when we cover
[classes][cls].
:::

## Task 5.4.1

Open the `tasks/task5_4_1` directory of your repository in your preferred
code editing environment. Edit the file `StringTools.kt` and add to this file
an extension function for the `String` type named `isTooLong()`. This should
return `true` if the length of the receiver is greater than 20 characters.

Next, edit `Main.kt` and add to `main()` a small program that tests
`isTooLong()` using strings of different lengths.

Finally, build and run the program to make sure that it behaves as expected.

## Task 5.4.2

Copy the two source files from `task5_4_1/src` into `task5_4_2/src`.

Edit the new copy of `StringTools.kt` in `task5_4_2/src` and change
`isTooLong()` into an extension property named `isTooLong`. Then change the
program in `Main.kt` accordingly.

Build and run the program to check that its behaviour is unchanged.


[prop]: ../classes/props.md
[cls]: ../classes/index.md
