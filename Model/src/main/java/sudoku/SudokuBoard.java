package sudoku;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;



public class SudokuBoard implements PropertyChangeListener, Serializable, Cloneable {

    private SudokuField[][] board;
    private SudokuSolver sudokusolver;
    private List<SudokuRow> rows = Arrays.asList(new SudokuRow[9]);
    private List<SudokuColumn> columns = Arrays.asList(new SudokuColumn[9]);
    private List<SudokuBox> boxes = Arrays.asList(new SudokuBox[9]);
    private boolean alreadyDeleted = false;

    public boolean isAlreadyDeleted() {
        return alreadyDeleted;
    }

    public void setAlreadyDeleted(boolean alreadyDeleted) {
        this.alreadyDeleted = alreadyDeleted;
    }

    public SudokuBoard(SudokuSolver sudokusolver) {

        this.sudokusolver = sudokusolver;
        this.board = new SudokuField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuField field = new SudokuField();
                field.addPropertyChangeListener(this);
                this.board[i][j] = field;
            }
        }

        SudokuField[] tmp = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = board[j][i];
            }
            rows.set(i, new SudokuRow(tmp));
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = board[i][j];
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof SudokuBoard)) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(this.sudokusolver, that.sudokusolver)
                .append(this.rows, that.rows)
                .append(this.boxes, that.boxes)
                .append(this.columns, that.columns)
                .append(this.board, that.board)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.board)
                .append(this.rows)
                .append(this.columns)
                .append(this.boxes)
                .append(sudokusolver)
                .toHashCode();
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
        return checkRow();
    }

    public boolean checkvalid() {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sum = sum + board[i][j].getFieldValue();
            }
        }
        return sum == 405;
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
    }

    public List<SudokuRow> getRows() {
        return rows;
    }

    public List<SudokuColumn> getColumns() {
        return columns;
    }

    public List<SudokuBox> getBoxes() {
        return boxes;
    }

    @Override
    public SudokuBoard clone() {
        try {
            SudokuBoard clone = (SudokuBoard) super.clone();
            clone.board = new SudokuField[9][9];
            clone.sudokusolver = new BacktrackingSudokuSolver();
            clone.rows = Arrays.asList(new SudokuRow[9]);
            clone.columns = Arrays.asList(new SudokuColumn[9]);
            clone.boxes = Arrays.asList(new SudokuBox[9]);
            SudokuField[] pomR = new SudokuField[9];
            SudokuField[] pomC = new SudokuField[9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    clone.board[i][j] = board[i][j].clone();
                    pomR[j] = board[i][j].clone();
                    pomC[j] = board[j][i].clone();
                }

                clone.rows.set(i, (SudokuRow) getRow(i).clone());
                clone.columns.set(i,(SudokuColumn) getColumn(i).clone());
            }

            int indeks = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    clone.boxes.set(indeks, (SudokuBox) getBox(i,j).clone());
                    indeks++;
                }
            }

            return clone;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public int getNumberOfEditable() {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].isEditable()) {
                    sum++;
                }
            }
        }
        return sum;
    }


}