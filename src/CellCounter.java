public class CellCounter {
    private CellColor player1, player2;
    private int[] points;

    public CellCounter() {
        points = new int[2];
        player1 = CellColor.BLACK;
        player2 = CellColor.WHITE;
        points[0] = 2;
        points[1] = 2;
    }

    public CellCounter(CellColor player1Color, CellColor player2Color, int pointsP1, int pointsP2) {
        player1 = player1Color;
        player2 = player2Color;
        points = new int[2];
        points[0] = pointsP1;
        points[1] = pointsP2;
    }

    public int getPoints(int numOfPlayer) {
        return points[numOfPlayer - 1];
    }

    public void changeValue(CellColor prev, CellColor current) {
        if (current == player1) {
            points[0] += 1;
            if (prev != CellColor.EMPTY) {
                points[1] -= 1;
            }
        } else if (current == player2) {
            points[1] += 1;
            if (prev != CellColor.EMPTY) {
                points[0] -= 1;
            }
        }
    }

    public int getWinner() {
        if (points[0] > points[1]) {
            return 1;
        } else if (points[1] > points[0]) {
            return 2;
        } else {
            return 0;
        }    //tie
    }

    public boolean gameOver(int size) {
        return (points[0] + points[1] == size * size) || (points[0] == 0) || (points[1] == 0);
    }
}
