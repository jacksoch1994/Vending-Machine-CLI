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
        //If there was no movement of money in the transaction, do not write to file
        if (moneyTendered.equals(BigDecimal.ZERO)) return;


        try (final FileOutputStream out = new FileOutputStream(LOG_FILE, true);
             PrintWriter writer = new PrintWriter(out)
        ) {
            //Get and format current date and time
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");
            String formattedDateTime = dateTime.format(myFormat);

            //Write transaction to file in format: [Date] [Time] [Operation] [moneyTendered] [currentBalance]
            writer.printf("%s %s $%.2f $%.2f\n", formattedDateTime, operation, moneyTendered, currentBalance);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Transaction not logged!");
        } catch (IOException e) {
            System.out.println("Something went wrong trying to write to file. Transaction not logged!");
        }
    }

    /**
     * Creates a new file and writes the current status of all sales to a file. Takes a String representing the sales
     * report, and a BigDecimal representing the total sales made since the machine was turned on.
     *
     * @param report the sales report as a String
     * @param totalSales the total sales as a BigDecimal
     */
    public void writeSalesReport(String report, BigDecimal totalSales) {

        //Get and format current date and time
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy HH'h'mm'm'ss's'");

        //Format file name for output
        String fileName = String.format("Sales Report %s.txt", dateTime.format(format));
        File sales = new File(fileName);

        try (final FileOutputStream out = new FileOutputStream(sales, true);
             PrintWriter writer = new PrintWriter(out)) {

            writer.println(report);
            writer.printf("\n\n**TOTAL SALES** $%.2f", totalSales);

        } catch (FileNotFoundException e) {

            System.out.println("File not found. Sales report generation failed.");
        } catch (IOException e) {
            System.out.println("Something went wrong trying to write to file. Sales report generation failed.");
        }

    }

    /*
    ########################################   Constructor   ##########################################
     */

    /**
     * Creates a new Slot object. Deletes the log file if it already exists.
     *
     */
    public Logger() {
        if (LOG_FILE.exists()) {
            LOG_FILE.delete();
        }
    }
}

