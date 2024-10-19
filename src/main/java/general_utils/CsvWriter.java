package general_utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CsvWriter {
    private static final Logger logger = LogManager.getLogger(CsvWriter.class);
    private static final String CSV_FILE_PATH = "scraped_numbers.csv"; // Path to the CSV file
    private CsvWriter(){
    }
    // Function for saving numbers in a CSV file
    public static void saveNumbersToCsv(List<Integer> numbers) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true)) {
            // Add a date
            writer.append(LocalDate.now().toString());
            // Add the numbers
            for (int number : numbers) {
                writer.append(",").append(String.valueOf(number));
            }
            writer.append("\n");
            writer.flush();
            logger.info("The numbers have been successfully saved in a CSV file.");
        } catch (IOException e) {
            logger.info("Error saving file:{} ", e.getMessage());
        }
    }
}