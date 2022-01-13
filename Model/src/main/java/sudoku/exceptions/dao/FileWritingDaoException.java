package sudoku.exceptions.dao;

public class FileWritingDaoException extends FileDaoException {
    public FileWritingDaoException() {
        super("writingGoesWrong");
    }
}
