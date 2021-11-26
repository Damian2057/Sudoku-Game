package sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileSudokuBoardDao<T> implements Dao<T> { //, AutoCloseable

    private File file;
    private final String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public T read() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fos = new FileInputStream(fileName);
            ObjectInputStream os = new ObjectInputStream(fos);
            return (T) os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void write(T obj) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() throws Exception {
        System.out.println("zamykam");
    }
}
