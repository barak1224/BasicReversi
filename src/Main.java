import GUI.GameSettings;
import GUI.IOSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * Main Class of the GUI Reversi Game.
 */
public class Main extends Application {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 400;
    private static final String MENU_FXML_NAME = "GUI/MenuFXML.fxml";
    private static final String STYLE_SHEET = "GUI/application.css";
    private static final String MENU_TITLE = "Menu";


    /**
     * Main method, launches the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    /**
     * Initializes all the relevant values to start the game,
     */
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage.setMinWidth(SCREEN_WIDTH);
            primaryStage.setMinHeight(SCREEN_HEIGHT);
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource(MENU_FXML_NAME));
            Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
            scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
            primaryStage.setTitle(MENU_TITLE);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}