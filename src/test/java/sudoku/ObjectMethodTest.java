package sudoku;

import org.junit.jupiter.api.Test;

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

        s2.getElement();
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





}
