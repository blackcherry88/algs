package features.record;

public class RecordDemo {
    record Person(String name, String email, String phoneNumber) {}

    public static void main(String[] args) {
        Person person = new Person("Unknown", "unknown@xxx.yyy", "111-111-2222");
        Person person2 = new Person("Unknown", "unknown@xxx.yyy", "111-111-2222");

        System.out.println("Person of record is equal by field equals " + person.equals(person2));
        System.out.println(person.hashCode());
        System.out.println(person);
    }
}
