package Core;

import java.util.ArrayList;

public class Player {
    private CellColor content;

    public Player(CellColor myContent) {
        content = myContent;
    }

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

    public boolean isOpponent(CellColor adv) {
        return (content != adv && adv != CellColor.EMPTY);
    }

    public void showPossibleMoves(ArrayList<Move> move) {
        String cont;
        if (content == CellColor.BLACK) {
            cont = "X";
        } else cont = "O";
//        printer.printStream("You play with: " + cont + "\n" + "Your possible moves are ");
        int size = move.size();
        for (int i = 0; i < size; i++) {
//            printer.printStream(" " + move.get(i).getCoordinateAsString());
        }
//        printer.printStream("\n\n");
    }

    public CellColor getContent() {
        return content;
    }

    public void conquerCell(Cell myCell) {
        myCell.beConquered(content);
    }

    private boolean checkInput(int row, int col) {
        return !(row == 0 || col == 0);
    }

}