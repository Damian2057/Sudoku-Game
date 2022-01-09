package sudoku.exceptions;

public abstract class SudokuBoardException extends LocalizedRunTimeException {
    public SudokuBoardException(String message) {
        super(message);
    }
}
