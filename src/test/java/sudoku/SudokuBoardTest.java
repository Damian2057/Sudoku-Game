package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuBoardTest {
    SudokuSolver s = new BacktrackingSudokuSolver();
    SudokuBoard sudoku = new SudokuBoard(s);

    @Test
    public void setBoard() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuS = new SudokuBoard(s);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sudokuS.set(i,j,5);
                assertEquals(sudokuS.get(i,j), 5);
            }
        }
    }

    @Test
    public void showBoard() {
        sudoku.solveGame();
        String s = "";
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                s = s + sudoku.get(i, j)+ "\t";
            }
            s+="\n";
        }
        assertEquals(s,sudoku.showBoard());
    }

    @Test
    public void getBoard() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuG = new SudokuBoard(s);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertEquals(sudokuG.get(0,0), 0);

            }
        }
        sudokuG.solveGame();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                assertTrue(sudokuG.get(0,0)>=1 && sudokuG.get(0,0)<=9);
            }
        }
    }

    @Test
    public void solveGame() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokusG = new SudokuBoard(s);
        sudokusG.solveGame();

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                assertNotEquals(sudokusG.get(i, j), 0);
            }
        }
    }

    @Test
    public void check() {

        sudoku.solveGame();
        for(int i = 1; i <= 9; i++) {
            assertEquals(sudoku.checkBoard(1, 1, i), false);
        } //petla, w ktorej sprawdzamy, czy liczby 1-9 wystepuja juz w kolumnie, wierszu i kwadracie
        assertEquals(sudoku.checkBoard( 1, 1, 0), true);
        //liczba 0 jest spoza zakresu 1-9, wiec nie powinna nigdy wystapic w tablicy
    }

    @Test
    public void validBoard() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudoku3 = new SudokuBoard(s);
        sudoku3.solveGame();

        boolean flag = false;

        for (int sprawdz = 1; sprawdz <= 9; sprawdz++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudoku3.get(i,j) == sprawdz) {
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
                    if (sudoku3.get(j,i) == sprawdz) {
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
                            if (sudoku3.get(i,j) == sprawdz) {
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
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuSolver d = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        SudokuBoard sudokuB = new SudokuBoard(d);
        sudokuA.solveGame();
        sudokuB.solveGame();

        int licznik = 0;
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(sudokuA.get(i,j) == sudokuB.get(i,j)) {
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