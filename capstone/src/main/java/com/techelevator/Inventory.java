package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
    ######################################## Setter Methods ##########################################
     */

    /*
    ########################################  Other Methods  ##########################################
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

    public BigDecimal purchase(String slotNumber) {
        if (inventory.containsKey(slotNumber)) {
            return inventory.get(slotNumber).vend();
        } else {
            return new BigDecimal(0);
        }
    }

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



    /*
    ########################################   Constructor   ##########################################
     */

    public Inventory() {
        stockInventory();
    }

}



