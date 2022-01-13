package sudoku.level;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.SudokuBoard;
import sudoku.exceptions.model.LevelLogicalException;


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
        } else {
            Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.error("Sudoku has already been processed by Lvl Module");
            throw new LevelLogicalException();
        }
    }

    public int getNumberOfFieldsToRemove() {
        return numberOfFieldsToRemove;
    }
}
