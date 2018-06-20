import java.awt.*;
import java.awt.image.BufferStrategy;

public class RenderThread implements Runnable {

    private BoardWattn boardWattn;

    private int width, height;

    private Display display;
    public String title;

    private BufferStrategy bs;
    private Graphics g;

    private boolean running = false;
    private Thread thread;

    public RenderThread(BoardWattn boardWattn, String title, int width, int height) {
        this.boardWattn = boardWattn;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        System.out.println("display");
        display = new Display(title, width, height);

        Assets assets = new Assets();
        assets.init();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!
        /*
        for(int j=0;j<4;j++){
            for(int i = 0;i<8;i++){
                g.drawImage(Assets.cards[j*8+i],5+130*i,5+235*j,120,230,null);
            }
        }*/
        boardWattn.render(g);

        //End Drawing!
        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
