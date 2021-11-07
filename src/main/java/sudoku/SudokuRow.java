package sudoku;

public class SudokuRow extends SudokuElement {

    public SudokuRow(SudokuField[] value) {
        for (int i = 0; i < 9; i++) {
            fields.set(i, value[i]);
        }
    }
}
