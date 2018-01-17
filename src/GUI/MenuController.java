package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class in charge of holding the Menu, showing it to the user and calling the settings or the game itself.
 */
public class MenuController {

    private static final String GAME_FXML_NAME = "ReversiGame.fxml";
    private static final String SETTINGS_FXML_NAME = "SettingsFXML.fxml";
    private static final String FAILURE = "Failed opening the window";

    @FXML
    private Button playButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button quitButton;

    @FXML
    /**
     * Method to call the Game.
     */
    protected void runGame() {

        try {
            Stage stage = (Stage) playButton.getScene().getWindow();
            HBox root = (HBox) FXMLLoader.load(getClass().getResource(GAME_FXML_NAME));
            Scene scene = new Scene(root, playButton.getScene().getWidth(), playButton.getScene().getHeight());
            stage.setScene((scene));
            stage.show();
        } catch (IOException e) {
            System.out.println(FAILURE);
        }
    }

    @FXML
    /**
     * Method to call the settings screen.
     */
    protected void settings() {
        try {
            Stage stage = (Stage) settingsButton.getScene().getWindow();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource(SETTINGS_FXML_NAME));
            Scene scene = new Scene(root, settingsButton.getScene().getWidth(), settingsButton.getScene().getHeight());
            stage.setScene((scene));
            stage.show();
        } catch (IOException e) {
            System.out.println(FAILURE);
        }
    }

    @FXML
    /**
     * Method to quit the game. Just closes the window.
     */
    private void quit() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}

