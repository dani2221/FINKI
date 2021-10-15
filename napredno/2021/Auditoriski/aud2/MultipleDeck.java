package mk.ukim.finki.aud2;

public class MultipleDeck {

    private Deck[] decks;

    public MultipleDeck(int numberOfDecks) {
        this.decks = new Deck[numberOfDecks];
        for(int i=0; i<numberOfDecks; i++){
            decks[i] = new Deck();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Deck deck: decks){
            sb.append(deck);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MultipleDeck multipleDeck = new MultipleDeck(3);
        System.out.println(multipleDeck);
    }
}
