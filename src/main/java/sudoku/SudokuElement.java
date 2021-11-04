package sudoku;

import java.util.Arrays;
import java.util.List;


public abstract class SudokuElement {

    protected List<SudokuField> fields = Arrays.asList(new SudokuField[9]);


    public int getFields(int i) {
        return fields.get(i).getFieldValue();
    }

    public boolean verify() {
        int value = 362880; // 9!
        int begin = 1;

        for (int i = 0; i < 9; i++) {
            begin *= fields.get(i).getFieldValue();
        }

        if (value != begin) {
            return false;
        }

        return true;
    }

}
