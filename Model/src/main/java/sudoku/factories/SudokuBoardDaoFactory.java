package sudoku.factories;

public class SudokuBoardDaoFactory<T> {
    public Dao<T> getFileDao(String fileName) {
        return new FileSudokuBoardDao<T>(fileName);
    }
}
