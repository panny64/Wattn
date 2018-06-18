package Players;

import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.util.ArrayList;


public class Bot extends Players{

    ArrayList<Integer> werte;

    public Bot() {
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
    @Override
    public Card playCard(ArrayList<Card> played,Schlag angesagerSchlag,Farbe angesagteFarbe){
        return hand.remove(0);
        /*
        int anzahl = played.size();                                                                //Anzahl der gespielten Karten
        ArrayList<Integer> playedvalues = new ArrayList<>();                                       //Übersetzen der bereits gespielten Karten in ihre Werte

        for(int i = 0; i < played.size(); i++){
            playedvalues.add(giveValence(played.get(i), angesagteFarbe, angesagerSchlag));
        }

        int low = lowest();
        int high = highest();

        switch (anzahl){
            case 0:
                werte.remove(low);
                return hand.remove(low);
            case 1:
                int higher = hashigher(playedvalues.get(0));
                werte.remove(higher);
                return hand.remove(higher);
            default: return hand.remove(0);
        }*/
    }

    public int contain(Card c){
        for(int i = 0; i < hand.size();i++){
            if(c.getSchlag() == hand.get(i).getSchlag() && c.getFarbe() == hand.get(i).getFarbe()){
                return i;
            }
        }
        return  -1;
    }

    public int lowest(){
        int z = 99;
        for (int i = 0; i < werte.size(); i++){
            if(z < werte.get(i)){
                z = werte.get(i);
            }
        }
        return z;
    }

    public int highest(){
        int z = 0;
        for (int i = 0; i < werte.size(); i++){
            if(z > werte.get(i)){
                z = werte.get(i);
            }
        }
        return z;
    }

    public int hashigher(int c){
        for(int i = 0; i < werte.size(); i++){
            if(highest() > werte.get(i) && werte.get(i) > c ){
                return i;
            }
            if(werte.size() == 1){
                return highest();
            }
            return lowest();
        }
        return lowest();
    }
}
