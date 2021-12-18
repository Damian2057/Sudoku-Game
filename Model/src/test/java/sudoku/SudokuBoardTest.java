package sudoku;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s = s + sudoku.get(i, j) + "\t";
            }
            s += "\n";
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

    @Test
    public void checkBoard() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        SudokuSolver d = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(d);
        sudokuA.solveGame();
        Method checkBoardMethod = SudokuBoard.class.getDeclaredMethod("checkBoard");
        checkBoardMethod.setAccessible(true);
        assertTrue((Boolean) checkBoardMethod.invoke(sudokuA));
        sudokuA.set(0,0,1);
        sudokuA.set(1,1,1);
        assertFalse((Boolean) checkBoardMethod.invoke(sudokuA));

    }

    @Test
    public void checkRow() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        sudokuA.solveGame();

        assertTrue(sudokuA.checkRow());
        sudokuA.set(0,0,sudokuA.get(0,1));
        assertFalse(sudokuA.checkRow());

    }

    @Test
    public void checkColumn() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        sudokuA.solveGame();

        assertTrue(sudokuA.checkColumn());
        sudokuA.set(0,0,sudokuA.get(0,1));
        assertFalse(sudokuA.checkColumn());
    }

    @Test
    public void checkBox() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        sudokuA.solveGame();

        assertTrue(sudokuA.checkBox());
        sudokuA.set(0,0,sudokuA.get(0,1));
        assertFalse(sudokuA.checkBox());
    }

    @Test
    public void getRow() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        sudokuA.solveGame();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(sudokuA.get(i,j), sudokuA.getRow(i).getFieldValue(j));
            }
        }

        int i = sudokuA.getRow(0).getFieldValue(0);
        sudokuA.set(0,0,10);
        assertTrue(i != sudokuA.getRow(0).getFieldValue(0));
    }

    @Test
    public void getColumn() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        sudokuA.solveGame();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(sudokuA.get(j,i), sudokuA.getColumn(i).getFieldValue(j));
            }
        }
    }

    @Test
    public void getBoardTest() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);

        assertEquals(sudokuA.getBoard()[0][0].getFieldValue(), 0);
    }

    @Test
    public void getBox() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        sudokuA.solveGame();


        int index1 = 0, index2 = 0, k = 0;
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int x = i; x < 3 + i; x++) {
                    for (int y = j; y < 3 + j; y++) {
                        assertEquals(sudokuA.get(x, y), sudokuA.getBox(index1, index2).getFieldValue(k));
                        k++;
                    }
                }
                k = 0;
                index2 ++;
            }
            index2 = 0;
            index1 ++;
        }

    }


    @Test
    public void getSudokuField() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        sudokuA.solveGame();
        assertTrue( sudokuA.getSudokuField(1,1) != null);
    }

    @Test
    public void addSetTest() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);

        SudokuField[] tmp = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = new SudokuField();
            }

            assertEquals(sudokuA.getRows().size(), 9);
            assertDoesNotThrow(() -> sudokuA.getRows().set(0, new SudokuRow(tmp)));
            assertThrows(UnsupportedOperationException.class,
                    ()->sudokuA.getRows().add(0, new SudokuRow(tmp)));

            assertEquals(sudokuA.getColumns().size(), 9);
            assertDoesNotThrow(() -> sudokuA.getColumns().set(0, new SudokuColumn(tmp)));
            assertThrows(UnsupportedOperationException.class,
                    ()->sudokuA.getColumns().add(0, new SudokuColumn(tmp)));

            assertEquals(sudokuA.getBoxes().size(), 9);
            assertDoesNotThrow(() -> sudokuA.getBoxes().set(0, new SudokuBox(tmp)));
            assertThrows(UnsupportedOperationException.class,
                    ()->sudokuA.getBoxes().add(0, new SudokuBox(tmp)));
        }
    }

    @Test
    public void toStringTest() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuA = new SudokuBoard(s);
        SudokuBoard sudokuB = new SudokuBoard(s);

        System.out.println(sudokuA.equals(sudokuA));
    }

    @Test
    public void cloneTest() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard s1 = new SudokuBoard(s);

        SudokuBoard c1 = s1.clone();

        assertEquals(c1, s1);
        c1.solveGame();

        assertNotEquals(s1, c1);
    }

    @Test
    public void czyJuzUsunietoTest() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());

        assertFalse(s1.isAlreadyDeleted());

        s1.setAlreadyDeleted(true);

        assertTrue(s1.isAlreadyDeleted());
    }


}