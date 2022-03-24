import au.com.bytecode.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author Alex Morunov
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String s = "uid,sname,mass,single,room,dep\n3,Jon,89,T,12,13\n14,Alex,101,F,18,4";
        String c = "mass";

        solutions(s, c);

    }

    static Integer createCSV(String s, String c) {
        int index = 0;
        try {
            PrintWriter pw = new PrintWriter(new File("\\table.csv"));
            String[] words = s.split(",");
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                if (word.equals("\n")) {
                    word = "\r" + word;
                }

                sb.append(word);
                sb.append(",");

                if (word.equals(c)) {
                    index = Arrays.asList(words).indexOf(word);
                }

            }
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return index;
    }

    @SuppressWarnings("resource")
    static void solutions(String s, String c) throws IOException {
        int a = createCSV(s, c);
        CSVReader csvReader = new CSVReader(new FileReader("\\table.csv"), ',', '"', 1);
        String[] nextLine;
        List<Integer> value = new ArrayList<>();
        while ((nextLine = csvReader.readNext()) != null) {
            try {
                value.add(Integer.valueOf(Arrays.asList(nextLine).get(a)));
            } catch (ArithmeticException e) {
                throw new ArithmeticException("These types cannot be compared");
            }
        }
        System.out.println("Max value = " + Collections.max(value));
    }


}
