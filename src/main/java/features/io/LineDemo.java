package features.io;

import java.io.*;
import java.util.List;

public class LineDemo {

    public static void main(String[] args) throws IOException {
        List<String> strings = List.of(
                "Hello world",
                "Great",
                "Work"
                );

        String inputFile = "test2.txt";
        String outputFile = "test2.txt";

        try (var out = new BufferedWriter(new FileWriter(outputFile))) {
            for (String s: strings) {
                out.write(s);
                out.newLine();
            }
        }

        try (var in = new BufferedReader(new FileReader(inputFile))) {
            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
        }
    }

}
