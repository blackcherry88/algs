package collection;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;

public class ApacheCommonDemo {

    public static void main(String[] args) {
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
        System.out.println(map);
    }

}
