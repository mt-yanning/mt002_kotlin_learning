# Collection Types

Like any modern programming language, Kotlin provides a range of ways in
which collections of objects can be organized and manipulated as a single
entity. There are six main collection types, and these can be nested to
create more complex data structures if needed.

| Type                   | Description                                   |
|------------------------|-----------------------------------------------|
| [Pair](pair-trip.md)   | Tuple of two values, each of any type         |
| [Triple](pair-trip.md) | Tuple of three values, each of any type       |
| [Array](arrays.md)     | Fixed-sized collection, indexed by position   |
| [List](lists.md)       | Collection of items, indexed by position      |
| [Set](sets-maps.md)    | Collection of unique items                    |
| [Map](sets-maps.md)    | Collection of associated keys & values        |

Pairs, triples and arrays allow us to represent collections with a fixed
number of elements. Lists, sets and maps can grow, shrink or have their
contents replaced, but only if we use the mutable variants of those
collection types, rather than the immutable versions.

After completing this section, you will understand how to create and use
instances of all these collection types in your Kotlin programs.
