package sudoku.exceptions;

public class ValueInconsistentException extends SudokuFieldException{
    public ValueInconsistentException() {
        super("invalidValueofIndex");
    }
}
