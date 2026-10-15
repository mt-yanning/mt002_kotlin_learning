// Task 12.8.2: program to manipulate playing cards
import Card.Rank
import Card.Suit

fun main() {
    val cards = mutableListOf<Card>()
    for (suit in Card.Suit.entries) {
        for (rank in Card.Rank.entries) {
            cards.add(Card(rank, suit))
        }
    }
    cards.shuffle()
    for ( card in cards ) {
        println(card.fullname)
    }
}
