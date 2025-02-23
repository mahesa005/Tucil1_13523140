import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
    private static int caseCtr = 0;
    private static int totalPuzzlePieces;
    private static char[][] board;
    private static List<char[][]> puzzlePieces = new ArrayList<>();
    private static int rows;
    private static int cols;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        preProcess(args);
        long startTime = System.currentTimeMillis();
        if (!IQPuzzlerPro(0)) {
            System.out.println("Gagal mendapatkan solusi");
            return;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Waktu pencarian: " + (endTime - startTime) + " ms");
        System.out.println("Jumlah kasus yang ditinjau: " + caseCtr);
        printColored();
        saveSolvedBoard();
    }

    public static void preProcess(String[] args) {
        System.out.print("Masukkan nama file: ");
        String filename = scanner.nextLine();
        String filepath = "test/input/" + filename;

        try {
            List<String> inputList = Files.readAllLines(Paths.get(filepath));

            rows = getRow(inputList.get(0));
            cols = getCol(inputList.get(0));
            totalPuzzlePieces = getTotalPieces(inputList.get(0));

            board = getBoardConfig(inputList.get(1), rows, cols);

            puzzlePieces = getPuzzlePieces(inputList, totalPuzzlePieces);
        }
        catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
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
            char[][] board = modules.Puzzle.createCharMatrix(row, col);
            return modules.Puzzle.fillBoardWithDot(board);
        } else {
            System.out.println("Error loading config: " + config);
            return new char[row][col]; 
        }
    }

    // string to arr of char
    public static char[] strToChar(String str) {
        return str.toCharArray();
    }

    // get first nonSpaceCharacter
    public static char getFirstNonSpaceCharacter(char[] line) {
        for (char c : line) {
            if (c != ' ') {
                return c;
            }
        }
        return ' ';
    }

    // extract puzzle pieces
    public static List<char[][]> getPuzzlePieces(List<String> inputList, int totalPuzzlePieces) {
        List<char[][]> puzzlePieces = new ArrayList<>();
        int strIdx = 2;

        while (strIdx < inputList.size()) {
            char[] currString = strToChar(inputList.get(strIdx));
            char currChar = getFirstNonSpaceCharacter(currString);
            char currBlock = currChar;
            int maxCol = 0;
    
            int tempIdx = strIdx;
            while (tempIdx < inputList.size() && getFirstNonSpaceCharacter(strToChar(inputList.get(tempIdx))) == currBlock) {
                maxCol = Math.max(maxCol, inputList.get(tempIdx).length());
                tempIdx++;
            }
    
            char[][] currPuzzlePiece = modules.Puzzle.createCharMatrix(1, maxCol);
            int currRow = 0;
    
            while (strIdx < inputList.size() && getFirstNonSpaceCharacter(strToChar(inputList.get(strIdx))) == currBlock) { 
                if (currRow > 0) {
                    currPuzzlePiece = modules.Puzzle.addRows(currPuzzlePiece, 1);
                }
    
                currString = strToChar(inputList.get(strIdx));
    
                if (currString.length > maxCol) {
                    currPuzzlePiece = modules.Puzzle.addCols(currPuzzlePiece, currString.length);
                    maxCol = currString.length;
                }

                for (int currCol = 0; currCol < maxCol; currCol++) {
                    char ch = (currCol < currString.length) ? currString[currCol] : '.'; 
                    if (ch == ' ') ch = '.';
                    currPuzzlePiece = modules.Puzzle.insCharToMatrix(currPuzzlePiece, currRow, currCol, ch);
                }
    
                currRow++;
                strIdx++;
    
                if (strIdx < inputList.size()) {
                    currString = strToChar(inputList.get(strIdx));
                    currChar = getFirstNonSpaceCharacter(currString);
                }
            }
    
            puzzlePieces.add(currPuzzlePiece);
    
            if (strIdx < inputList.size()) {
                currBlock = getFirstNonSpaceCharacter(strToChar(inputList.get(strIdx)));
            }
        }
        return puzzlePieces;
    }

    // IQ Puzzle Solver YESYESYES
    public static boolean IQPuzzlerPro(int puzzlePieceIdx) {
        if (puzzlePieceIdx == puzzlePieces.size()) return modules.Puzzle.isBoardFilled(board);
        char[][] puzzlePiece = puzzlePieces.get(puzzlePieceIdx);
        List<char[][]> pieceVariants = modules.Puzzle.getAllPieceVariations(puzzlePiece);
        for (char[][] uniquePiece : pieceVariants) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (modules.Puzzle.isPlacable(board, i, j, uniquePiece)) {
                        modules.Puzzle.placePiece(board, i, j, uniquePiece);
                        if (IQPuzzlerPro(puzzlePieceIdx + 1)) return true;
                        modules.Puzzle.removePiece(board, uniquePiece, i, j);
                        caseCtr++;
                    }
                }
            }
        }
        return false;
    }

    // print colored board
    public static void printColored() {
        final String RESET = "\u001B[0m";
        final String[] COLORS = {
            "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m",
            "\u001B[91m", "\u001B[92m", "\u001B[93m", "\u001B[94m", "\u001B[95m", "\u001B[96m",
            "\u001B[97m", "\u001B[90m", "\u001B[41m", "\u001B[42m", "\u001B[43m", "\u001B[44m",
            "\u001B[45m", "\u001B[46m", "\u001B[101m", "\u001B[102m", "\u001B[103m", "\u001B[104m",
            "\u001B[105m", "\u001B[106m"
        };
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char piece = board[i][j];
                String color = RESET;
    
                if (piece >= 'A' && piece <= 'Z') {
                    color = COLORS[piece - 'A'];
                }
    
                System.out.print(color + piece + " " + RESET);
            }
            System.out.println();
        }
    }

    // Save solution in a txt file in the output folder
    public static void saveSolvedBoard() {
        System.out.print("Apakah anda ingin menyimpan solusi? (ya/tidak): ");
        String response = scanner.nextLine().trim().toLowerCase();
    
        if (!response.equals("ya")) {
            System.out.println("Solusi tidak disimpan.");
            return;
        }
    
        String outputPath = "test/output/solusi.txt";  
        try {
            Files.createDirectories(Paths.get("output"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
    
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    writer.write(board[i][j] + " ");
                }
                writer.newLine();
            }
    
            writer.close();
            System.out.println("Solusi berhasil disimpan di: " + outputPath);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan solusi: " + e.getMessage());
        }
    }
}

    