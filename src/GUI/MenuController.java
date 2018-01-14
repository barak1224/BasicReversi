package GUI;

import Core.Board;
import Core.GUIPrinter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
            Stage stage = (Stage) playButton.getScene().getWindow();
            GUIPrinter printer = new GUIPrinter(stage, game.getColorPlayerOne(), game.getColorPlayerTwo());
//            Core.GameFlow gameFlow = new Core.GameFlow(8, printer);
//            gameFlow.run();
            Board board = new Board(8, printer);
            board.print();
        }
    }

    @FXML
    protected void settings() {
        try {
            Stage stage = (Stage) settingsButton.getScene().getWindow();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("GUI/SettingsFXML.fxml"));
            Scene scene = new Scene(root, 400, 500);
            stage.setScene((scene));
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open Settings");
        }
    }
}
