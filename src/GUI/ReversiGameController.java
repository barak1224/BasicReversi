package GUI;

import Core.GameFlow;
import Core.Move;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable, ActionListener {
    private ReversiBoardController boardController;
    private GameFlow gameFlow;

    @FXML
    private HBox root;


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
        if (!gameFlow.playOneTurn(row, col)) {
            gameOver();
        }
        boardController.setPossibleMoves(gameFlow.getPossibleMoves());
        boardController.draw();
    }

    private void gameOver() {

    }
}
