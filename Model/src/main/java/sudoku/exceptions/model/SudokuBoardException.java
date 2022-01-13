package sudoku.exceptions.model;

import sudoku.exceptions.LocalizedRunTimeException;

public abstract class SudokuBoardException extends LocalizedRunTimeException {
    public SudokuBoardException(String message) {
        super(message);
    }
}
