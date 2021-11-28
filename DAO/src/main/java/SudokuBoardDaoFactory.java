public class SudokuBoardDaoFactory<T> {
    Dao<T> getFileDao(String fileName) {
        return new FileSudokuBoardDao<T>(fileName);
    }
}
