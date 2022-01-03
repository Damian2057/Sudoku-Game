package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldVerifyTest {

    @Test
    public void runTestTest() {
        FieldVerify f = new FieldVerify();
        assertTrue(FieldVerify.runTest("[1-9]","9"));
        assertFalse(FieldVerify.runTest("[1-9]","d"));
    }

    @Test
    public void verifyTextFieldTest() {
        FieldVerify f = new FieldVerify();
        assertEquals(f.verifyTextField("9"),9);
        assertEquals(f.verifyTextField("-9"),-1);
        assertEquals(f.verifyTextField("d"),-1);
    }
}
