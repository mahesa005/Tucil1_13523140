package modules;

import java.util.Arrays;
import java.util.Scanner;

public class Puzzle {
    // Driver
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();
        
        char[][] puzzle = new char[rows][cols];
        
        System.out.println("Enter puzzle elements:");
        for (int i = 0; i < rows; i++) {
            String line = scanner.next();
            for (int j = 0; j < cols; j++) {
                puzzle[i][j] = line.charAt(j);
            }
        }
        
        System.out.println("Original Puzzle:");
        printPuzzle(puzzle);
        
        System.out.println("Rotated Puzzle (90 degrees clockwise):");
        printPuzzle(rotate90(puzzle));

        System.out.println("Matrix mirrored: ");
        printPuzzle(mirror(puzzle));

        
        scanner.close();
    }
    // 1. Rotate puzzle
    public static char[][] rotate90(char[][] puzzle) {
        int row = puzzle.length;
        int col = puzzle[0].length;
        char[][] rotated = new char[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rotated[j][row - 1 - i] = puzzle[i][j];
            }
        }
        return rotated;
    }

    // 2. Mirror puzzle
    public static char[][] mirror(char[][] puzzle) {
        int row = puzzle.length;
        int col = puzzle[0].length;
        char[][] mirrored = new char[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                mirrored[i][j] = puzzle[i][col - 1 - j];
            }
        }
        return mirrored;
    }

    // 3. Print puzzle
    public static void printPuzzle(char[][] puzzle) {
        for (char[] row : puzzle) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    // 4. Make default board/matrix
    public static char[][] createDefaultBoard(int N, int M) {
        char[][] board = new char[N][M];
        return board;
    }
}
