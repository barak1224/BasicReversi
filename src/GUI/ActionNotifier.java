package GUI;

/**
 * The interface which notify to all about the hit.
 * @author Barak Talmor
 *
 */
public interface ActionNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - hitListener object
     */
    void addHitListener(ActionListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - hitListener object
     */
    void removeHitListener(ActionListener hl);
}
