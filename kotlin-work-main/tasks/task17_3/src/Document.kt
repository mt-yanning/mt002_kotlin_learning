// Task 17.3: Document class, implementing Printable
class Document(val text: String): Printable {
    override fun print() {
        println("$text")
    }
//  override fun print() = (println("$text")

}
