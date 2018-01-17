package Core;

import java.util.ArrayList;

/**
 * Completely virtual Player class. It is used to determine a general definition of Player, and let me define
 * different kinds of them, like: console or GUI, human or AI, local or network.
 */
public class Player {
    private CellColor content;

    public Player(CellColor myContent) {
        content = myContent;
    }

    /**
     * Performs a move on the Board, according to input by the user.
     * This is the interface for playing. CAREFUL. IT WORKS PERFECTLY.
     * @return Move - the move to be applied by the Board (called in GameFlow).
     */
    public Move move(ArrayList<Move> possibleMoves, int row, int col) {
        int[] input = new int[2];
        boolean validMove = false;
        Move move = null;
            int maxSize = possibleMoves.size();
            for (int i = 0; i < maxSize; i++) {
                Move moveToCheck = possibleMoves.get(i);
                Coordinate checkPos = moveToCheck.getCoordinate();
                if (checkPos.getRow() == row && checkPos.getCol() == col) {
                    move = moveToCheck;
                    validMove = true;
                    break;
                }
            }
        return move;
    }

    /**
     * Checks whether a Cell's content belongs to the opponent.
     * @return true if the Cell's content is not empty and is different from own content.
     */
    public boolean isOpponent(CellColor adv) {
        return (content != adv && adv != CellColor.EMPTY);
    }

    /**
     * Gets the content of the Player.
     * @return Color - the color with which the player plays.
     */
    public CellColor getContent() {
        return content;
    }

    /**
     * Set the Cell's content to be the same as the Player's own.
     */
    public void conquerCell(Cell myCell) {
        myCell.beConquered(content);
    }
}