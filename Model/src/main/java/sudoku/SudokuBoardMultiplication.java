package sudoku;

public class SudokuBoardMultiplication {
    private SudokuBoard sudokuboard;

    public SudokuBoardMultiplication(SudokuBoard sudokuboard) {
        this.sudokuboard = new SudokuBoard(new BacktrackingSudokuSolver());
    }

    public SudokuBoard createSudoku() {
        sudokuboard.solveGame();
        return sudokuboard.clone();
    }
}
