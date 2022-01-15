package sudoku.exceptions.jdbc;

import sudoku.exceptions.LocalizedRunTimeException;

public class DataJdbcDaoException extends LocalizedRunTimeException {
    public DataJdbcDaoException(String messageKey) {
        super(messageKey);
    }

    public DataJdbcDaoException() {
        super("CannotReadDatabaseFields");
    }
}
