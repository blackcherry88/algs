package features.collection.map;

import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {

        Map<String, Integer> numberMapping = Map.of(
                "One", 1,
                "Two", 2,
                "Three", 3,
                "Four", 4,
                "Five", 5
        );

        System.out.println(numberMapping);

        System.out.println(numberMapping.isEmpty());

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
