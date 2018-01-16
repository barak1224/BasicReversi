package GUI;

import Core.GameFlow;
import Core.Move;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable, ActionListener {
    private final static String PLAYER_ONE_POINTS = "First player points: ";
    private final static String PLAYER_TWO_POINTS = "Second player points: ";
    private final static String PLAYER_TURN = "Player turn: ";
    private static final int PLAY = 2;
    private static final int NO_MOVES = 1;
    private static final int GAME_OVER = 0;


    private ReversiBoardController boardController;
    private GameFlow gameFlow;
    private AlertBox alertBox;

    @FXML
    private HBox root;

    @FXML
    private Button menuButton;

    @FXML
    private Button quitButton;

    @FXML
    private Text playerTurn;

    @FXML
    private Text playerOnePoints;

    @FXML
    private Text playerTwoPoints;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alertBox = new AlertBox();
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
        setDetails();
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
        int status;
        status = gameFlow.playOneTurn(row, col);
        List<Move> possibleMoves = gameFlow.getPossibleMoves();
        boardController.setPossibleMoves(possibleMoves);
        setDetails();
        boardController.draw();
        if (status == NO_MOVES) {
            alertBox.display("No move alert", "You don't have moves, the turn pass to the other player");
            if (gameFlow.getPossibleMoves().size() == 0) {
                gameOver();
            }
        }
        if (status == GAME_OVER) gameOver();
    }

    public void setDetails() {
        playerOnePoints.setText(PLAYER_ONE_POINTS + gameFlow.getBoard().getPlayerPoints(1));
        playerTwoPoints.setText(PLAYER_TWO_POINTS + gameFlow.getBoard().getPlayerPoints(2));
    }

    private void gameOver() {
        String gameResult = null;
        switch (gameFlow.getBoard().getWinner()) {
            case 1: gameResult = "First player won!";
                break;
            case 2: gameResult = "Second player wond";
                break;
            case 3: gameResult = "It's a tie!";
        }
        alertBox.display("Winner winner", gameResult);
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
