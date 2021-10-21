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

    @Test
    public void solveGame() {
        SudokuBoard sudokusG = new SudokuBoard();
        sudokusG.solveGame();

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                assertNotEquals(sudokusG.getBoard(i, j), 0);
            }
        }
    }

    @Test
    public void check() {

        sudoku.solveGame();
        for(int i = 1; i <= 9; i++) {
            assertEquals(sudoku.check(1, 1, i), false);
        } //petla, w ktorej sprawdzamy, czy liczby 1-9 wystepuja juz w kolumnie, wierszu i kwadracie
        assertEquals(sudoku.check( 1, 1, 0), true);
        //liczba 0 jest spoza zakresu 1-9, wiec nie powinna nigdy wystapic w tablicy
    }


}