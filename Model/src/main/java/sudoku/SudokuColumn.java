package sudoku;

public class SudokuColumn extends SudokuElement {

    public SudokuColumn(SudokuField[] value) {
        for (int i = 0; i < 9; i++) {
            fields.set(i, value[i]);
        }
    }
}
