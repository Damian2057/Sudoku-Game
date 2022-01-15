package sudoku.exceptions.jdbc;

import sudoku.exceptions.LocalizedRunTimeException;

public class WrongNameJdbcException extends LocalizedRunTimeException{
    public WrongNameJdbcException(String messageKey) { super(messageKey); }

    public WrongNameJdbcException() { super("WrongNameInDatabase");}
}
