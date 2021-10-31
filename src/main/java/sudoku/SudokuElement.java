package sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuElement {

    protected List<SudokuField> fields = Arrays.asList(new SudokuField[9]);


    protected void init(SudokuField[] value) {
        for (int i = 0; i < 9; i++) {
            fields.set(i, value[i]);
        }
    }

    boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()) {
                    return true;
                }
            }
        }
        return false;
    }

}
