# More Tasks

## Task 7.7.1

In this task, you will create a program that computes these statistics for
a numeric dataset:

* Minimum
* Maximum
* Mean ('average')
* Median

:::{tip}
To implement this quickly and easily, you will need to make effective use
of the methods and extension functions of lists. Everything you need for
this task was mentioned in the discussions of [iterative file I/O][iter]
and [extension functions on collections][ops].

A good solution to this task shouldn't require much more than 30 lines of
source code (excluding blank lines & comments).
:::

### Reading The Data

Open the `tasks/task7_7_1` directory of your repository. Add to `Data.kt` a
function that reads floating-point values from a file, returning them as
an immutable list:

```kotlin
import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun readData(filename: String) = buildList {
   Path(filename).forEachLine {
       add(it.toFloat())
   }
}
```

This code should be largely familiar. Refer back to earlier discussions of
[reading lines][lin] and [`buildList()`][bl] if you need reminders of
how it works.

### Computing & Displaying Statistics

Now edit `Stats.kt` and write a function that computes the [median][med] of a
list of floating-point values. (Be careful here: the calculation changes,
depending on whether the list size is odd or even.)

After completing this, write another function in the same file that displays
the required statistics for a list of floating-point values. Obviously,
this should make use of the function that computes a median. **The other
statistics can each be computed using a single line of code** and therefore
don't require specialised functions.

Finally, write a small `main()` function in `Main.kt` that uses all of
the previously written functions to read the data and display the required
statistics. Your `main()` should expect to receive the name of the data file
as a command line argument.

## Task 7.7.2

Open the `tasks/task7_7_2` directory of your repository. This is a Gradle
project for a program to simulate a database of contacts and their telephone
numhers. This database should be stored on disk as a CSV file and represented
in memory as a map of strings onto strings.

Edit the file `Database.kt`. This defines `Database` as a convenient **type
alias** for a map of strings onto strings, and implements a function to create
an empty database. It also defines stubs of functions to load the database
from a CSV file and save it to a CSV file.

Your first job is to implement these stubs fully.

:::{note}
We have assumed that the functions to load and save data are extension
functions of `Database`, but you can change them to be regular functions
if you prefer.

As in the previous task, you might find the discussion of [iterative
file I/O][iter] to be useful here.
:::

When you've done that, edit `Main.kt` and write a program that reads a
database from a file, then repeatedly prompts the user to enter a contact's
name. If the name is present in the database, the program should display the
corresponding phone number; otherwise, it should prompt for entry of that
person's phone number and then store the pairing of name and number in
the database, as well as saving the data back to the file.

Feel free to implement additional functions in `Main.kt` if that would
improve the structure and readability of the program.

You can also try making the program more robust if you like&mdash;e.g., by
checking that empty strings haven't been input, or that the phone number
string consists solely of digits.


[iter]: ../flow/file-iter.md
[ops]: other.md#extension-functions
[lin]: ../flow/file-iter.md#reading-lines
[bl]: lists.md#buildlist
[med]: https://en.wikipedia.org/wiki/Median
