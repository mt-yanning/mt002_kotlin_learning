// Task 15.2.1: syntax for subclasses
open class Parent

//class Child : Parent()
open class Child : Parent()

class GrandChild : Child() // 编译失败。因为 Child 没有 open 修饰符，不能被继承
