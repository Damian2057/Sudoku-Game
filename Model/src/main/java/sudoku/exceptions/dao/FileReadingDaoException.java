package sudoku.exceptions.dao;

public class FileReadingDaoException extends FileDaoException {
    public FileReadingDaoException() {
        super("readingGoesWrong");
    }
}
