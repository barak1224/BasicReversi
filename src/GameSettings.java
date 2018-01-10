import javafx.scene.paint.Color;


public class GameSettings {
    private PlayerStart whoStarts;
    private Color colorPlayerOne;
    private Color colorPlayerTwo;
    private int sizeBoard;

    public GameSettings(PlayerStart whoStarts, Color colorPlayerOne, Color colorPlayerTwo, int sizeBoard) {
        this.whoStarts = whoStarts;
        this.colorPlayerOne = colorPlayerOne;
        this.colorPlayerTwo = colorPlayerTwo;
        this.sizeBoard = sizeBoard;
    }

    public Color getColorPlayerOne() {
        return colorPlayerOne;
    }

    public PlayerStart getWhoStarts() {
        return whoStarts;
    }

    public Color getColorPlayerTwo() {
        return colorPlayerTwo;
    }

    public int getSizeBoard() {
        return sizeBoard;
    }
}
