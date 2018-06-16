import java.util.ArrayList;

public class BoardWattn {
    Player [] players;
    int [] scores;
    ArrayList<Card> cardsPlayed;
    int ausspielerIndex;
    int aktuellerSpieler;

    public BoardWattn(){
        players = new Player[4];
        scores = new int[4];
        cardsPlayed = new ArrayList<Card>();
        ausspielerIndex = 0;

        players[0] = new Human();
        players[1] = new Bot();
        players[2] = new Bot();
        players[3] = new Bot();
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
