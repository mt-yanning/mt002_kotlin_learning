# Using Interfaces

To use an interface, we must make a class **implement** that interface. The
syntax for this is very similar to the syntax for inheriting from a superclass.
You must follow the class name with a colon, then the name of the interface.

Let's return to the GUI scenario discussed earlier, where we introduced the
`Clickable` interface. We can create a class `Button` that implements this
interface like so:

```kotlin
class Button : Clickable {
    override fun click() {
        ...
    }
}
```

Because `Button` implements the `Clickable` interface, it must override
`click()` explicitly and it must provide an implementation for this method.
Failing to do so would be a compiler error.

Notice the lack of parentheses after `Clickable` in the definition of
`Button` above This is because it is an interface and not the superclass of
`Button`.

When we create a subclass, we must name its superclass _and_ indicate which
superclass constructor we want to invoke. Hence the superclass name is always
followed by parentheses, possibly containing arguments that will be passed
to the chosen superclass constructor.

Implementing an interface is simpler, because interfaces don't have
constructors. All we need to do when implementing an interface is name that
interface. No constructor is specified, hence there are no parentheses.

A slightly more realistic version of the example above might look like this:

```kotlin
class Button : Widget(), Clickable {
    override fun click() {
        ...
    }
}
```

Here, `Widget` represents a superclass, possibly abstract, from which all GUI
components should inherit. It provides `Button` with some properties to
represent state, and some methods. In this case, we specify that the default
constructor of `Widget` will be invoked as part of the process of creating
a `Button` object.

In this scenario, `Clickable` effectively represents an additional contract
that `Button` objects must fulfil. We can say that `Button` is primarily a
kind of widget, but it also has the more general capability of being clickable.

:::{tip}
When you see a list of type names appearing after a colon in a class
definition, you can say immediately that the first name in the list is either
a superclass or an interface, and all subsequent names are interfaces.

If the first type name is followed by parentheses, possibly containing
constructor arguments, then you know that it is the name of the superclass,
rather than an interface.
:::

## Task 17.3

Open the `tasks/task17_3` directory of your repository and edit the source
file `Printable.kt`. Add code that defines the interface shown in
@fig-ifc-example.

Next, edit `Document.kt`. In this file, write a class named `Document`, with
a `val` property named `filename`, of type `String`.

Make your `Document` class implement the `Printable` interface. Your
implementation of `print()` should display the string "Printing " on the
console, followed by the document's filename.

Examine `Main.kt`. This contains a very simple program that creates a
`Document` object and invokes its `print()` method. Notice how the variable
`item` is declared to be of type `Printable`.

Finally, compile and run the program, with

    ./kotlin run
