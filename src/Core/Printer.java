public interface Printer {
    void printBoard(Cell[][] matrix, int size, int points1, int points2);

    void printStream(String output);

    int[] getInput();
}
