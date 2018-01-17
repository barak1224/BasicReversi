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

/**
 * Class in charge of holding the settings setters and showing the Settings Screen to the user.
 */
public class SettingsController {
    private static final int DEFAULT_BOARD_SIZE = 8;
    private static final String SETTINGS_FILE_NAME = "UserSettings.txt";
    private static final String MENU_FXML_NAME = "MenuFXML.fxml";
    private static final String FAILURE = "Failed to open file";
    private static final String CHOOSE_DIFFERENT_COLOR = "Choose different colors";


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
    /**
     * Method to initialize the Settings Screen.
     */
    private void initialize() {
        boardSizes.getItems().addAll(4,6,8,10,12,14,16,18,20);
        GameSettings game = IOSettings.read();
        if (game != null) {
            String s = game.getWhoStarts().name();
            String b = PlayerStart.BLACK.name();
            if (game.getWhoStarts().name().equals(PlayerStart.BLACK.name())) {
                playerOneFirst.setSelected(true);
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
    /**
     * Method to return to the main menu saving the chosen settings.
     */
    protected void signIn() {
        boolean valid = true;
        if (playerOneColor.getValue().toString().equals(playerTwoColor.getValue().toString())) {
            messageText2.setText(CHOOSE_DIFFERENT_COLOR);
            messageText2.setFill(Color.RED);
            valid = false;
        }
        if(valid) {
            PrintWriter bWriter = null;
            try {
                File fileLocation = new File(SETTINGS_FILE_NAME);
                bWriter =
                    new PrintWriter( new OutputStreamWriter(
                            new FileOutputStream(fileLocation)));
                    List<String> lines = linesToWrite();
                    IOSettings.writeToFile(bWriter, lines);
            } catch (IOException e) {
                System.err.println(FAILURE);
                System.exit(0);
            } finally {
                bWriter.close();
            }
            callMenuBack();
        }
    }

    /**
     * Method to get the data to be written to the settings file.
     * @return List<String> - the lines to be written to the settings file, to save for later usage.
     */
    private List<String> linesToWrite() {
        List<String> lines = new ArrayList<>();
        String whoPlaysFirst;
        if (playerOneFirst.isSelected()) {
            whoPlaysFirst = PlayerStart.BLACK.name();
        } else {
            whoPlaysFirst = PlayerStart.WHITE.name();
        }
        lines.add("Who starts:" + whoPlaysFirst);
        lines.add("Player one color:" + playerOneColor.getValue().toString());
        lines.add("Player two color:" + playerTwoColor.getValue().toString());
        lines.add("Size board:" + boardSizes.getValue());
        return lines;
    }

    /**
     * Method to go back to the Main Menu Screen.
     */
    private void callMenuBack() {
        try {
            Stage stage = (Stage) doneButton.getScene().getWindow();
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource(MENU_FXML_NAME));
            Scene scene = new Scene(root, doneButton.getScene().getWidth(), doneButton.getScene().getHeight());
            stage.setScene((scene));
            stage.show();
        } catch (IOException e) {
            System.out.println(FAILURE);
        }
    }
}