package datetime;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateTimeDemo {
    public static void main(String[] args) {
        var ld = LocalDate.of(2022, Month.AUGUST, 31);
        System.out.println("Local data is " + ld);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("local data is " + ld.format(dtf));
    }
}
