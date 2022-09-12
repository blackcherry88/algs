package features.collection.hashmap;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    public void putNoExist() {
        var map = new HashMap<String, String>();
        var oldValue = map.put("Hello", "world");
        assertNull(oldValue);
        oldValue = map.put("Hello", "unknown");
        assertEquals(oldValue, "world");
    }

}