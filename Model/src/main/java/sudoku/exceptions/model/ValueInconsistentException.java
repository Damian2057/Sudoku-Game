package sudoku.exceptions.model;

public class ValueInconsistentException extends SudokuFieldException {
    public ValueInconsistentException() {
        super("invalidValueofIndex");
    }
}
