package sudoku;

import java.io.File;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private File file;

    public FileSudokuBoardDao(String pathToFile) {
        file = new File(pathToFile);
    }

    @Override
    public SudokuBoard read() {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {

    }
}
