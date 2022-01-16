package sudoku.factories;

import org.junit.jupiter.api.Test;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.SudokuField;
import sudoku.exceptions.dao.FileNotFoundDaoException;


import java.io.File;
import java.io.FileNotFoundException;

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

            var factory2 = SudokuBoardDaoFactory.getFileDao("test.txt");
            File myObj = new File("test.txt");
            myObj.delete();
            assertThrows(FileNotFoundDaoException.class, ()->factory2.read());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
