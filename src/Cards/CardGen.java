package Cards;

import java.util.Stack;

public final class CardGen {


    private CardGen() {

    }

    public static Stack<Card> giveCards(){
        Stack<Card> cards = new Stack<Card>();

        Schlag[] schlag = {Schlag.SIEBEN,Schlag.ACHT,Schlag.NEUN,Schlag.ZEHN,Schlag.UNTER,Schlag.OBER,Schlag.KÖNIG,Schlag.SAU};
        Farbe[] farbe = {Farbe.EICHEL,Farbe.GRAS,Farbe.HERZ,Farbe.SCHELLEN};

        for(int i = 0; i<schlag.length;i++){
            for(int j = 0;j<farbe.length;j++){
                cards.push(new Card(farbe[j],schlag[i]));
            }
        }

        return cards;
    }
}
