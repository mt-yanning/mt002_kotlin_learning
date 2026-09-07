# Enum Classes

Many of the data types we've seen so far can represent a huge number of
possible values. An `Int` variable, for example, can have any of over four
billion values, and the number of different values possible for a `String`
is vastly greater than that.

It simply isn't practical to enumerate all possible values of these
fundamental types, and if that's the case, it follows that it also won't be
practical to enumerate all the possible states for an instance of any class
composed of those types.

However, there are situations in which the number of possible values for
something is relatively small: small enough that they can be enumerated
easily. Take days of the week, for example. There are only seven possible
values here: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday.

We could represent days of the week using an `Int` with a value between
1 and 7, but how do we restrict it to that range? Another problem is that
`Int` supports operations such as multiplication or division that clearly
don't make any sense for days of the week:

```kotlin
val today = 1               // Monday (minimum value)
val yesterday = today - 1   // has value 0, which isn't a day
val day = today * 3         // what does this even mean?
```

Like many other programming languages, Kotlin offers a better solution:
a way of creating special **enumerated types** that have a predefined set
of possible values.

## An Example

In Kotlin, we can represent days of the week by defining them as an
**enum class**.

Here's an example, along with the nearest equivalent in Python & C[^safe]:

::::{tab-set}
:::{tab-item} Kotlin
```kotlin
enum class Day {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday
}
```
:::
:::{tab-item} Python
```python
from enum import Enum

class Day(Enum):
    MONDAY = 1
    TUESDAY = 2
    WEDNESDAY = 3
    THURSDAY = 4
    FRIDAY = 5
    SATURDAY = 6
    SUNDAY = 7
```
:::
:::{tab-item} C
```c
typedef enum {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
} Day;
```
::::

Here, the possible values for a `Day` variable are provided in the body of
the class, as a comma-separated list of constants. For example, `Monday` is
a constant, of type `Day`. It is not a string. `Monday` is both its name
_and_ its value.

When you use one of these constants in your code, you'll need to be
explicit about its type:

```kotlin
val day = Monday        // 'Unresolved reference' error

val day = Day.Monday    // OK

if (day == Day.Monday) {
    println("Another week begins...")
}
```

If that becomes too tedious, you could import the constants from the class
by putting the following at the top of the `.kt` file:

```kotlin
import Day.*
```

This would allow you to refer to them more simply, as `Monday`, `Tuesday`,
etc. However, this may have a negative impact on code clarity.

:::{note}
The official [Kotlin coding conventions][conv] state that enum constants should
be named using upper camel case (as here) or screaming snake case. In Python
and C, it is customary to use screaming snake case.

The constants in Kotlin & C enums are comma-separated and therefore don't
have to be defined on separate lines (although using separate lines
simplifies maintenance).
:::

## Built-in Properties & Methods

Enum constants each have a property `name`, which gives the constant's
name as a string, and a property `ordinal`, which gives the zero-based
position of the constant in the enum's list of constants:

```kotlin
Day.Monday.name      // "Monday"
Day.Monday.ordinal   // 0
Day.Sunday.ordinal   // 6
```

Another property, `entries`, is associated with the enum *class*, rather
than individual constants. This represents all of the constants as a
collection, which can be useful if you need to iterate over all possible
values:

```kotlin
for (day in Day.entries) {
    println("Tasks for $day:")
    // tasks that needs to be performed for each day
}
```

A useful method associated with the class is `valueOf()`. This will parse
the given string, returning the enum constant that matches that string:

```kotlin
val day = Day.valueOf(dayString)
```

### Task 12.8.1

1. Open the `tasks/task12_8_1` directory of your repository. Edit `Day.kt`
   and add to this file the enum class for days of the week shown above.

1. In `Main.kt`, write a `main()` function that asks the user to enter a day,
   reads that day as a string, then attempts to parse it using
   `Day.valueOf()`.

1. Use the Gradle wrapper to compile and run the program. Try entering
   `Monday` as the string. Run it again, this time entering `monday`.
   What happens?

1. Modify the program so that it handles errors more gracefully and
   indicates to the user what their options are.

   :::{hint .dropdown} Hints
   Refer to [the earlier discussion of exceptions](../errors/catching.md)
   if you need a reminder of how to handle run-time errors gracefully.

   You can use the `entries` property to get the options for specifying a day.
   :::

## A More Complex Example

You can create enum classes with additional properties and methods. Every
enum constant will acquire those properties, initialized to the values you
supply as constructor arguments.

As an example, let's imagine that you are creating software that plays games
using [playing cards][card]. A standard 52-card deck contains of four
[**suits**][suit]: Clubs (♣), Diamonds (♦), Hearts (♥) and Spades (♠). Each
suit consists of cards with thirteen different **ranks**: Ace, Two, Three,
etc, up to Ten, followed by Jack, Queen and King.

Here's how you could represent card suits as an enum class, in which each
constant has a `symbol` property representing the Unicode character of
the suit:

```{code} kotlin
:linenos:
enum class Suit(val symbol: Char) {
    Clubs('♣'),
    Diamonds('♦'),
    Hearts('♥'),
    Spades('♠');

    val plainSymbol get() = name[0]

    override fun toString() = "$symbol"
}
```

