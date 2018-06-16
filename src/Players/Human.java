package Players;

import Cards.Card;
import Cards.Farbe;
import Cards.Schlag;

import java.util.ArrayList;
import java.util.Scanner;

public class Human extends Players {

    public Human() {
        ArrayList<Card> hand = new ArrayList<>();
    }

    public Schlag SchlagAnsagen() {
        return Schlag.NEUN;
    }

    public Farbe FarbeAnsagen() {
        return Farbe.EICHEL;
    }

    @Override
    public Card playCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Numbers for 0 to " + (hand.size()-1));

        for(int i = 0;i<hand.size();i++){
            System.out.println(i + ": " + hand.get(i).getSchlag() + " " + hand.get(i).getFarbe());
        }

        int cardIndex = Integer.parseInt(scanner.next());
        return hand.remove(cardIndex);
    }
}
