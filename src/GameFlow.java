import java.awt.*;
import java.util.ArrayList;

public class GameFlow {
    private Board board;
    private ClassicLogic logic;
    private Player[] players;
    private Printer printer;
    private TurnManager turnManager;

    public GameFlow(int size, Printer newPrinter, int typeOfGame) {
        printer = newPrinter;
        players = new Player[2];
        CellColor color1, color2;
        if ( typeOfGame == 0) {
            color1 = CellColor.BLACK;
            color2 = CellColor.WHITE;
        } else {
            color1 = CellColor.WHITE;
            color2 = CellColor.BLACK;
        }
        board = new Board(size, printer, color1, color2);
        logic = new ClassicLogic();
        players[0] = new Player(color1, printer);
        players[1] = new Player(color2, printer);
        turnManager = new TurnManager(players);
    }

    private void playOneTurn() {
        board.print();
        Player currentPlayer = turnManager.nextPlayer();
        ArrayList<Move> possibleMoves = logic.getPossibleMoves(currentPlayer, board);
        Move move = currentPlayer.move(possibleMoves);
        if (move != null) {
            turnManager.setNoMove(false);
            board.applyMove(move, currentPlayer);
        } else {
            turnManager.setNoMove(true);
        }
    }

    public void run() {
        while(!gameOver()) {
            playOneTurn();
        }
        finishGame();
    }

    private void finishGame() {
        board.print();
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
//
//    private void runMenu() {
//
//    }


}
