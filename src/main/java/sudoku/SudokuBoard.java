package sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuBoard {

    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver sudokusolver;
    private List<SudokuRow> row = Arrays.asList(new SudokuRow[9]);
    private List<SudokuColumn> column = Arrays.asList(new SudokuColumn[9]);
    private List<SudokuBox> box = Arrays.asList(new SudokuBox[9]);



    public SudokuBoard(SudokuSolver sudokusolver) {

        this.sudokusolver = sudokusolver;

        //utworzenie strutury board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
            }
        }

        //utworzenie wierszy kolumn boxow
        for (int i = 0; i < 9; i++) {
            row.set(i, new SudokuRow());
            column.set(i, new SudokuColumn());
            box.set(i, new SudokuBox());
        }

        SudokuField[] tmp = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = board[i][j];
            }
            row.get(i).creation(tmp);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = board[j][i];
            }
            column.get(i).creation(tmp);
        }
        int index = 0;
        int t = 0;
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int x = i; x < 3 + i; x++) {
                    for (int y = j; y < 3 + j; y++) {
                        tmp[index] = board[x][y];
                        index++;
                    }
                }
                index = 0;
                box.get(t).creation(tmp);
                t++;
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

    public SudokuRow getRow(int y) {
        return row.get(y);
    }

    public SudokuColumn getColumn(int x) {
        return column.get(x);
    }
    //y
    // 0 1 2
    //x// 3 4 5
    // 6 7 8

    public SudokuBox getBox(int x, int y) {
        if (x == 1) {
            y = y + 3;
        }
        if (x == 2) {
            y = y + 6;
        }
        return box.get(y);
    }

    public String showBoard() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s.append(get(i, j)).append("\t");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private boolean checkBoard() {
        if (checkRow() && checkColumn() && checkBox()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkRow() {
        for (int i = 0; i < 9; i++) {
            if (!row.get(i).verify()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkColumn() {
        for (int i = 0; i < 9; i++) {
            if (!column.get(i).verify()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkBox() {
        for (int i = 0; i < 9; i++) {
            if (!box.get(i).verify()) {
                return false;
            }
        }
        return true;
    }

}