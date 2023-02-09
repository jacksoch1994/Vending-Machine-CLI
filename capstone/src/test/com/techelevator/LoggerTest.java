package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class LoggerTest {


    @Test
    public void logger_should_append_transactions() {
        Logger logger = new Logger();
        logger.logTransaction("FEED MONEY", new BigDecimal("5.00"), new BigDecimal("2.00"));
        logger.logTransaction("FEED MONEY", new BigDecimal("5.00"), new BigDecimal("2.00"));
        String expected = "02/09/2023 12:00:25 PM FEED MONEY: $5.00 $2.00\n" +
        "02/09/2023 12:00:25 PM FEED MONEY: $5.00 $2.00\n";
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
