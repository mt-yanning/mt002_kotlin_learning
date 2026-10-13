// Task 7.2: array comparison
fun main() {
    val numbers = arrayOf(9, 6, 3, 2)
    val numbers2 = intArrayOf(10, 9, 8, 7)

    val cls = numbers::class // ::class 获取该对象运行时的实际类型
    val cls2 = numbers2::class

    println(cls.qualifiedName)
    println(cls.java)

    println(cls2.qualifiedName)
    println(cls2.java)
}
// numbers::class	对象上的 ::class，取运行时真实类型
// List::class	    类名上的 ::class，取编译期已知的类型

//kotlin.Array  arrayOf()     arrayOf() 创建的是泛型 Array
//class [Ljava.lang.Integer;  底层是 Java 的 Integer[] 对象数组
//kotlin.IntArray             intArrayOf() 创建的是 IntArray
//class [I                          底层是 Java 的原始 int[] 数组