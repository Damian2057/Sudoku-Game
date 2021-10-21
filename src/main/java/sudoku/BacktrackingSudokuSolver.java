package sudoku;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public void solve(SudokuBoard board) {
        solveBoard(0, 0, board);
    }

    public boolean solveBoard(int rzad, int kolumna, SudokuBoard board) {
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
            int numer = randomNum(9, rzad, board);
            //losuj po 9 liczb od 1-9 bez powtorzen(tyle ile w wierszu
            if (board.check(rzad, kolumna, numer)) {

                board.setBoard(rzad, kolumna, numer);

                if (solveBoard(rzad, kolumna + 1, board)) {   //przejdz do nastepnej komorki
                    return true;
                }
            }
            board.setBoard(rzad, kolumna, 0);
            //jezeli nasz check zwraca false wartosc nie jest wpisywana
            //tzn nie mozemy przejsc dalej bo krok poprzedni powoduje brak rozwiazania
            //zaczynamy sie cofac
        }
        return false;
    }



    public int randomNum(int max, int rzad, SudokuBoard board) {
        Random rand = new Random();
        int intRandom = rand.nextInt(max) + 1;
        boolean f = false;
        do {
            for (int i = 0; i < 9; i++) {
                if (board.getBoard(rzad, i) == intRandom) {
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