The standard syntax is used on line 1 to specify both the property and a
primary constructor to initialize that property. The property is of type
`Char`, so a `Char` value must be supplied when creating each enum constant
in the body of the class definition (lines 2&ndash;5).

Notice that this enum class has two additional features, besides the enum
constants. First is a computed property, `plainSymbol` (line 7). This provides
a simpler symbol for each suit: the first character of each constant's name.
This can be used in situations where it might not be possible to display
the Unicode symbols.

The second additional feature is an overridden `toString()` method (line 9).
All enum classes have a default implementation of `toString()` that returns an
enum constant's name, but this can overridden if required. The new version of
the method shown here returns a string containing the Unicode symbol of
the suit.

:::{note}
Did you spot the semicolon that has now appeared at the end of the list
of constants?

You need this if you are adding anything extra to the enum class, besides
the list of constants. This is one of the few occasions where semicolons
are necessary in Kotlin code!
:::

### Task 12.8.2

Open the `tasks/task12_8_2` directory of your repository. Examine `Suit.kt`,
in the `src` subdirectory. This file contains the enum class for playing
card suits discussed above.

#### `Rank` Enum Class

Edit the file `Rank.kt`. Add to this file an enum class named `Rank` that
represents the rank of a playing card. Like `Suit`, this should give each
enum constant a `symbol` property. Use `'A'`, `'2'`, `'3'`,..., `'9'`, `'T'`,
`'J'`, `'Q'`, `'K'` as values for this property.

Next, give `Rank` an overridden `toString()` method just like the one
for `Suit`.

Check that the enum class compiles, using

    ./kotlin build

#### `Card` Class

Edit the file `Card.kt`. In this file, create a class named `Card` with a
primary constructor that defines and initializes two properties:
`rank`, of type `Rank`, and `suit`, of type `Suit`. Check that the class
compiles before proceeding further.

Next, add to the `Card` class a computed property `fullName`, which provides
the full name of a card, as a `String` object.

You should construct the full name by concatenating the name of the card's
rank, the string `" of "`, and the name of the card's suit. Thus, if the
`rank` property has the value `Rank.Ace` and the `suit` property has the
value `Suit.Clubs`, the value of `fullName` should be `"Ace of Clubs"`.

Now override `toString()` in `Card` so that it returns a two-character string
consisting of the rank's symbol and the suit's symbol. So if the `rank`
property has the value `Rank.Ten` and the `suit` property has the value
`Suit.Hearts`, the string `"T♥"` should be returned.

As before, make sure that the class compiles before proceeding further.

#### Main Program

Edit `Main.kt`. In this file, write a program that

* Creates a mutable list of `Card` objects, to represent a deck of cards
* Populates that list with a full set of 52 standard playing cards
* Shuffles the deck randomly
* Prints the full name of each card in the shuffled deck

Check program behaviour by running it with

    ./kotlin run

:::{hint .dropdown} Hints
You can use `Suit.entries` and `Rank.entries`, in combination with nested
`for` loops or `forEach` function calls, to populate the list.

Remember that lists in Kotlin have a `shuffle()` extension function...
:::

## Nested Classes

If you've completed the tasks above, you should have enum classes `Rank`
and `Suit`, plus a regular class `Card` that uses those enum classes. There
is one further improvement we can make to this implementation of playing
cards.

If you think about it, the concepts of 'rank' and 'suit' have no separate
meaning outside the context of playing cards. We are unlikely to ever write
code that works with ranks or suits on their own, without there being a
`Card` object somewhere.

We can model the intimacy of this relationship between `Rank`, `Suit` and
`Card` by **nesting the definitions of the enum classes inside the
definition of the `Card` class**:

```kotlin
class Card(val rank: Rank, val suit: Suit) {

  enum class Rank { ... }

  enum class Suit { ... }

  ...
}
```

Kotlin allows class definitions to be nested within other class definitions.
It allows you to do the same with functions, too[^scope].

The practical impact of this change is that code outside the `Card` class
will need to refer to the enums as `Card.Rank` and `Card.Suit`:

```kotlin
for (suit in Card.Suit.entries) {
    ...
}
```

However, this can be avoided by importing the enum classes from `Card`:

```kotlin
import Card.Rank
import Card.Suit
```

### Optional Task Extension

If you like, try modifying your Task 12.8.2 solution so that the `Rank` and
`Suit` enums are nested inside the `Card` class, as described above.

Make sure that this new solution compiles and runs successfully.


[^safe]: Note that C enums are not 'type-safe'. They are just a convenient way
of defining a set of symbolic constants that have integer values. The compiler
won't prevent us from doing arithmetic with those values. Kotlin and Python
enums are type-safe and do not allow such nonsense.

[^scope]: In general, you can limit the scope of a function or class to a
single block of code by defining it within that block of code.

[conv]: https://kotlinlang.org/docs/coding-conventions.html
[card]: https://en.wikipedia.org/wiki/Playing_card
[suit]: https://en.wikipedia.org/wiki/Playing_card_suit
