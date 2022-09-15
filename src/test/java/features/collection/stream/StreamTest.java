package features.collection.stream;

import features.record.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StreamTest {
    static List<Person> getPersons() {
        return List.of(
                new Person("Tom", 80),
                new Person("Bob", 20),
                new Person("Jack", 72),
                new Person("Paul", 32),
                new Person("Jill", 11)
        );
    }

    @Test
    void testReduce() {
        var ages = getPersons().stream()
                .filter(e -> e.age() > 20)
                .map(Person::age)
                .reduce(0, Integer::sum);
        System.out.println(ages);
        assertEquals(184, ages);
    }

    @Test
    void testSum() {
        var ages = getPersons().stream()
                .filter(e -> e.age() > 20)
                .mapToInt(Person::age)
                .sum();
        System.out.println(ages);
        assertEquals(184, ages);
    }

    @Test
    void testFilter() {
        var sList = getPersons().stream()
                .filter(p -> p.name().startsWith("T"))
                .toList();
        System.out.println(sList);
        assertEquals(1, sList.size());
    }

}