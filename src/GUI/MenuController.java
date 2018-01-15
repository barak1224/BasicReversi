package GUI;

import Core.Board;
import Core.GUIPrinter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button playButton;

    @FXML
    private Button settingsButton;

    @FXML
    protected void runGame() {
        GameSettings game = IOSettings.read();
        if (game != null) {
            try {
                Stage stage = (Stage) settingsButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
                Scene scene = new Scene(root, 400, 500);
                stage.setScene((scene));
                stage.show();
            } catch (IOException e) {
                System.out.println("Failed to open game");
            }
        }
    }

    @FXML
    protected void settings() {
        try {
            Stage stage = (Stage) settingsButton.getScene().getWindow();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("SettingsFXML.fxml"));
            Scene scene = new Scene(root, 400, 500);
            stage.setScene((scene));
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open Settings");
        }
    }
}
