package sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileSudokuBoardDao<T> implements Dao<T> {

    private File file;
    private final String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public T read() throws IOException, ClassNotFoundException {
        FileInputStream fos = new FileInputStream(fileName);
        ObjectInputStream os = new ObjectInputStream(fos);
        return (T) os.readObject();
    }

    @Override
    public void write(T obj) throws IOException {

    }

    @Override
    public void close() throws Exception {

    }
}

