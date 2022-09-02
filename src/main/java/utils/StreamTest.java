package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>() {
            {
                add(new Employee(1, "Hello"));
                add(new Employee(2, "World"));
                add(new Employee(3, "World2"));
                add(new Employee(4, "World3"));
            }
        };

        List<String> names = employees.stream().map(e -> e.getName()).collect(Collectors.toList());
        System.out.println(names);
    }
}
