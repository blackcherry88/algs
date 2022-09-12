package problems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReverseWords {
    static final Logger logger = LogManager.getLogger(ReverseWords.class.getName());

    public static void reverseChar(char[] s, int i, int j) {
        while (i < j) {
            char t = s[i];
            s[i]= s[j];
            s[j] = t;
            i += 1;
            j -= 1;
        }
    }

    public static void reverseWords(char[] s) {
        reverseChar(s, 0, s.length-1);
        int i = 0;
        for (int j = 0; j < s.length; j++) {
            if (s[j] == ' ') {
                reverseChar(s, i, j-1);
                i = j+1;
            }
        }
        reverseChar(s, i, s.length-1);
    }

    public static void main(String[] argv) {
        logger.info("Test logging {}", "great");
    }
}
