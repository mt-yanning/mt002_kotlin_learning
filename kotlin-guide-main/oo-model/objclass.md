# Objects & Classes

## The Procedural Perspective

Our exploration of Kotlin thus far has covered how to organize software in
a modular fashion as a collection of [functions][fun]. This is similar to how
you wrote C programs last year.

This approach is sometimes described as **procedural programming**.

The procedural paradigm reflects a 'task-oriented' perspective, in which the
overall task we want the software to carry out is decomposed into a number
of smaller sub-tasks, which themselves might be decomposed into even smaller
elements.

These various sub-tasks are mapped onto discrete, self-contained units of
code, described as procedures, subroutines or functions, depending on the
programming language used. (In C, Python and Kotlin, for example, we describe
them as functions.)

Some functions will handle the low-level detail. Others will orchestrate
things by sequencing calls to those lower-level functions. Some functions may
even call themselves, a process known as *recursion*. (We looked at this idea
briefly last year.)

With this perspective, the most important things to focus on in a description
of the task are the **verbs and verb phrases**. These will provide clues
about some of the functions that you will need to implement. The nouns and
noun phrases will give you information about the kinds of data that will need
to be provided as input to these functions, and the kinds of data that they
will produce as output.

However, this isn't the only way of looking at a problem, nor is it the only
way of structuring software that solves that problem.

## The Object-Oriented Perspective

A dictionary once [defined the word 'system'][sys] like this:

> An **assemblage of objects** arranged in regular subordination, or after
> some distinct method, usually logical or scientific; a complete whole
> of objects related by some common law, principle, or end; a complete
> exhibition of essential principles or facts, **arranged in a rational
> dependence or connection**; a regular union of principles or parts forming
> one entire thing; as, a system of philosophy; a system of government; a
> system of divinity; a system of botany or chemistry; a military system;
> the solar system.
>
> -- Webster's Dictionary, 1913 edition

For a long time, humans have understood this idea of systems as collections
of interacting, 'rationally dependent' objects. Take the solar system, for
example. This is a collection of various objects (the Sun, Venus, Earth,
Jupiter, etc). These objects interact with each other according to the theory
of gravitation developed by Isaac Newton and later refined by Albert Einstein.

If it is useful to think of systems in the real world as being collections of
interacting objects, then it follows that this may also be a useful way
of structuring software that models such systems.

For example, in software that manages books in a library, it may be useful
to imagine two objects: one representing a person who is a member of the
library, and the other representing a book they might want to borrow.

To handle borrowing a book, these objects exchange messages. The object
representing the library member first sends a message to itself, asking
whether it is allowed to borrow more books (which will involve checking that
the number of books currently borrowed is below some specified limit).

If further borrowing is allowed, the object representing the library member
then issues a 'borrow' request to the object representing the book. The latter
replies to indicate whether borrowing succeeded. (It might fail if there are
no remaining copies of the book on the shelves.)

### The Challenge of Object-Oriented Thinking

In his 1896 paper *Some Preliminary Experiments in Vision*, the psychologist
[George M Stratton][strat] reported on his experiences of viewing the world
through [upside-down goggles][gog]. Initially, he found this nauseating,
but after a few days he found that he adapted fully to this strange new
perception of his environment.

Moving from a procedural view of software systems to an object-oriented view
can be similarly disorienting at first. Much like Stratton's goggles, it turns
things upside down. Instead of the verbs and verb phrases being the focal
point, now it is the **nouns and noun phrases** that are of primary
importance, as these provide clues to the objects that will exist in the
system. The verbs and verb phrases still matter, as they will provide
information about the nature of the messages that need to pass back and forth
between the objects, but they are not the prime focus of our thinking.

Just as Stratton adjusted to his new perception, viewing software systems with
your 'object-oriented goggles' will feel more natural in time, even if you
struggle with it to start with.

## What Are Classes?

Systems consist of objects, but those objects fall naturally into particular
categories or **classes**.

The solar system, for example, contains objects named the Sun, Venus, Earth,
Jupiter, etc. But these objects are not all of the same type. The Sun is a
star, whereas Venus, Earth and Jupiter are all planets.

Star and Planet are classes. The object named 'The Sun' is an **instance** of
the Star class. The objects named Venus, Earth and Jupiter are all instances
of the Planet class.

In software, a class specifies the **attributes** that describe objects of
that class, and the **operations** that can be performed on or by objects of
that class. All instances of the class will have these attributes, and
all instances will support the specified set of operations.

A software class is a **simplified abstraction** of a real-world class.
Rather than attempting to provide a complete model of the real-world class,
It will specify only those attributes and operations that are pertinent to
the software being developed.

When a class is implemented in an object-oriented programming language, the
attributes are mapped onto a set of variables typically known as **instance
variables** or **fields**. (In Kotlin, we refer to these in a more abstract
way, as [properties][prop].) The values of these variables collectively
represent the **state** of an object of that class. Two objects in identical
states are nevertheless distinct from each other, i.e., they each have their
own unique identity. (In practical terms, this means that they occupy
different addresses in memory.)

The operations we have specified for a class are implemented as **member
functions** or **methods** of the class. These look a lot like regular
functions, except that they have privileged access to the fields of the class.
Some methods will query object state, whereas others will change the state
of an object. The latter are sometimes described as mutator methods.

```{exercise}
:label: ex-obj-class
:enumerator: 11.1
Identify whether each of the following is an object or class.

1. Lecturer
1. Professor Dillon Mayhew
1. Student
1. John Smith, studying Computer Science
1. Room LT21 in the Roger Stevens Building
1. Lecture Theatre
```

### Origins & Philosophy

The idea of classes originated with the Greek philosopher Plato (428&ndash;348 BC).

Plato suggested that each object in the world was made in the image of a
perfect, ideal form, which provided the template for its creation. He believed
that there was an immaterial 'universe' of these ideal forms.

This fits remarkably well with how classes work in software. A class
definition is indeed a kind of template for objects of that class, specifying
not only how to create instances of the class but also how those instances
are subsequently allowed to behave.

```{figure} greeks.jpg
:label: fig-greeks
:enumerator: 11.1
:alt: A painting by Raphael of the Greek Philosophers Plato and Aristotle
:width: 495px
:align: center

Plato (left) and Aristotle, in "The School of Athens"
```

Plato's student Aristotle (384&ndash;322 BC) set out to find the "common and
distinguishing properties of all things, from the most primary object down to
the tiniest details".

Like Plato, he regarded objects as belonging to specific categories or
classes. He proposed that the 'essence' of a class derives from two sources:
the 'genus' and the 'differentia'.

The genus is the set of characteristics provided by a parent class, whereas
the differentia are the extra characteristics that distinguish the class from
its parent and its sibling classes.

This idea from ancient Greece turns out to be extremely important when we
consider how to organize software as a hierarchy of classes. We will consider
this further in the section on [inheritance][inh].


[fun]: ../funcs/index.md
[sys]: https://www.websters1913.com/words/System
[strat]: https://en.wikipedia.org/wiki/George_M._Stratton
[gog]: https://en.wikipedia.org/wiki/Upside_down_goggles
[prop]: https://kotlinlang.org/docs/properties.html
[inh]: ../inherit/basics.md
