import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import modules.*;

public class Main {
    public static void main(String[] args) {
        preprocess(args);
    }

    public static void preprocess(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file: ");
        String filename = scanner.nextLine();
        String filepath = "test/input/" + filename;

        try {
            List<String> inputList = Files.readAllLines(Paths.get(filepath));
            for (String line : inputList) {
                System.out.println(line);
            }
            System.out.println(extractNums(inputList.get(0)));
            System.out.println(getRow(inputList.get(0)));
            System.out.println(getCol(inputList.get(0)));
            System.out.println(getTotalPieces(inputList.get(0)));
        }
        catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
        scanner.close();
    }

    // extract nums to help get total puzzle piece and board size
    public static int[] extractNums(String line) {
        String[] parts = line.split(" ");
        int[] nums = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

    // get row length
    public static int getRow(String firstLine) {
        int[] nums = extractNums(firstLine);
        int row = nums[0];
        return row;
    }

    // get col length
    public static int getCol(String firstLine) {
        int[] nums = extractNums(firstLine);
        int col = nums[1];
        return col;
    }

    // get total pieces
    public static int getTotalPieces(String firstLine) {
        int[] nums = extractNums(firstLine);
        int totalPuzzlePieces = nums[2];
        return totalPuzzlePieces;
    }

    // get config
    /*public static char[][] getBoardConfig(String config, int row, int col) {
        char [][] board;
        if (config.equals("DEFAULT")) {
            board = modules.Puzzle.createDefaultBoard(row, col);
        }
        else {
            
        }
        return board;
    } */
}