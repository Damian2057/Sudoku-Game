package sudoku.factories;

import org.junit.jupiter.api.Test;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;


import static org.junit.jupiter.api.Assertions.*;

public class FileTest {
    @Test
    public void FileTest() {
        try {
            var factory = SudokuBoardDaoFactory.getFileDao("test.txt");
            SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
            sudoku.solveGame();

            factory.write(sudoku);
            SudokuBoard sudoku2 = factory.read();

            assertEquals(sudoku, sudoku2);

            FileSudokuBoardDao file = new FileSudokuBoardDao("test.txt");
            file.write(sudoku);
            sudoku2 = (SudokuBoard) file.read();

            assertEquals(sudoku, sudoku2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
