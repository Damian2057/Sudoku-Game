package sudoku;

import java.util.Random;


public class SudokuBoard {
    private final int[][] board = new int[9][9];
    //private SudokuSolver sudokusolver;




    public SudokuBoard() {
    }

    public void solveGame() {

    }

    public  void fillBoard() {
        solveBoard(0,0);
    }

    public boolean solveBoard(int rzad, int kolumna) {
        if (rzad == 8 && kolumna == 9) {
            //koniec sudoku wszystkie komorki uzupelnione
            return true;
        }
        if (kolumna == 9) {
            //ostatnia komorka w wierszu przejscie do nastepnego wiersza
            rzad++;
            kolumna = 0;
        }

        for  (int i = 0; i < 9; i++) {
            int numer = randomNum(9,rzad);
            //losuj po 9 liczb od 1-9 bez powtorzen(tyle ile w wierszu
            if (check(rzad, kolumna, numer)) {

                board[rzad][kolumna] = numer;

                if (solveBoard(rzad, kolumna + 1)) {   //przejdz do nastepnej komorki
                    return true;
                }
            }
            board[rzad][kolumna] = 0;
            //jezeli nasz check zwraca false wartosc nie jest wpisywana
            //tzn nie mozemy przejsc dalej bo krok poprzedni powoduje brak rozwiazania
            //zaczynamy sie cofac
        }
        return false;
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

    public int randomNum(int max, int rzad) {
        Random rand = new Random();
        int intRandom = rand.nextInt(max) + 1;
        boolean f = false;
        do {
            for (int i = 0; i < 9; i++) {
                if (getBoard(rzad, i) == intRandom) {
                    f = true;
                    intRandom = rand.nextInt(max) + 1;
                } else {
                    f = false;
                }
            }
        } while (f);
        return  intRandom;
    }

}