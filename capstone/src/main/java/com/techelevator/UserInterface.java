package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * UserInterface provides methods for displaying information to the console and returning valid input from the user.
 * Contain methods that will force the user to continue providing inputs until a valid input is received.
 */
public class UserInterface {

    /*
    ########################################   Attributes   ##########################################
     */

    private Scanner userInput = new Scanner(System.in);
    private String titleCard = "";

    /*
    ########################################  Other Methods  ##########################################
     */

    private void loadTitleCard() {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new File("TitleCard.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                stringBuilder.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        titleCard = stringBuilder.toString();
    }

    public void printTitleCard() {
        System.out.println(titleCard);
    }

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


    /**
     * Displays the provided prompt to the user, and obtains the next line of user input. If the user input is not a
     * positive integer value, displays an error message and re-displays the prompt until a valid value is provided.
     *
     * @param prompt the prompt to display to the user before processing input
     * @return user input as an int.
     */
    public int getPositiveInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String userValue = userInput.nextLine();
            if (isValidIntegerInput(userValue) && Integer.parseInt(userValue) >= 0) {
                return Integer.parseInt(userValue);

            }
            banner();
            System.out.println("Invalid input. Please provide a positive integer value.");
            banner();

        }
    }

    /**
     * Displays the provided header, and then the contents of a String Array containing menu prompts, numbering them
     * starting from 1, After displaying these values, obtains the user input. If the provided value is not a valid
     * integer, or does not match the number of an option, displays an error message and re-displays the prompt until a
     * valid value is provided. The method will not display the header if provided an empty String.
     *
     * @param options a String[] containing each option that the user can select
     * @param header the header to print before the presented user options
     * @return user input as an int.
     */
    public int getMenuSelection(String[] options, String header) {
        while (true) {

            if (header.length() > 0) {
                System.out.println(header + "\n");
            }

            for (int i = 0; i < options.length; i++) {
                if (options[i].length() > 0 && options[i].startsWith("-")) {
                    continue;
                }
                System.out.printf("(%d) %s\n", i + 1, options[i]);
            }

            System.out.print("\nPlease enter your selection from the options above: ");

            String selection = userInput.nextLine();
            if (isValidIntegerInput(selection)) {
                int selectionInt = Integer.parseInt(selection);
                if (selectionInt > 0 && selectionInt <= options.length) {
                    banner();
                    return selectionInt;
                }
            }

            banner();

            System.out.println("Invalid selection. Please try again.");

            banner();
        }
    }

    /**
     * Displays the contents of a String Array containing menu prompts, numbering them starting from 1, After displaying
     * these values, obtains the user input. If the provided value is not a valid integer, or does not match the number
     * of an option, displays an error message and re-displays the prompt until a valid value is provided.
     *
     * @param options a String[] containing each option that the user can select
     * @return user input as an int.
     */
    public int getMenuSelection(String[] options) {
        return this.getMenuSelection(options, "");
    }

    public void banner() {
        System.out.println("\n*****************************************************\n");
    }


    /**
     * Takes in a String value and determines if it can be converted into an Integer using the Integer.parseInt()
     * method.
     *
     * @param input the String to evaluate
     * @return true if the provided String can be parsed as an Integer, false otherwise
     */
    public static boolean isValidIntegerInput(String input) {
        //	Tries to convert the provided string to an integer value. If an invalid input is provided, catches the exception and returns false.
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public UserInterface() {
        loadTitleCard();
    }

}
