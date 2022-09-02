package collection.arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListDemo {

    public static void main(String[] args) {
        List<String> fruits = List.of("Banana", "Apple", "Mango", "Orange", "Watermelon", "Strawberry");

        System.out.println("\n=== Iterate using for loop with index ===");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println("Index " + i + " is " + fruits.get(i));
        }

        System.out.println("\n=== Iterate using simple for-each loop ===");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        System.out.println("\n=== Iterate using an iterator() ===");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String fruit = (String) iterator.next();
            System.out.println(fruit);
        }

        System.out.println("\n=== Iterate using a listIterator() to traverse in both direction ===");
        ListIterator<String> listIterator = fruits.listIterator(fruits.size());
        while (listIterator.hasPrevious()) {
            String fruit = listIterator.previous();
            System.out.println(fruit);
        }

        while (listIterator.hasNext()) {
            String fruit = listIterator.next();
            System.out.println(fruit);
        }

        System.out.println("=== Iterate using Jave 8 forEach and lambda ===");
        fruits.forEach(e -> System.out.println(e));
    }
}
