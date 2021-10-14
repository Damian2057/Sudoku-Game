package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    SudokuBoard sudoku = new SudokuBoard();
    int[][] b = new int[9][9];

    @Test
    public void fillBoard() {
        sudoku.fillBoard();
    }

    @Test
    public void solveBoard() {
        sudoku.solveBoard(b,0,0);
    }

    @Test
    public void check() {
        sudoku.check(b,1,1,5);
    }

    @Test
    public void getBoard() {
        sudoku.getBoard();
    }

    @Test
    public void showBoard() {
        sudoku.showBoard();
    }

    @Test
    public void randomNum() {
        sudoku.randomNum(9,5);
    }
}