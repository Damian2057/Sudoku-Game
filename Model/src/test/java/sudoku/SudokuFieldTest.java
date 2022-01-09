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

    @Test
    void compareToTest() {
        SudokuField f1 = new SudokuField();
        SudokuField f2 = new SudokuField();

        assertThrows(NullPointerException.class, () -> f1.compareTo(null));
        assertDoesNotThrow(() -> f2.compareTo(f1));

        assertTrue(f2.compareTo(f2) == 0 && f2.equals(f2) == true);
        assertTrue(f1.compareTo(f1) == 0 && f1.equals(f1) == true);
        assertTrue(f1.compareTo(f2) == 0 && f1.equals(f2) == true);

        f2.setFieldValue(5);

        assertTrue(f2.compareTo(f1) != 0 && f2.equals(f1) == false);
    }

    @Test
    void cloneTest() {
        SudokuField f1 = new SudokuField();

        SudokuField c1 = f1.clone();
        assertEquals(f1, c1);

        c1.setFieldValue(5);
        assertNotEquals(f1, c1);
    }

    @Test
    void isEditableTest() {
        SudokuField f1 = new SudokuField();
        assertFalse(f1.isEditable());
        f1.setEditable(true);
        assertTrue(f1.isEditable());
    }
}