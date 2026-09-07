# Abstract Classes in Kotlin

## Creating an Abstract Class

We indicate abstractness in Kotlin by using the `abstract` modifier when
defining the class. The modifier must be applied to the start of the class
definition and to the declarations of any specific abstract features.
Note that both methods and properties can be declared as abstract.

For example, the `Vehicle` class in @fig-abs-example could be defined in
Kotlin like so:

```{code} kotlin
:linenos:
abstract class Vehicle(var fuelLevel: Int) {
    fun refuel(amount: Int) {
        require(amount >= 0) { "Invalid fuel amount" }
        fuelLevel += amount
    }

    abstract fun drive()
}
```

Note the use of `abstract` on lines 1 and 7, and the lack of a method body
on line 7.

## Using an Abstract Class

To be useful, an abstract class must act as the superclass for other classes
in an application, i.e., we must have classes that inherit from it.

Inheriting from an abstract class doesn't look any different to inheriting
from a regular concrete class. However, when you do so, you must either
provide implementations of all the abstract features, or declare the subclass
itself as abstract.

Consider this example of inheriting from `Vehicle`:

```kotlin
class Car(fuel: Int) : Vehicle(fuel)
```

This won't compile, because in its current form it satisfies neither of
those requirements.

If the intention is to create instances of `Car`, it will be necessary to
provide an implementation of the `drive()` method; otherwise, the `abstract`
modifier should be applied to the class.

## Task 16.2

The directory `tasks/task16_2` in your repository is a KT project that you
can use to experiment with defining abstract classes.

### `Vehicle` Class

Open the file `Vehicle.kt`, in the project's `src` subdirectory. Add the
definition of `Vehicle` shown above and save the file.

Check that the class compiles successfully, e.g. using

    ./kotlin build

Now try removing the `abstract` modifier at the start of the class definition.
What error do you see when you try rebuilding the project?

Reapply the `abstract` modifier to the start of the class definition, then
remove the modifier on the `drive()` method. What error do you see when you
try rebuilding the project?

Finally, reapply the `abstract` modifier to the `drive()` method.

### `Car` Class

Edit the file named `Car.kt` and add the following class definition:

```kotlin
class Car(fuel: Int) : Vehicle(fuel)
```

Try rebuilding the project. What error do you see?

The error message suggests two possible fixes. Implement the first of them
now by adding the `abstract` modifier to the definition of `Car`. Verify that
this fixes the compiler error.

Now try the other fix. Modify `Car` so that it looks like this:

```kotlin
class Car(fuel: Int) : Vehicle(fuel) {
   fun drive() {
       println("Vrooom!")
   }
}
```

When you rebuild the project, you should see another error from the compiler.

Use the information from the compiler error message to make one final change
to the class, fixing the error. Rebuild the project to verify that you have
done this successfully.

Finally, edit the file `Main.kt`. Add code to the `main()` function that
creates a `Car` object and invokes its `drive()` method. Verify that this
program runs as expected, using

    ./kotlin run
