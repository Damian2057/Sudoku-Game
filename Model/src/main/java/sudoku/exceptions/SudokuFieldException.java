package sudoku.exceptions;

public abstract class SudokuFieldException extends LocalizedRunTimeException {

    SudokuFieldException(String message) {
        super(message);
    }
}