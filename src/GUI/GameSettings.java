package GUI;

import javafx.scene.paint.Color;


/**
 * Class to deal with saving and passing the settings of the game.
 */
public class GameSettings {
    private PlayerStart whoStarts;
    private Color colorPlayerOne;
    private Color colorPlayerTwo;
    private int sizeBoard;

    /**
     * Constructor, recieves all the data needed to store the settings.
     *
     * @param whoStarts      - defines which player starts (P1 or P2).
     * @param colorPlayerOne - defines the color of P1.
     * @param colorPlayerTwo - defines the color of P2.
     * @param sizeBoard      - defines the size of the board to create.
     */
    public GameSettings(PlayerStart whoStarts, Color colorPlayerOne, Color colorPlayerTwo, int sizeBoard) {
        this.whoStarts = whoStarts;
        if (whoStarts.name().equals("BLACK")) {
            this.colorPlayerOne = colorPlayerOne;
            this.colorPlayerTwo = colorPlayerTwo;
        } else {
            this.colorPlayerOne = colorPlayerTwo;
            this.colorPlayerTwo = colorPlayerOne;
        }
        this.sizeBoard = sizeBoard;
    }

    /**
     * Getter for the color of P1.
     *
     * @return Color - the color of P1.
     */
    public Color getColorPlayerOne() {
        return colorPlayerOne;
    }


    /**
     * Returns the color of the second player.
     *
     * @return Color - of the second player.
     */
    public Color getColorPlayerTwo() {
        return colorPlayerTwo;
    }

    /**
     * Getter for whoStarts.
     *
     * @return PlayerStart - data defining which player moves first.
     */
    public PlayerStart getWhoStarts() {
        return whoStarts;
    }

    /**
     * Returns the NUMBER of the player holding the first turn. (Can be 1 or 2, defined in settings).
     *
     * @return int - the number of the starting player.
     */
    public int firstPlayerNumber() {
        if (whoStarts.name().equals(PlayerStart.BLACK.name())) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Getter for the size of the Board.
     * @return int - the size of the board to be created.
     */
    public int getSizeBoard() {
        return sizeBoard;
    }
}
