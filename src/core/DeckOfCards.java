package core;

import java.util.ArrayList;

import static core.Dealer.FACE;
import static core.Dealer.SUITE;

public class DeckOfCards {

    private ArrayList<Card> deck;

    public DeckOfCards(){

        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
        String[] suite = {"diamonds", "clubs", "hearts", "spades"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        deck = new ArrayList<Card>();

        for (int i = 0; i < SUITE; i++) {
            for(int j = 0; j < FACE ; j++){

                Card card = new Card();
              
                card.setSuite(suite[i]);
                card.setFace(faces[j]);
                card.setValue(values[j]);
               
                deck.add(card);
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
