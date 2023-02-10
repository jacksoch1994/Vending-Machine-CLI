package com.techelevator;

import java.math.BigDecimal;

/**
 * Controller contains the logic for displaying menus, processing user input, handling transactions, and recording
 * transaction information by incorporating the Inventory, Logger, MoneyHandler, and UserInterface classes.
 */
public class Controller {

    /*
    ####################################### Constant Values ##########################################
     */

    final int FIRST_OPTION = 1;
    final int SECOND_OPTION = 2;
    final int THIRD_OPTION = 3;
    final int SECRET_OPTION = 4;

    /*
    ########################################   Attributes   ##########################################
     */

    private Inventory inventory = new Inventory();
    private Logger logger = new Logger();
    private MoneyHandler moneyHandler = new MoneyHandler();
    private UserInterface console = new UserInterface();

    /*
    ########################################    Methods    ###########################################
     */

    /**
     * Starts the operation of the Vending Machine Controller. Displays the machine's title card to the console and
     * then starts the main menu.
     */
    public void start() {
        console.printTitleCard();
        mainMenu();
    }

    /**
     * Displays and operates the main menu of the Vending Machine. Routes user to different options based on the
     * selection provided.
     */
    private void mainMenu() {
        String[] options = {"Display Vending Machine Items",
        "Purchase",
        "Exit",
        "-Print Sales Report"};

        outer:
        while (true) {
            switch (console.getMenuSelection(options)) {
                case FIRST_OPTION:
                    console.display(inventory.toString());
                    break;
                case SECOND_OPTION:
                    purchaseMenu();
                    break;
                case THIRD_OPTION:
                    break outer;
                case SECRET_OPTION:
                    logger.writeSalesReport(inventory.salesReport(), moneyHandler.getTotalSales());
                    console.display("Generated Sales Report!");
                    break;
            }
            console.banner();
        }
    }


    /**
     * Displays and operates the purchasing menu of the Vending Machine. Routes user to different options based on the
     * selection provided.
     */
    private void purchaseMenu() {
        String[] options = {"Feed Money",
                "Select Product",
                "Finish Transaction"};

        outer:
        while (true) {

            String balance = String.format("Available Funds: $%.2f", moneyHandler.getWallet());

            switch (console.getMenuSelection(options, balance)) {
                case FIRST_OPTION:
                    feedMoneyMenu();
                    break;
                case SECOND_OPTION:
                    productSelectionMenu();
                    break;
                case THIRD_OPTION:
                    BigDecimal change = moneyHandler.getWallet();
                    console.display(moneyHandler.getChange());
                    logger.logTransaction("GIVE CHANGE:", change, moneyHandler.getWallet());
                    break outer;
            }
            console.banner();
        }
    }


    /**
     * Displays and operates the feed money menu of the Vending Machine. Forces user to input a positive integer value
     * to add to the current balance, or zero in order to return to the previous menu without changing the balance.
     */
    private void feedMoneyMenu() {
        BigDecimal money = moneyHandler.feed(console.getPositiveInteger("Enter a whole number dollar amount to add to current " +
                "balance, or \"0\" to return to the previous menu: "));
        logger.logTransaction("FEED MONEY:", money, moneyHandler.getWallet());
    }


    /**
     * Displays and operates the product selection menu of the Vending Machine. Prompts user to specify a Slot Number to
     * purchase a product from and behaves accordingly based on the input provided:
     *
     * If the specified slot number does not exist in the inventory, displays an error message
     * and returns to the previous menu.
     *
     * If there is no product remaining at the specified slot, displays an error
     * message and returns to the previous menu.
     *
     * If there is not enough money in the current balance to purchase the specified product, displays an error message
     * and returns to the previous menu.
     *
     * If a valid slot number is provided, AND there is product remaining, AND the current balance is not less than the
     * price, updates the current balance and product quantity to reflect the purchase. Successful purchases are logged.
     */
    private void productSelectionMenu() {

        console.display(inventory.toString());
        String input = console.getString("Please select a product: ");
        console.banner();
        
        if (!inventory.isValidSlotNumber(input)) {
            console.display("Invalid selection. Returning to previous menu.");
            return;
        }
        if (!inventory.isInStock(input)) {
            console.display("Product out of stock. Returning to previous menu.");
            return;
        }
        if (moneyHandler.spend(inventory.costOf(input))) {
            dispenseProduct(input);
            
        } else {
            console.display("Insufficient funds. Returning to previous menu.");
        }

    }


    /**
     * Displays information to the console related to the purchased product. Prints the item being dispensed, the total
     * balance remaining in the current balance, and the items "vocalization". Also logs the Transaction to the log
     * file.
     *
     * @param input the slot number of item to be dispensed
     */
    private void dispenseProduct(String input) {
        console.display(String.format("Now dispensing %s $%.2f", inventory.nameOf(input), inventory.costOf(input)));
        console.display(String.format("Balance remaining: $%.2f", moneyHandler.getWallet()));
        console.display(inventory.consume(input));
        logger.logTransaction(inventory.nameOf(input) + " " + input, inventory.costOf(input), moneyHandler.getWallet());
    }
    
}
