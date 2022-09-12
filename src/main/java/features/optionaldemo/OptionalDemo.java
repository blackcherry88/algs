package features.optionaldemo;

import java.util.Optional;


public class OptionalDemo {

    public static void main(String[] args) {
        Optional<String> o1 = Optional.of("This is a sample text");
        Optional<String> o2 = Optional.empty();

        o1.ifPresent(s -> System.out.println("o1 is " + o1));
        o2.ifPresent(s -> System.out.println("o1 is " + o2));

        System.out.println(o2.map(String::toUpperCase).orElse("Default value"));
    }

}
