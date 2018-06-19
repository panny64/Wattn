public class Launcher {
    public static void main(String[] args) {
       // Game game = new Game("Watten", 640*2, 480*2);
       // game.start();

        BoardWattn boardWattn = new BoardWattn();

        TickThread tickThread = new TickThread(boardWattn);
        tickThread.start();
        RenderThread renderThread = new RenderThread(boardWattn,"Watten", 1280,960);
        renderThread.start();
    }
}
