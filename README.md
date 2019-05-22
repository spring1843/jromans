# jromans
Java web service that converts decimals up to 2,200,000,000 into Roman Numerals. The implementation first calculates the Roman Numerals for a set up fundamentals i.e. (1,2,...,4,5,6,7,...,10,11,12,13,...,20,30,...,50,60,...,100,200,...,Max) that is later used to represent any number in Roman Numerals.

## Requirements
Requirements
* mvn https://maven.apache.org/download.cgi
* Java 12

## How to build and run
* `mvn test` Run tests
* `mvn package` Build the jar
* `mvn exec:java -Dexec.mainClass="com.spring1843.app.Main"` Run the application

## Larger numbers and Vinculum addition
You can run larger numbers, the API currently supports only up to 2,200,000,000, but `RomanNumbers.FromDecimal` is tested to support up to 9,000,000,000. With the given (`IVXLCDM`) characters in Roman numbers we run out of characters to represent numbers larger than 3999. The solution is to add another a new set set of characters to the list. This is done by using a unicode connecting character to the sequence `VXLCDM` thereby increasing the range of numbers that we can represent. 4,000 for example can be represented as `V̇` (V with one dot on top of it). The following characters are used to accomplish this.
* First repetition, Combining Dot Above https://unicode-table.com/en/0307/
* Second repetition, Combining Diaeresis https://unicode-table.com/en/0308/
* Third repetition, Combining Three Dots Above https://unicode-table.com/en/20DB/

Therefore when you see a V with one dot on top it you know it's the first repetition of V, and when you see two dots you know it's the second repetition.

For example conversions please take a look at `RomanNumbersTet.java`.

## Package layout
```
java/
  APIEndpoint.java  // Common functions needed to implement web based APIs
  Main.java         // The process that runs the web service
  RomanNumbers.java // Decimal to Roman Numerals conversion
  WebService.java   // Implements the endpoint that servers the conversion over web
  ops/              // Interfaces and example implementations for operational aspects
    Config.java          // Configuration values
    HealthCheck.java     // Endpoints providing health check for load balancing and monitoring
    Log.java             // Simple stdout log
    Logger.java          // Logging interface
    Metrics.java         // Simple stdout stats
    MetricsAgent.java    // Stats interface
    Ops.java             // Encapsulates all operational aspects in one class
```

## Dependencies
* mvn
* junit
* com.sun.net.httpserver
