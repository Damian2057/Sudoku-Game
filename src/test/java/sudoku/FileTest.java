package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTest {
    @Test
    public void WriteTest() throws IOException, ClassNotFoundException {
        try {
            SudokuBoardDaoFactory<SudokuBoard> factory = new SudokuBoardDaoFactory<>();
            Dao<SudokuBoard> file = factory.getFileDao("test.txt");
            SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
            sudoku.solveGame();
            System.out.println(sudoku.showBoard());
            file.write(sudoku);
            SudokuBoard sudoku2 = file.read();
            System.out.println(sudoku2.showBoard());

            //assertEquals(sudoku, sudoku2);
        } catch (IOException e) {
            e.printStackTrace();
    }
}
}
