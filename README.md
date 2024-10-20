# Daily Number Scraper

This Java project scrapes numbers from the URL website and saves them into a CSV file for further analysis. The project uses the OkHttp library for making HTTP requests and Jsoup for parsing HTML content.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Data Analysis](#data-analysis)
- [Acknowledgements](#acknowledgements)

## Features
- Scrapes numbers from the specified URL.
- Saves the scraped numbers in a CSV file with the date.
- Uses logging for error handling and information messages.
- Analyzes frequency of scraped numbers and provides sorted output.

## Technologies
- Java 23
- OkHttp (4.12.0)
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

Frequency Analysis: Count how many times each number from 1 to 22 appears in the CSV file.
Sorting: The numbers are sorted based on their frequency in descending order, providing insights into which numbers are most commonly drawn.
Output: The analysis results are logged for easy review.

### Running the Analyzer

To perform data analysis, execute the NumberFrequencyAnalyzer class. This will:

Load the numbers from scraped_numbers.csv.
Analyze the frequency of each number.
Print the sorted frequency of numbers to the console.
```
mvn exec:java -Dexec.mainClass="general_utils.NumberFrequencyAnalyzer"
```
Example Output
When you run the analyzer, you can expect output similar to the following:

```
2024-10-20 13:23:56 INFO  CsvAnalyzer - Numbers sorted by frequency:
2024-10-20 13:23:56 INFO  CsvAnalyzer - Number 5 appears 10 times.
2024-10-20 13:23:56 INFO  CsvAnalyzer - Number 14 appears 8 times.
2024-10-20 13:23:56 INFO  CsvAnalyzer - Number 3 appears 7 times.
...
```

## Acknowledgements

- OkHttp for HTTP requests.
- Jsoup for HTML parsing.
- Log4j for logging functionality.
