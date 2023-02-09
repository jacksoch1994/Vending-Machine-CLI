package com.techelevator;

import java.util.Scanner;

public class UserInterface {

    /*
    ########################################   Attributes   ##########################################
     */
    private Scanner userInput = new Scanner(System.in);

    /*
    ########################################  Other Methods  ##########################################
     */

    /**
     * Prints the provided string to the console.
     *
     * @param message the message to print to the screen as a String.
     */
    public void display(String message) {
        System.out.println(message);
    }

    /**
     * Obtains the next line of user Input to the console.
     *
     * @return user input as a String.
     */
    public String getString(String prompt) {
        System.out.print(prompt);
        return userInput.nextLine();
    }


    public int getPositiveInteger(String prompt) {
        while (true) {
            String userValue = userInput.nextLine();
            if (isValidIntegerInput(userValue) && Integer.parseInt(userValue) >= 0) {
                banner();
                return Integer.parseInt(userValue);

            }

            System.out.println("\nInvalid selection. Please try again.\n");
        }
    }

    public int getMenuSelection(String[] options, String header) {
        while (true) {

            banner();

            if (header.length() > 0) {
                System.out.println(header + "\n");
            }

            for (int i = 0; i < options.length; i++) {
                System.out.printf("(%d) %s\n", i + 1, options[i]);
            }

            banner();

            System.out.print("Please enter your selection from the options above: ");

            String selection = userInput.nextLine();
            if (isValidIntegerInput(selection)) {
                int selectionInt = Integer.parseInt(selection);
                if (selectionInt > 0 && selectionInt <= options.length) {
                    banner();
                    return selectionInt;
                }
            }

            System.out.println("\nInvalid selection. Please try again.\n");
        }
    }

    public int getMenuSelection(String[] options) {
        return this.getMenuSelection(options, "");
    }

    public void banner() {
        System.out.println("\n*****************************************************\n");
    }


    //	Tests to see if String can be converted by Integer.parseInt()
    public static boolean isValidIntegerInput(String input) {
        //	Tries to convert the provided string to an integer value. If an invalid input is provided, catches the exception and returns false.
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /*
    ########################################   Constructor   ##########################################
     */
}
