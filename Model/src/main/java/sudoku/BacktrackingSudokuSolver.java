package sudoku;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {

    private List<Integer> randList  =  Arrays.asList();

    private void fillRandList() {
            randList = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList());
    }

    public int getElement() {
        if (randList.size() == 0) {
            fillRandList();
        }
        Random randomizer = new Random();
        int i = randList.get(randomizer.nextInt(randList.size()));
        Integer integer = Integer.valueOf(i);
        randList.remove(integer);
        return i;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public void solve(SudokuBoard board) {
        solveBoard(0, 0, board);
    }

    public boolean solveBoard(int rzad, int kolumna, SudokuBoard board) {
        if (rzad == 8 && kolumna == 9) {
            return true;
        }
        if (kolumna == 9) {
            rzad++;
            kolumna = 0;
        }
        for  (int i = 0; i < 9; i++) {
            int numer = getElement();
            if (checkCorrect(rzad, kolumna, numer, board)) {

                board.set(rzad, kolumna, numer);
                if (solveBoard(rzad, kolumna + 1, board)) {
                    return true;
                }
            }
            board.set(rzad, kolumna, 0);
        }
        return false;
    }

    public boolean checkCorrect(int rzad, int kolumna, int numer, SudokuBoard board) {

        for (int i = 0; i <= 8; i++) {
            if (board.get(i, kolumna) == numer) {
                return false;
            }
        }
        for (int i = 0; i <= 8; i++) {
            if (board.get(rzad, i) == numer) {
                return false;
            }
        }

        int pomrzad = rzad - rzad % 3;
        int pomkol = kolumna - kolumna % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(pomrzad + i, pomkol + j) == numer) {
                    return false;
                }
            }
        }
        return true;
    }
}
