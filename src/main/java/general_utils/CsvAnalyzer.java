package general_utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class CsvAnalyzer {
    private static final Logger logger = LogManager.getLogger(CsvAnalyzer.class);

    // Analyze the frequency of numbers (Static method)
    public static Map<Integer, Integer> analyzeNumbers(List<List<Integer>> numbers) {
        Map<Integer, Integer> numberFrequency = new HashMap<>();
        for (int i = 1; i <= 22; i++) {
            numberFrequency.put(i, 0);
        }

        for (List<Integer> row : numbers) {
            for (int number : row) {
                numberFrequency.put(number, numberFrequency.get(number) + 1);
            }
        }

        return numberFrequency;
    }

    // Get sorted numbers by frequency (Static method)
    public static List<Map.Entry<Integer, Integer>> getSortedNumbers(Map<Integer, Integer> numberFrequency) {
        List<Map.Entry<Integer, Integer>> sortedNumbers = new ArrayList<>(numberFrequency.entrySet());
        sortedNumbers.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        return sortedNumbers;
    }

    // Print sorted numbers (Static method)
    public static void printSortedNumbers(Map<Integer, Integer> numberFrequency) {
        List<Map.Entry<Integer, Integer>> sortedNumbers = getSortedNumbers(numberFrequency);
        logger.info("Numbers sorted by frequency: ");
        for (Map.Entry<Integer, Integer> entry : sortedNumbers) {
            logger.info("Number {} appears {} times.", entry.getKey(), entry.getValue());
        }
    }
}