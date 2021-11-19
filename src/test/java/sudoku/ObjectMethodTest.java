package sudoku;

import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        assertNotEquals(s1, s2);
        assertNotEquals(s1.hashCode(), s2.hashCode());
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


    }

    @Test
    public void toStringBacktrackingSudokuSolverTest() {
        BacktrackingSudokuSolver b1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver b2 = new BacktrackingSudokuSolver();
        b2.getElement();

        assertEquals(b1.toString(), "BacktrackingSudokuSolver[randList=[]]");
        assertNotEquals(b1.toString(), b2.toString());
    }

//    @Test
//    public void toStringSudokuFieldTest() {
//        SudokuField s1 = new SudokuField();
//        SudokuField s2 = new SudokuField();
//    }

}
