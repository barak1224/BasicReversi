import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private CellColor content;
    private Printer printer;

    public Player(CellColor myContent, Printer myPrinter) {
        content = myContent;
        printer = myPrinter;
    }

    public Move move(ArrayList<Move> possibleMoves) {
        Scanner reader = new Scanner(System.in);
        int row, col;
        char comma;
        boolean validMove = false;
        Move move = null;
        do {
            showPossibleMoves(possibleMoves);
            printer.printStream("Please enter your move row col: ");
            row = reader.nextInt();
            col = reader.nextInt();
            checkInput(row, col);
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
            if (!validMove) {
                printer.printStream("Wrong move, please try again.\n");
            }
        } while (!validMove);
        printer.printStream("You picked " + move.getCoordinateAsString() + "\n");
//        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        return move;
    }

    public boolean isOpponent(CellColor adv) {
        return (content != adv && adv != CellColor.EMPTY);
    }

    public void showPossibleMoves(ArrayList<Move> move) {
        String cont;
        if (content == CellColor.BLACK) { cont = "X";}
        else cont = "O";
        printer.printStream("You play with: " + cont + "\n" + "Your possible moves are ");
        int size = move.size();
        for (int i = 0; i < size; i++) {
            printer.printStream(" " + move.get(i).getCoordinateAsString());
        }
        printer.printStream("\n\n");
    }

    public CellColor getContent() {
        return content;
    }

    public void conquerCell(Cell myCell) {
        myCell.beConquered(content);
    }

    private void checkInput(int row, int col) {
        //???
    }
}
