package sudoku.exceptions;

public class FileWritingDaoException extends FileDaoException {
    public FileWritingDaoException() {
        super("writingGoesWrong");
    }
}
