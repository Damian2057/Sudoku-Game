package sudoku.exceptions.jdbc;

import sudoku.exceptions.LocalizedRunTimeException;

public class SyntaxJdbcDaoException extends LocalizedRunTimeException{
    public SyntaxJdbcDaoException(String messageKey) { super(messageKey); }

    public SyntaxJdbcDaoException() { super("JdbcSyntaxError");}
}
