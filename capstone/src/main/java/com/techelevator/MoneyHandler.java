package com.techelevator;

import java.math.BigDecimal;

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

    public void feed(int dollars) {
        if (dollars > 0) {
            this.wallet = this.wallet.add(new BigDecimal(dollars));
        }
    }


    public boolean spend(BigDecimal amount) {
        //compareTo returns a negative value if the first BigDecimal has a value less than the second. Is zero if same
        if (amount.compareTo(this.wallet) <= 0) {
            this.wallet = this.wallet.subtract(amount);
            this.revenue = this.revenue.add(amount);
            return true;
        }
        return false;
    }


    public String getChange() {
        int changeInCents = this.wallet.multiply(new BigDecimal("100")).intValue();
        this.wallet = new BigDecimal(0);

        int quarters = changeInCents / 25;
        int dimes = (changeInCents % 25) / 10;
        int nickels = ((changeInCents % 25) % 10) / 5;

        String quarterOutput = (quarters > 0) ? quarters + " Quarter(s)" : "";
        String dimesOutput = (quarters > 0) ? dimes + " Dime(s)" : "";
        String nickelsOutput = (quarters > 0) ? nickels + " Nickel(s)" : "";

        String change = (quarters + dimes + nickels == 0) ? "" : String.format("Your change: %s %s %s",
                quarterOutput, dimesOutput, nickelsOutput);

        return change;


    }
}
