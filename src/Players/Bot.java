package Players;

import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.util.ArrayList;


public class Bot extends Players{

    ArrayList<Card> hand;
    ArrayList<Integer> werte;

    public Bot() {
        hand = new ArrayList<>();
        werte = new ArrayList<>();

    }

    

    public Schlag SchlagAnsagen() {

        int[] counters = {0, 0, 0, 0, 0, 0, 0, 0};  //7,8,9,10,U,O,K,S
        Schlag[] schlag = {Schlag.SIEBEN, Schlag.ACHT, Schlag.NEUN, Schlag.ZEHN, Schlag.UNTER, Schlag.OBER, Schlag.KÖNIG, Schlag.SAU};

        for(int i = 0; i < hand.size(); i++){
            for (int j = 0; j < schlag.length; j++){
                if (schlag[j] == hand.get(i).getSchlag()){
                    counters[j]++;
                }
            }
        }


        int index = 0;
        int maxvalue = 0;

        for(int i = 0; i < schlag.length; i++){
            if(counters[i] > maxvalue){
                index = i;
                maxvalue = counters[i];
            }
        }


        return schlag[index];
    }

    public Farbe FarbeAnsagen() {

        int[] counters = {0, 0, 0, 0};     //Eichel,Gras,Herz,Schellen
        Farbe[] farben = {Farbe.EICHEL, Farbe.GRAS, Farbe.HERZ, Farbe.SCHELLEN};


        for (int i = 0; i < 4; i++) {
            if (hand.get(i).getFarbe().equals(Farbe.EICHEL)) {
                counters[0]++;
            } else if (hand.get(i).getFarbe().equals(Farbe.GRAS)) {
                counters[1]++;
            } else if (hand.get(i).getFarbe().equals(Farbe.HERZ)) {
                counters[2]++;
            } else counters[3]++;
        }
        return Farbe.EICHEL;
    }

    public void Wertezuweisen(ArrayList<Card> hand, Farbe angesagteFarbe, Schlag angesagterSchlag){


        for(int i = 0; i < hand.size(); i++){

            werte.add(giveValence(hand.get(i),angesagteFarbe, angesagterSchlag));
            


            }
        }


    public int giveValence(Card c, Farbe angesagteFarbe, Schlag angesagterSchlag){
        Schlag[] schlag = {Schlag.SIEBEN, Schlag.ACHT, Schlag.NEUN, Schlag.ZEHN, Schlag.UNTER, Schlag.OBER, Schlag.KÖNIG, Schlag.SAU};

        if(c.equals(new Card(Farbe.HERZ,Schlag.KÖNIG))){
            return 13;
        }else if(c.equals(new Card(Farbe.SCHELLEN,Schlag.SIEBEN))){
            return 12;
        }else if(c.equals(new Card(Farbe.EICHEL,Schlag.SIEBEN))){
            return 11;
        }else if(c.equals(new Card(angesagteFarbe,angesagterSchlag))){
            return 10;
        }else if(c.getSchlag()==angesagterSchlag){
            return 9;
        }else if(c.getFarbe()==angesagteFarbe){
            for(int i= 8;i>=1;i--){
                if(c.getSchlag() == schlag[i-1]){
                    return i;
                }
            }
        }
        return 0;
    }

    public Card playCard(ArrayList<Card> played, Farbe angesagteFarbe, Schlag angesagerSchlag){
        int c = contain(new Card(Farbe.HERZ, Schlag.KÖNIG));
        if(played.isEmpty() && c>0){
            return hand.remove(c);
        }



        return hand.remove(0);
    }

    public int contain(Card c){
        for(int i = 0; i < hand.size();i++){
            if(c.getSchlag() == hand.get(i).getSchlag() && c.getFarbe() == hand.get(i).getFarbe()){
                return i;
            }
        }
        return  -1;
    }


}
