package GUI;

import Core.Board;
import Core.GameFlow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {
    private ReversiBoardController boardController;

    @FXML
    private HBox root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameSettings game = IOSettings.read();
        GameFlow gameFlow = createGameFlow(game);
        boardController = new ReversiBoardController(gameFlow.getBoard(),
                game.getColorPlayerOne(), game.getColorPlayerTwo());
        boardController.setPrefWidth(600);
        boardController.setPrefHeight(400);
        root.getChildren().add(0, boardController);
        boardController.draw();
    }

    private GameFlow createGameFlow(GameSettings game) {
        return new GameFlow(game.getSizeBoard());
    }
}
