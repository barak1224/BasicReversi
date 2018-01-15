package Core;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private Cell[][] matrix;
    CellCounter counter;

    public Board(int newSize) {
        matrix = new Cell[newSize][newSize];
        counter = new CellCounter();
        size = newSize;
        initialize(counter);
    }

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

    public Cell getCell(int row, int col) {
        return matrix[row-1][col-1];
    }

    public Cell getCell(Coordinate coor) {
        return getCell(coor.getRow(), coor.getCol());
    }

    public boolean contains(Coordinate coor) {
        int row = coor.getRow();
        int col = coor.getCol();
        return (row > 0 && row <= size && col > 0 && col <= size);
    }

    public int getSize() {
        return size;
    }

    public void applyMove(Move myMove, Player player) {
        Coordinate pos = myMove.getCoordinate();
        player.conquerCell(getCell(pos));
        List<Coordinate> directions = myMove.getDirections();
        for (int i = 0; i < directions.size(); i++) {
            Coordinate dir = directions.get(i);
            flipGains(pos, player, dir);
        }
    }

    public boolean gameOver() {
        return counter.gameOver(size);
    }

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

    private void flipGains(Coordinate position, Player player, Coordinate direction) {
        Cell next = getCell(position.sum(direction));
        if (player.isOpponent(next.getContent())) {
            player.conquerCell(next);
            flipGains(next.getPosition(), player, direction);
        }
    }

    public int getWinner() {
        return counter.getWinner();
    }

    public int getPlayerPoints(int numOfPlayer) {
        return counter.getPoints(numOfPlayer);
    }
}
