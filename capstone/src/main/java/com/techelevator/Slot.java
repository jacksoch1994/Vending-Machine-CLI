package com.techelevator;

import java.math.BigDecimal;

public class Slot {

    /*
    ########################################   Attributes   ##########################################
     */

    private String productName;
    private BigDecimal price;
    private String type;
    private int quantity;

    /*
    ######################################## Getter Methods ##########################################
     */


    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    /*
    ########################################  Other Methods  ##########################################
     */

    /**
     * Returns a boolean value indicating if there are any products remaining in the Slot.
     *
     * @return true if there is product remaining in the slot, false if there is no product remaining in the slot
     */
    public boolean hasProductInStock() {
        return quantity > 0;
    }

    /**
     * TBD
     *
     * @return TBD
     */
    public BigDecimal vend() {
        if (hasProductInStock()) {
            this.quantity--;
            return getPrice();
        } else {
            return new BigDecimal(0);
        }

    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s", getProductName()
        , getPrice(), getType(), getQuantity());
    }

    /*
    ########################################   Constructor   ##########################################
     */

    public Slot(String productName, BigDecimal price, String type, int quantity) {
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }



}
