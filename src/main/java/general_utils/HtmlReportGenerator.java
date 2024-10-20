package general_utils;

import java.util.List;
import java.util.Map;

public class HtmlReportGenerator {
    private HtmlReportGenerator(){
    }
    public static String generateHtmlReport(Map<Integer, Integer> numberFrequency) {
        StringBuilder htmlContent = new StringBuilder();

        // Start of HTML
        htmlContent.append("<html>");
        htmlContent.append("<head>");
        htmlContent.append("<title>Weekly Number Analysis Report</title>");

        // Adding basic styling using inline CSS
        htmlContent.append("<style>");
        htmlContent.append("body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; }");
        htmlContent.append("h1 { text-align: center; color: #4CAF50; }");
        htmlContent.append("table { width: 50%; margin: 0 auto; border-collapse: collapse; }");
        htmlContent.append("th, td { padding: 10px; border: 1px solid #ddd; text-align: center; }");
        htmlContent.append("th { background-color: #4CAF50; color: white; }");
        htmlContent.append("tr:nth-child(even) { background-color: #f2f2f2; }");
        htmlContent.append("</style>");

        htmlContent.append("</head>");
        htmlContent.append("<body>");
        htmlContent.append("<h1>Weekly Number Analysis Report</h1>");

        // Table start
        htmlContent.append("<table>");
        htmlContent.append("<tr><th>Number</th><th>Frequency</th></tr>");

        // Filling the table with data
        List<Map.Entry<Integer, Integer>> sortedNumbers = CsvAnalyzer.getSortedNumbers(numberFrequency);
        for (Map.Entry<Integer, Integer> entry : sortedNumbers) {
            htmlContent.append("<tr>")
                    .append("<td>").append(entry.getKey()).append("</td>")
                    .append("<td>").append(entry.getValue()).append("</td>")
                    .append("</tr>");
        }

        // Closing table and HTML
        htmlContent.append("</table>");
        htmlContent.append("</body>");
        htmlContent.append("</html>");

        // Return the HTML content as a string
        return htmlContent.toString();
    }
}