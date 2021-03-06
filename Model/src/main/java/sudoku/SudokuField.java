package sudoku;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.exceptions.model.ValueInconsistentException;


public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private boolean editable = false;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public SudokuField() {
        value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
         Logger logger = LoggerFactory.getLogger(this.getClass());
         if (value != this.value) {
             if (value > 9 || value < 0) {
                 logger.error("Value is out of range: " + value);
                 throw new ValueInconsistentException();
             }
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

    @Override
    public int compareTo(SudokuField o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return this.getFieldValue() - o.getFieldValue();
    }

    @Override
    public SudokuField clone() {
        try {
            SudokuField clone = (SudokuField) super.clone();
            clone.changes = new PropertyChangeSupport(clone);
            return clone;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
