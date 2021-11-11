package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SudokuRowTest {

    @Test
    void verify() {
        SudokuField[] tablica = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            tablica[i] = new SudokuField();
            tablica[i].setFieldValue(i+1);
        }

        SudokuElement element = new SudokuRow(tablica);
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

        SudokuElement element = new SudokuRow(tablica);

        for (int i = 0; i < 9; i++) {
            assertEquals(tablica[i].getFieldValue(), element.getFieldValue(i));
        }
    }

    @Test
    void addSetRowTest() {
        SudokuField[] tablica = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            tablica[i] = new SudokuField();
            tablica[i].setFieldValue(i+1);
        }

        SudokuElement element = new SudokuRow(tablica);

        assertEquals(element.fields.size(), 9);

        assertDoesNotThrow(()->element.fields.set(5, new SudokuField()));

        assertThrows(UnsupportedOperationException.class, ()->element.fields.add(5, new SudokuField()));
    }
}