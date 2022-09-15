package features.collection.map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void testCompute() {
        var m = new HashMap<>(
                Map.of(
                "One", 1,
                "Two", 2,
                "Three", 3,
                "Four", 4,
                "Five", 5
            )
        );
        var doubleV = m.compute("One", (k, v) -> 2 * v);
        int expected = 2;
        assertEquals(expected, doubleV);
        assertTrue(m.containsKey("One"));
    }
    @Test
    void testGetDefault() {
        var m = new HashMap<>(
                Map.of(
                        "One", 1,
                        "Two", 2,
                        "Three", 3,
                        "Four", 4,
                        "Five", 5
                )
        );
        var v = m.getOrDefault("One", 0);
        assertEquals(1, v);
        v = m.getOrDefault("UnknownKey", 0);
        assertEquals(0, v);
    }

    @Test
    void testReplace() {
        var m = new HashMap<>(
                Map.of(
                        "One", 1,
                        "Two", 2,
                        "Three", 3,
                        "Four", 4,
                        "Five", 5
                )
        );
        var v = m.replace("One", 10);
        assertEquals(1, v);
        var r = m.replace("Two", 3, 4);
        assertFalse(r);
    }

    @Test
    void forEachLoop() {
        var m = new HashMap<String, Integer>();

        String s = "a";
        for(int i= 0; i < 10; ++i) {
            var key = s + i;
            m.put(key, i);
        }
        System.out.println(m);

        m.forEach((k, v) -> System.out.println(k + "-->" + v));
    }
    @Test
    void changeMapValue() {
        var m = new HashMap<String, Integer>();

        String s = "a";
        for(int i= 0; i < 10; ++i) {
            var key = s + i;
            m.put(key, i);
        }
        System.out.println(m);

        var e = m.entrySet().stream()
                .findFirst()
                .orElse(null);
        if (e !=null) {
            e.setValue(100);
        }
        e = m.entrySet().stream()
                .filter(x -> x.getValue()==100)
                .findFirst()
                .orElse(null);
        assertNotNull(e);
    }
    @Test
    void testIteration() {
        Map<String, Integer> numberMapping = Map.of(
                "One", 1,
                "Two", 2,
                "Three", 3,
                "Four", 4,
                "Five", 5
        );

        System.out.println(numberMapping);
        for(Map.Entry<String, Integer> e: numberMapping.entrySet()) {
            System.out.println("Key ->" + e.getKey() + " value-> " + e.getValue());
        }

        System.out.println("Java iterator");
        var iterator = numberMapping.entrySet().iterator();
        while(iterator.hasNext()) {
            var e = iterator.next();
            System.out.println(e.getKey() + "->" + e.getValue());
        }

        System.out.println("Java 8 forEach");
        numberMapping.forEach((K, V) -> {
            System.out.println(K + "->" + V);
        });
    }

}