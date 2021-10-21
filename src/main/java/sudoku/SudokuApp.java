package sudoku;

public class SudokuApp {
    public static void main(String[] args) {

        SudokuBoard sudoku = new SudokuBoard();
        sudoku.solveGame();
        System.out.println(sudoku.showBoard());
        sudoku.getBoard(0,0);
        sudoku.setBoard(0,0,1);
    }
}