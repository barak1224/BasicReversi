public class Coordinate {
    private int row, col;

    public Coordinate(int myRow, int myCol) {
        row = myRow;
        col = myCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String toString() {
        String result = Integer.toString(row) + ", " + Integer.toString(col);
        return result;
    }

    public Coordinate sum(Coordinate newCoor) {
        return new Coordinate(row + newCoor.getRow(), col + newCoor.getCol());
    }

    public Coordinate getDirectionTo(Coordinate newCoor) {
        return newCoor.sum(new Coordinate(-1 * row, -1 * col));
    }
}
