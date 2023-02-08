package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    private Map<String, Slot> inventory = new HashMap<>();


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
                        splitEntry[SOURCE_FILE_SLOT_NUMBER_INDEX],
                        splitEntry[SOURCE_FILE_PRODUCT_NAME_INDEX],
                        splitEntry[SOURCE_FILE_PRODUCT_PRICE_INDEX],
                        splitEntry[SOURCE_FILE_PRODUCT_TYPE_INDEX],
                        SLOT_CAPACITY
                );
                inventory.put(newSlot.getSlotNumber(), newSlot);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Source file not found.");
        }

    }

    /*
    ########################################   Constructor   ##########################################
     */

    public Inventory() {
        stockInventory();
    }

}



