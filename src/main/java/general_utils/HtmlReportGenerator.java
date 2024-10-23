package general_utils;

import java.util.List;
import java.util.Map;

public class HtmlReportGenerator {
    // Define a constant for the closing table cell tag
    private static final String TD_CLOSE = "</td>";

    private HtmlReportGenerator() {
    }

    public static String generateHtmlReport(Map<Integer, Integer> numberFrequency, Map<String, Integer> subsequenceFrequency) {
        StringBuilder htmlContent = new StringBuilder();

        // Start of HTML
        htmlContent.append("<html>");
        htmlContent.append("<head>");
        htmlContent.append("<title>Weekly Number and Subsequence Analysis Report</title>");

        // Adding basic styling using inline CSS
        htmlContent.append("<style>");
        htmlContent.append("body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; margin: 0; padding: 20px; }");
        htmlContent.append("h1 { text-align: center; color: #4CAF50; margin-bottom: 40px; }");
        htmlContent.append("h2 { color: #333; text-align: center; margin-top: 50px; margin-bottom: 20px; }");
        htmlContent.append("table { width: 80%; margin: 20px auto; border-collapse: collapse; box-shadow: 0 0 15px rgba(0,0,0,0.1); }");
        htmlContent.append("th, td { padding: 15px; border: 1px solid #ddd; text-align: center; }");
        htmlContent.append("th { background-color: #4CAF50; color: white; font-size: 16px; }");
        htmlContent.append("tr:nth-child(even) { background-color: #f2f2f2; }");
        htmlContent.append("td { font-size: 14px; }");
        htmlContent.append("</style>");

        htmlContent.append("</head>");
        htmlContent.append("<body>");
        htmlContent.append("<h1>Weekly Number and Subsequence Analysis Report</h1>");

        // Section for number frequency
        htmlContent.append("<h2>Number Frequency Analysis</h2>");
        htmlContent.append("<table>");
        htmlContent.append("<tr><th>Number</th><th>Frequency</th></tr>");

        // Filling the table with number frequency data
        List<Map.Entry<Integer, Integer>> sortedNumbers = CsvAnalyzer.getSortedNumbers(numberFrequency);
        for (Map.Entry<Integer, Integer> entry : sortedNumbers) {
            htmlContent.append("<tr>")
                    .append("<td>").append(entry.getKey()).append(TD_CLOSE)
                    .append("<td>").append(entry.getValue()).append(TD_CLOSE)
                    .append("</tr>");
        }
        htmlContent.append("</table>");

        // Section for subsequence frequency
        htmlContent.append("<h2>Subsequence Frequency Analysis</h2>");
        htmlContent.append("<table>");
        htmlContent.append("<tr><th>Subsequence</th><th>Frequency</th></tr>");

        // Filling the table with subsequence data (only those that appear more than once)
        for (Map.Entry<String, Integer> entry : subsequenceFrequency.entrySet()) {
            if (entry.getValue() > 1) {
                htmlContent.append("<tr>")
                        .append("<td>").append(entry.getKey()).append(TD_CLOSE)
                        .append("<td>").append(entry.getValue()).append(TD_CLOSE)
                        .append("</tr>");
            }
        }
        htmlContent.append("</table>");

        // Closing the HTML
        htmlContent.append("</body>");
        htmlContent.append("</html>");

        // Return the HTML content as a string
        return htmlContent.toString();
    }
}