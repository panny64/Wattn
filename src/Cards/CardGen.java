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


        for(int i = 0; i < 100000; i++){

            int x = random.nextInt(31);         //random.nextInt(31) kann 0-31 an Werten annehmen
            int y = random.nextInt(31);         //same

           Card first =  sorted.get(x);
           Card second = sorted.get(y);
           Card swap = second;
           sorted.set(y,first);
           sorted.set(x,swap);

        }
        Stack<Card> shuffled = new Stack<>();

        while(!sorted.isEmpty()){
            shuffled.push(sorted.remove(0));
        }

        return shuffled;

    }


    public static void main(String[] args) {

        CardGen gen = new CardGen();
        ArrayList<Card> mix = new ArrayList<>();

        Schlag[] schlag = {Schlag.SIEBEN,Schlag.ACHT,Schlag.NEUN,Schlag.ZEHN,Schlag.UNTER,Schlag.OBER,Schlag.KÖNIG,Schlag.SAU};
        Farbe[] farbe = {Farbe.EICHEL,Farbe.GRAS,Farbe.HERZ,Farbe.SCHELLEN};

        for(int j = 0;j<farbe.length;j++){

              for(int i = 0; i<schlag.length;i++){
                mix.add(new Card(farbe[j],schlag[i]));
            }
        }
        for(int i = 0; i < mix.size(); i++){
            System.out.println(mix.get(i).getFarbe() + " " + mix.get(i).getSchlag() + " ");
        }
        System.out.println("");
        System.out.println("Ab jetzt gemischt");
        System.out.println("");

        Stack<Card> stack = shuffle(mix);

        for(int i = 0; i < 31; i++) {
            System.out.println(stack.peek().getFarbe() + " " +  stack.peek().getSchlag());
            stack.pop();

        }

    }



}
