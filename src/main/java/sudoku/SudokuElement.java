package sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuElement {

    protected List<SudokuField> fields = Arrays.asList(new SudokuField[9]);


    protected void creation(SudokuField[] value) {
        for (int i = 0; i < 9; i++) {
            fields.set(i, value[i]);
        }

    }

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
