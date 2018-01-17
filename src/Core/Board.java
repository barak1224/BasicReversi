package Core;

import GUI.ActionNotifier;
import javafx.scene.layout.GridPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class holding most of the Data. It holds a two-dimensional array which in turn holds pointers to Cells,
 * which can be empty or have a char content. It also holds a printer (depending on the interface) and a Counter
 * to be in charge of the points throughout the Game.
 */
public class Board  {
    private int size;
    private Cell[][] matrix;
    private CellCounter counter;

    /**
     * Recieves a direction to move towards, and flip every Cell until it reaches one that belongs to the Player.
     */
    public Board(int newSize) {
        matrix = new Cell[newSize][newSize];
        counter = new CellCounter();
        size = newSize;
        initialize(counter);
    }

    /**
     * The method initializeing the board
     * @param myCounter
     */
    private void initialize(CellCounter myCounter) {
        CellColor color1 = CellColor.BLACK;
        CellColor color2 = CellColor.WHITE;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrix[row][col] = new Cell(new Coordinate(row + 1, col + 1), counter, getNeighbours(row, col));
            }
        }
        int middle = size / 2;
        matrix[middle][middle - 1].setContent(color1);
        matrix[middle - 1][middle].setContent(color1);
        matrix[middle][middle].setContent(color2);
        matrix[middle - 1][middle - 1].setContent(color2);
    }

    /**
     * Same as getCell(int, int), but gets a Coordinate.
     * @return Cell - the Cell at the given Coordinate.
     */
    public Cell getCell(int row, int col) {
        return matrix[row-1][col-1];
    }

    /**
     * Same as getCell(Coordinate), but gets a Coordinate.
     * @return Cell - the Cell at the given Coordinate.
     */
    public Cell getCell(Coordinate coor) {
        return getCell(coor.getRow(), coor.getCol());
    }

    /**
     * Checks whether the Board contains a Cell at the given Coordinates.
     * @return true of the Coordinates define a Cell, false if not.
     */
    public boolean contains(Coordinate coor) {
        int row = coor.getRow();
        int col = coor.getCol();
        return (row > 0 && row <= size && col > 0 && col <= size);
    }

    /**
     * Getter for the size of the Board.
     * @return int - the size of the two-dimensional array 'board'.
     */
    public int getSize() {
        return size;
    }

    /**
     * Performs the actual work of conquering a Cell.
     */
    public void applyMove(Move myMove, Player player) {
        Coordinate pos = myMove.getCoordinate();
        player.conquerCell(getCell(pos));
        List<Coordinate> directions = myMove.getDirections();
        for (int i = 0; i < directions.size(); i++) {
            Coordinate dir = directions.get(i);
            flipGains(pos, player, dir);
        }
    }

    /**
     * Checks whether the game has to end.
     * @return true if the Game should end now.
     */
    public boolean gameOver() {
        return counter.gameOver(size);
    }

    /**
     * The method returns the neighbors of the cell
     * @return list of neighbors
     */
    private List<Coordinate> getNeighbours(int row, int col) {
        int maxNeighbours = 8;
        List<Coordinate> neighbours = new ArrayList<Coordinate>();
        Coordinate coor = new Coordinate(row + 1, col + 1);
        Coordinate directions[] = {new Coordinate(-1, -1), new Coordinate(-1, 0),
                new Coordinate(-1, 1), new Coordinate(0, -1),
                new Coordinate(0, 1), new Coordinate(1, -1), new Coordinate(1, 0),
                new Coordinate(1, 1),};

        for (int i = 0; i < maxNeighbours; i++) {
            Coordinate position = coor.sum(directions[i]);
            if (this.contains(position)){
                neighbours.add(position);
            }
        }
        return neighbours;
    }

    /**
     * Recieves a direction to move towards, and flip every Cell until it reaches one that belongs to the Player.
     */
    private void flipGains(Coordinate position, Player player, Coordinate direction) {
        Cell next = getCell(position.sum(direction));
        if (player.isOpponent(next.getContent())) {
            player.conquerCell(next);
            flipGains(next.getPosition(), player, direction);
        }
    }

    /**
     * Compares the points of the players, and returns the winner (or tie).
     * @return int - and integer representing who won (0 for tie, 1 for P1, 2 for P2).
     */
    public int getWinner() {
        return counter.getWinner();
    }

    /**
     * Getter for the points player.
     * @return int - the points of Player 1.
     */
    public int getPlayerPoints(int numOfPlayer) {
        return counter.getPoints(numOfPlayer);
    }
}
