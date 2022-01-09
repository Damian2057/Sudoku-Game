package sudoku.exceptions;

public class FileDaoException extends DaoException {
    public FileDaoException(String messageKey) {
        super(messageKey);
    }

    public FileDaoException() {
        super();
    }
}