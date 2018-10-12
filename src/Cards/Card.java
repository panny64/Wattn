package Cards;

public class Card {
    private Farbe farbe;
    private Schlag schlag;

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
