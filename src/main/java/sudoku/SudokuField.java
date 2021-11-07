package sudoku;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SudokuField {
    private int value = 0;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public SudokuField() {
        value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        changes.firePropertyChange("value",this.value,value);
        this.value = value;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public PropertyChangeSupport getChanges() {
        return changes;
    }
}
