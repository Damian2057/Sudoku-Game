package sudoku;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public abstract class SudokuElement implements Serializable, Cloneable {

    protected List<SudokuField> fields = Arrays.asList(new SudokuField[9]);


    public int getFieldValue(int i) {
        return fields.get(i).getFieldValue();
    }

    public boolean verify() {
        int expected = 362880;
        int begin = 1;
        for (SudokuField field : fields) {
            begin *= field.getFieldValue();
        }
        if (expected != begin) {
            return false;
        }

        return true;
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
        if (!(o instanceof SudokuElement)) {
            return false;
        }
        SudokuElement that = (SudokuElement) o;

        return new EqualsBuilder().append(this.fields, that.fields).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.fields).toHashCode();
    }

    @Override
    public SudokuElement clone() {
        try {
            SudokuElement clone = (SudokuElement) super.clone();
            clone.fields = Arrays.asList(new SudokuField[9]);
            int i = 0;
            for (SudokuField type : fields) {
                clone.fields.set(i, type.clone());
                i++;
            }
            return clone;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
            }
    }

}
