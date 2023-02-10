package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger writes transaction information to a Log file. Erases the previous log file when instantiated.
 */
public class Logger {

    /*
    ####################################### Constant Values ##########################################
     */

    //File representation of the Log text file.
    final private File LOG_FILE = new File("Log.txt");


    /*
    ########################################    Methods    ###########################################
     */

    /**
     * Writes an entry to the LOG_FILE containing transaction information. All entries are preceded with the time that
     * the transaction was recorded. Entries are formatted as: [Date] [Time] [Operation] [moneyTendered]
     * [currentBalance]
     *
     * @param operation the description of the transaction that was performed as a String
     * @param moneyTendered the amount of money involved in the transaction as a BigDecimal.
     * @param currentBalance the amount of money in the current balance at the time of the transaction as a BigDecimal
     */
    public void logTransaction(String operation, BigDecimal moneyTendered,
                               BigDecimal currentBalance) {
        try (final FileOutputStream out = new FileOutputStream(LOG_FILE, true);
             PrintWriter writer = new PrintWriter(out)
        ) {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");
            String formattedDateTime = dateTime.format(myFormat);
            writer.printf("%s %s $%.2f $%.2f\n", formattedDateTime, operation, moneyTendered, currentBalance);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void writeSalesReport(String report, BigDecimal totalSales) {

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy HH'h'mm'm'ss's'");


        String fileName = String.format("Sales Report %s.txt", dateTime.format(format));

        File sales = new File(fileName);

        try (final FileOutputStream out = new FileOutputStream(sales, true);
             PrintWriter writer = new PrintWriter(out)) {

            writer.println(report);
            writer.printf("\n\n**TOTAL SALES** $%.2f", totalSales);

        } catch (FileNotFoundException e) {

            System.out.println("WTF");
        } catch (IOException e) {
            System.out.println("File not found");
        }


    }

    /*
    ########################################   Constructor   ##########################################
     */

    /**
     * Creates a new Slot object. Deletes the log file if it already exists.
     *
     * @return instance of the Logger class.
     */
    public Logger() {
        if (LOG_FILE.exists()) {
            LOG_FILE.delete();
        }
    }
}

