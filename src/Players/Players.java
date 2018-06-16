package Players;

import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.util.ArrayList;

public abstract class Players {

    ArrayList<Card> hand = new ArrayList<>();


    public void addCard(ArrayList<Card> cards){

        while(!cards.isEmpty()){
            hand.add(cards.remove(0));
        }
    }

    public void emptyHands(){

        while(!hand.isEmpty()){
            hand.remove(0);
        }
    }

    public Card playCard(){
        return hand.remove(0);
    }

    public Schlag SchlagAnsagen(){
        return Schlag.NEUN;
    }

    public Farbe FarbeAnsagen(){
        return Farbe.EICHEL;
    }

    public void printHand(){
        for(int i = 0;i<hand.size();i++){
            System.out.print(hand.get(i).getFarbe() + "  " +hand.get(i).getSchlag() + "         ");
        }

        System.out.println();
    }



}
