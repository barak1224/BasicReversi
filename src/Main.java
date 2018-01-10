import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
//        GameFlow game = new GameFlow(8, new ConsolePrinter(), 1);
//        game.run();
        launch(args);
        GameSettings gameSettings = null;
        try {
            gameSettings = IOSettings.read();
        } catch (Exception e) {
            System.out.println("PROBLEM...");
        }
        if (gameSettings != null) {
            System.out.println(gameSettings.getSizeBoard());
            System.out.println(gameSettings.getColorPlayerOne().toString());
            System.out.println(gameSettings.getColorPlayerTwo());
            System.out.println(gameSettings.getWhoStarts());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
            Scene scene = new Scene(root,400,350);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
