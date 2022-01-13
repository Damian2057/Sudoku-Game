package sudoku.exceptions.model;

public class WrongIndexSudokuBoardException extends SudokuBoardException {
    public WrongIndexSudokuBoardException() {
        super("invalidIndex");
    }
}
