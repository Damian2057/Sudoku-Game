package sudoku.factories;

import java.sql.SQLException;
import sudoku.SudokuBoard;

public class SudokuBoardDaoFactory {
    private SudokuBoardDaoFactory() {

    }

    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static JdbcSudokuBoardDao getJdbcDao(String boardName, String databaseUrl)
            throws SQLException {
        return new JdbcSudokuBoardDao(boardName, databaseUrl);
    }
}