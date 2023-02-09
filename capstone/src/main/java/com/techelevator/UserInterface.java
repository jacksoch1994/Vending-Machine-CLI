package com.techelevator;

import java.util.Scanner;

public class UserInterface {
     /*
    ####################################### Constant Values ##########################################
     */

    /*
    ########################################   Attributes   ##########################################
     */
   private Scanner userInput = new Scanner(System.in);
    /*
    ######################################## Getter Methods ##########################################
     */

    /*
    ######################################## Setter Methods ##########################################
     */

    /*
    ########################################  Other Methods  ##########################################
     */

    public void display(String message) {
        System.out.println(message);
    }

    public String getString() {
        return userInput.nextLine();
    }

    public int getMenuSelection(String[] options) {
        while (true) {
            for (int i = 0; i < options.length; i++) {
                System.out.printf("(%d) %s", i + 1, options[i]);
            }
            String selection = userInput.nextLine();
            if (isValidIntegerInput(selection)) {
                int selectionInt = Integer.parseInt(selection);
                if (selectionInt > 0 && selectionInt <= options.length) {
                    return selectionInt;
                }
            }
            System.out.println("Invalid selection. Please try again.");
        }
    }

    //	Tests to see if String can be converted by Integer.parseInt()
    public static boolean isValidIntegerInput(String input) {
        //	Tries to convert the provided string to an integer value. If an invalid input is provided, catches the exception and returns false.
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    /*
    ########################################   Constructor   ##########################################
     */
}
