import Cards.Card;
import Cards.CardGen;
import Cards.Farbe;
import Cards.Schlag;
import Players.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BoardWattn {

    Stack<Card> allCards;
    Players [] players;
    int [] scores;
    ArrayList<Card> cardsPlayed;
    int ausspielerIndex;
    int aktuellerSpieler;
    Schlag angesagterSchlag;
    Farbe angesagteFarbe;

    public BoardWattn(){
        players = new Players[4];
        scores = new int[4];
        cardsPlayed = new ArrayList<Card>();
        ausspielerIndex = 0;
        aktuellerSpieler = 0;

        players[0] = new Human();
        players[1] = new Bot();
        players[2] = new Bot();
        players[3] = new Bot();
    }

    public void tick(){

        //initialize game
        if(allCards==null){
            resetRound();
        }

        int playerWhoWon = -1;

        if(cardsPlayed.size() == 4){
            playerWhoWon = (ausspielerIndex + eval(cardsPlayed)) % 4;
            ausspielerIndex = playerWhoWon;
            scores[playerWhoWon]++;

            cardsPlayed.clear();

            aktuellerSpieler = ausspielerIndex;
        }else{
            players[aktuellerSpieler].printHand();
            playCard(aktuellerSpieler);
            aktuellerSpieler = (aktuellerSpieler+1)%4;
        }

        if(scores[0]+scores[2] >= 3){
            System.out.println("Team 1 won.");
            resetRound();
        }else if(scores[1]+scores[3] >= 3){
            System.out.println("Team 2 won.");
            resetRound();
        }
    }

    public void resetRound(){

        for(int i = 0;i<players.length;i++){
            players[i].emptyHands();
        }
        allCards = CardGen.giveCards();
        handOutCards();

        for(int i = 0;i<players.length;i++){
            players[i].printHand();
        }
        scores = new int[4];

        angesagterSchlag = players[0].SchlagAnsagen();
        angesagteFarbe = players[0].FarbeAnsagen();
    }

    public void handOutCards(){
        for(int i = 0;i<players.length;i++){
            ArrayList<Card> l = new ArrayList<Card>();
            l.add(allCards.pop());
            l.add(allCards.pop());
            l.add(allCards.pop());
            players[i].addCard(l);
        }
        for(int i = 0;i<players.length;i++){
            ArrayList<Card> l = new ArrayList<>();
            l.add(allCards.pop());
            l.add(allCards.pop());
            players[i].addCard(l);
        }
    }

    public void playCard(int playerIndex){
        cardsPlayed.add(players[playerIndex].playCard(cardsPlayed,angesagterSchlag,angesagteFarbe));
    }

    public void render(){

    }

    public int eval(ArrayList<Card> cardsPlayed) {

        Schlag[] schlag = {Schlag.SIEBEN, Schlag.ACHT, Schlag.NEUN, Schlag.ZEHN, Schlag.UNTER, Schlag.OBER, Schlag.KÖNIG, Schlag.SAU};
        int[] valances = new int[4];
        boolean highestDouble = false;
        int highestValenceIndex = 0;

        for (int i = 0; i < cardsPlayed.size(); i++) {
            int valance = giveValence(cardsPlayed.get(i));
            valances[i] = valance;
            if (valance > valances[highestValenceIndex]||i==0) {
                highestDouble = false;
                highestValenceIndex = i;
                continue;
            } else if (valance == valances[highestValenceIndex]) {
                highestDouble = true;
            }
        }

        if (!highestDouble) {
            return highestValenceIndex;
        }

        if(valances[highestValenceIndex]==9){
            for(int i = 0;i<valances.length;i++){
                if(valances[i]==9){
                    return i;
                }
            }
        }

        Card untersteKarte = cardsPlayed.get(0);
        Farbe untersteKarteFarbe = untersteKarte.getFarbe();
        int höchsterSchlagIndex = -1;

        for (int i = 0;i<schlag.length;i++){
            if(schlag[i]==untersteKarte.getSchlag()){
                höchsterSchlagIndex = i;
                break;
            }
        }

        for (int i = 1; i<cardsPlayed.size();i++){
            for(int j = höchsterSchlagIndex;j<schlag.length;j++){
                if(cardsPlayed.get(i).getFarbe() == untersteKarteFarbe && schlag[j] == cardsPlayed.get(i).getSchlag()){
                    höchsterSchlagIndex = j;
                }
            }
        }

        for (int i = 0; i<cardsPlayed.size();i++){
            if(untersteKarteFarbe == cardsPlayed.get(i).getFarbe() && cardsPlayed.get(i).getSchlag()==schlag[höchsterSchlagIndex]){
                return i;
            }
        }
        return 0;
    }

    public int giveValence(Card c){
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
}
