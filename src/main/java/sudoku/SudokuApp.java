package sudoku;

public class SudokuApp {
    public static void main(String[] args) {

        SudokuBoard sudoku = new SudokuBoard();
        sudoku.solveGame();
        System.out.println(sudoku.showBoard());
    }
}