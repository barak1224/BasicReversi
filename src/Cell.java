import java.util.List;

public class Cell {


    private Coordinate position;
    private CellColor content;
    private List<Coordinate> neighbours;
    private CellCounter counter;

    public Cell(Coordinate pos, CellCounter newCounter, List<Coordinate> myNeighbours) {
        position = pos;
        neighbours = myNeighbours;
        counter = newCounter;
        //TODO define color maybe?
    }

    public CellColor getContent() {
        return content;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setContent(CellColor newContent) {
        content = newContent;
    }

    public List<Coordinate> getNeighbours() {
        return neighbours;
    }

    public String toString() {
        if (content == CellColor.BLACK) {
            return "X";
        } else if (content == CellColor.WHITE) {
            return "O";
        } else {
            return " ";
        }
    }

    public boolean isEmpty() {
        return content == CellColor.EMPTY;
    }

    public void sumOne(CellColor prev, CellColor current) {
        counter.changeValue(prev, current);
    }

    public void beConquered(CellColor newColor) {
        sumOne(content, newColor);
        setContent(newColor);
    }
}
