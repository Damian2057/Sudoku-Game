package sudoku.exceptions;

public class FileReadingDaoException extends FileDaoException {
    public FileReadingDaoException() {
        super("readingGoesWrong");
    }
}
