package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementTest {

    @Test
    void creation() {
        SudokuField[] tablica = new SudokuField[9];
        SudokuElement element = new SudokuElement();
        for(int i = 0; i < 9; i++) {
            tablica[i] = new SudokuField();
        }

        for (int i = 0; i < 9; i++) {
            tablica[i].setFieldValue(i+1);
        }

        element.creation(tablica);

        for (int i = 0; i < 9; i++) {
            assertEquals(tablica[i].getFieldValue(), element.getFields(i));
        }
    }

    @Test
    void verify() {
        SudokuField[] tablica = new SudokuField[9];
        SudokuElement element = new SudokuElement();
        for(int i = 0; i < 9; i++) {
            tablica[i] = new SudokuField();
        }

        for (int i = 0; i < 9; i++) {
            tablica[i].setFieldValue(i+1);
        }
        element.creation(tablica);

        assertTrue(element.verify());

        tablica[0].setFieldValue(0);
        element.creation(tablica);

        assertFalse(element.verify());
    }

    @Test
    void getFields() {
        SudokuField[] tablica = new SudokuField[9];
        SudokuElement element = new SudokuElement();
        for(int i = 0; i < 9; i++) {
            tablica[i] = new SudokuField();
        }

        for (int i = 0; i < 9; i++) {
            tablica[i].setFieldValue(i+1);
        }

        element.creation(tablica);

        for (int i = 0; i < 9; i++) {
            assertEquals(tablica[i].getFieldValue(), element.getFields(i));
        }
    }
}