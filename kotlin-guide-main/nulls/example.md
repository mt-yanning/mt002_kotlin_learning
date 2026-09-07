# An Example

Let's begin with an example of where null values can occur and see how
Kotlin deals with this situation.

Open the `tasks/task10_1` directory of your repository and study the code
therein. This is a program that translates a word supplied on the command line
from one language to another. It does this using a map containing words and
their corresponding translations. The contents of this map are loaded from
a CSV file.

1. Examine the file `en-fr.csv`, which provides some English to French
   translations. Then run the program, supplying one of the English words
   from the file as a command line argument.

1. Run the program again, this time supplying a word that isn't found in
   the translation file. What do you see as output?

1. Comment out the call to `println()` at the end of `main()`, then uncomment
   the call to the `display()` function. Try recompiling the program with
   `./kotlin build`. What error message do you see from the compiler?

:::{note .dropdown} Explanation
When using `println()` to output the result of translation, the program
compiles and runs successfully. The look-up operation performed on the map
will yield the special value `null` if the supplied word is not in the map,
but `println()` can cope with this.

Problems occur when trying to use `display()` instead of `println()`. This
function expects to receive a `String` object as a parameter, but the
compiler complains about an **argument type mismatch**:

    actual type is 'String?', but 'String' was expected

But what does this mean, and how do we fix it?

Read on for answers to these questions...
:::
