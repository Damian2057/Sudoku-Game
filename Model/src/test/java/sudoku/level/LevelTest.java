package sudoku.level;

import org.junit.jupiter.api.Test;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.exceptions.model.LevelLogicalException;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {

    @Test
    public void levelTest() {
        Level tooez = new VeryEasy();
        Level ez = new Easy();
        Level med = new Medium();
        Level hard = new Hard();

        assertEquals(tooez.getNumberOfFieldsToRemove(), 1);
        assertEquals(ez.getNumberOfFieldsToRemove(), 40);
        assertEquals(med.getNumberOfFieldsToRemove(), 50);
        assertEquals(hard.getNumberOfFieldsToRemove(), 55);

        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        s1.solveGame();
        hard.removeFields(s1);

        int index = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (s1.get(i, j) == 0) {
                    index++;
                }
            }
        }

        assertEquals(index, 55);

        assertEquals(tooez.toString(), "VeryEasy");
        assertEquals(ez.toString(), "Easy");
        assertEquals(med.toString(), "Medium");
        assertEquals(hard.toString(), "Hard");
    }

    @Test
    public void czyJuzUsunieteTest() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());

        Level ez = new Easy();

        s1.solveGame();
        assertTrue(ez.removeFields(s1));
        assertThrows(LevelLogicalException.class, ()->ez.removeFields(s1));

    }
}
