package sudoku.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;
import org.slf4j.LoggerFactory;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.exceptions.jdbc.JdbcDaoException;
import sudoku.exceptions.jdbc.SyntaxJdbcDaoException;
import sudoku.exceptions.jdbc.WrittingJdbcDaoException;
import sudoku.exceptions.jdbc.WrongNameJdbcException;
import sudoku.exceptions.model.LevelLogicalException;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    private static String dataBaseUrl;
    private String boardName;

    private Connection conn;
    private Statement stmt;

    public JdbcSudokuBoardDao(String boardName, String dataBaseUrl) throws SQLException {
        this.boardName = boardName;
        this.dataBaseUrl = dataBaseUrl;
        createDatabase();
    }

    public void createDatabase() throws SQLException {
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        try {
            logger.info("Database creation attempt");
            conn = DriverManager.getConnection(dataBaseUrl);
            stmt = conn.createStatement();


            String sql1 = "CREATE TABLE SudokuBoards"
                    + "("
                    + "id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "name VARCHAR(40) NOT NULL,"
                    + "PRIMARY KEY (id)"
                    + ")";
            stmt.execute(sql1);
            sql1 = "CREATE TABLE SudokuFields"
                    + "("
                    + "id INT NOT NULL,"
                    + "col_index INT NOT NULL,"
                    + "row_index INT NOT NULL,"
                    + "value INT NOT NULL,"
                    + "editable INT NOT NULL,"
                    + "PRIMARY KEY (id, col_index, row_index),"
                    + "FOREIGN KEY (id) REFERENCES SudokuBoards(id)"
                    + ")";
            stmt.execute(sql1);
            conn.commit();
        } catch (DerbySQLIntegrityConstraintViolationException except) {
            logger.error("Database integer error");
        } catch (SQLException e) {
            logger.debug("Database already exists");
        }
    }

    private void createConnection() throws SQLException {

        conn = DriverManager.getConnection(dataBaseUrl);
        stmt = conn.createStatement();
    }

    private void disconnectConnection() throws SQLException {
        if (conn != null && stmt != null) {
            conn.close();
            stmt.close();
        }
    }

    @Override
    public SudokuBoard read() {

        SudokuBoard temp = new SudokuBoard(new BacktrackingSudokuSolver());
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Database read attempt");

        try {
            createConnection();
            conn.setAutoCommit(false);
            int boardid = getBoardIdFromDataBase();
            readFieldsFormDataBase(temp, boardid);
            disconnectConnection();
        } catch (SQLException throwables) {
            logger.error("Read failure");
        }
        logger.info("Database read success");
        return temp;
    }

    private int getBoardIdFromDataBase() throws SQLException {
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        String query = "SELECT MAX(id) "
                + "FROM SudokuBoards "
                + "WHERE name = ?";
        logger.info("BoardID access attempt");
        try (var getBoardIDSStatement = conn.prepareStatement(query)) {
            getBoardIDSStatement.setString(1, boardName);

            try (var resultSet = getBoardIDSStatement.executeQuery()) {
                int boardId = 0;
                if (resultSet.next()) {
                    boardId = resultSet.getInt(1);
                } else {
                    throw new WrongNameJdbcException();
                }
                logger.debug("{}'s id is {}", boardName, boardId);
                return boardId;
            }
        } catch (Exception e) {
            logger.info("BoardID access another attempt");
            conn.rollback();
            throw e;
        }
    }

    private void readFieldsFormDataBase(SudokuBoard tempSudokuBoard,
                                        int boardId) throws SQLException {

        String sql = "SELECT * "
                + "FROM SudokuFields "
                + "WHERE id = ?";
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Database Fields read attempt");
        try {
            createConnection();
            var getFieldsFormDatabase = conn.prepareStatement(sql);
            getFieldsFormDatabase.setInt(1, boardId);
            try (var resultSet = getFieldsFormDatabase.executeQuery()) {
                resultSet.next();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        tempSudokuBoard.set(resultSet.getInt(2),
                                resultSet.getInt(3),
                                resultSet.getInt(4));

                        tempSudokuBoard.getSudokuField(resultSet.getInt(2),
                                resultSet.getInt(3)).setEditable(itb(resultSet.getInt(5)));
                        resultSet.next();
                    }
                }
            }
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }

    private boolean itb(int i) {
        return i == 1;
    }

    @Override
    public void write(SudokuBoard obj) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Database write attempt");
        try {
            Connection conn = DriverManager.getConnection(dataBaseUrl);
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            int boardId = createBoardEntryInDataBase(conn);
            saveFieldsInDatabase(obj, boardId, conn);
            conn.commit();
            conn.setAutoCommit(true);
            logger.debug("Write success");
            disconnectConnection();

        } catch (SQLDataException e) {
            logger.error("Database data error");
            throw new SyntaxJdbcDaoException();
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
            logger.error("Database syntax error");
            throw new SyntaxJdbcDaoException();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Cannot save board to database");
            throw new WrittingJdbcDaoException();
        }
    }

    private void saveFieldsInDatabase(SudokuBoard obj, int boardId, Connection connection) throws SQLException {
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Database save fields attempt");
        String sql = "INSERT INTO "
                + "SudokuFields "
                + "VALUES (?,?,?,?,?)";
        try (var createFieldStatement =
                     connection.prepareStatement(sql)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    createFieldStatement.setInt(1, boardId);
                    createFieldStatement.setInt(2, i);
                    createFieldStatement.setInt(3, j);
                    createFieldStatement.setInt(4, obj.getSudokuField(i,j).getFieldValue());
                    createFieldStatement.setInt(5,boolToInt(obj.getSudokuField(i,j).isEditable()));
                    createFieldStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    int boolToInt(Boolean b) {
        return b.compareTo(false);
    }

    private int createBoardEntryInDataBase(Connection connection) throws SQLException {
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Creating {} Entry", boardName);
        String query = "INSERT INTO SudokuBoards(name) VALUES (?)";

        try (var createBoardStatement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            createBoardStatement.setString(1, boardName);
            createBoardStatement.executeUpdate();

            try (var resultSet = createBoardStatement.getGeneratedKeys()) {
                int boardID;
                if (resultSet.next()) {
                    boardID = resultSet.getInt(1);
                    logger.debug("Created {} Entry with id: {}", boardName, boardID);
                    return boardID;
                } else {
                    throw new LevelLogicalException();
                }
            }
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    public List<String> getAllBoardsInDataBase() throws SQLException {
        createConnection();
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        String sql = "SELECT name FROM SudokuBoards";
        var savedBoards = new ArrayList<String>();

        logger.info("Getting all boards in database...");

        try  {
            PreparedStatement boardID = conn.prepareStatement(sql);
            try {
                var resultSet = boardID.executeQuery();
                while (resultSet.next()) {
                    savedBoards.add(resultSet.getString(1));
                }
                return savedBoards;
            } catch (Exception e) {
                logger.error("error");
            }
        } catch (Exception e) {
            logger.error("Cannot get all boards in database");
            throw new JdbcDaoException();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        disconnectConnection();
    }


}