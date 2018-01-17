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


/**
 * Class in charge of actually running the game, holding references to the GameFlow, the BoardController and the GameSettings.
 * It created the game, "runs it", and closes it when it's over, going back to the Main Menu.
 * It implements Initializable because it is a controller, and ActionListener to listen to the BoardController.
 */
public class ReversiGameController implements Initializable, ActionListener {
    private final static String FXML_FILENAME = "MenuFXML.fxml";
    private final static String ERROR_MESSAGE_BACK = "Failed going back to menu";
    private final static String PLAYER_ONE_WINS = "The first player won!";
    private final static String PLAYER_TWO_WINS = "The second player won!";
    private final static String TIE = "It's a tie!";
    private final static String NO_MOVE_TITLE = "No Moves Alert";
    private final static String NO_MOVE_ALERT = "You have no moves, you lose your turn";
    private final static String WINNER_TITLE = "Winner Winner";
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
    /**
     * Initializes the game with the settings from the file.
     */
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
            alertBox.display(NO_MOVE_TITLE, NO_MOVE_ALERT);
            if (gameFlow.getPossibleMoves().size() == 0) {
                gameOver();
            }
        }
        if (status == GAME_OVER) gameOver();
    }

    /**
     * Adds the text to show the user data about the game.
     */
    public void setDetails() {
        playerOnePoints.setText(PLAYER_ONE_POINTS + gameFlow.getBoard().getPlayerPoints(1));
        playerTwoPoints.setText(PLAYER_TWO_POINTS + gameFlow.getBoard().getPlayerPoints(2));
    }

    /**
     * Deals with finishing the game, telling the user the final state.
     */
    private void gameOver() {
        String gameResult = null;
        switch (gameFlow.getBoard().getWinner()) {
            case 1:
                gameResult = PLAYER_ONE_WINS;
                break;
            case 2:
                gameResult = PLAYER_TWO_WINS;
                break;
            case 3:
                gameResult = TIE;
        }
        alertBox.display(WINNER_TITLE, gameResult);
        callMenuBack();
    }

    @FXML
    /**
     * Method in charge of going back to the main menu when the user clicks the menuButton..
     */
    private void callMenuBack() {
        try {
            Stage stage = (Stage) menuButton.getScene().getWindow();
            GridPane newRoot = (GridPane) FXMLLoader.load(getClass().getResource(FXML_FILENAME));
            Scene scene = new Scene(newRoot, menuButton.getScene().getWidth(), menuButton.getScene().getHeight());
            stage.setScene((scene));
            stage.show();
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE_BACK);
        }
    }

    @FXML
    /**
     * Method in charge of quitting the game when the user click the quitButton.
     */
    private void quit() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}
