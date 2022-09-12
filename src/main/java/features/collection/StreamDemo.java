package features.collection;

import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<String> strings = List.of("Hollis", "HollisChuang",
                "hollis","Hollis666", "Hello", "HelloWorld", "Hollis");
        var sList = strings.stream()
                .filter(s -> s.startsWith("hello"))
                .toList();
        System.out.println(sList);
    }
}
