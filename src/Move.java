import java.util.List;

public class Move {
    private Coordinate position;
    List<Coordinate> directions;
    int gain;

    public Move(Coordinate myPosition, List<Coordinate> myDirections) {
        position = myPosition;
        directions = myDirections;
    }

    public String getCoordinateAsString() {
        return position.toString();
    }

    public Coordinate getCoordinate() {
        return position;
    }

    public List<Coordinate> getDirections() {
        return directions;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int newGain) {
        gain = newGain;
    }

}
