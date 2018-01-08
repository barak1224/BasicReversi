public class CellCounter {
    private CellColor player1, player2;
    private int points1, points2;

    public CellCounter(CellColor player1Color, CellColor player2Color) {
        player1 = player1Color;
        player2 = player2Color;
    }

    public CellCounter(CellColor player1Color, CellColor player2Color, int pointsP1, int pointsP2) {
        player1 = player1Color;
        player2 = player2Color;
        points1 = pointsP1;
        points2 = pointsP2;
    }

    public int getPoints1() {
        return points1;
    }

    public int getPoints2() {
        return points2;
    }

    public void changeValue(CellColor prev, CellColor current) {
        if (current == player1) {
            points1 += 1;
            if (prev != CellColor.EMPTY) { points2 -= 1; }
        } else if (current == player2) {
            points2 += 1;
            if (prev != CellColor.EMPTY) { points1 -= 1; }
        }
    }

    public int getWinner() {
        if (points1 > points2) { return 1; }
        else if (points2 > points1) { return 2; }
        else { return 0; }    //tie
    }

    public boolean gameOver(int size) {
        return (points1 + points2 == size * size) || (points1 == 0) || (points2 == 0);
    }
}
