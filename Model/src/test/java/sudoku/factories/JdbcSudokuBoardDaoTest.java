package sudoku.factories;

import org.junit.jupiter.api.Test;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.exceptions.jdbc.SyntaxJdbcDaoException;
import sudoku.exceptions.jdbc.WrongNameJdbcException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

//    @Test
//    void readTest() {
//        try {
//            SudokuBoard sudoku1 = new SudokuBoard(new BacktrackingSudokuSolver());
//            sudoku1.solveGame();
//            SudokuBoard sudoku2 = new SudokuBoard(new BacktrackingSudokuSolver());
//            var file = SudokuBoardDaoFactory.getJdbcDao("test", "jdbc:derby:testowa_baza;create=true");
//            assertThrows(SyntaxJdbcDaoException.class, ()->file.write(sudoku1));
//            sudoku2 = file.read();
//
//            assertNotEquals(sudoku1, sudoku2);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void supertestTest() {
//        try {
//            SudokuBoard sudoku1 = new SudokuBoard(new BacktrackingSudokuSolver());
//            sudoku1.solveGame();
//            SudokuBoard sudoku2 = new SudokuBoard(new BacktrackingSudokuSolver());
//            var file = SudokuBoardDaoFactory.getJdbcDao("test", "jdbc:derby:testowa_baza;create=true");
//
//            assertDoesNotThrow(()->file.getAllBoardsInDataBase());
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}