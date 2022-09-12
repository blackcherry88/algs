package problems;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;


public class ReverseWordsTest {
    private static  final Logger logger = LogManager.getLogger(ReverseWordsTest.class.getName());

    @Test
    public void testReverseWords() {
        char[] s = "The sky is blue".toCharArray();
        ReverseWords.reverseWords(s);
        String r = String.valueOf(s);
        assert r.equals("blue is sky The");

        logger.info("Result is: {}", r);
    }

}
