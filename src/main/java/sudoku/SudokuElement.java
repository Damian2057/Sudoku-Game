package sudoku;

import java.util.Arrays;
import java.util.List;


public abstract class SudokuElement {

    protected List<SudokuField> fields = Arrays.asList(new SudokuField[9]);


    public int getFieldValue(int i) {
        return fields.get(i).getFieldValue();
    }

    public boolean verify() {

        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()
                        && fields.get(j).getFieldValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
