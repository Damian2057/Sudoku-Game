package sudoku.exceptions.dao;

public class FileNotFoundDaoException extends FileDaoException {
    public FileNotFoundDaoException() {
        super("fileNotFound");
    }
}
