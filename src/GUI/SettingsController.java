package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsController {
    private static final int DEFAULT_BOARD_SIZE = 8;
    @FXML
    private Button doneButton;
    @FXML
    private Text messageText2;
    @FXML
    private RadioButton playerOneFirst;
    @FXML
    private RadioButton playerTwoFirst;
    @FXML
    private ColorPicker playerTwoColor;
    @FXML
    private ColorPicker playerOneColor;
    @FXML
    private ChoiceBox<Integer> boardSizes;
    @FXML
    private void initialize() {
        boardSizes.getItems().addAll(4,6,8,10,12,14,16,18,20);
        GameSettings game = IOSettings.read();
        if (game != null) {
            if (game.getWhoStarts().name().equals(PlayerStart.BLACK.name())) {
                playerTwoFirst.setSelected(true);
            } else {
                playerTwoFirst.setSelected(true);
            }
            playerOneColor.setValue(game.getColorPlayerOne());
            playerTwoColor.setValue(game.getColorPlayerTwo());
            boardSizes.setValue(game.getSizeBoard());
        } else {
            boardSizes.setValue(DEFAULT_BOARD_SIZE);
            playerOneColor.setValue(Color.BLACK);
        }
    }
    @FXML
    protected void signIn() {
        boolean valid = true;
        if (playerOneColor.getValue().toString().equals(playerTwoColor.getValue().toString())) {
            messageText2.setText("Choose different colors");
            messageText2.setFill(Color.RED);
            valid = false;
        }
        if(valid) {
            PrintWriter bWriter = null;
            try {
                File fileLocation = new File("UserSettings.txt");
                bWriter =
                    new PrintWriter( new OutputStreamWriter(
                            new FileOutputStream(fileLocation)));
                    List<String> lines = linesToWrite();
                    IOSettings.writeToFile(bWriter, lines);
            } catch (IOException e) {
                System.err.println("Failed to open file");
                System.exit(0);
            } finally {
                bWriter.close();
            }
            callMenuBack();
        }
    }

    private List<String> linesToWrite() {
        List<String> lines = new ArrayList<>();
        String whoPlaysFirst;
        if (playerOneFirst.isSelected()) {
            whoPlaysFirst = PlayerStart.BLACK.name();
        } else {
            whoPlaysFirst = PlayerStart.WHITE.name();
        }
        lines.add("Who starts:" + whoPlaysFirst);
        lines.add("Core.Player one color:" + playerOneColor.getValue().toString());
        lines.add("Core.Player two color:" + playerTwoColor.getValue().toString());
        lines.add("Size board:" + boardSizes.getValue());
        return lines;
    }

    protected void callMenuBack() {
        try {
            Stage stage = (Stage) doneButton.getScene().getWindow();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("GUI/MenuFXML.fxml"));
            Scene scene = new Scene(root, 400, 350);
            stage.setScene((scene));
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to open Settings");
        }
    }
}