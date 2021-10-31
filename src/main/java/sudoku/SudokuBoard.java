package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver sudokusolver;

    public SudokuBoard(SudokuSolver sudokusolver) {

        this.sudokusolver = sudokusolver;

        for(int i=0; i < 9; i++){
            for(int j=0; j < 9; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public void solveGame() {
        sudokusolver.solve(this);
    }


    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public int get(int x, int y) {

        return board[x][y].getFieldValue();
    }

    public String showBoard() {
        String s = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s += get(i, j) + "\t";
            }
            s += "\n";
        }
        return s;
    }

    public boolean checkBoard(int rzad, int kolumna, int los) {

        for (int i = 0; i <= 8; i++) {        //przejscie po kolumnie
            if (get(i, kolumna) == los) {
                return false;
            }
        }
        for (int i = 0; i <= 8; i++) {        //przejscie po wierszu
            if (get(rzad, i) == los) {
                return false;
            }
        }

        int pomrzad = rzad - rzad % 3;
        int pomkol = kolumna - kolumna % 3;
        for (int i = 0; i < 3; i++) {        //przejscie po kwadracie 3x3
            for (int j = 0; j < 3; j++) {
                if (get(pomrzad + i, pomkol + j) == los) {
                    return false;
                }
            }
        }
        return true;
    }

}