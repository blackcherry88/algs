package features.generic;

import java.util.*;

// Using a recursive type bound to express mutual comparability (Pages 137-8)
public class RecursiveTypeBound {

    public static <E extends Comparable<E>> E max(Collection<E> c) {
        E result = null;
        for (E e: c) {
            if (result == null || e.compareTo(result) > 0) {
                result = e;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> argList = Arrays.asList(args);
        System.out.println(max(argList));
    }
}