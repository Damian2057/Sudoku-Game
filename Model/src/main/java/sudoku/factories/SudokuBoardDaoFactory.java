package sudoku.factories;

import sudoku.SudokuBoard;

import java.sql.SQLException;

public class SudokuBoardDaoFactory {
    private SudokuBoardDaoFactory() {}

    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
    public static JdbcSudokuBoardDao getJdbcDao(String boardName, String databaseUrl) throws SQLException {
        return new JdbcSudokuBoardDao(boardName, databaseUrl);
    }
}