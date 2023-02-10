package com.techelevator;

import java.math.BigDecimal;
/**
 * MoneyHandler keeps track of the Vending Machine's current balance and the total sales revenue that has been accrued
 * over all successful transactions made since the Vending Machine was run.
 */
public class MoneyHandler {

    /*
    ########################################   Attributes   ##########################################
     */

    private BigDecimal wallet = new BigDecimal(0);
    private BigDecimal revenue = new BigDecimal(0);

    /*
    ######################################## Getter Methods ##########################################
     */

    public BigDecimal getWallet() {
        return this.wallet;
    }

    public BigDecimal getTotalSales() {
        return this.revenue;
    }

    /*
    ########################################  Other Methods  ##########################################
     */

    /**
     * Adds money to the current balance in whole dollar amounts.
     *
     * @param dollars the number of dollars to add to the current balance, as an int.
     */
    public BigDecimal feed(int dollars) {
        if (dollars > 0) {
            this.wallet = this.wallet.add(new BigDecimal(dollars));
        }
        return new BigDecimal(dollars);
    }

    /**
     * Updates the current balance by the monetary amount provided and returns true if successful. If the
     * operation is successful, also updates the total revenue accrued by the amount of the purchased object. If the
     * amount provided is more than the amount in the current balance, the operation fails and the method returns false.
     *
     * @param amount the monetary amount of the purchase in BigDecimal format
     * @return true if the operation could be completed, false otherwise
     */
    public boolean spend(BigDecimal amount) {
        //compareTo returns a negative value if the first BigDecimal has a value less than the second. Is zero if same
        if (amount.compareTo(this.wallet) <= 0) {
            this.wallet = this.wallet.subtract(amount);
            this.revenue = this.revenue.add(amount);
            return true;
        }
        return false;
    }


    /**
     * Returns a string representation of the current balance in the form of Quarters, Dimes, anc Nickels. Resets the
     * current balance to zero when called.
     *
     * @return String representation of the change. Returns an empty string if the current balance is zero.
     */
    public String getChange() {
        int changeInCents = this.wallet.multiply(new BigDecimal("100")).intValue();
        this.wallet = new BigDecimal(0);

        int quarters = changeInCents / 25;
        int dimes = (changeInCents % 25) / 10;
        int nickels = ((changeInCents % 25) % 10) / 5;

        String quarterOutput = (quarters > 0) ? quarters + " Quarter(s)" : "";
        String dimesOutput = (quarters > 0) ? dimes + " Dime(s)" : "";
        String nickelsOutput = (quarters > 0) ? nickels + " Nickel(s)" : "";

        String change = (quarters + dimes + nickels == 0) ? "No change to dispense!" : String.format("Your change: %s %s %s",
                quarterOutput, dimesOutput, nickelsOutput);

        return change;
    }

}
