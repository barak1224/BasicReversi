package GUI;

import javafx.scene.paint.Color;


public class GameSettings {
    private PlayerStart whoStarts;
    private Color colorPlayerOne;
    private Color colorPlayerTwo;
    private int sizeBoard;

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

    public Color getColorPlayerOne() { return colorPlayerOne; }

    public PlayerStart getWhoStarts() {
        return whoStarts;
    }

    public int firstPlayerNumber() {
        if (whoStarts.name().equals(PlayerStart.BLACK.name())) {
            return 1;
        } else {
            return 2;
        }
    }

    public int secondPlayerNumber() {
        if (firstPlayerNumber() == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    public Color getColorPlayerTwo() {
        return colorPlayerTwo;
    }

    public int getSizeBoard() {
        return sizeBoard;
    }
}
