package Players;

import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.util.ArrayList;

public abstract class Players {

    ArrayList<Card> hand = new ArrayList<>();


    void addCard(ArrayList<Card> cards){

        while(!cards.isEmpty()){
            hand.add(cards.remove(0));
        }
    }

    void emptyHands(){

        while(!hand.isEmpty()){
            hand.remove(0);
        }
    }

    Card playCard(){

        return hand.remove(0);
    }

    Schlag SchlagAnsagen(){
        return Schlag.NEUN;
    }

    Farbe FarbeAnsagen(){
        return Farbe.EICHEL;
    }



}
