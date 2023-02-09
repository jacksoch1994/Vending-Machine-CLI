package com.techelevator;

public class Controller {

    private Inventory inventory = new Inventory();
    private Logger logger = new Logger();
    private MoneyHandler moneyHandler = new MoneyHandler();
    private UserInterface console = new UserInterface();

    public void start() {

        mainMenu();
    }

    private void mainMenu() {
        String[] options = {"Display Vending Machine Items",
        "Purchase",
        "Exit"};

        outer:
        while (true) {

            switch (console.getMenuSelection(options)) {
                case 1:
                    console.display(inventory.toString());
                    break;
                case 2:
                    purchaseMenu();
                    break;
                case 3:
                    shutDown();
                    break outer;
            }
        }
    }

    private void purchaseMenu() {
        String[] options = {"Feed Money",
                "Select Product",
                "Finish Transaction"};



        outer:
        while (true) {

            String balance = String.format("Current Money Provided: $%.2f", moneyHandler.getWallet());

            switch (console.getMenuSelection(options, balance)) {
                case 1:
                    feedMoneyMenu();
                    break;
                case 2:
                    break;
                case 3:
                    break outer;
            }
        }
    }

    private void feedMoneyMenu() {
        moneyHandler.feed(console.getPositiveInteger("Enter a whole number dollar amount to add to current " +
                "balance, or \"0\" to return to the previous menu: "));
    }

    private void purchaseProductMenu() {
        console.display("");
    }

    private void shutDown() {

    }
    
}
