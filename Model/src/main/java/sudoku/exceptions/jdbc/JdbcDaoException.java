package sudoku.exceptions.jdbc;

import sudoku.exceptions.LocalizedRunTimeException;

public class JdbcDaoException extends LocalizedRunTimeException{
    public JdbcDaoException(String messageKey) {
        super(messageKey);
    }

    public JdbcDaoException() {
        super("JdbcSomethingGoesWrong");
    }
}
