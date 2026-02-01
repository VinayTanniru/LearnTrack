package com.airtribe.learntrack.ui;

import java.util.Scanner;

/**
 * Utility class for console input/output operations.
 * Package-private - only accessible within the ui package.
 */
class ConsoleHelper {

    private final Scanner scanner;

    ConsoleHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Gets a string input from the user
     */
    String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Gets an integer input from the user with error handling.
     * Keeps prompting until valid input is received.
     */
    int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printError("Please enter a valid number.");
            }
        }
    }

    /**
     * Prints a success message
     */
    void printSuccess(String message) {
        System.out.println("\n[SUCCESS] " + message);
    }

    /**
     * Prints an error message
     */
    void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    /**
     * Prints a section header
     */
    void printHeader(String title) {
        System.out.println("\n--- " + title + " ---");
    }

    /**
     * Truncates a string to a maximum length
     */
    static String truncate(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }
}
