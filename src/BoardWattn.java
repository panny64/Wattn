import Cards.Card;
import Cards.CardGen;
import Cards.Farbe;
import Cards.Schlag;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

class BoardWattn {

    private Stack<Card> allCards;
    private Players[] players;
    private int [] scores;
    private ArrayList<Card> cardsPlayed;
    private int ausspielerIndex;
    private int aktuellerSpieler;
    private Schlag angesagterSchlag;
    private Farbe angesagteFarbe;

    private int screenHeight;
    private int screenWidth;

    BoardWattn(int screenWidth, int screenHeight){
        players = new Players[4];
        scores = new int[4];
        cardsPlayed = new ArrayList<>();
        ausspielerIndex = 0;
        aktuellerSpieler = 0;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        players[0] = new Human();
        players[1] = new Bot();
        players[2] = new Bot();
        players[3] = new Bot();
    }

    void tick(){

        //initialize game
        if(allCards==null){
            resetRound();
        }

        int playerWhoWon;

        if(cardsPlayed.size() == 4){
            playerWhoWon = (ausspielerIndex + eval(cardsPlayed)) % 4;
            ausspielerIndex = playerWhoWon;
            scores[playerWhoWon]++;

            cardsPlayed.clear();

            aktuellerSpieler = ausspielerIndex;
        }else{
            players[aktuellerSpieler].printHand();
            playCard(aktuellerSpieler);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    private void resetRound(){
        for(Players player : players){
            player.emptyHand();
        }

        allCards = CardGen.giveCards();

        handOutCards();

        scores = new int[4];

        angesagterSchlag = null;
        angesagteFarbe = null;

        // TODO: 12.10.2018 Ansager wechseln
        angesagterSchlag = players[0].SchlagAnsagen();
        angesagteFarbe = players[0].FarbeAnsagen();
    }

    private void handOutCards(){
        for(Players player : players){
            ArrayList<Card> l = new ArrayList<>();
            l.add(allCards.pop());
            l.add(allCards.pop());
            l.add(allCards.pop());
            player.addCard(l);
        }

        for(Players player : players){
            ArrayList<Card> l = new ArrayList<>();
            l.add(allCards.pop());
            l.add(allCards.pop());
            player.addCard(l);
        }
    }

    private void playCard(int playerIndex){
        cardsPlayed.add(players[playerIndex].playCard(cardsPlayed,angesagterSchlag,angesagteFarbe));
    }

    synchronized void render(Graphics g){
        players[0].render(g);
        players[1].render(g);
        players[2].render(g);
        players[3].render(g);

        for(int i = 0;i<cardsPlayed.size();i++){
          g.drawImage(Assets.getImage(cardsPlayed.get(i)),screenWidth/2 - 60 * cardsPlayed.size() + 120 * i,screenHeight/2 - 115,120,230,null);
        }

        if(angesagterSchlag!=null) {
            g.drawString("angesageter Schlag: " + angesagterSchlag, 5, screenHeight-20);
        }
        if(angesagteFarbe!=null) {
            g.drawString("angesagete Farbe: " + angesagteFarbe, 5, screenHeight-5);
        }

    }

    private int eval(ArrayList<Card> cardsPlayed) {

        int[] valances = new int[4];
        boolean highestDouble = false;
        int highestValenceIndex = 0;

        for (int i = 0; i < cardsPlayed.size(); i++) {
            int valance = giveValence(cardsPlayed.get(i));
            valances[i] = valance;
            if (valance > valances[highestValenceIndex] || i==0) {
                highestDouble = false;
                highestValenceIndex = i;
            } else if (valance == valances[highestValenceIndex]) {
                highestDouble = true;
            }
        }

        if (!highestDouble) {
            return highestValenceIndex;
        }

        //wenn zwei angesagte Schläge existieren, gewinnt der, der als erstes gespielt wurde
        if(valances[highestValenceIndex]==9){
            for(int i = 0;i<valances.length;i++){
                if(valances[i]==9){
                    return i;
                }
            }
        }

        Card untersteKarte = cardsPlayed.get(0);
        Farbe untersteKarteFarbe = untersteKarte.getFarbe();

        int hoechsterSchlagIndex = 0;
        int maxIndexInHand = 0;
        Schlag[] schlag = {Schlag.SIEBEN, Schlag.ACHT, Schlag.NEUN, Schlag.ZEHN, Schlag.UNTER, Schlag.OBER, Schlag.KOENIG, Schlag.SAU};

        for(int i = 0; i < cardsPlayed.size(); i++){
            if(cardsPlayed.get(i).getFarbe() == untersteKarteFarbe){
                // TODO: 12.10.2018 "rang" eines schlages über statische methode finden
                for(int j = 0; j < schlag.length; j++){
                    if(schlag[j] == cardsPlayed.get(i).getSchlag() && hoechsterSchlagIndex < j){
                        hoechsterSchlagIndex = j;
                        maxIndexInHand = i;
                    }
                }
            }
        }



        return maxIndexInHand;
    }

    private int giveValence(Card c){
        Schlag[] schlag = {Schlag.SIEBEN, Schlag.ACHT, Schlag.NEUN, Schlag.ZEHN, Schlag.UNTER, Schlag.OBER, Schlag.KOENIG, Schlag.SAU};

        if(c.equals(new Card(Farbe.HERZ,Schlag.KOENIG))){
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
