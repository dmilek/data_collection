package general_utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class CsvAnalyzer {
    private static final Logger logger = LogManager.getLogger(CsvAnalyzer.class);
    private CsvAnalyzer(){
    }
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

    // New Method: Find all subsequences of length 2 or more
    public static List<List<Integer>> findSubsequences(List<Integer> list) {
        List<List<Integer>> subsequences = new ArrayList<>();

        // Generate all possible subsequences (at least length 2)
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j <= list.size(); j++) {
                List<Integer> sublist = list.subList(i, j);
                if (sublist.size() > 1) {
                    subsequences.add(new ArrayList<>(sublist));
                }
            }
        }
        return subsequences;
    }

    // Analyze subsequences across multiple rows and return frequency of each subsequence
    public static Map<String, Integer> analyzeSubsequences(List<List<Integer>> rows) {
        Map<String, Integer> subsequenceMap = new HashMap<>();

        for (List<Integer> row : rows) {
            List<List<Integer>> subsequences = findSubsequences(row);

            // Add subsequences to the map and count their occurrences
            for (List<Integer> subsequence : subsequences) {
                String key = subsequence.toString();
                subsequenceMap.put(key, subsequenceMap.getOrDefault(key, 0) + 1);
            }
        }

        return subsequenceMap;
    }

    // Print only subsequences that appear more than once
    public static void printFrequentSubsequences(Map<String, Integer> subsequenceMap) {
        logger.info("Subsequences that appear more than once:");
        for (Map.Entry<String, Integer> entry : subsequenceMap.entrySet()) {
            if (entry.getValue() > 1) {  // Print only if subsequence appears more than once
                logger.info("Subsequence {} appears {} times.", entry.getKey(), entry.getValue());
            }
        }
    }
}