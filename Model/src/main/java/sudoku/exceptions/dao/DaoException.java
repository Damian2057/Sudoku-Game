package sudoku.exceptions.dao;

import sudoku.exceptions.LocalizedRunTimeException;

public class DaoException extends LocalizedRunTimeException {
    public DaoException(String messageKey) {
        super(messageKey);
    }

    public DaoException() {
        super("somethingGoesWrong");
    }
}
