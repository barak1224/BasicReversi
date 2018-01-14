package GUI;

import Core.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {

    @FXML
    private HBox root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameSettings game = IOSettings.read();
        ReversiBoardController board = new ReversiBoardController(new Board(game.getSizeBoard()),
                game.getColorPlayerOne(), game.getColorPlayerTwo());
        board.setPrefWidth(600);
        board.setPrefHeight(400);
        root.getChildren().add(0, board);
        board.draw();
    }
}
