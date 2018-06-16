package Players;

import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.util.ArrayList;

public class Bot extends Players{



    public Bot(){
        ArrayList<Card> hand = new ArrayList<>();
    }

    Schlag SchlagAnsagen(){
        return Schlag.NEUN;
    }

    Farbe FarbeAnsagen(){
        return Farbe.EICHEL;
    }
}
