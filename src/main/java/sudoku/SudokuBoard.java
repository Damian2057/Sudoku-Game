package sudoku;


public class SudokuBoard {
    private final int[][] board = new int[9][9];
    private SudokuSolver sudokusolver = new BacktrackingSudokuSolver();

    public SudokuBoard() {
    }

    public void solveGame() {
        sudokusolver.solve(this);
    }


    public void setBoard(int x, int y, int value) {
        board[x][y] = value;
    }

    public int getBoard(int x, int y) {
        return board[x][y];
    }

    public String showBoard() {
        String s = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s += getBoard(i, j) + "\t";
            }
            s += "\n";
        }
        return s;
    }

    public boolean check(int rzad, int kolumna, int los) {

        for (int i = 0; i <= 8; i++) {        //przejscie po kolumnie
            if (getBoard(i, kolumna) == los) {
                return false;
            }
        }
        for (int i = 0; i <= 8; i++) {        //przejscie po wierszu
            if (getBoard(rzad, i) == los) {
                return false;
            }
        }

        int pomrzad = rzad - rzad % 3;
        int pomkol = kolumna - kolumna % 3;
        for (int i = 0; i < 3; i++) {        //przejscie po kwadracie 3x3
            for (int j = 0; j < 3; j++) {
                if (getBoard(pomrzad + i, pomkol + j) == los) {
                    return false;
                }
            }
        }
        return true;
    }

}