package sudoku.exceptions.jdbc;

import sudoku.exceptions.LocalizedRunTimeException;

public class WrittingJdbcDaoException extends LocalizedRunTimeException {
    public WrittingJdbcDaoException(String messageKey) {
        super(messageKey);
    }

    public WrittingJdbcDaoException() {
        super("JdbcWritingError");
    }
}
