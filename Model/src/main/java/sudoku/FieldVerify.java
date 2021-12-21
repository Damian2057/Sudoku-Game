package sudoku;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldVerify {

    public FieldVerify() {
    }

    public static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    public int verifyTextField(String textField) {
        int matches = runTest("[1-9]", textField);
        if (matches == 1) {
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
