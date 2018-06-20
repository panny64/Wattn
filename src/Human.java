import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Human extends Players {

    public Human() {
    }

    public Schlag SchlagAnsagen() {
        Schlag[] schlag = {Schlag.SIEBEN, Schlag.ACHT, Schlag.NEUN, Schlag.ZEHN, Schlag.UNTER, Schlag.OBER, Schlag.KÖNIG, Schlag.SAU};

        System.out.println("Deine Karten:");
        for(int i = 0;i<hand.size();i++){
            System.out.println(hand.get(i).getSchlag() + " " + hand.get(i).getFarbe());
        }
        System.out.println("Bitte Schlag ansagen: ");
        for(int i = 0;i<schlag.length;i++){
            System.out.println(i + ": " +schlag[i]);
        }
        Scanner scanner = new Scanner(System.in);
        String playerInput = scanner.next();
        int schlagIndex = Integer.parseInt(playerInput);
        return schlag[schlagIndex];
    }

    public Farbe FarbeAnsagen() {
        Farbe[] farben = {Farbe.EICHEL, Farbe.GRAS, Farbe.HERZ, Farbe.SCHELLEN};

        System.out.println("Deine Karten:");
        for(int i = 0;i<hand.size();i++){
            System.out.println(hand.get(i).getSchlag() + " " + hand.get(i).getFarbe());
        }
        System.out.println("Bitte Farbe ansagen: ");
        for(int i = 0;i<farben.length;i++){
            System.out.println(i + ": " +farben[i]);
        }
        Scanner scanner = new Scanner(System.in);
        int farbIndex = Integer.parseInt(scanner.next());
        return farben[farbIndex];
    }

    @Override
    public void render(Graphics g) {
        for(int i = 0;i<hand.size();i++){
            g.drawImage(Assets.getImage(hand.get(i)),340+120*i,710,120,230,null);
        }
    }

    @Override
    public Card playCard(ArrayList<Card> playedCards, Schlag angesagterSchlag, Farbe angesagteFarbe) {
        System.out.println("already played cards:");
        for(int i = 0;i<playedCards.size();i++){
            System.out.println(playedCards.get(i).getSchlag() + " " + playedCards.get(i).getFarbe());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Numbers for 0 to " + (hand.size() - 1));

        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i).getSchlag() + " " + hand.get(i).getFarbe());
        }

        int cardIndex = Integer.parseInt(scanner.next());
        return getCardAt(cardIndex);
    }
}
