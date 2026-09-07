# OO Analysis Techniques

## Overall Goal

The goal of object-oriented analysis is to come up with a high-level model
of the system as a collection of collaborating classes.

The process of identifying objects and classes, and how they relate to each
other, is not an exact science. It isn't necessary to get this right first
time; indeed, it is entirely normal to refine an object-oriented model as
we learn more about the system and how it is supposed to work.

Object-oriented analysis and design are thus fundamentally *iterative* in
nature, like many aspects of software development.

A number of techniques have emerged for creating a high-level class model
of a system. Two of the most popular are **noun identification** and
**CRC cards**. Typically, these are used in conjunction with each other
to analyze a problem domain and come up up with an OO model.

## Noun Identification

The starting point for this technique is the text of a particular
[use case][uc] or [user story][story], i.e., a description of something that
the software will need to do.

Step 1 is to underline all the nouns and noun phrases in the text. These,
reduced to their singular form[^sing], become the initial set of **candidate
classes**.

Step 2 involves discarding candidates that are deemed inappropriate for any
reason, and also renaming initial candidates if necessary.

Here are some reasons why we might discard a candidate class:

* Redundant
* Vague
* Attribute of another class
* Event or operation
* Meta-language
* Outside scope of system

**Redundancy** occurs when you have multiple noun phrases that all refer to
the same thing. Pick one noun to use as the name of the class and discard all
the others.

**Vagueness** arises when you can't tell unambiguously what is meant by a
particular noun. You need to clear up that ambiguity before you can say whether
the noun should be a class.

An **attribute of another class** will typically be a noun representing
something that is too simple to be a class itself. For example, a customer's
name can just be a string attribute of a `Customer` class; it almost certainly
doesn't need to be a class itself.[^attr]

Some nouns may refer to an **event or operation**: something that is done to,
by, or in the system. Sometimes it is helpful for these things to be classes
themselves, but often this is not the case. Ask yourself whether the thing
has state, behaviour and identity: if so, it should be represented with a
class; if not, it can be discarded.

**Meta-language** includes nouns that are part of the way that we define
things. For example, the text of a use case or user story may use words like
'feature' or 'system'. These are part of the language we use to describe
requirements. They should not be classes themselves.

Some nouns represent things that are **outside the scope of the system**.
For example, in an e-commerce application, we wouldn't have a class named
`Shop`, to represent the place from which a customer purchases items.
The classes we choose should generally be things that are inside the system
being modelled.

### Example

Consider a university library that offers books and journals on loan to
students and members of staff. Here is some text describing how the loan
system should operate, with the nouns & noun phrases underlined:

> The {u}`library` contains {u}`books` and {u}`journals`. It may have
> several {u}`copies of a given book`.
> 
> Some of the books are for {u}`short term loans` only. All other books may
> be borrowed by any {u}`library member` for three {u}`weeks`.
> 
> {u}`Members of the library` can normally borrow up to six {u}`items` at
> a {u}`time`, but {u}`members of staff` may borrow up to twelve items
> at one time. Only members of staff may borrow journals.
>
> The {u}`system` must keep track of when books and journals are borrowed
> and returned, enforcing the {u}`rules` described above.

The underlined things are the initial candidate classes. From this list,
we would discard:

* Library (outside scope of system)
* Short term loan (loans are really events)
* Week (measure of time, not a useful thing)
* Member of the library (redundant: duplicate of 'library member')
* Item (vague: really means book or journal)
* Time (outside scope of system)
* System (meta-language)
* Rule (meta-language)

This leaves us with

* Book
* Journal
* Copy (of book)
* Library member
* Member of staff

This is only an initial model, and it may be refined further. For example,
we might later realize that all copies of a book are identical, so it will
be sufficient to just count them and represent this as an attribute of
the `Book` class.

## CRC Cards

[**CRC cards**][crc] are a long-established technique for checking and further
refining an object-oriented model. The acronym 'CRC' stands for "Class,
Responsibilities, Collaborations".

The idea is fairly simple. For each class, you create a 'card' with the name of
the class at the top. Underneath the name, you list the **responsibilities**
of the class on the left side of the card, and the **collaborators** of the
class on the right side.

The list of responsibilities captures, at a high level, the purpose of the
class. They explain what it does, and why it is needed.

The list of collaborators identifies the other classes that help this class
fulfil its responsibilities.

CRC cards are traditionally created as physical [index cards][index] that can
be placed on a large table and rearranged as necessary while a team of
developers discusses the design of the system. A variation on this idea uses
[Post-it notes][post] stuck to a whiteboard, or stuck onto paper sheets that
can be mounted on a wall later (@fig-crc).

```{figure} crc.jpg
:label: fig-crc
:enumerator: 11.2.1
:alt: Photograph of a table with a large number of Post-it notes of different colours stuck to it
:width: 400px
:align: center

CRC cards as Post-it notes
```

The class name and its responsibilities are written on the note. Collaborators
are shown by drawing lines on the whiteboard or the backing paper, linking
the note to other notes.

It's usually easier to identify responsibilities than collaborators at first.
The list of collaborators will typically grow as the team discusses the
classes and explores how these classes might work in concert with each other.

:::{danger} Important
Too many responsibilities indicates that a class has low **cohesion**.

Too many collaborators indicates that a class has high **coupling**.

Systems are easier to develop, test, maintain and extend if they are composed
of highly cohesive classes that are loosely coupled with each other.
:::

### Example

Let's consider the classes emerging from the earlier noun identification
example.

The most important of these classes are `LibraryMember`, `Copy` and `Book`.
@fig-crc-example shows what the CRC cards for these classes might look like.

```{figure} crc-example.png
:label: fig-crc-example
:enumerator: 11.2.2
:alt: Three CRC cards for classes LibraryMember, Copy and Book
:width: 500px
:align: center

Examples of CRC cards
```

<!-- TODO: Add a Task 11.2 here? e.g., on noun identification -->


[^sing]: A class describes a singular thing. Thus 'Bank Account' is a class,
but 'Bank Accounts' is not.

[^attr]: Sometimes it isn't clear whether something should be represented with
a simple data type or a dedicated class. In such a situation, pick one option
and then refine the design or refactor your code later, when you've learned
more about the system.

[uc]: https://en.wikipedia.org/wiki/Use_case
[story]: https://en.wikipedia.org/wiki/User_story
[crc]: https://en.wikipedia.org/wiki/Class-responsibility-collaboration_card
[index]: https://en.wikipedia.org/wiki/Index_card
[post]: https://en.wikipedia.org/wiki/Post-it_note
