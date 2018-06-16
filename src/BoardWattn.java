import Cards.Card;
import Cards.CardGen;
import Players.*;

import java.lang.reflect.Array;
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

    public BoardWattn(){
        players = new Players[4];
        cardsPlayed = new ArrayList<Card>();
        ausspielerIndex = 0;
        aktuellerSpieler = 0;

        players[0] = new Human();
        players[1] = new Bot();
        players[2] = new Bot();
        players[3] = new Bot();

        resetRound();
    }

    public void tick(){

        int playerWhoWon = -1;

        if(cardsPlayed.size() == 4){
            playerWhoWon = (ausspielerIndex + eval(cardsPlayed)) % 4;
            ausspielerIndex = playerWhoWon;
            scores[playerWhoWon]++;

            while(!cardsPlayed.isEmpty()){
                cardsPlayed.remove(0);
            }
            aktuellerSpieler = ausspielerIndex;
        }else{
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

        System.out.println(Arrays.toString(scores));
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
            ArrayList<Card> l = new ArrayList<Card>();
            l.add(allCards.pop());
            l.add(allCards.pop());
            players[i].addCard(l);
        }
    }

    public void playCard(int playerIndex){
        cardsPlayed.add(players[playerIndex].playCard());
    }

    public void render(){

    }

    public int eval(ArrayList<Card> cardsPlayed){
        return 0;
    }
}
