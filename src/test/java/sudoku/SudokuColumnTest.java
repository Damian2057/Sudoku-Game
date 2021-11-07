package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SudokuColumnTest {

    @Test
    void verify() {
        SudokuField[] tablica = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            tablica[i] = new SudokuField();
            tablica[i].setFieldValue(i+1);
        }

        SudokuElement element = new SudokuColumn(tablica);
        assertTrue(element.verify());

        tablica[1].setFieldValue(1);

        assertFalse(element.verify());
    }
    @Test
    void getFields() {
        SudokuField[] tablica = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            tablica[i] = new SudokuField();
            tablica[i].setFieldValue(i+1);
        }

        SudokuElement element = new SudokuColumn(tablica);

        for (int i = 0; i < 9; i++) {
            assertEquals(tablica[i].getFieldValue(), element.getFields(i));
        }
    }
}