public class  Launcher {
    public static void main(String[] args) {
        final int WIDTH=1280;
        final int HEIGHT=720;

        BoardWattn boardWattn = new BoardWattn(WIDTH, HEIGHT);

        TickThread tickThread = new TickThread(boardWattn);
        tickThread.start();
        RenderThread renderThread = new RenderThread(boardWattn,"Watten", WIDTH,HEIGHT);
        renderThread.start();
    }
}
