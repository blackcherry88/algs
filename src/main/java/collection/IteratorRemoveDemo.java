package collection;

import java.util.concurrent.ConcurrentLinkedDeque;

public class IteratorRemoveDemo {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> userNames = new ConcurrentLinkedDeque<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};

        for (String userName : userNames) {
            if (userName.equals("Hollis")) {
                userNames.remove();
            }
        }
        System.out.println(userNames);
    }
}
