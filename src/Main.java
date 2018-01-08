import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        GameFlow game = new GameFlow(8, new ConsolePrinter(), 1);
        game.run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
