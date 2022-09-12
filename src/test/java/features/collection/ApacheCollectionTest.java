package features.collection;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApacheCollectionTest {

    @Test
    public void testMapIterator() {
        IterableMap<String, String> map = new HashedMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("4", "Four");
        map.put("5", "Five");
        MapIterator<String, String> iterator = map.mapIterator();
        while (iterator.hasNext()) {
            var key = iterator.next();
            var value = iterator.getValue();
            System.out.println("key: " + key);
            System.out.println("Value: " + value);
            iterator.setValue(value + "_");
        }
        assertEquals(map.get("1"), "One_");
    }

}