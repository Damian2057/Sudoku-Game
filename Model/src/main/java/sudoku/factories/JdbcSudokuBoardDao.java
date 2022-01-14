package sudoku.factories;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;
import org.slf4j.LoggerFactory;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>{
    private static String dataBaseUrl;
    private String boardName;

    private static Connection conn = null;
    private static Statement stmt = null;

    public JdbcSudokuBoardDao(String boardName, String dataBaseUrl) throws SQLException {
        this.boardName = boardName;
        this.dataBaseUrl = dataBaseUrl;
        createDatabase();
    }

    private static void createDatabase() throws SQLException {
        try
        {

            conn = DriverManager.getConnection(dataBaseUrl);
            stmt = conn.createStatement();

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
            conn.commit();
        }
        catch (DerbySQLIntegrityConstraintViolationException except)
        {
            except.printStackTrace();
        }
    }

    private void createConnection() throws SQLException {
        conn = DriverManager.getConnection(dataBaseUrl);
        stmt = conn.createStatement();
    }

    private void disconnectConnection() throws SQLException {
        conn.close();
        stmt.close();
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        SudokuBoard temp = new SudokuBoard(new BacktrackingSudokuSolver());
        try {
            conn.setAutoCommit(false);
            int board_id = getBoardIdFromDataBase();
            readFieldsFormDataBase(temp, board_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return temp;
    }

    private int getBoardIdFromDataBase() throws SQLException {
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        String query = "SELECT MAX(sudoku_board_id) "
                + "FROM SudokuBoards "
                + "WHERE sudoku_board_name = ?;";

        try (var getBoardIDSStatement = conn.prepareStatement(query)) {
            getBoardIDSStatement.setString(1, boardName);

            try (var resultSet = getBoardIDSStatement.executeQuery()) {
                int boardId = 0;
                if (resultSet.next()) {
                    boardId = resultSet.getInt(1);
                } else {
                    //throw new WrongNameJdbcException();
                }
                logger.debug("{}'s id is {}", boardName, boardId);
                return boardId;
            }
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }

    private void readFieldsFormDataBase(SudokuBoard tempSudokuBoard,
                                        int boardId) throws SQLException {
        String query = "SELECT value "
                + "FROM SudokuFields "
                + "WHERE (sudoku_board_id, column_index, row_index) = (?, ?, ?);";

        try (var getFieldsFormDatabase = conn.prepareStatement(query)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    getFieldsFormDatabase.setInt(1, boardId);
                    getFieldsFormDatabase.setInt(2, i);
                    getFieldsFormDatabase.setInt(3, j);

                    try (var resultSet = getFieldsFormDatabase.executeQuery()) {
                        if (resultSet.next()) {
                            tempSudokuBoard.set(i, j, resultSet.getInt(1));
                        } else {
                            //throw new DataJdbcDaoException();
                        }
                    }
                }
            }
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {

    }

    @Override
    public void close() throws Exception {

    }
}