package Core;

public class TurnManager {
    private Player[] players;
    private boolean[] noMoves;
    private int turns;

    /**
     * Constructor
     */
    public TurnManager(Player[] newPlayers) {
        players = new Player[2];
        players[0] = newPlayers[0];
        players[1] = newPlayers[1];
        noMoves = new boolean[2];
        noMoves[0] = false;
        noMoves[1] = false;
        turns = 0;
    }

    /**
     * The method set no move to the player that's
     * @param value
     */
    public void setNoMove(boolean value) {
        noMoves[turns % 2] = value;
    }

    /**
     * The method returns the next player pointer
     */
    public Player nextPlayer() {
        return players[turns++ % 2];
    }

    /**
     * The method checks if there is no more moves to be played
     * @return
     */
    public boolean noMoreMoves() {
        return (noMoves[0] && noMoves[1]);
    }
}
