package sudoku.exceptions.model;

import sudoku.exceptions.model.SudokuBoardException;

public class LevelLogicalException extends SudokuBoardException {
    public LevelLogicalException() {
        super("logicallyLevel");
    }
}
