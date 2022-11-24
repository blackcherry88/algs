package features.collection.stream;

import features.record.EventParticipation;
import features.record.Guest;
import features.record.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
                .map(Person::age)
                .filter(age -> age > 20)
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
    void testFindFirst() {
        var p = getPersons().stream()
                .filter(e -> e.age() > 20)
                .findFirst()
                .orElse(null);
        System.out.println(p);
        assertEquals("Tom", p.name());
    }

    @Test
    void testFilter() {
        var sList = getPersons().stream()
                .filter(p -> p.name().startsWith("T"))
                .toList();
        System.out.println(sList);
        assertEquals(1, sList.size());
    }
    @Test
    void streamNotExecutedWithoutTerminateOp() {
        var s = getPersons().stream()
                .filter(p -> {
                    boolean r = p.name().compareTo("T") > 0;
                    System.out.printf("In filter, got {%s} when checking %s\n", r, p);
                    return r;
                })
                .map(p -> {
                    System.out.printf("In map for %s\n", p);
                    return p.name();
                });
        System.out.println(s);
        assertNotEquals(List.class, s.getClass());
    }
    @Test
    void streamSplitData() {
        var s = getPersons().stream()
                .filter(p -> {
                    boolean r = p.name().compareTo("T") > 0;
                    System.out.printf("In filter, got {%s} when checking %s\n", r, p);
                    return r;
                })
                .map(p -> {
                    System.out.printf("In map for %s\n", p);
                    return p.name();
                })
                .collect(Collectors.toList());
        System.out.println(s);
    }
    @Test
    void sortedChangeStreamDataSplit() {
        var s = getPersons().stream()
                .filter(p -> {
                    boolean r = p.name().compareTo("T") > 0;
                    System.out.printf("In filter, got {%s} when checking %s\n", r, p);
                    return r;
                })
                .sorted()
                .map(p -> {
                    System.out.printf("In map for %s\n", p);
                    return p.name();
                })
                .collect(Collectors.toList());
        System.out.println(s);
    }
    @Test
    void testEmptyIntStream() {
        int c = IntStream.empty()
                .sum();
        System.out.println(c);
    }

    @Test
    void testTee() {
        var result =
                Stream.of(
                    // Guest(String name, boolean participating, Integer participantsNumber)
                    new Guest("Marco", true, 3),
                    new Guest("David", false, 2),
                    new Guest("Roger",true, 6))
                    .collect(Collectors.teeing(
                            Collectors.filtering(Guest::participating,
                                    Collectors.mapping(Guest::name, Collectors.toList())),
                                    Collectors.summingInt(Guest::number),
                            EventParticipation::new));

        System.out.println(result);
    }

    @Test
    void testTee2() {
        record Result(long count, int sum) {}

        var result =
                Stream.of(5, 12, 19, 21)
                        .collect(Collectors.teeing(
                                // first collector
                                Collectors.counting(),
                                // second collector
                                Collectors.summingInt(n -> n),
                                // merger: (count, sum) -> new Result(count, sum);
                                Result::new
                        ));

        System.out.println(result); // -> {count=4, sum=57}
    }

}