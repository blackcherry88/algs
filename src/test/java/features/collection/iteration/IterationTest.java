package features.collection.iteration;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentLinkedDeque;

import static org.junit.jupiter.api.Assertions.*;

class IterationTest {
    @Test
    void testIterationRemove() {
        ConcurrentLinkedDeque<String> a = new ConcurrentLinkedDeque<String>() {{
            add("1");
            add("2");
            add("3");
            add("4");
        }};
        int size = a.size();

        var it = a.iterator();
        String objToRemove = "3";
        while (it.hasNext()) {
            if (it.next() == objToRemove) {
                it.remove();
            }
        }
        size--;
        assertEquals(size, a.size());
        assertFalse(a.contains(objToRemove));
    }

}