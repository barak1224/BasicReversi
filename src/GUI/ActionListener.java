package GUI;

import Core.Coordinate;

public interface ActionListener {
    /**
     * This method is called whenever the beingHit object is hit.
     */
    void hitEvent(int row, int col);
}
