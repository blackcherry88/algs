package ds.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectIntMapTest {

    @Test
    void get() {
        ObjectIntMap<String> obj2Int = new ObjectIntMap<>();
        String[] names = {"A", "B", "C"};
        for(var n: names) {
            obj2Int.put(n);
        }
        System.out.println(obj2Int);
        int size = obj2Int.size();
        for(int i = 0; i < size; ++i) {
            assertEquals(obj2Int.get(i), names[i]);
        }
    }

    @Test
    void toIndex() {
        ObjectIntMap<String> obj2Int = new ObjectIntMap<>();
        String[] names = {"A", "B", "C"};
        for(var n: names) {
            obj2Int.put(n);
        }

        for(int i = 0; i < names.length; ++i) {
            assertEquals(obj2Int.getIndex(names[i]), i);
        }
    }

    @Test
    void contains() {
        ObjectIntMap<String> obj2Int = new ObjectIntMap<>();
        String[] names = {"A", "B", "C"};
        for(var n: names) {
            obj2Int.put(n);
        }
        assert(obj2Int.contains("A"));
        assert(!obj2Int.contains("D"));
    }
}