package sudoku.exceptions;

public class FileNotFoundDaoException extends FileDaoException {
    public FileNotFoundDaoException() {
        super("fileNotFound");
    }
}
