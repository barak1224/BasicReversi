package Core;

public interface Printer {
    /**
     * Print the board to the console.
     */
    void printBoard(Cell[][] matrix, int size, int points1, int points2);

    /**
     * Print the stream to the console
     */
    void printStream(String output);
}
