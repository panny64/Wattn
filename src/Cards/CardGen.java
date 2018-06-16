package Cards;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;


public final class CardGen {


    private CardGen() {

    }

    public static Stack<Card> giveCards(){

        ArrayList<Card> mix = new ArrayList<>();

        Schlag[] schlag = {Schlag.SIEBEN,Schlag.ACHT,Schlag.NEUN,Schlag.ZEHN,Schlag.UNTER,Schlag.OBER,Schlag.KÖNIG,Schlag.SAU};
        Farbe[] farbe = {Farbe.EICHEL,Farbe.GRAS,Farbe.HERZ,Farbe.SCHELLEN};

        for(int i = 0; i<schlag.length;i++){
            for(int j = 0;j<farbe.length;j++){
                mix.add(new Card(farbe[j],schlag[i]));
            }
        }

        Stack<Card> stack = shuffle(mix);



        return stack;

    }

    public static Stack<Card> shuffle(ArrayList<Card> sorted){

        Random random = new Random();


        for(int i = 0; i < 1000; i++){

            int x = random.nextInt(31) + 1;         //31 maximum und 1 Minimum
            int y = random.nextInt(31) + 1;         //same

           Card first =  sorted.get(x);
           Card second = sorted.get(y);
           Card swap = second;
           sorted.set(y,first);
           sorted.set(x,swap);

        }
        Stack<Card> shuffled = new Stack<>();

        while(!sorted.isEmpty()){
            shuffled.add(sorted.remove(0));
        }

        return shuffled;

    }




}
