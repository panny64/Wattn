package Cards;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

public final class CardGen {
    private CardGen() {}

    public static Stack<Card> giveCards(){
        ArrayList<Card> mix = new ArrayList<>();

        Schlag[] schlag = {Schlag.SIEBEN,Schlag.ACHT,Schlag.NEUN,Schlag.ZEHN,Schlag.UNTER,Schlag.OBER,Schlag.KOENIG,Schlag.SAU};
        Farbe[] farbe = {Farbe.EICHEL,Farbe.GRAS,Farbe.HERZ,Farbe.SCHELLEN};

        for(Schlag s : schlag){
            for(Farbe f : farbe){
                mix.add(new Card(f ,s));
            }
        }

        return shuffle(mix);
    }

    private static Stack<Card> shuffle(ArrayList<Card> sorted){
        Random random = new Random();

        for(int i = 0; i < 1000000; i++){
            int x = random.nextInt(31);         //random.nextInt(31) kann 0-31 an Werten annehmen
            int y = random.nextInt(31);         //same

           Card tmp =  sorted.get(x);
           sorted.set(x, sorted.get(y));
           sorted.set(y, tmp);
        }

        Stack<Card> shuffled = new Stack<>();

        while(!sorted.isEmpty()){
            shuffled.push(sorted.remove(0));
        }
        return shuffled;
    }
}
