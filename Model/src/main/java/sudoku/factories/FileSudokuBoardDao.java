package sudoku.factories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.SudokuBoard;
import sudoku.exceptions.dao.DaoException;
import sudoku.exceptions.dao.FileNotFoundDaoException;
import sudoku.exceptions.dao.FileReadingDaoException;
import sudoku.exceptions.dao.FileWritingDaoException;


public class FileSudokuBoardDao<T> implements Dao<T> { //AutoCloseable

    private String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public T read() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Reading file: {}", fileName);
        SudokuBoard temp;
        try (FileInputStream inputFileStream = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(inputFileStream)) {
            temp = (SudokuBoard)in.readObject();
        } catch (FileNotFoundException exception) {
            logger.error("File \"{}\" not found",fileName);
            throw new FileNotFoundDaoException();
        } catch (IOException exception) {
            logger.error("IOException was thrown with message: {}", exception.getMessage());
            throw new FileReadingDaoException();
        } catch (ClassNotFoundException e) {
            logger.error("Class not Found");
            throw new DaoException("classNotFound");
        }
        return (T) temp;
    }

    @Override
    public void write(T obj) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Writing to file: {}", fileName);
        try (FileOutputStream outputFileStream = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(outputFileStream)) {
            out.writeObject(obj);
        } catch (IOException exception) {
            logger.error("IOException was thrown with message: {}", exception.getMessage());
            throw new FileWritingDaoException();
        }
    }

    @Override
    public void close() throws Exception {
    }
}
