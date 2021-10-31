package sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuBoard {

    private SudokuField[][] board = new SudokuField[9][9];
    private SudokuSolver sudokusolver;
    private List<SudokuRow> row = Arrays.asList(new SudokuRow[9]);
    private List<SudokuColumn> column = Arrays.asList(new SudokuColumn[9]);
    private List<SudokuBox> box = Arrays.asList(new SudokuBox[9]);



    public SudokuBoard(SudokuSolver sudokusolver) {

        this.sudokusolver = sudokusolver;


        //utworzenie wierszy kolumn boxow
        for (int i = 0; i < 9; i++) {
            row.set(i, new SudokuRow());
            column.set(i, new SudokuColumn());
            box.set(i, new SudokuBox());
        }

        SudokuField[] tmp = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = board[i][j];
            }
            row.get(i).init(tmp);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmp[j] = board[j][i];
            }
            column.get(i).init(tmp);
        }
//        int kk = 0;
//        for(int i = 0; i < 3; i++){
//            for(int j = 0; j < 3; j++){
//                for(int k=0; k < 3; k++){
//                    for(int z=0;z<3;z++){
//                        tmp[kk] = board[k][z];
//                    }
//                }
//            }
//        }

        //utworzenie strutury board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public void solveGame() {
        sudokusolver.solve(this);
    }


    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public int get(int x, int y) {

        return board[x][y].getFieldValue();
    }

    SudokuRow getRow(int x) {
        return row.get(x);
    }

    SudokuColumn getColumn(int y) {
        return column.get(y);
    }
    // 0 1 2 3 4 5 6 7 8 9
          //y
      // 0 1 2
   //x// 3 4 5
      // 6 7 8
//    SudokuColumn getBox(int x, int y) {
//        if(y>0){
//            y+=2+x;
//        }
////        return box.get(x);
//    }

    public String showBoard() {
        String s = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s += get(i, j) + "\t";
            }
            s += "\n";
        }
        return s;
    }

    public boolean checkBoard(int rzad, int kolumna, int los) {

        for (int i = 0; i <= 8; i++) {        //przejscie po kolumnie
            if (get(i, kolumna) == los) {
                return false;
            }
        }
        for (int i = 0; i <= 8; i++) {        //przejscie po wierszu
            if (get(rzad, i) == los) {
                return false;
            }
        }

        int pomrzad = rzad - rzad % 3;
        int pomkol = kolumna - kolumna % 3;
        for (int i = 0; i < 3; i++) {        //przejscie po kwadracie 3x3
            for (int j = 0; j < 3; j++) {
                if (get(pomrzad + i, pomkol + j) == los) {
                    return false;
                }
            }
        }
        return true;
    }

}