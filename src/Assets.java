import org.omg.CORBA.PUBLIC_MEMBER;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {
    public static BufferedImage[] cards;

    public void init(){
        BufferedImage image = null;

        cards = new BufferedImage[32];

        try{
            image = ImageIO.read(getClass().getResource("cardsImages.png"));
        }catch (IOException e){
            System.out.println("YOU SUCK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

        for(int j=0;j<4;j++){
        for(int i= 0;i<8;i++){
            cards[j*8+i] = image.getSubimage(10+140*i,10+j*250,120,230);
        }}
    }
}
