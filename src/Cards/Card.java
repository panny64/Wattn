package Cards;

public class Card {
    Farbe farbe;
    Schlag schlag;

    public Card(Farbe farbe,Schlag schlag){
        this.farbe = farbe;
        this.schlag = schlag;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public Schlag getSchlag() {
        return schlag;
    }
}
