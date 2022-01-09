package sudoku.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.SudokuBoard;
import sudoku.exceptions.DaoException;
import sudoku.exceptions.FileNotFoundDaoException;
import sudoku.exceptions.FileReadingDaoException;
import sudoku.exceptions.FileWritingDaoException;

import java.io.*;

public class FileSudokuBoardDao<T> implements Dao<T> { //AutoCloseable

    private String fileName;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream os;

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
        fis.close();
        ois.close();
        fos.close();
        os.close();
    }
}
