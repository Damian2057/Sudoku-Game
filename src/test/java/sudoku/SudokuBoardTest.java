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

    @Test
    public void validBoard() {
        SudokuBoard sudoku3 = new SudokuBoard();
        sudoku3.solveGame();

        boolean flag = false;

        for (int sprawdz = 1; sprawdz <= 9; sprawdz++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudoku3.getBoard(i,j) == sprawdz) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }

        assertEquals(flag, true);
        //petla sprawdza, czy gdziekolwiek w kazdym wierszu brakuje jakiejkolwiek liczby
        //z zakresu 1-9, w przypadku powtorzenia sie jakiejs liczby algorytm i tak zwroci
        //false, gdyz jednoczesnie brakowaloby jakiejs innej

        boolean flag2 = false;

        for (int sprawdz = 1; sprawdz <= 9; sprawdz++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudoku3.getBoard(j,i) == sprawdz) {
                        flag2 = true;
                        break;
                    }
                }
                if (!flag2) {
                    break;
                }
            }
            if(!flag2) {
                break;
            }
        }

        assertEquals(flag2, true);
        //petla sprawdza, czy gdziekolwiek w kazdej kolumnie brakuje jakiejkolwiek liczby
        //z zakresu 1-9, w przypadku powtorzenia sie jakiejs liczby algorytm i tak zwroci
        //false, gdyz jednoczesnie brakowaloby jakiejs innej

        boolean flag3 = false;
        for (int sprawdz = 1; sprawdz <= 9; sprawdz++) {
            for (int row = 0; row <= 6; row += 3) {
                for (int col = 0; col <= 6; col += 3) {
                    for (int i = row; i <= row + 2; i++) {
                        for (int j = col; j <= col + 2; j++) {
                            if (sudoku3.getBoard(i,j) == sprawdz) {
                                flag3 = true;
                                break;
                            }
                        }
                    }
                    if (!flag3) {
                        break;
                    }
                }
                if (!flag3) {
                    break;
                }
            }
        }

        assertEquals(flag, true);
        //petla sprawdza, czy gdziekolwiek w kazdym kwadracie 3x3 brakuje jakiejkolwiek
        //liczby z zakresu 1-9, w przypadku powtorzenia sie jakiejs liczby algorytm
        //i tak zwroci false, gdyz jednoczesnie brakowaloby jakiejs innej
    }

    @Test
    public void uniqueBoard() {
        SudokuBoard sudokuA = new SudokuBoard();
        SudokuBoard sudokuB = new SudokuBoard();
        sudokuA.solveGame();
        sudokuB.solveGame();

        int licznik = 0;
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudokuA.getBoard(i,j) == sudokuB.getBoard(i,j)) {
                    licznik++;
                }
            }
        }
        assertTrue(licznik < 81);

        //petla sprawdza powtorzenia liczb w odpowiednich miejscach, zliczajac je
        //w zmiennej licznik. Jesli liczba powtorzen jest mniejsza niz wielkosc
        //tablicy, oznacza to ze obie tablice sa unikalne.
    }

}