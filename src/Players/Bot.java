package Players;


import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;


import java.util.ArrayList;
import java.util.Map;

public class Bot extends Players {

    public Bot() {
        ArrayList<Card> hand = new ArrayList<>();
    }

    public Schlag SchlagAnsagen() {

        int[] counters = {0, 0, 0, 0, 0, 0, 0, 0};  //7,8,9,10,U,O,K,S
        return Schlag.NEUN;
    }

    public Farbe FarbeAnsagen() {

        int[] counters = {0, 0, 0, 0};     //Eichel,Gras,Herz,Schellen
        Farbe[] farben = {Farbe.EICHEL, Farbe.GRAS, Farbe.HERZ, Farbe.SCHELLEN};


        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < farben.length; j++) {
                if (farben[j] == hand.get(i).getFarbe()) {
                    counters[j]++;
                }
            }
        }

        int index = 0;
        int maxvalue= 0;

        for (int i = 0; i < counters.length; i++) {
            if(counters[i] > maxvalue){
                index = i;
                maxvalue = counters[i];
            }
        }

        return farben[index];
    }

}
