package Core;

import java.util.List;

/**
 * Class containing the definition of a Cell, which conforms the Board matrix.
 * The Cell class knows it's position, content and neighbours. It also holds a reference to the Board's Cell Counter.
 */
public class Cell {


    private Coordinate position;
    private CellColor content;
    private List<Coordinate> neighbours;
    private CellCounter counter;

    /**
     * Constructor. Recieves a Coordinate, a pointer to the Counter, and content.
     */
    public Cell(Coordinate pos, CellCounter newCounter, List<Coordinate> myNeighbours) {
        position = pos;
        neighbours = myNeighbours;
        counter = newCounter;
        content = CellColor.EMPTY;
    }

    /**
     * Getter for the Cell's content.
     * @return char - content.
     */
    public CellColor getContent() {
        return content;
    }

    /**
     * Getter for the Cell's position.
     * @return Coordinate - position.
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Setter for the Cell's content.
     */
    public void setContent(CellColor newContent) {
        content = newContent;
    }

    /**
     * Getter for the list (vector) of the Cell's neighbouring Cells.
     * @return list<Cell> - neighbours.
     */
    public List<Coordinate> getNeighbours() {
        return neighbours;
    }

    /**
     * Returns a string representing the Cell with its content (for console).
     * @return
     */
    public String toString() {
        if (content == CellColor.BLACK) {
            return "X";
        } else if (content == CellColor.WHITE) {
            return "O";
        } else {
            return " ";
        }
    }

    /**
     * Checks wether the Cell is empty or not ('empty' meaning content == ' ').
     * It also accepts a Cell with the content '*' since it marks possible moves.
     * @return bool - true if content
     */
    public boolean isEmpty() {
        return content == CellColor.EMPTY;
    }

    /**
     * Function to deal with the counting of the Cells through their flipping.
     */
    public void sumOne(CellColor prev, CellColor current) {
        counter.changeValue(prev, current);
    }

    /**
     * The method change the color of the cell's content
     * @param newColor
     */
    public void beConquered(CellColor newColor) {
        sumOne(content, newColor);
        setContent(newColor);
    }
}
