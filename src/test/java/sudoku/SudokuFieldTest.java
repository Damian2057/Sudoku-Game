package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    void getFieldValue() {
        SudokuField s = new SudokuField();
        assertEquals(s.getFieldValue(), 0);
    }

    @Test
    void setFieldValue() {
        SudokuField s = new SudokuField();
        s.setFieldValue(5);
        assertEquals(s.getFieldValue(), 5);
    }

    @Test
    void addObserver() {
        SudokuField b = new SudokuField();
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuS = new SudokuBoard(s);
        b.addObserver(sudokuS);
        assertTrue(b.deleteObserver(sudokuS));
        assertFalse(b.deleteObserver(sudokuS));
    }

    @Test
    void deleteObserver() {
        SudokuField b = new SudokuField();
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard sudokuS = new SudokuBoard(s);

        SudokuBoard sudokuB = new SudokuBoard(s);

        b.addObserver(sudokuS);
        assertTrue(b.deleteObserver(sudokuS));
        assertFalse(b.deleteObserver(sudokuS));

        assertFalse(b.deleteObserver(sudokuB));
    }

    @Test
    void updateObserver() {

    }
}