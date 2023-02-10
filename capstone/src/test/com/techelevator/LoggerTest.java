package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoggerTest {


    @Test
    public void logger_should_record_transactions_to_output_file() {
        Logger logger = new Logger();
        logger.logTransaction("FEED MONEY:", new BigDecimal("5.00"), new BigDecimal("2.00"));

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");
        String formattedDateTime = dateTime.format(myFormat);

        String expected = formattedDateTime + " FEED MONEY: $5.00 $2.00\n";
        String actual = helper();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void logger_should_append_transactions_to_existing_transactions() {
        Logger logger = new Logger();
        logger.logTransaction("FEED MONEY:", new BigDecimal("5.00"), new BigDecimal("2.00"));
        logger.logTransaction("FEED MONEY:", new BigDecimal("5.00"), new BigDecimal("2.00"));

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");
        String formattedDateTime = dateTime.format(myFormat);

        String expected = formattedDateTime + " FEED MONEY: $5.00 $2.00\n";
        expected = expected + expected;
        String actual = helper();
        Assert.assertEquals(expected, actual);
    }

    private String helper() {
        StringBuilder stringBuilder = new StringBuilder();
        try(Scanner scanner = new Scanner(new File("Log.txt"))) {
            while(scanner.hasNextLine()){
                stringBuilder.append(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return stringBuilder.toString();
    }
}
