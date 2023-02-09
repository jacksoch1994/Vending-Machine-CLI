package com.techelevator;

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
     * Starts the operation of the Vending Machine Controller.
     */
    public void start() {
        mainMenu();
    }

    /**
     * Displays and operates the main menu of the Vending Machine. Routes user to different options based on the
     * selection provided.
     */
    private void mainMenu() {
        String[] options = {"Display Vending Machine Items",
        "Purchase",
        "Exit"};

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
                    shutDown();
                    break outer;
            }
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

            String balance = String.format("Current Money Provided: $%.2f", moneyHandler.getWallet());

            switch (console.getMenuSelection(options, balance)) {
                case FIRST_OPTION:
                    feedMoneyMenu();
                    break;
                case SECOND_OPTION:
                    purchaseProductMenu();
                    break;
                case THIRD_OPTION:
                    break outer;
            }
        }
    }


    /**
     * Displays and operates the feed money menu of the Vending Machine. Forces user to input a positive integer value
     * to add to the current balance, or zero in order to return to the previous menu without changing the balance.
     */
    private void feedMoneyMenu() {
        moneyHandler.feed(console.getPositiveInteger("Enter a whole number dollar amount to add to current " +
                "balance, or \"0\" to return to the previous menu: "));
    }


    /**
     * Displays and operates the product purchase menu of the Vending Machine. Prompts user to specify a Slot Number to
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
    private void purchaseProductMenu() {

        console.display(inventory.toString());
        String input = console.getString("Please select a product: ");
        if (inventory.isValidSlotNumber(input)) {

        } else {
            console.display("Invalid selection. Returning to previous menu.");
        }


    }

    /**
     * Method to be called when machine is finished running. Outputs the total product sales to a file.
     */
    private void shutDown() {

    }
    
}
