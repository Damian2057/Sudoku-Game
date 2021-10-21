package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuBoardTest {

    SudokuBoard sudoku = new SudokuBoard();

    @Test
    public void setBoard() {

        SudokuBoard sudokuS = new SudokuBoard();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sudokuS.setBoard(i,j,5);
                assertEquals(sudokuS.getBoard(i,j), 5);
            }
        }
    }

    @Test
    public void showBoard() {
        sudoku.solveGame();
        String s = "";
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                s = s + sudoku.getBoard(i, j)+ "\t";
            }
            s+="\n";
        }
        assertEquals(s,sudoku.showBoard());
    }

    @Test
    public void getBoard() {

        SudokuBoard sudokuG = new SudokuBoard();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(sudokuG.getBoard(0,0), 0);

            }
        }
        sudokuG.solveGame();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertTrue(sudokuG.getBoard(0,0)>=1 && sudokuG.getBoard(0,0)<=9);
            }
        }
    }






}