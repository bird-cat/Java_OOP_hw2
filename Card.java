package ntu.r09922114.gambling;


public class Card {
    private char suit;
    private String rank;

    public Card(char suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String toString() {
        return suit + rank;
    }

    public char getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int compare(Card card) {
        int d1 = Computer.rankOrder.get(rank) - Computer.rankOrder.get(card.rank);
        int d2 = Computer.suitOrder.get(suit) - Computer.suitOrder.get(card.suit);
        return (d1 != 0)? d1 : d2;
    }

}