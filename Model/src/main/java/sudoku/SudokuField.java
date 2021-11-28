package sudoku;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;



public class SudokuField implements Serializable {
    private int value;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public SudokuField() {
        value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        if (value != this.value) {
            changes.firePropertyChange("value",this.value,value);
            this.value = value;
        }
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
        if (!(o instanceof SudokuField)) {
            return false;
        }
        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(this.value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.value).toHashCode();

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
