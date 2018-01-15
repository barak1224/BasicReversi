import GUI.GameSettings;
import GUI.IOSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class Main extends Application {

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 400;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage.setMinWidth(SCREEN_WIDTH);
            primaryStage.setMinHeight(SCREEN_HEIGHT);
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("GUI/MenuFXML.fxml"));
            Scene scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("GUI/application.css").toExternalForm());
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}