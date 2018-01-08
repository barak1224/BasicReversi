public class Main {
    public static void main(String[] args) {
        GameFlow game = new GameFlow(8, new ConsolePrinter(), 1);
        game.run();
    }
}
