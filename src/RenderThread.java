import java.awt.*;
import java.awt.image.BufferStrategy;

public class RenderThread implements Runnable {

    private BoardWattn boardWattn;

    private int width, height;

    private Display display;
    private String title;

    private boolean running = false;
    private Thread thread;

    RenderThread(BoardWattn boardWattn, String title, int width, int height) {
        this.boardWattn = boardWattn;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init() {
        display = new Display(title, width, height);

        Assets assets = new Assets();
        assets.init();
    }

    private void render() {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!
        boardWattn.render(g);


        //End Drawing!
        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = (double) 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                render();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }
        }
        stop();
    }

    synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
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
