package sudoku;

import org.junit.jupiter.api.Test;
import sudoku.exceptions.model.ValueInconsistentException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMethodTest {

    @Test
    public void hashCodeBacktrackingSudokuSolverTest() {
        BacktrackingSudokuSolver s1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver s2 = new BacktrackingSudokuSolver();

        assertEquals(s1, s1);
        assertEquals(s1.hashCode(), s1.hashCode());
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());

        s1.getElement();
        assertNotEquals(s1, s2);
        assertNotEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    public void hashCodeSudokuFieldTest() {
        SudokuField s1 = new SudokuField();
        SudokuField s2 = new SudokuField();

        assertEquals(s1, s1);
        assertEquals(s1.hashCode(), s1.hashCode());
        assertEquals(s1,s2);
        s2.setFieldValue(5);
        assertNotEquals(s1, s2);
        assertNotEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    public void hashCodeSudokuBoardTest() {
        BacktrackingSudokuSolver b1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver b2 = new BacktrackingSudokuSolver();
        SudokuBoard s1 = new SudokuBoard(b1);
        SudokuBoard s2 = new SudokuBoard(b2);

        assertEquals(s1, s1);
        assertEquals(s1.hashCode(), s1.hashCode());
        assertEquals(s1,s2);
        s2.set(0,0,0);
        assertEquals(s1.hashCode(),s2.hashCode());
    }

    @Test
    public void hashCodeSudokuElementTest() {
        SudokuField[] t = new SudokuField[9];
        SudokuField[] t2 = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            t[i] = new SudokuField();
            t2[i] = new SudokuField();
            t[i].setFieldValue(i);
            t2[i].setFieldValue(9-i);
        }

        //test dla row
        SudokuElement row1 = new SudokuRow(t);
        SudokuElement row2 = new SudokuRow(t);
        SudokuElement row3 = new SudokuRow(t2);
        assertEquals(row1, row1);
        assertEquals(row1.hashCode(), row1.hashCode());
        assertEquals(row1, row2);
        assertEquals(row1.hashCode(), row2.hashCode());
        assertEquals(row3, row3);
        assertEquals(row3.hashCode(), row3.hashCode());
        assertNotEquals(row1, row3);
        assertNotEquals(row1.hashCode(), row3.hashCode());

        //test dla column
        SudokuElement column1 = new SudokuColumn(t);
        SudokuElement column2 = new SudokuColumn(t);
        SudokuElement column3 = new SudokuColumn(t2);
        assertEquals(column1, column1);
        assertEquals(column1.hashCode(), column1.hashCode());
        assertEquals(column1, column2);
        assertEquals(column1.hashCode(), column2.hashCode());
        assertEquals(column3, column3);
        assertEquals(column3.hashCode(), column3.hashCode());
        assertNotEquals(column1, column3);
        assertNotEquals(column1.hashCode(), column3.hashCode());

        //test dla box
        SudokuElement box1 = new SudokuBox(t);
        SudokuElement box2 = new SudokuBox(t);
        SudokuElement box3 = new SudokuBox(t2);
        assertEquals(box1, box1);
        assertEquals(box1.hashCode(), box1.hashCode());
        assertEquals(box1, box2);
        assertEquals(box1.hashCode(), box2.hashCode());
        assertEquals(box3, box3);
        assertEquals(box3.hashCode(), box3.hashCode());
        assertNotEquals(box1, box3);
        assertNotEquals(box1.hashCode(), box3.hashCode());

    }

    @Test
    public void toStringBacktrackingSudokuSolverTest() {
        BacktrackingSudokuSolver b1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver b2 = new BacktrackingSudokuSolver();
        b2.getElement();

        assertEquals(b1.toString(), "BacktrackingSudokuSolver[randList=[]]");
        assertNotEquals(b1.toString(), b2.toString());
    }

    @Test
    public void toStringSudokuFieldTest() {
        SudokuField s1 = new SudokuField();
        SudokuField s2 = new SudokuField();

        assertNotEquals(s1.toString(), s2.toString());
        assertEquals(s1.toString(), s1.toString());
    }

    @Test
    public void toStringSudokuBoardTest() {
        BacktrackingSudokuSolver b1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver b2 = new BacktrackingSudokuSolver();
        SudokuBoard s1 = new SudokuBoard(b1);
        SudokuBoard s2 = new SudokuBoard(b2);

        assertEquals(s1.toString(), s1.toString());
        assertNotEquals(s1.toString(), s2.toString());
    }

    @Test
    public void toStringSudokuElementTest() {
        SudokuField[] t = new SudokuField[9];
        SudokuField[] t2 = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            t[i] = new SudokuField();
            t2[i] = new SudokuField();
            t[i].setFieldValue(i);
            t2[i].setFieldValue(9-i);
        }

        //test dla row
        SudokuElement row1 = new SudokuRow(t);
        SudokuElement row2 = new SudokuRow(t);
        SudokuElement row3 = new SudokuRow(t2);

        assertEquals(row1.toString(), row1.toString());
        assertEquals(row1.toString(), row2.toString());
        assertNotEquals(row1.toString(), row3.toString());

        //test dla column
        SudokuElement column1 = new SudokuColumn(t);
        SudokuElement column2 = new SudokuColumn(t);
        SudokuElement column3 = new SudokuColumn(t2);

        assertEquals(column1.toString(), column1.toString());
        assertEquals(column1.toString(), column2.toString());
        assertNotEquals(column1.toString(), column3.toString());

        //test dla box
        SudokuElement box1 = new SudokuBox(t);
        SudokuElement box2 = new SudokuBox(t);
        SudokuElement box3 = new SudokuBox(t2);

        assertEquals(box1.toString(), box1.toString());
        assertEquals(box1.toString(), box2.toString());
        assertNotEquals(box1.toString(), box3.toString());
    }

    @Test
    public void equalsBacktrackingSudokuSolverTest() {
        BacktrackingSudokuSolver s1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver s2 = new BacktrackingSudokuSolver();
        SudokuSolver s3 = new BacktrackingSudokuSolver();
        SudokuBoard b1 = new SudokuBoard(s2);
        s2.getElement();

        assertEquals(s1, s1);
        assertNotEquals(null, s1);
        assertFalse(s1.equals(null));
        assertNotEquals(s1, s2);
        assertNotEquals(s2, s1);
        assertEquals(s3, s1);
        assertEquals(s1, s3);
        assertNotEquals(s1, s3.getClass());
        assertNotEquals(s1, b1);
    }

    @Test
    public void equalsSudokuFieldTest() {
        SudokuField s1 = new SudokuField();
        SudokuField s2 = new SudokuField();
        BacktrackingSudokuSolver b1 = new BacktrackingSudokuSolver();

        assertEquals(s1, s1);
        assertEquals(s2, s2);
        assertEquals(s1, s2);
        s2.setFieldValue(5);
        assertNotEquals(s2, s1);
        assertNotEquals(null, s1);
        assertFalse(s1.equals(null));
        assertNotEquals(s1, b1);

    }

    @Test
    public void equalsSudokuElementTest() {
        SudokuField[] t = new SudokuField[9];
        SudokuField[] t2 = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            t[i] = new SudokuField();
            t2[i] = new SudokuField();
            t[i].setFieldValue(i);
            t2[i].setFieldValue(9-i);
        }
        BacktrackingSudokuSolver b1 = new BacktrackingSudokuSolver();

        //test dla row
        SudokuElement row1 = new SudokuRow(t);
        SudokuElement row2 = new SudokuRow(t);
        SudokuElement row3 = new SudokuRow(t2);


        assertEquals(row1, row1);
        assertEquals(row1, row2);
        assertEquals(row2, row1);
        assertNotEquals(row3, row2);
        assertNotEquals(row1, row3);
        assertFalse(row1.equals(null));
        assertNotEquals(null, row3);
        assertNotEquals(row3, b1);

        //test dla column
        SudokuElement column1 = new SudokuColumn(t);
        SudokuElement column2 = new SudokuColumn(t);
        SudokuElement column3 = new SudokuColumn(t2);


        assertEquals(column1, column1);
        assertEquals(column1, column2);
        assertEquals(column2, column1);
        assertNotEquals(column3, column2);
        assertNotEquals(column1, column3);
        assertFalse(column1.equals(null));
        assertNotEquals(null, column3);
        assertNotEquals(column3, b1);

        //test dla box
        SudokuElement box1 = new SudokuBox(t);
        SudokuElement box2 = new SudokuBox(t);
        SudokuElement box3 = new SudokuBox(t2);


        assertEquals(box1, box1);
        assertEquals(box1, box2);
        assertEquals(box2, box1);
        assertNotEquals(box3, box2);
        assertNotEquals(box1, box3);
        assertFalse(box1.equals(null));
        assertNotEquals(null, box3);
        assertNotEquals(box3, b1);

        //mieszane asercje
        SudokuField s = new SudokuField();
        assertThrows(ValueInconsistentException.class, ()->s.setFieldValue(10));
        column1.fields.set(5, s);
        assertNotEquals(row1, column1);
        assertNotEquals(column1, box1);
        SudokuField b = new SudokuField();
        assertThrows(ValueInconsistentException.class, ()->b.setFieldValue(11));
        box1.fields.set(5,b);
        assertNotEquals(row1, box1);
    }

    @Test
    public void equalsSudokuBoardTest() {
        BacktrackingSudokuSolver b1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver b2 = new BacktrackingSudokuSolver();
        SudokuBoard s1 = new SudokuBoard(b1);
        SudokuBoard s2 = new SudokuBoard(b2);

        assertEquals(s1, s1);
        assertEquals(s2, s2);
        assertThrows(ValueInconsistentException.class, ()->s2.set(0,0,10));
        assertEquals(s1, s2);
        assertNotEquals(null, s1);
        assertFalse(s1.equals(null));
        assertNotEquals(s2, b2);
        assertNotEquals(s2, b1);


    }

    @Test
    public void cohesionTest() {
        //test dla BacktrackingSudokuSolver
        BacktrackingSudokuSolver b1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver b2 = new BacktrackingSudokuSolver();
        assertTrue(b1.equals(b2) && (b1.hashCode()==b2.hashCode()));

        //test dla SudokuBoard
        SudokuBoard s1 = new SudokuBoard(b1);
        SudokuBoard s2 = new SudokuBoard(b2);
        assertTrue(s1.equals(s1) && (s1.hashCode()==s1.hashCode()));

        //test dla SudokuField
        SudokuField f1 = new SudokuField();
        SudokuField f2 = new SudokuField();
        assertTrue(f1.equals(f1) && (f1.hashCode()==f1.hashCode()));

        //test dla SudokuElement
        SudokuField[] t = new SudokuField[9];
        SudokuField[] t2 = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            t[i] = new SudokuField();
            t2[i] = new SudokuField();
            t[i].setFieldValue(i);
            t2[i].setFieldValue(9-i);
        }

        SudokuElement r1 = new SudokuRow(t);
        SudokuElement r2 = new SudokuRow(t);
        SudokuElement r3 = new SudokuRow(t2);

        assertTrue(r1.equals(r2) && (r1.hashCode()==r2.hashCode()));
    }

    @Test
    void cloneTest() {
        SudokuField[] t = new SudokuField[9];

        for(int i = 0; i < 9; i++) {
            t[i] = new SudokuField();
            t[i].setFieldValue(i);
        }

        SudokuElement row1 = new SudokuRow(t);

        SudokuElement c1 = row1.clone();

        assertEquals(row1, c1);

        assertThrows(ValueInconsistentException.class, ()->c1.fields.get(0).setFieldValue(10));

        assertEquals(row1, c1);
    }

}
