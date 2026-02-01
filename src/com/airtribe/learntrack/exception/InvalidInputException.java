package com.airtribe.learntrack.exception;

/**
 * Custom exception thrown when user input is invalid.
 * Examples: Empty name, invalid email format, negative duration.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructor with message
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Convenience factory method for empty field
     */
    public static InvalidInputException emptyField(String fieldName) {
        return new InvalidInputException(fieldName + " cannot be empty.");
    }

    /**
     * Convenience factory method for invalid email
     */
    public static InvalidInputException invalidEmail() {
        return new InvalidInputException("Invalid email format.");
    }

    /**
     * Convenience factory method for invalid number
     */
    public static InvalidInputException invalidNumber(String fieldName) {
        return new InvalidInputException(fieldName + " must be a valid positive number.");
    }

    /**
     * Convenience factory method for invalid menu option
     */
    public static InvalidInputException invalidMenuOption() {
        return new InvalidInputException("Invalid menu option. Please try again.");
    }
}
