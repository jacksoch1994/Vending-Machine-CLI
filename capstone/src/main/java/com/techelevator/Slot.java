package com.techelevator;

import java.math.BigDecimal;

/**
 * Representation of a Vending Machine slot that holds product. Provides methods that allow the user to obtain product
 * information and decrement the value of remaining product.
 */
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

    public String getVocalization() {
        String vocalization = "";
        switch (type.toLowerCase()) {
            case "chip":
                vocalization = "Crunch Crunch, Yum!";
                break;
            case "candy":
                vocalization = "Munch Munch, Yum!";
                break;
            case "drink":
                vocalization = "Glug Glug, Yum!";
                break;
            case "gum":
                vocalization = "Chew Chew, Yum!";
                break;
        }
        return vocalization;
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
     * Decrements the amount of product in stock by 1 if the product quantity is greater than 0.
     *
     * @return price of the vended item. Returns 0 if product quantity is 0.
     */
    public String vend() {
        if (hasProductInStock()) {
            this.quantity--;
            return getVocalization();
        } else {
            return "";
        }

    }

    /**
     * Returns a string representation of the object.
     *
     * @return String representation of the instance of Slot.
     */
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s", getProductName()
                , getPrice(), getType(), getQuantity());
    }

    /*
    ########################################   Constructor   ##########################################
     */

    /**
     * Creates a new Slot object.
     *
     * @param productName The name of the product contained in the Slot.
     * @param price       The price of 1 unit of the product in the Slot.
     * @param type        The type of product in the Slot.
     * @param quantity    The starting quantity of product in the Slot.
     */
    public Slot(String productName, BigDecimal price, String type, int quantity) {
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }


}
