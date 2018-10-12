import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.awt.*;
import java.util.ArrayList;

public abstract class Players {

    ArrayList<Card> hand = new ArrayList<>();

    void addCard(ArrayList<Card> cards) {
        while (!cards.isEmpty()) {
            hand.add(cards.remove(0));
        }

    }

    void emptyHand() {
        System.out.println("clearing hands now...");
        while (!hand.isEmpty()) {
            hand.remove(0);
        }
    }

    public abstract void render(Graphics g);

    public abstract Card playCard(ArrayList<Card> cardsPlayed, Schlag angesagterSchlag, Farbe angesagteFarbe);

    public Schlag SchlagAnsagen() {
        return Schlag.NEUN;
    }

    public Farbe FarbeAnsagen() {
        return Farbe.EICHEL;
    }

    void printHand(){
        for(Card c : hand){
            System.out.print(c.getFarbe() + "  " + c.getSchlag() + "         ");
        }
        System.out.println();
    }

    synchronized Card getCardAt(int i){
        return hand.remove(i);
    }
}
