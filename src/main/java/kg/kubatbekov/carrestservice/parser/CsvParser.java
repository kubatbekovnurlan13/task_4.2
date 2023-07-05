package kg.kubatbekov.carrestservice.parser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvParser {
    @Value("${csv.file}")
    private String csvFile;

    public List<List<String>> parse() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {

                if (!line.contains("objectId")) {
                    String[] values = line.split(",");
                    if (values.length == 5) {
                        records.add(Arrays.asList(values));
                    } else if (values.length > 5) {
                        StringBuilder lastValue = new StringBuilder();
                        for (int i = 4; i < values.length; i++) {
                            lastValue.append(values[i]);
                        }

                        List<String> newValue = new ArrayList<>(Arrays.asList(values).subList(0, 4));
                        newValue.add(lastValue.toString());
                        records.add(newValue);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("There is error with reading file, " + e.getMessage());
        }
        return records;
    }

    public void read() {
        List<List<String>> record = parse();

        for (List<String> line : record) {
            for (String word : line) {
                System.out.print(word + ", ");
            }
            System.out.println(" ");
        }
    }

}
