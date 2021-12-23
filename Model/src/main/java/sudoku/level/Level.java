package sudoku.level;

import java.util.Random;
import sudoku.SudokuBoard;


public class Level {
    protected int numberOfFieldsToRemove;

    public Level(int numberOfFieldsToRemove) {
        this.numberOfFieldsToRemove = numberOfFieldsToRemove;
    }

    public boolean removeFields(final SudokuBoard s) {

        Random r = new Random();
        if (!s.isAlreadyDeleted()) {
            s.setAlreadyDeleted(true);
            for (int i = 0; i < numberOfFieldsToRemove; i++) {
                int x = r.nextInt(9);
                int y = r.nextInt(9);

                if (s.getSudokuField(x, y).getFieldValue() != 0) {
                    s.set(x, y, 0);
                    s.getSudokuField(x,y).setEditable(true);
                } else {
                    i--;
                }
            }
            return true;
        }
        return false;
    }

    public int getNumberOfFieldsToRemove() {
        return numberOfFieldsToRemove;
    }
}