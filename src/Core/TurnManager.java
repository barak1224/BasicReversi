package Core;

public class TurnManager {
    private Player[] players;
    private boolean[] noMoves;
    private int turns;

    public TurnManager(Player[] newPlayers) {
        players = new Player[2];
        players[0] = newPlayers[0];
        players[1] = newPlayers[1];
        noMoves = new boolean[2];
        noMoves[0] = false;
        noMoves[1] = false;
        turns = 0;
    }

    public void setNoMove(boolean value) {
        noMoves[turns % 2] = value;
    }

    public Player nextPlayer() {
        return players[turns++ % 2];
    }

    public boolean noMoreMoves() {
        return (noMoves[0] && noMoves[1]);
    }
}
