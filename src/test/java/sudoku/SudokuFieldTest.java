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
}