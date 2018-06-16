import java.awt.*;
import java.awt.image.BufferStrategy;


    public class Game implements Runnable {

        private Display display;
        private int width, height;
        public String title;

        private boolean running = false;
        private Thread thread;

        private BufferStrategy bs;
        private Graphics g;

        private BoardWattn boardWattn;

        public Game(String title, int width, int height){
            this.width = width;
            this.height = height;
            this.title = title;
        }

        private void init(){
            display = new Display(title, width, height);

            boardWattn = new BoardWattn();

        }

        private void tick(){
            boardWattn.tick();
        }

        private void render(){
            bs = display.getCanvas().getBufferStrategy();
            if(bs == null){
                display.getCanvas().createBufferStrategy(3);
                return;
            }
            g = bs.getDrawGraphics();
            //Clear Screen
            g.clearRect(0, 0, width, height);
            //Draw Here!

            g.drawRect(10,10,20,10);
            g.setColor(Color.BLACK);

            //End Drawing!
            bs.show();
            g.dispose();
        }

        public void run(){

            init();

            int fps = 60;
            double timePerTick = 1000000000 / fps;
            double delta = 0;
            long now;
            long lastTime = System.nanoTime();
            long timer = 0;
            int ticks = 0;

            while(running){
                now = System.nanoTime();
                delta += (now - lastTime) / timePerTick;
                timer += now - lastTime;
                lastTime = now;

                if(delta >= 1){
                    tick();
                    render();
                    ticks++;
                    delta--;
                }

                if(timer >= 1000000000){
                    System.out.println("Ticks and Frames: " + ticks);
                    ticks = 0;
                    timer = 0;
                }
            }

            stop();

        }

        public int getWidth(){
            return width;
        }

        public int getHeight(){
            return height;
        }

        public synchronized void start(){
            if(running)
                return;
            running = true;
            thread = new Thread(this);
            thread.start();
        }

        public synchronized void stop(){
            if(!running)
                return;
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
