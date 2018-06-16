package Players;

import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.util.ArrayList;
import java.util.Map;

public class Bot extends Players{

    public Bot(){
        ArrayList<Card> hand = new ArrayList<>();
    }

    public Schlag SchlagAnsagen(){

        int[] counters = {0,0,0,0,0,0,0,0};  //7,8,9,10,U,O,K,S
        return Schlag.NEUN;
    }

    public Farbe FarbeAnsagen(){

        int[] counters = {0,0,0,0};     //Eichel,Gras,Herz,Schellen

        for(int i = 0; i<4;i++){
            if(hand.get(i).getFarbe().equals(Farbe.EICHEL)){
                counters[0]++;
            }
            else if(hand.get(i).getFarbe().equals(Farbe.GRAS)){
                counters[1]++;
            }
            else if(hand.get(i).getFarbe().equals(Farbe.HERZ)){
                counters[2]++;
            }
            else counters[3]++;
        }





        return Farbe.EICHEL;
    }

}
