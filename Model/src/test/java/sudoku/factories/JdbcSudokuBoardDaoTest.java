package sudoku.factories;

import org.apache.commons.io.FileUtils;
import org.apache.derby.iapi.services.io.FileUtil;
import org.junit.jupiter.api.Test;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.SudokuField;
import sudoku.exceptions.jdbc.SyntaxJdbcDaoException;
import sudoku.exceptions.jdbc.WrittingJdbcDaoException;
import sudoku.exceptions.jdbc.WrongNameJdbcException;

import java.io.File;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

    @Test
    void readTest() {
        try (var file = SudokuBoardDaoFactory.getJdbcDao("test", "jdbc:derby:testowa_baza;create=true");
             var file2 = SudokuBoardDaoFactory.getJdbcDao(null, "jdbc:derby:testowa_baza;create=true");
             var file3 = SudokuBoardDaoFactory.getJdbcDao(null, "jdbc:derby:testowa_baza;create=true");
             var file4 = SudokuBoardDaoFactory.getJdbcDao("null", "jdbc:derby:testowa_baza;create=true")) {
            SudokuBoard sudoku1 = new SudokuBoard(new BacktrackingSudokuSolver());
            sudoku1.solveGame();
            SudokuBoard sudoku2 = new SudokuBoard(new BacktrackingSudokuSolver());

            assertDoesNotThrow( ()->file.write(sudoku1));
            sudoku2 = file.read();
            assertNotSame(sudoku1,sudoku2);
            sudoku2.set(1,1,9);
            sudoku2.set(1,2,9);
            assertNotEquals(sudoku1, sudoku2); //assertsame

            SudokuBoard sudoku3 = new SudokuBoard(new BacktrackingSudokuSolver());

            assertThrows(SyntaxJdbcDaoException.class, ()->file2.write(sudoku3));

            SudokuBoard d = file4.read();
            assertDoesNotThrow( ()->file4.read());
            assertDoesNotThrow(()->file3.close());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void supertestTest() {
        try (var file = SudokuBoardDaoFactory.getJdbcDao("test", "jdbc:derby:testowa_baza;create=true")){
            SudokuBoard sudoku1 = new SudokuBoard(new BacktrackingSudokuSolver());
            sudoku1.solveGame();
            SudokuBoard sudoku2 = new SudokuBoard(new BacktrackingSudokuSolver());


            assertDoesNotThrow(()->file.getAllBoardsInDataBase());
            SudokuField ss = new SudokuField();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}