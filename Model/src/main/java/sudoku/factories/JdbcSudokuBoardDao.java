package sudoku.factories;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;
import sudoku.SudokuBoard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>{
    private static String dataBaseUrl;
    private String boardName;

    private static Connection conn = null;
    private static Statement stmt = null;

    public JdbcSudokuBoardDao(String boardName, String dataBaseUrl) throws SQLException {
        this.boardName = boardName;
        this.dataBaseUrl = dataBaseUrl;
        createConnection();
    }

    private static void createConnection() throws SQLException {
        try
        {
            conn = DriverManager.getConnection(dataBaseUrl);
            stmt = conn.createStatement();
//            String SQL = "CREATE TABLE SudokuBoards (id INT NOT NULL AS IDENTITY(start with 1, INCREMENT by 1) PRIMARY KEY,"
//                    + "name VARCHAR(40) NOT NULL)";
//            stmt.execute(SQL);
//            SQL = "CREATE TABLE SudokuFields (id INT NOT NULL,"
//                    +"col_index INT NOT NULL,"
//                    + "row_index INT NOT NULL,"
//                    + "value INT NOT NULL,"
//                    + "PRIMARY KEY (id, col_index, row_index),"
//                    + "FOREIGN KEY (id) REFERENCES SudokuBoards(id))";
//            stmt.execute(SQL);

            String SQL = "CREATE TABLE SudokuBoards" +
                    "(" +
                    "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    "name VARCHAR(40) NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ")";
            stmt.execute(SQL);
            SQL = "CREATE TABLE SudokuFields"
                    + "("
                    + "id INT NOT NULL,"
                    + "col_index INT NOT NULL,"
                    + "row_index INT NOT NULL,"
                    + "value INT NOT NULL,"
                    + "PRIMARY KEY (id, col_index, row_index),"
                    + "FOREIGN KEY (id) REFERENCES SudokuBoards(id)"
                    + ")";
            stmt.execute(SQL);
        }
        catch (DerbySQLIntegrityConstraintViolationException except)
        {
            except.printStackTrace();
        }
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {

    }

    @Override
    public void close() throws Exception {

    }
}