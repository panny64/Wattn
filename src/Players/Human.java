package Players;

import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.util.ArrayList;

public class Human extends Players {

    public Human(){
        ArrayList<Card> hand = new ArrayList<>();
    }

    public Schlag SchlagAnsagen(){
        return Schlag.NEUN;
    }

    public Farbe FarbeAnsagen(){
        return Farbe.EICHEL;
    }



}
