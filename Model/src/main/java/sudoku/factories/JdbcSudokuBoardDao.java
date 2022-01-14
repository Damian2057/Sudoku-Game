package sudoku.factories;

import sudoku.SudokuBoard;

import java.io.IOException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private final String URL;
    private String boardName;

    public JdbcSudokuBoardDao(String boardName, String URL) {
        this.URL = URL;
        this.boardName = boardName;
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
