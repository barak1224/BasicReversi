package GUI;

import Core.GameFlow;
import Core.Move;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable, ActionListener {

    private ReversiBoardController boardController;
    private GameFlow gameFlow;

    @FXML
    private HBox root;

    @FXML
    private Button menuButton;

    @FXML
    private Button quitButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameSettings game = IOSettings.read();
        if (game == null) return;
        gameFlow = new GameFlow(game.getSizeBoard());
        boardController = new ReversiBoardController(gameFlow.getBoard(),
                game.getColorPlayerOne(), game.getColorPlayerTwo());
        boardController.addHitListener(this);
        boardController.setPrefWidth(800);
        boardController.setPrefHeight(400);
        root.getChildren().add(0, boardController);
        boardController.setPossibleMoves(gameFlow.getPossibleMoves());
        boardController.draw();
        // listeners...
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 200;
            boardController.setPrefWidth(boardNewWidth);
            boardController.draw();
        });

        // listeners...
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            boardController.setPrefHeight(newValue.doubleValue() - 40);
            boardController.draw();
        });
    }

    @Override
    public void hitEvent(int row, int col) {
        boolean shouldContinue;
        shouldContinue = gameFlow.playOneTurn(row, col);
        boardController.setPossibleMoves(gameFlow.getPossibleMoves());
        boardController.draw();
        if (!shouldContinue) gameOver();
    }

    private void gameOver() {
        GameOverBox gameOverBox = new GameOverBox();
        //TODO SET WHO WINS MESSAGE
        gameOverBox.display("Winner winner", "You Won");
        callMenuBack();
    }

    @FXML
    private void callMenuBack() {
        try {
            Stage stage = (Stage) menuButton.getScene().getWindow();
            GridPane newRoot = (GridPane) FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
            Scene scene = new Scene(newRoot, menuButton.getScene().getWidth(), menuButton.getScene().getHeight());
            stage.setScene((scene));
            stage.show();
        } catch (IOException e) {
            System.out.println("Failed to go back");
        }
    }

    @FXML
    private void quit() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}
