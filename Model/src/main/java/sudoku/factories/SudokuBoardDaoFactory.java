package sudoku.factories;

public class SudokuBoardDaoFactory<T> {
    public Dao<T> getFileDao(String fileName) {
        return new FileSudokuBoardDao<T>(fileName);
    }

    public Dao<T> getJdbcDao(String boardName, String databaseURL) {
        return (Dao<T>) new JdbcSudokuBoardDao(boardName, databaseURL);
    }

}
