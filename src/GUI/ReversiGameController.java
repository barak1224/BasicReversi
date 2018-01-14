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

public class ReversiGameController implements Initializable {
    private ReversiBoardController boardController;

    @FXML
    private HBox root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GameSettings game = IOSettings.read();
        if (game == null) return;
        GameFlow gameFlow = new GameFlow(game.getSizeBoard());
        boardController = new ReversiBoardController(gameFlow.getBoard(),
                game.getColorPlayerOne(), game.getColorPlayerTwo());
        boardController.setPrefWidth(600);
        boardController.setPrefHeight(400);
        root.getChildren().add(0, boardController);
        System.out.println("barak is my bitch1");
        ArrayList<Move> possibleMoves = gameFlow.getPossibleMoves();
        System.out.println("barak is my bitch2");
        boardController.setPossibleMoves(possibleMoves);
        System.out.println("barak is my bitch3");
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
}
