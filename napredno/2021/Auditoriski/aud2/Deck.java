package mk.ukim.finki.aud2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private PlayingCard[] cards;
    private boolean[] picked;
    private int pickedTotal;

    public Deck() {
        cards = new PlayingCard[52];
        picked = new boolean[52];
        pickedTotal = 0;

        for(int i=0; i<PlayingCard.TYPE.values().length; i++){
            for(int j=0; j<13; j++){
                cards[j + 13*i] = new PlayingCard(PlayingCard.TYPE.values()[i], j+1);
            }
        }
    }

    public PlayingCard[] getCards() {
        return cards;
    }

    public void setCards(PlayingCard[] cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;

        if(this.getClass() != o.getClass()) return false;
        Deck other = (Deck) o;
        return Arrays.equals(cards, other.cards);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(cards);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlayingCard card: cards){
            sb.append(card);
            sb.append("\n");
        }
        return sb.toString();
    }

    public PlayingCard dealCard() {
        if(pickedTotal == 52) return null;
        // the random function returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
        int card = (int) (52 * Math.random());
        if(!picked[card]){
            pickedTotal++;
            picked[card] = true;
            return cards[card];
        }
        return dealCard();
    }

    public PlayingCard[] shuffle() {
        List<PlayingCard> playingCardList = Arrays.asList(cards);
        Collections.shuffle(playingCardList);
        return cards;
    }

    public boolean hasCards() {
        return cards.length > 0;
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck);

        deck.shuffle();
        System.out.println(deck);

        PlayingCard card;
        while((card = deck.dealCard()) != null){
            System.out.println(card);
        }
    }
}
