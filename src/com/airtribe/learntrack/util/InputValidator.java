package com.airtribe.learntrack.util;

/**
 * Utility class for validating user input.
 * Contains static helper methods for common validation scenarios.
 */
public class InputValidator {

    // Private constructor to prevent instantiation
    private InputValidator() {
        // Utility class - should not be instantiated
    }

    /**
     * Checks if a string is non-empty (not null and not blank).
     * @param value The string to check
     * @return true if the string is non-empty, false otherwise
     */
    public static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Checks if a number is positive (greater than zero).
     * @param value The number to check
     * @return true if positive, false otherwise
     */
    public static boolean isPositiveNumber(int value) {
        return value > 0;
    }

    /**
     * Trims and sanitizes a string input.
     * @param value The string to sanitize
     * @return Trimmed string or empty string if null
     */
    public static String sanitize(String value) {
        return value == null ? "" : value.trim();
    }
}
