package Core;

import java.util.List;

public class Move {
    private Coordinate position;
    List<Coordinate> directions;

    /**
     * Constructor. Gets the position and the vector, ready for using.
     */
    public Move(Coordinate myPosition, List<Coordinate> myDirections) {
        position = myPosition;
        directions = myDirections;
    }

    /**
     * Getter for the Coordinates string form, used to show the user the possible Moves.
     * @return string - string expression of the Coordinate.
     */
    public String getCoordinateAsString() {
        return position.toString();
    }

    /**
     * Getter for the position of the Move.
     * @return Coordinate - the position of the Cell the user can conquer.
     */
    public Coordinate getCoordinate() {
        return position;
    }

    /**
     * Getter for the list of directions in which to move to flip cells.
     * @return vector<Coordinate*> directions - the directions of gains for the Move.
     */
    public List<Coordinate> getDirections() {
        return directions;
    }
}
