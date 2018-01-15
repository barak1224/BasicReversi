package Core;

import java.util.ArrayList;

public class Player {
    private CellColor content;

    public Player(CellColor myContent) {
        content = myContent;
    }

    public Move move(ArrayList<Move> possibleMoves) {
        int[] input = new int[2];
        boolean validMove = false;
        Move move = null;
        do {
            showPossibleMoves(possibleMoves);
//            input = getInput();
            if (!checkInput(input[0], input[1])) {
                continue;
            }
            int maxSize = possibleMoves.size();
            for (int i = 0; i < maxSize; i++) {
                Move moveToCheck = possibleMoves.get(i);
                Coordinate checkPos = moveToCheck.getCoordinate();
                if (checkPos.getRow() == input[0] && checkPos.getCol() == input[1]) {
                    move = moveToCheck;
                    validMove = true;
                    break;
                }
            }
            if (!validMove) {
                //printer.printStream("Wrong move, please try again.\n");
            }
        } while (!validMove);
//        printer.printStream("You picked " + move.getCoordinateAsString() + "\n");
//        cin.ignore(numeric_limits<streamsize>::max(), '\n');
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