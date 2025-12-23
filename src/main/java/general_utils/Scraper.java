package general_utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
    private static final Logger logger = LogManager.getLogger(Scraper.class);
    private static final String URL = "https://www.lutrija.hr/lotoigre/sve-ili-nista";
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(java.time.Duration.ofSeconds(30))
            .build();

    private Scraper() {
    }

    public static List<Integer> scrapeNumbers() throws IOException, InterruptedException {
        List<Integer> numbers = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Response body: {}", response.body()); // Log the full response body

        if (response.statusCode() == 200) {
            String html = response.body();
            Document document = Jsoup.parse(html);

            String scriptContent = document.getElementsByTag("script").html();

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
            logger.error("Failed to retrieve data. Status code: {}", response.statusCode());
        }

        return numbers;
    }
}