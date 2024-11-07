import java.util.Scanner;

public class SudokuSolver {
    private static final int GRID_SIZE = 9;
    
    public static void main(String[] args) {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle row by row (use 0 for empty cells):");

        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print("Enter row " + (i + 1) + ": ");
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = scanner.nextInt();
                while (board[i][j] < 0 || board[i][j] > 9) {
                    System.out.println("Invalid number. Enter a number between 0 and 9:");
                    board[i][j] = scanner.nextInt();
                }
            }
        }

        scanner.close();

        if (solveBoard(board)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board.");
        }

        printBoard(board);
    }
    
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------+---------+---------");
            }
            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print(" | ");
                }
                System.out.print(" " + board[row][col]);
            }
            System.out.println();
        }
    }
    
    private static boolean isValid(int[][] board, int number, int row, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number || board[i][col] == number) {
                return false;
            }
        }
        int localRow = row - row % 3;
        int localCol = col - col % 3;
        for (int i = localRow; i < localRow + 3; i++) {
            for (int j = localCol; j < localCol + 3; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValid(board, numberToTry, row, col)) {
                            board[row][col] = numberToTry;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
