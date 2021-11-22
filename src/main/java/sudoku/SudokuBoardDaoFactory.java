package sudoku;

public class SudokuBoardDaoFactory {
    public static FileSudokuBoardDao getFileDao(String pathToFile) {
        return new FileSudokuBoardDao(pathToFile);
    }
}
