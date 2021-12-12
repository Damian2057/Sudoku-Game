package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuMultiplicationTest {

    @Test
    public void sudokuBoardMultiplicationTest() {
        SudokuBoardMultiplication factory = new SudokuBoardMultiplication();
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard c1 = factory.createSudoku(s1);

        assertEquals(c1, s1);
        c1.solveGame();
        assertNotEquals(c1, s1);

    }


}
