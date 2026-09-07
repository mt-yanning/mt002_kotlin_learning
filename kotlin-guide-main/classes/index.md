# Creating & Using Classes

Class design techniques will give you an idea of the attributes that a
class has, and the operations that it supports. The next step is to translate
this into Kotlin code.

One of Kotlin's strengths is that it allows classes to implemented in a
[very concise way][conc]. Attributes are implemented as **properties**, which
can represent values stored within an object or values that are computed on
demand. Operations are implemented as **methods**, also known as member
functions, which look a lot like regular Kotlin functions.

One important issue to consider when implementing a class is the visibility
of its members. The properties and methods of a Kotlin class are public by
default, but it can sometimes be useful to hide them from class users, by
making them private.

In this section, you will also learn about two more specialized kinds of
class: **data classes** and **enum classes**.


[conc]: basics.md#compact-syntax
