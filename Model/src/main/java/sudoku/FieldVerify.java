package sudoku;


import java.util.regex.Pattern;

public class FieldVerify {

    public FieldVerify() {
    }

    public static boolean runTest(String regex, String text) {
        if (Pattern.matches(regex,text)) {
            return true;
        }
        return false;
    }

    public int verifyTextField(String textField) {
        if (runTest("[1-9]", textField)) {
            if (Integer.parseInt(textField) > 0) {
                return Integer.parseInt(textField);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
