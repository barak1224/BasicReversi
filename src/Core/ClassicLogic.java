package Core;

import java.util.ArrayList;
import java.util.List;

public class ClassicLogic {

    /**
     * Constructor
     */
    public ClassicLogic() {
    }

    /**
     * Get the directions in which the Player should check for possible Moves.
     * @return vector<Coordinate *> the list of possible directions to move.
     */
    private List<Coordinate> getDirections(Coordinate pos, Player player, Board board) {
        List<Coordinate> result = new ArrayList<>();
        Cell cell = board.getCell(pos);
        List<Coordinate> directions = new ArrayList<>();
        List<Coordinate> neighbours = cell.getNeighbours();
        for (int i = 0; i < neighbours.size(); i++) {
            Coordinate dir = cell.getPosition().getDirectionTo(neighbours.get(i));
            Cell nextCell = board.getCell(pos.sum(dir));
            if (player.isOpponent(nextCell.getContent())) {
                if (validDirection(nextCell.getPosition(), player, dir, board)) {
                    result.add(dir);
                }
            }
        }
        return result;
    }

    /**
     * Checks whether the direction is valid. It runs toward the desired direction and checks it as a Turing Machine.
     * @return boolean - true only if the direction gives any gains for the Move.
     */
    private boolean validDirection(Coordinate pos, Player player, Coordinate direction, Board board) {
        Coordinate newPos = pos.sum(direction);
        if (!board.contains(newPos)) {
            return false;
        } // for a cell outside the board
        Cell next = board.getCell(newPos);
        if (player.isOpponent(next.getContent())) {
            return validDirection(next.getPosition(), player, direction, board);
        } else if (next.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Analizes each empty cell and finds all the possible moves for the Player.
     * @return list<Move> - the list of possible moves for the Player.
     */
    public ArrayList<Move> getPossibleMoves(Player player, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int row = 1; row <= board.getSize(); row++) {
            for (int col = 1; col <= board.getSize(); col++) {
                if (board.getCell(row, col).isEmpty()) {
                    Coordinate pos = new Coordinate(row, col);
                    List<Coordinate> directions = getDirections(pos, player, board);
                    if (!directions.isEmpty()) {
                        moves.add(new Move(new Coordinate(row, col), directions));
                    }
                }
            }
        }
        return moves;
    }
}
