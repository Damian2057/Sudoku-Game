package sudoku;

public class SudokuBox extends SudokuElement {

    public SudokuBox(SudokuField[] value) {
        for (int i = 0; i < 9; i++) {
            fields.set(i, value[i]);
        }
    }
}
