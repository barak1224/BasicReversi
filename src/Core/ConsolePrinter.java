package Core;

import java.util.Scanner;

public class ConsolePrinter implements Printer {

    @Override
    public void printBoard(Cell[][] matrix, int size, int points1, int points2) {
        System.out.println();
        System.out.println("Current Core.Board:");
        System.out.println("Score:");
        System.out.println("\tCore.Player 1 " + points1);
        System.out.println("\tCore.Player 2 " + points2);
        for (int row = 0; row <= size; row++) {
            for (int col = 0; col <= size; col++) {
                if (row == 0) {
                    if (col == 0) {
                        System.out.print(" |");
                    } else {
                        System.out.print(" " + col + " |");
                    }
                } else if (col == 0) {
                    System.out.print(row + "|");
                } else {
                    System.out.print(" " + matrix[row - 1][col - 1].toString() + " |");
                }
            }
            System.out.println();
            for (int i = 0; i < size * 2 + 1; i++) {
                System.out.print("--");
            }
            System.out.println();
        }
    }

    @Override
    public void printStream(String output) {
        System.out.print(output);
    }

    public int[] getInput() {
        Scanner reader = new Scanner(System.in);
        int[] result = new int[2];
        result[0] = reader.nextInt();
        result[1] = reader.nextInt();
        return result;
    }
}
