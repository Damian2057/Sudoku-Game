package sudoku.exceptions;

public class DaoException extends LocalizedRunTimeException {
    public DaoException(String messageKey) {
        super(messageKey);
    }

    public DaoException() {
        super("somethingGoesWrong");
    }
}
