package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    SudokuBoard sudoku = new SudokuBoard();
    SudokuBoard sudoku2 = new SudokuBoard();
    int[][] b = new int[9][9];

    @Test
    public void fillBoard() {

        sudoku.fillBoard();
        for(int i = 0; i < 9 ; i++) {
            for(int j = 0; j < 9; j++) {
                assertNotEquals(sudoku.getBoard()[i][j], 0);
            }
        }
    }

    @Test
    public void solveBoard() {

        assertEquals(sudoku.solveBoard(sudoku.getBoard(), 0, 0), true);
        //metoda solveBoard na koncu swojego dzialania zwraca wartosc true
    }

    @Test
    public void check() {

        sudoku.fillBoard();
        for(int i = 1; i <= 9; i++) {
            assertEquals(sudoku.check(sudoku.getBoard(), 1, 1, i), false);
        } //petla, w ktorej sprawdzamy, czy liczby 1-9 wystepuja juz w kolumnie, wierszu i kwadracie
        assertEquals(sudoku.check(sudoku.getBoard(), 1, 1, 0), true);
        //liczba 0 jest spoza zakresu 1-9, wiec nie powinna nigdy wystapic w tablicy
    }

    @Test
    public void getBoard() {

        int[][] a = sudoku.getBoard();
        int[][] b = sudoku.getBoard();
        assertEquals(a, b);
        int[][] c = sudoku2.getBoard();
        assertNotEquals(a, c);
        //sprawdzamy, czy metoda getBoard() wywolana dla tego samego obiektu
        //za kazdym razem zwraca ta sama tablice, a wywolana dla dwoch roznych
        //obiektow dwie rozne tablice
    }

    @Test
    public void showBoard() {

        sudoku2.fillBoard();
        sudoku2.showBoard();
    }

    @Test
    public void randomNum() {
        int liczba = 0;
        for(int i = 0; i < 100; i++) {
            liczba = sudoku2.randomNum(9, 0, sudoku2.getBoard());
            assertTrue(1 <= liczba && liczba <=9 );
        }
        //metoda dla 100 prob sprawdza, czy choc raz wylosowana liczba
        //byla poza przedzialem [1;9]
    }

    @Test
    public void validBoardRow() {
        SudokuBoard sudoku3 = new SudokuBoard();
        sudoku3.fillBoard();

        boolean flag = false;

        for(int sprawdz = 1; sprawdz <= 9; sprawdz++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudoku3.getBoard()[i][j] == sprawdz) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if(!flag) {
                break;
            }
        }

        assertEquals(flag, true);
        //petla sprawdza, czy gdziekolwiek w kazdym wierszu brakuje jakiejkolwiek liczby
        //z zakresu 1-9, w przypadku powtorzenia sie jakiejs liczby algorytm i tak zwroci
        //false, gdyz jednoczesnie brakowaloby jakiejs innej
    }

    @Test
    public void validBoardColumn() {
        SudokuBoard sudoku3 = new SudokuBoard();
        sudoku3.fillBoard();

        boolean flag = false;

        for(int sprawdz = 1; sprawdz <= 9; sprawdz++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudoku3.getBoard()[j][i] == sprawdz) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if(!flag) {
                break;
            }
        }

        assertEquals(flag, true);
        //petla sprawdza, czy gdziekolwiek w kazdej kolumnie brakuje jakiejkolwiek liczby
        //z zakresu 1-9, w przypadku powtorzenia sie jakiejs liczby algorytm i tak zwroci
        //false, gdyz jednoczesnie brakowaloby jakiejs innej
    }

    @Test
    public void validBoardSquare() {
        SudokuBoard sudoku3 = new SudokuBoard();
        sudoku3.fillBoard();

        boolean flag = false;
        for (int sprawdz = 1; sprawdz <= 9; sprawdz++) {
            for (int row = 0; row <= 6; row += 3) {
                for (int col = 0; col <= 6; col += 3) {
                    for (int i = row; i <= row + 2; i++) {
                        for (int j = col; j <= col + 2; j++) {
                            if (sudoku3.getBoard()[i][j] == sprawdz) {
                                flag = true;
                                break;
                            }
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
        }

        assertEquals(flag, true);
        //petla sprawdza, czy gdziekolwiek w kazdym kwadracie 3x3 brakuje jakiejkolwiek
        //liczby z zakresu 1-9, w przypadku powtorzenia sie jakiejs liczby algorytm
        //i tak zwroci false, gdyz jednoczesnie brakowaloby jakiejs innej
    }

}