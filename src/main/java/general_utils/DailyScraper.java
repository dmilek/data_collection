package general_utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class DailyScraper {
    private static final Logger logger = LogManager.getLogger(DailyScraper.class);

    public static void main(String[] args) {
        try {
            // Scrape the data
            List<Integer> numbers = Scraper.scrapeNumbers();

            // Save the data in a CSV file
            CsvWriter.saveNumbersToCsv(numbers);

        } catch (IOException e) {
            logger.error("IOException occurred:", e);
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted:", e);

            // Re-interrupt the current thread to maintain interrupt status
            Thread.currentThread().interrupt();
        }
    }
}