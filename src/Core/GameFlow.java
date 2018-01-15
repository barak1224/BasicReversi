package Core;

import java.util.ArrayList;
import java.util.List;

public class GameFlow {
    private Board board;
    private ClassicLogic logic;
    private Player[] players;
    private Printer printer;
    private TurnManager turnManager;
    private Player currentPlayer;

    public GameFlow(int size) {
        players = new Player[2];
        CellColor color1 = CellColor.BLACK;
        CellColor color2 = CellColor.WHITE;
        board = new Board(size);
        logic = new ClassicLogic();
        players[0] = new Player(color1);
        players[1] = new Player(color2);
        turnManager = new TurnManager(players);
        currentPlayer = turnManager.nextPlayer();
    }

    public boolean playOneTurn(int row, int col) {
        ArrayList<Move> possibleMoves = getPossibleMoves();
        Move move = currentPlayer.move(possibleMoves, row, col);
        board.applyMove(move, currentPlayer);
        turnManager.setNoMove(false);
        // get the relevant data for the next turn
        currentPlayer = nextPlayer();
        if (getPossibleMoves().isEmpty()) {
            turnManager.setNoMove(true);
            currentPlayer = nextPlayer();
        }
        return !gameOver();
    }

    private boolean gameOver() {
        return (board.gameOver() || turnManager.noMoreMoves());
    }

    public Board getBoard() {
        return this.board;
    }

    public ArrayList<Move> getPossibleMoves() {
        return logic.getPossibleMoves(currentPlayer, board);
    }

    private Player nextPlayer() {
        return turnManager.nextPlayer();
    }
}
