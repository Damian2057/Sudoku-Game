package sudoku;

public class SudokuApp {
    public static void main(String[] args) {

        SudokuBoard sudoku = new SudokuBoard();
        sudoku.fillBoard();
        sudoku.showBoard();
    }
}