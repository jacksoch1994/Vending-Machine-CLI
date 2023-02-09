package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    final private File LOG_FILE = new File("Log.txt");

    public Logger() {
        if (LOG_FILE.exists()) {
            LOG_FILE.delete();
        }
    }

    public void logTransaction(String operation, BigDecimal moneyTendered,
                               BigDecimal currentBalance) {
        try (final FileOutputStream out = new FileOutputStream(LOG_FILE, true);
             PrintWriter writer = new PrintWriter(out)
        ) {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");
            String formattedDateTime = dateTime.format(myFormat);
            writer.printf("%s %s: $%.2f $%.2f\n", formattedDateTime, operation, moneyTendered, currentBalance);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}

