package sudoku;

import java.util.Random;


public class SudokuBoard {
    private final int[][] board = new int[9][9];
    private SudokuSolver sudokusolver = new BacktrackingSudokuSolver();

    public SudokuBoard() {
    }

    public void solveGame() {
        sudokusolver.solve(this);
    }


    public void setBoard(int x, int y, int value) {
        board[x][y] = value;
    }

    public int getBoard(int x, int y) {
        return board[x][y];
    }

    public String showBoard() {
        String s = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s += getBoard(i, j) + "\t";
            }
            s += "\n";
        }
        return s;
    }



}