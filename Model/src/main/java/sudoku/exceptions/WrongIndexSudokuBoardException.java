package sudoku.exceptions;

public class WrongIndexSudokuBoardException extends SudokuBoardException {
    public WrongIndexSudokuBoardException() {
        super("invalidIndex");
    }
}
