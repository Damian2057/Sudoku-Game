
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao<T> implements Dao<T> { //AutoCloseable

    private final String fileName;
    private FileInputStream fis;
    private ObjectInputStream ois;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public T read() throws IOException, ClassNotFoundException {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            return (T) ois.readObject();
    }

    @Override
    public void write(T obj) throws IOException {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(obj);
    }


    @Override
    public void close() throws Exception {
        fis.close();
        ois.close();
    }
}
