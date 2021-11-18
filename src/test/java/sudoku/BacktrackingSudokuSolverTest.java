package sudoku;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BacktrackingSudokuSolverTest {

    @Test
    void solve() {
        SudokuSolver s = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(s);
        s.solve(board);
        for(int i = 0; i < 9 ; i++) {
            for(int j = 0; j < 9; j++) {
                assertNotEquals(board.get(i,j), 0);
            }
        }
    }
    @Test
    void listTest() {
        List<Integer> d =  Arrays.asList();
        d = IntStream.rangeClosed(1, 9)
                .boxed().collect(Collectors.toList());
        Random randomizer = new Random();
        int i = d.get(randomizer.nextInt(d.size()));
        d.remove(4);
        d.remove(7);
        d.remove(1);
        Integer integer = Integer.valueOf(8);
        d.remove(integer);
        System.out.println(d);
        System.out.println(i);
    }

}