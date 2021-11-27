package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTest {
    @Test
    public void WriteTest() {
        SudokuBoardDaoFactory<SudokuBoard> factory = new SudokuBoardDaoFactory<>();
        try (Dao<SudokuBoard> file = factory.getFileDao("test.txt")){
            SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
            sudoku.solveGame();

            file.write(sudoku);
            SudokuBoard sudoku2 = file.read();

            assertEquals(sudoku, sudoku2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
