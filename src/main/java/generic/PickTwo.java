package generic;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class AAA {

}

// Subtle heap pollution (Pages 147-8)
public class PickTwo {
    // UNSAFE - Exposes a reference to its generic parameter array!
    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> List<T> pickTwo(T a, T b, T c) {
        switch(ThreadLocalRandom.current().nextInt(3)) {
            case 0: return List.of(a, b);
            case 1: return List.of(a, c);
            case 2: return List.of(b, c);
        }
        throw new AssertionError(); // Can't get here
    }

    public static void main(String[] args) {
        List<String> attributes = pickTwo("Good", "Fast", "Cheap");
        System.out.println(attributes);
    }
}