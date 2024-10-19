package general_utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
    private static final Logger logger = LogManager.getLogger(Scraper.class);
    private static final String URL = "https://www.lutrija.hr/lotoigre/sve-ili-nista"; // Scraping URL
    private Scraper(){
    }
    // Number scraping function
    public static List<Integer> scrapeNumbers() throws IOException {
        List<Integer> numbers = new ArrayList<>();

        // Create an OkHttp client
        OkHttpClient client = new OkHttpClient();

        // Create a request
        Request request = new Request.Builder()
                .url(URL)
                .build();

        // Send request and download HTML content
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Check if the response body is not null
            if (response.body() != null) {
                // Pass HTML content to JSoup
                String html = response.body().string();
                Document document = Jsoup.parse(html);

                // Find the section that says "results"
                String scriptContent = document.getElementsByTag("script").html();

                // Search for the result in the script
                String resultsStart = "\"results\":[[";
                int start = scriptContent.indexOf(resultsStart);
                if (start != -1) {
                    int end = scriptContent.indexOf("]]", start);
                    String resultString = scriptContent.substring(start + resultsStart.length(), end);
                    String[] resultNumbers = resultString.split(",");
                    for (String numberStr : resultNumbers) {
                        numbers.add(Integer.parseInt(numberStr.trim()));
                    }
                } else {
                    logger.info("No results found.");
                }
            } else {
                logger.info("The response body is null.");
            }
        }

        return numbers;
    }
}