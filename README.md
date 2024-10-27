# Daily Number Scraper

This Java project scrapes numbers from a specified URL and saves them into a CSV file for further analysis. The project uses Java's built-in HttpClient for making HTTP requests and Jsoup for parsing HTML content. Additionally, it includes a workflow for sending weekly analysis reports via email.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Data Analysis](#data-analysis)
- [Workflows](#workflows)
- [Acknowledgements](#acknowledgements)
- [License](#license)

## Features

- Scrapes numbers from the specified URL.
- Saves the scraped numbers in a CSV file with the date.
- Analyzes the frequency of numbers and generates an HTML report.
- Analyzes repeating sequences of numbers (subsequences) that appear in multiple rows.
- Sends the HTML report via email using GitHub Actions.
- Uses logging for error handling and information messages.

## Technologies
- Java 23
- Java's built-in HttpClient (for HTTP requests)
- Jsoup (1.18.1)
- Log4j (for logging)

## Getting Started

### Prerequisites

- JDK 23 or higher installed.
- Maven installed for dependency management.

### Clone the Repository

```
git clone https://github.com/yourusername/daily-lottery-scraper.git
cd daily-lottery-scraper
```
### Run the Scraper

To run the scraper, execute the DailyScraper class, which will perform the following actions:

Scrape the numbers from the specified URL.
Save the numbers into a CSV file named scraped_numbers.csv.
```
mvn exec:java -Dexec.mainClass="general_utils.DailyScraper"
```

## Data Analysis

The project includes a data analysis component that allows you to analyze the scraped numbers stored in the CSV file.

### Features of Data Analysis

- **Frequency Analysis**: Count how many times each number from 1 to 22 appears in the CSV file.
- **Subsequence Analysis**: Identify repeating sequences of numbers that appear in multiple rows of the CSV file.
- **Sorting**: The numbers are sorted based on their frequency in descending order, providing insights into which numbers are most commonly drawn.
- **Output**: The analysis results are logged for easy review.

### Running the Analyzer

To perform data analysis, execute the NumberFrequencyAnalyzer class. This will:

- Load the numbers from `scraped_numbers.csv`.
- Analyze the frequency of each number.
- Identify and log repeating subsequences.
- Print the sorted frequency of numbers to the console.
- Generate and log the HTML report.
```
mvn exec:java -Dexec.mainClass="general_utils.NumberFrequencyAnalyzer"
```
### Number Frequency Analyzer

The `NumberFrequencyAnalyzer` class is responsible for analyzing the numbers stored in the `scraped_numbers.csv` file. It calculates the frequency of each number and generates an HTML report for easy visualization. The analyzer logs the results and optionally sends them via email using the GitHub Actions workflow.

#### Key Features:

- **Frequency Analysis**: It counts the frequency of each number appearing in the CSV file.
- **Subsequence Analysis**: It identifies subsequences of numbers that appear in multiple rows.
- **HTML Report Generation**: Creates an HTML report showing a table of numbers and their respective frequencies as well as identified subsequences.
- **Logging**: Logs the frequency analysis and HTML report.

### Example Output (Logged):

When you run the analyzer, you can expect output similar to the following:
```
INFO CsvAnalyzer - Numbers sorted by frequency: INFO CsvAnalyzer - Number 5 appears 10 times. INFO CsvAnalyzer - Number 14 appears 8 times. INFO CsvAnalyzer - Number 3 appears 7 times. INFO CsvAnalyzer - Subsequence [1, 2, 3, 4] appears 3 times. INFO HtmlReportGenerator - Generated HTML Report:

<html> <head> <title>Weekly Number Analysis Report</title> <style> /* CSS styles */ </style> </head> <body> <h1>Weekly Number Analysis Report</h1> <table> <tr><th>Number</th><th>Frequency</th></tr> <tr><td>5</td><td>10</td></tr> <tr><td>14</td><td>8</td></tr> <tr><td>3</td><td>7</td></tr> </table> <h2>Subsequence Analysis</h2> <table> <tr><th>Subsequence</th><th>Frequency</th></tr> <tr><td>[1, 2, 3, 4]</td><td>3</td></tr> <tr><td>[7, 8, 9]</td><td>2</td></tr> </table> </body> </html>
```
  
## Workflows

### Daily Scraper Workflow

This workflow runs daily at 02:00 UTC and scrapes numbers from the specified URL. It then commits the updated CSV file back to the repository.

### Weekly Number Analysis Workflow

This workflow runs every Monday at 3:00 UTC and sends an email with the HTML report of the number analysis.

## Acknowledgements

- Java's HttpClient for HTTP requests.
- Jsoup for HTML parsing.
- Log4j for logging functionality.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.
