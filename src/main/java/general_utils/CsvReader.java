package general_utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private final String csvFilePath;

    // Constructor
    public CsvReader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    // Method to read numbers from CSV file
    public List<List<Integer>> readNumbersFromCsv() throws IOException {
        List<List<Integer>> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<Integer> rowNumbers = new ArrayList<>();
                // Start from 1 to skip the date
                for (int i = 1; i < values.length; i++) {
                    rowNumbers.add(Integer.parseInt(values[i].trim()));
                }
                numbers.add(rowNumbers);
            }
        }

        return numbers;
    }
}