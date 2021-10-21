package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void solve() {
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        s.solve(board);
        for(int i = 0; i < 9 ; i++) {
            for(int j = 0; j < 9; j++) {
                assertNotEquals(board.getBoard(i,j), 0);
            }
        }
    }

    @Test
    void solveBoard() {
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        assertEquals(s.solveBoard( 0, 0, board), true);
        //metoda solveBoard na koncu swojego dzialania zwraca wartosc true
    }

    @Test
    void randomNum() {
        SudokuBoard board = new SudokuBoard();
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();

        int liczba = 0;
        for(int i = 0; i < 100; i++) {
            liczba = s.randomNum(9, 0, board);
            assertTrue(1 <= liczba && liczba <=9 );
        }
        //metoda dla 100 prob sprawdza, czy choc raz wylosowana liczba
        //byla poza przedzialem [1;9]
    }
}