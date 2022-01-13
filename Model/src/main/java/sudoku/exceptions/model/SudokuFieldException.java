package sudoku.exceptions.model;

import sudoku.exceptions.LocalizedRunTimeException;

public abstract class SudokuFieldException extends LocalizedRunTimeException {

    SudokuFieldException(String message) {
        super(message);
    }
}