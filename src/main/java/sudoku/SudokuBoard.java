package sudoku;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard implements PropertyChangeListener {

    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver sudokusolver;
    private List<SudokuRow> rows = Arrays.asList(new SudokuRow[9]);
    private List<SudokuColumn> columns = Arrays.asList(new SudokuColumn[9]);
    private List<SudokuBox> boxes = Arrays.asList(new SudokuBox[9]);


    public SudokuBoard(SudokuSolver sudokusolver) {

        this.sudokusolver = sudokusolver;

        //utworzenie strutury board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
                board[i][j].addPropertyChangeListener(this);
            }
        }

        SudokuField[] tmp = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = board[i][j];
            }
            rows.set(i, new SudokuRow(tmp));
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = board[j][i];
            }
            columns.set(i, new SudokuColumn(tmp));
        }
        int index = 0;
        int pom = 0;
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int x = i; x < 3 + i; x++) {
                    for (int y = j; y < 3 + j; y++) {
                        tmp[index] = board[x][y];
                        index++;
                    }
                }
                index = 0;
                boxes.set(pom, new SudokuBox(tmp));
                pom++;
            }
        }
    }

    public void solveGame() {
        sudokusolver.solve(this);
    }

    public SudokuField getSudokuField(int i, int j) {
        return board[i][j];
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public int get(int x, int y) {

        return board[x][y].getFieldValue();
    }

    public SudokuRow getRow(int y) {
        List<SudokuRow> copyr = List.copyOf(rows);
        return copyr.get(y);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuColumn> copyC = List.copyOf(columns);
        return copyC.get(x);
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
        List<SudokuBox> copyB = List.copyOf(boxes);
        return copyB.get(y);
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
        //nasz if ma jedynie checkRow, gdyz jezeli wadliwa bedzie komorka
        //to jednoczesnie zepsuje ona kolumne, wiersz i boxa.
        return checkRow();
    }

    public boolean checkRow() {
        for (int i = 0; i < 9; i++) {
            if (!rows.get(i).verify()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkColumn() {
        for (int i = 0; i < 9; i++) {
            if (!columns.get(i).verify()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkBox() {
        for (int i = 0; i < 9; i++) {
            if (!boxes.get(i).verify()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        checkBoard();
        //cos w checkstylu nie dziala
    }
}