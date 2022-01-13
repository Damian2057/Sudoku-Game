package sudoku.factories;

import org.junit.jupiter.api.Test;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

import static org.junit.jupiter.api.Assertions.*;

public class FileTest {
    @Test
    public void FileTest() {
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

//    @Test
//    public void FileReadTest() {
//        SudokuBoardDaoFactory<SudokuBoard> factory = new SudokuBoardDaoFactory<>();
//        Dao<SudokuBoard> file = factory.getFileDao("test.txt");
//        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
//        try{
//            file.write(board);
//            SudokuField field = new SudokuField();
//            assertThrows(ClassNotFoundException.class,()-> field = file.read());
//
//        }
//        catch(Exception e) {
//        }
//    }
}
