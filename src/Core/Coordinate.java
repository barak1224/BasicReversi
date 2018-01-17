package Core;

/**
 * Class in charge of storing a pair of ints. To be used as Coordinates for the Cells in the Board
 * and as directions for the gains in Move.
 */
public class Coordinate {
    private int row, col;

    /**
     * Constructor. Must get two ints.
     */
    public Coordinate(int myRow, int myCol) {
        row = myRow;
        col = myCol;
    }

    /**
     * Getter for the row index.
     * @return int - row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for column index.
     * @return int - col index.
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns the coordinate in string form ---> (row,col)
     * @return string - the coordinates human-readable.
     */
    public String toString() {
        String result = "(" + Integer.toString(row) + "," + Integer.toString(col) + ")";
        return result;
    }

    /**
     * Sum Coordinates. Used to get next Cell in a determined direction.
     * @return Coordinate - The place of the resulting Cell.
     */
    public Coordinate sum(Coordinate newCoor) {
        return new Coordinate(row + newCoor.getRow(), col + newCoor.getCol());
    }

    /**
     * Gets the direction from the current Cell to the next one ( by coordinates, duh).
     * @return Coordinate - direction to next cell.
     */
    public Coordinate getDirectionTo(Coordinate newCoor) {
        return newCoor.sum(new Coordinate(-1 * row, -1 * col));
    }
}
