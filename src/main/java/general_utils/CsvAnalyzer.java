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

    public static List<List<Integer>> findNonContiguousSubsequences(List<Integer> list) {
        List<List<Integer>> subsequences = new ArrayList<>();
        int n = list.size();

        // Iterate over all subsequences of length 2 or more
        for (int len = 2; len <= n; len++) {
            findSubsequencesOfLength(list, new ArrayList<>(), 0, len, subsequences);
        }

        return subsequences;
    }

    // Helper function to generate subsequences of a specific length
    private static void findSubsequencesOfLength(List<Integer> list, List<Integer> current, int start, int len, List<List<Integer>> result) {
        if (current.size() == len) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            current.add(list.get(i));
            findSubsequencesOfLength(list, current, i + 1, len, result);
            current.removeLast(); // Backtrack
        }
    }

    // Analyze subsequences across multiple rows and return frequency of each subsequence
    public static Map<String, Integer> analyzeSubsequences(List<List<Integer>> rows) {
        Map<String, Integer> subsequenceMap = new HashMap<>();

        for (List<Integer> row : rows) {
            List<List<Integer>> subsequences = findNonContiguousSubsequences(row);

            // Add subsequences to the map and count their occurrences
            for (List<Integer> subsequence : subsequences) {
                String key = subsequence.toString();
                subsequenceMap.put(key, subsequenceMap.getOrDefault(key, 0) + 1);
            }
        }

        return subsequenceMap;
    }

    // Get sorted subsequences by frequency (Static method)
    public static List<Map.Entry<String, Integer>> getSortedFrequentSubsequences(Map<String, Integer> subsequenceMap) {
        // Only include subsequences that appear more than once
        // Sort by frequency, descending
        return subsequenceMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1) // Only include subsequences that appear more than once
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Sort by frequency, descending
                .toList();
    }
    // Print only subsequences that appear more than once, sorted by frequency
    public static void printFrequentSubsequences(Map<String, Integer> subsequenceMap) {
        logger.info("Subsequences that appear more than once (sorted by frequency):");
        List<Map.Entry<String, Integer>> frequentSubsequences = getSortedFrequentSubsequences(subsequenceMap);
        frequentSubsequences.forEach(entry ->
                logger.info("Subsequence {} appears {} times.", entry.getKey(), entry.getValue())
        );
    }
}