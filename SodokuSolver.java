class SudokuSolver {
    private static final int GRID_SIZE = 9;
    
    public static void main(String[] args) {
        int[][] board = {
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 5},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };
        
        if (solveBoard(board)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board.");
        }
        
        printBoard(board);
    }
    
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                System.out.print(board[row][col] == 0 ? "." : board[row][col] + " ");
            }
            System.out.println();
        }
    }
    
    private static boolean isValid(int[][] board, int number, int row, int col) {
        // Check the row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return false;
            }
        }
        
        // Check the column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return false;
            }
        }
        
        // Check the 3x3 grid
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