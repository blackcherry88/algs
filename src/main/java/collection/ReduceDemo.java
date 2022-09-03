package collection;

import record.Person;

import java.util.List;

public class ReduceDemo {

    public static List<Person> getPersons() {
        return List.of(
                new Person("Tom", 80),
                new Person("Bob", 20),
                new Person("Jack", 72),
                new Person("Paul", 32),
                new Person("Jill", 11)
        );
    }

    public static void main(String[] args) {
        var ages = getPersons().stream()
                .filter(e -> e.age() > 20)
                .map(Person::age)
                .reduce(0, Integer::sum);
        System.out.println(ages);
    }
}
