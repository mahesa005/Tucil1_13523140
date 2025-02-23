import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/* To do
 * 1. Extract pieces DONE
 * 2. isPlacable DONE
 * 3. Placing piece on board DONE
 * 4. Deleting piece from board 
 * 5. Algorithmmmmmmmm
 */


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

            int rows = getRow(inputList.get(0));
            int cols = getCol(inputList.get(0));
            int totalPuzzlePieces = getTotalPieces(inputList.get(0));

            char[][] board = getBoardConfig(inputList.get(1), rows, cols);
            modules.Puzzle.printBoard(board);

            List<char[][]> puzzlePieces = getPuzzlePieces(inputList, totalPuzzlePieces);
            
            int pieceIndex = 1;
            for (char[][] piece : puzzlePieces) {
                System.out.println("\nPuzzle Piece " + pieceIndex + ":");
                modules.Puzzle.printPuzzle(piece);

                boolean placed = false;

                // Coba cari posisi kosong untuk menempatkan puzzle
                for (int i = 0; i < rows && !placed; i++) {
                    for (int j = 0; j < cols && !placed; j++) {
                        if (modules.Puzzle.isPlacable(board, i, j, piece)) {
                            board = modules.Puzzle.placePiece(board, i, j, piece);
                            placed = true;
                            System.out.println("\nPlaced puzzle at (" + i + "," + j + "):");
                            modules.Puzzle.printBoard(board);
                        }
                    }
                }

                if (!placed) {
                    System.out.println("\nPuzzle Piece " + pieceIndex + " cannot be placed on board.");
                }

                pieceIndex++;
            }
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
    public static char[][] getBoardConfig(String config, int row, int col) {
        if (config.equals("DEFAULT")) {
            return modules.Puzzle.createCharMatrix(row, col);
        } else {
            System.out.println("Error loading config: " + config);
            return new char[row][col]; 
        }
    }

    // string to arr of char
    public static char[] strToChar(String str) {
        return str.toCharArray();
    }

    // extract puzzle pieces
    public static List<char[][]> getPuzzlePieces(List<String> inputList, int totalPuzzlePieces) {
        List<char[][]> puzzlePieces = new ArrayList<>();
        int strIdx = 2;

        while (strIdx < inputList.size()) {

            char[] currString = strToChar(inputList.get(strIdx));
            char currChar = currString[0];
            char currBlock = currChar;
            int maxCol = currString.length;

            char[][] currPuzzlePiece = modules.Puzzle.createCharMatrix(1, maxCol);

            int currRow = 0;

            while (strIdx < inputList.size() && currChar == currBlock) {
                if (currRow > 0) {
                    currPuzzlePiece = modules.Puzzle.addRows(currPuzzlePiece, currRow - (currRow - 1));
                }
                currString = strToChar(inputList.get(strIdx));

                if (currString.length > maxCol) {
                    currPuzzlePiece = modules.Puzzle.addCols(currPuzzlePiece, currString.length);
                    maxCol = currString.length;
                }

                for (int currCol = 0; currCol < currString.length; currCol++) {
                    currPuzzlePiece = modules.Puzzle.insCharToMatrix(currPuzzlePiece, currRow, currCol, currString[currCol]);
                }

                currRow++;
                strIdx++;

                if (strIdx < inputList.size()) {
                    currString = strToChar(inputList.get(strIdx));
                    currChar = currString[0];
                }
            }

            puzzlePieces.add(currPuzzlePiece);

            if (strIdx < inputList.size()) {
                currBlock = inputList.get(strIdx).charAt(0);
            }
        }
        return puzzlePieces;
        }
    }