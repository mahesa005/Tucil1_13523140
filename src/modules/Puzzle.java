package modules;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Puzzle {
    // Driver
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] puzzle = null;
        
        while (true) {
            System.out.println("\n==== PUZZLE MATRIX MENU ====");
            System.out.println("1. Create Puzzle");
            System.out.println("2. Rotate 90 Degrees");
            System.out.println("3. Mirror Matrix");
            System.out.println("4. Add Rows");
            System.out.println("5. Add Columns");
            System.out.println("6. Insert Character");
            System.out.println("7. Fill with Random Chars");
            System.out.println("8. Copy Matrix");
            System.out.println("9. Print Matrix");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    puzzle = createMatrix(scanner);
                    break;
                case 2:
                    if (puzzle != null) puzzle = Puzzle.rotate90(puzzle);
                    else System.out.println("Create a matrix first!");
                    break;
                case 3:
                    if (puzzle != null) puzzle = Puzzle.mirror(puzzle);
                    else System.out.println("Create a matrix first!");
                    break;
                case 4:
                    if (puzzle != null) {
                        System.out.print("Enter number of rows to add: ");
                        int rows = scanner.nextInt();
                        puzzle = Puzzle.addRows(puzzle, rows);
                    } else System.out.println("Create a matrix first!");
                    break;
                case 5:
                    if (puzzle != null) {
                        System.out.print("Enter number of columns to add: ");
                        int cols = scanner.nextInt();
                        puzzle = Puzzle.addCols(puzzle, cols);
                    } else System.out.println("Create a matrix first!");
                    break;
                case 6:
                    if (puzzle != null) {
                        System.out.print("Enter row index: ");
                        int row = scanner.nextInt();
                        System.out.print("Enter column index: ");
                        int col = scanner.nextInt();
                        System.out.print("Enter character: ");
                        char ch = scanner.next().charAt(0);
                        puzzle = Puzzle.insCharToMatrix(puzzle, row, col, ch);
                    } else System.out.println("Create a matrix first!");
                    break;
                case 7:
                    if (puzzle != null) Puzzle.fillBoardWithRandomChars(puzzle);
                    else System.out.println("Create a matrix first!");
                    break;
                case 8:
                    if (puzzle != null) {
                        char[][] copiedMatrix = new char[puzzle.length][puzzle[0].length];
                        Puzzle.copyMatrix(puzzle, copiedMatrix);
                        System.out.println("Copied Matrix:");
                        Puzzle.printPuzzle(copiedMatrix);
                    } else System.out.println("Create a matrix first!");
                    break;
                case 9:
                    if (puzzle != null) {
                        System.out.println("Current Matrix:");
                        Puzzle.printPuzzle(puzzle);
                    } else System.out.println("No matrix to display!");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    // Helper function to create a matrix
    public static char[][] createMatrix(Scanner scanner) {
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        char[][] matrix = new char[rows][cols];
        System.out.println("Enter matrix elements row by row:");
        for (int i = 0; i < rows; i++) {
            String line = scanner.next();
            for (int j = 0; j < Math.min(line.length(), cols); j++) {
                matrix[i][j] = line.charAt(j);
            }
        }
        return matrix;
    }

    // KERJAAN GW //
    
    // 1. Rotate puzzle
    public static char[][] rotate90(char[][] puzzle) {
        int row = puzzle.length;
        int col = puzzle[0].length;
        char[][] rotated = new char[col][row];

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
            for (int j = 0; j < col; j++) {
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

    // 4. Create matrix
    public static char[][] createCharMatrix(int N, int M) {
        char[][] matrix = new char[N][M];
        return matrix;
    }

    public static void fillBoardWithRandomChars(char[][] board) {
        Random rand = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = (char) ('A' + rand.nextInt(26)); // Huruf A-Z
            }
        }
    }

    // 5. Print board
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // 6. Insert element to matrix
    public static char[][] insCharToMatrix(char[][] matrix, int row, int col, char character) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            System.out.println("Error: Cannot insert, index out of range");
            return matrix;
        }
        matrix[row][col] = character;
        return matrix;
    }

    // 7. Add Row to Matrix
    public static char[][] addRows(char[][] matrix, int addedRows) {
        if (matrix.length == 0) { // handle case matrix.length = 0
            return new char[addedRows][0];
        }
        char[][] newMatrix = new char[addedRows + matrix.length][matrix[0].length];
        copyMatrix(matrix, newMatrix); // save the value of the old matrix
        return newMatrix; // new matrix with added rows
    }

    // 8. Add Col to Matrix
    public static char[][] addCols(char[][] matrix, int addedCols) {
        if (matrix.length == 0) {
            return new char[0][addedCols];
        }
    
        char[][] newMatrix = new char[matrix.length][matrix[0].length + addedCols];
        copyMatrix(matrix, newMatrix);
        return newMatrix;
    }

    // 9. Copy Matrix
    public static void copyMatrix (char[][] matrix, char[][] copyMatrix) {
        if (matrix.length > copyMatrix.length || matrix[0].length > copyMatrix[0].length) {
            System.out.println("Error: Cannot copy, index out of range");
            return;
        }
        for (int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                copyMatrix[i][j] = matrix[i][j];
            }
        }
    }

    // 10. Check if puzzle piece is placable on the board
    public static boolean isPlacable(char[][] board, int currRow, int currCol, char[][] puzzlePiece) {
        if ((currRow + puzzlePiece.length) > board.length) {
            return false;
        }

        for (int i = 0; i < puzzlePiece.length; i++) {
            if ((currCol + puzzlePiece[i].length > board[0].length)) return false;

            for (int j = 0; j < puzzlePiece[i].length; j++) {
                if (board[currRow + i][currCol + j] != '.' && puzzlePiece[i][j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    // 11. Place puzzle piece on board
    public static char[][] placePiece(char[][] board, int currRow, int currCol, char[][] puzzlePiece) { // make sure udh di check placable dulu
        for (int i = 0; i < puzzlePiece.length; i++) {
            for (int j = 0; j < puzzlePiece[0].length; j++) {
                if (puzzlePiece[i][j] != '.') {
                    board[currRow + i][currCol + j] = puzzlePiece[i][j];
                }
            }
        }
        return board;
    }

    // 12. Get all puzzle variations
    public static List<char[][]> getAllPieceVariations(char[][] puzzlePiece) {
        List<char[][]> pieceVariations = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pieceVariations.add(puzzlePiece);
            puzzlePiece = rotate90(puzzlePiece);
        }
        puzzlePiece = mirror(puzzlePiece);
        for (int i = 0; i < 4; i++) {
            pieceVariations.add(puzzlePiece);
            puzzlePiece = rotate90(puzzlePiece);
        }
        pieceVariations = removeDuplicateMatrices(pieceVariations);
        return pieceVariations;
    }

    // 13. fill puzzle board with '.'
    public static char[][] fillBoardWithDot(char[][] emptyBoard) {
        int row = emptyBoard.length;
        int col = emptyBoard[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                emptyBoard[i][j] = '.';
            }
        }
        return emptyBoard;
    }

    // 14. Check is board filled
    public static boolean isBoardFilled(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') return false;
            }
        }
        return true;
    }

    // 15. isPuzzlePieceSame
    public static boolean isSamePuzzlePiece(char[][] piece1, char[][] piece2) {
        if ((piece1.length != piece2.length) || (piece1[0].length != piece2[0].length)) return false; // check dimensions
        for (int i = 0; i < piece1.length; i++) {
            for (int j = 0; j < piece1[0].length; j++) {
                if (piece1[i][j] != piece2[i][j]) return false;
            }
        }
        return true;
    }

    // 16. Remove Duplicates from matrix list
    public static List<char[][]> removeDuplicateMatrices(List<char[][]> matrixList) {
        List<char[][]> uniqueMatrices = new ArrayList<>();
    
        for (char[][] matrix : matrixList) {
            boolean isDuplicate = false;
    
            for (char[][] uniqueMatrix : uniqueMatrices) {
                if (isSamePuzzlePiece(matrix, uniqueMatrix)) {
                    isDuplicate = true;
                    break;
                }
            }
    
            if (!isDuplicate) {
                uniqueMatrices.add(matrix);
            }
        }
        return uniqueMatrices;
    }

    // 17. Remove piece from board
    public static void removePiece(char[][] board, char[][] puzzlePiece, int row, int col) {
        for (int i = 0; i < puzzlePiece.length; i++) {
            for (int j = 0 ; j < puzzlePiece[0].length; j++) {
                if (puzzlePiece[i][j] != '.') {
                    board[i + row][j + col] = '.';
                }
            }
        }
    }
}
