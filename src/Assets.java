import Cards.Card;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Assets {
    private static BufferedImage[] cards;

    void init() {
        BufferedImage image = null;

        cards = new BufferedImage[32];

        try {
            image = ImageIO.read(getClass().getResource("cardsImages2.png"));
        } catch (IOException e) {
            System.out.println("Couldn't get CardImage.");
        }

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 8; i++) {
                if (image != null) {
                    cards[j * 8 + i] = image.getSubimage(10 + 140 * i, 10 + j * 250, 120, 230);
                }
            }
        }
    }

    static BufferedImage getImage(Card c) {
        int schlag=-1;
        int farbe=-1;

        switch (c.getSchlag()) {
            case SIEBEN:
                schlag = 0;
                break;
            case ACHT:
                schlag = 1;
                break;
            case NEUN:
                schlag = 2;
                break;
            case ZEHN:
                schlag = 3;
                break;
            case UNTER:
                schlag = 4;
                break;
            case OBER:
                schlag = 5;
                break;
            case KOENIG:
                schlag = 6;
                break;
            case SAU:
                schlag = 7;
                break;
        }

        switch (c.getFarbe()){
            case HERZ:
                farbe=0;
                break;
            case SCHELLEN:
                farbe=1;
                break;
            case GRAS:
                farbe=2;
                break;
            case EICHEL:
                farbe=3;
                break;
        }

        return cards[farbe*8+schlag];
    }
}
