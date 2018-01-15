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

    public void playOneTurn() {
        ArrayList<Move> possibleMoves = getPossibleMoves();
        Move move = currentPlayer.move(possibleMoves);
        if (move != null) {
            turnManager.setNoMove(false);
            board.applyMove(move, currentPlayer);
        } else {
            turnManager.setNoMove(true);
        }
        currentPlayer = turnManager.nextPlayer();
    }

    public void run() {
        while (!gameOver()) {
            playOneTurn();
        }
        finishGame();
    }

    private void finishGame() {
        int winner = board.getWinner();
        if (0 == winner) {
            printer.printStream("It's a tie!!\n");
        } else {
            int points1 = board.getPlayerPoints(1);
            int points2 = board.getPlayerPoints(2);
            int maxPoint = Math.max(points1, points2);
            int minPoint = Math.min(points1, points2);
            printer.printStream("Player " + winner + " has won with a score of " +
                    maxPoint + " against " + minPoint + "\n");
        }
    }

    private boolean gameOver() {
        return (board.gameOver() || turnManager.noMoreMoves());
    }

    public Board getBoard() { return this.board;}

    public ArrayList<Move> getPossibleMoves() {
        return logic.getPossibleMoves(currentPlayer, board);
    }
}
