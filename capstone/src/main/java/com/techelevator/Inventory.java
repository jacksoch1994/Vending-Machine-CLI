package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
/**
 * Representation of a Vending Machine inventory consisting of the machine's slots. Reads from an input file to
 * create initial inventory upon instantiation. Provides methods that allow the user to provide a slot number and
 * access/manipulate the values of slots at those locations.
 */
public class Inventory {


    /*
    ####################################### Constant Values ##########################################
     */

    private final int SLOT_CAPACITY = 5;
    private final File STOCK_SOURCE_FILE = new File("vendingmachine.csv");
    private final int SOURCE_FILE_SLOT_NUMBER_INDEX = 0;
    private final int SOURCE_FILE_PRODUCT_NAME_INDEX = 1;
    private final int SOURCE_FILE_PRODUCT_PRICE_INDEX = 2;
    private final int SOURCE_FILE_PRODUCT_TYPE_INDEX = 3;


    /*
    ########################################   Attributes   ##########################################
     */

    private Map<String, Slot> inventory = new TreeMap<>();


    /*
    ######################################## Getter Methods ##########################################
     */

    public Map<String, Slot> getInventory() {
        return inventory;
    }


    /*
    ########################################  Other Methods  ##########################################
     */

    /**
     * Reads from the STOCK_SOURCE_FILE and populates the inventory with Slot objects.
     */
    private void stockInventory() {
        try (Scanner dataInput = new Scanner(STOCK_SOURCE_FILE)) {
            while (dataInput.hasNextLine()) {
                String entry = dataInput.nextLine();
                String[] splitEntry = entry.split("\\|");
                Slot newSlot = new Slot(
                        splitEntry[SOURCE_FILE_PRODUCT_NAME_INDEX],
                        new BigDecimal(splitEntry[SOURCE_FILE_PRODUCT_PRICE_INDEX]),
                        splitEntry[SOURCE_FILE_PRODUCT_TYPE_INDEX],
                        SLOT_CAPACITY
                );
                inventory.put(splitEntry[SOURCE_FILE_SLOT_NUMBER_INDEX], newSlot);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Source file not found.");
        }
    }
    
    /**
     * Decrements the quantity of the product in the slot by 1. Returns a "vocalization" based on the type of product in
     * the slot.
     *
     * @param slotNumber The slot number of the item to consume as a String.
     * @return the vocalization associated with the product's type
     */
    public String consume(String slotNumber) {
        if (inventory.containsKey(slotNumber)) {
            return inventory.get(slotNumber).vend();
        } else {
            return "";
        }
    }

    /**
     * Returns the price of the item in the specified slot as a BigDecimal
     *
     * @param slotNumber The slot number of the item to obtain the price of as a String.
     * @return the price of the purchased item as a BigDecimal. Returns a BigDecimal with a value of 0 if the product
     * cannot be purchased.
     */
    public BigDecimal costOf(String slotNumber) {
        if (inventory.containsKey(slotNumber)) {
            return inventory.get(slotNumber).getPrice();
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Returns the name of the product in the specified slot as a String.
     *
     * @param slotNumber The slot number of the item to view the name of as a String.
     * @return the name of the product in the specified slot
     */
    public String nameOf(String slotNumber) {
        if (inventory.containsKey(slotNumber)) {
            return inventory.get(slotNumber).getProductName();
        } else {
            return "";
        }
    }

    /**
     * Returns a boolean value indicating if there are any products remaining in the Slot.
     *
     * @param slotNumber The slot number of the item to check as a String.
     * @return true if the slot has products remaining, false otherwise
     */
    public boolean isInStock(String slotNumber) {
        if (inventory.containsKey(slotNumber)) {
            return inventory.get(slotNumber).hasProductInStock();
        } else {
            return false;
        }
    }

    /**
     * Returns a boolean indicating if the specified slot number exists in Inventory.
     *
     * @param slotNumber the slot number to check for validity
     * @return true if the slot number exists in inventory, false if not
     */
    public boolean isValidSlotNumber(String slotNumber) {
        return inventory.containsKey(slotNumber);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String representation of the instance of Inventory.
     */
    @Override
    public String toString() {
        //StringBuilder to append to for output
        StringBuilder output = new StringBuilder();
        //Make NumberFormat to format quantity into dollar amounts
        NumberFormat form = NumberFormat.getCurrencyInstance();

        for (String key : this.inventory.keySet()) {
            Slot slot = inventory.get(key);

            //Format quantity based on number remaining
            String qty = (slot.hasProductInStock()) ? slot.getQuantity() + " left" : "SOLD OUT";

            //Format price to reflect currency format
            String price = form.format(slot.getPrice());

            String line = String.format("(%s) - %-25s %-8s %s\n",
                    key,
                    slot.getProductName(),
                    price,
                    qty);
            output.append(line);
        }

        return output.toString();
    }

    public String salesReport() {
        StringBuilder output = new StringBuilder();
        for (String key : inventory.keySet()) {

            String name = nameOf(key);
            int quantityRemaining = inventory.get(key).getQuantity();

            String line = String.format("%s|%d\n", name, SLOT_CAPACITY - quantityRemaining);
            output.append(line);
        }
        return output.toString();
    }




    /*
    ########################################   Constructor   ##########################################
     */

    public Inventory() {
        stockInventory();
    }

}



