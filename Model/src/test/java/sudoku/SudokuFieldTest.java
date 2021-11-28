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
    void addPropertyChangeListener() {
        SudokuField s = new SudokuField();
        assertTrue(s.getChanges() != null);
    }

    @Test
    void removePropertyChangeListener() {
        SudokuSolver b = new BacktrackingSudokuSolver();
        SudokuBoard sudo = new SudokuBoard(b);
        sudo.getSudokuField(0,0).removePropertyChangeListener(sudo);
        assertFalse(sudo.getSudokuField(0,0).getChanges() == null);

    }

}