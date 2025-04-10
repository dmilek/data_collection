package general_utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NumberFrequencyAnalyzer {
    private static final Logger logger = LogManager.getLogger(NumberFrequencyAnalyzer.class);

    public static void main(String[] args) {
        String csvFilePath = "scraped_numbers.csv";  // Path to the CSV file

        // Create an instance of CsvReader
        CsvReader csvReader = new CsvReader(csvFilePath);

        try {
            // Read numbers from CSV file
            List<List<Integer>> numbers = csvReader.readNumbersFromCsv();

            // Analyze the frequency of numbers (static method call)
            Map<Integer, Integer> numberFrequency = CsvAnalyzer.analyzeNumbers(numbers);

            // Print sorted numbers by frequency (static method call)
            CsvAnalyzer.printSortedNumbers(numberFrequency);

            // Analyze subsequences in the numbers and get their frequency
            Map<String, Integer> subsequenceFrequency = CsvAnalyzer.analyzeSubsequences(numbers);

            // Print only subsequences that appear more than once
            CsvAnalyzer.printFrequentSubsequences(subsequenceFrequency);

            // Call the HtmlReportGenerator to generate the HTML report (static method call)
            String htmlReport = HtmlReportGenerator.generateHtmlReport(numberFrequency);
            logger.info("Generated HTML Report: \n{}", htmlReport); // Log the HTML report

        } catch (IOException e) {
            logger.error("Error reading the CSV file: ", e);
        }
    }
}