package features.collection.map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    public void changeMapValue() {
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
        assertTrue(e != null);
    }

    @Test
    public void forEachLoop() {
        var m = new HashMap<String, Integer>();

        String s = "a";
        for(int i= 0; i < 10; ++i) {
            var key = s + i;
            m.put(key, i);
        }
        System.out.println(m);

        m.forEach((k, v) -> System.out.println(k + "-->" + v));
    }
}