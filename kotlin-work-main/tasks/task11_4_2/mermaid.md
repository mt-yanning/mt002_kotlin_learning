# UML Class Diagram

Here is a UML class diagram, specified using [Mermaid][mer].

```mermaid
%% Replace this line and the two below
classDiagram
    Customer -- Order : places
    Order -- OrderItem : includes
    class Customer {
        name
        placeOrder()
    }
    class Order {
        orderNumber
        datePlaced
        deliveryDate
        checkStock()
        takePayment()
    }
    class OrderItem {
        name
        description
    }
```


[mer]: https://mermaid.js.org/
