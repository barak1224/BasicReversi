package GUI;


/**
 * Interface that listens for Actions, mostly Mouse Clicks.
 * Used to listen to AtionNotifiers.
 */
public interface ActionListener {
    /**
     * This method is called whenever the beingHit object is hit.
     */
    void hitEvent(int row, int col);
}
