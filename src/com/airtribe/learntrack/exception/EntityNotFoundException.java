package com.airtribe.learntrack.exception;

/**
 * Custom exception thrown when a requested entity is not found.
 * Examples: Student not found by ID, Course not found by ID.
 */
public class EntityNotFoundException extends Exception {

    /**
     * Constructor with message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Convenience factory method for Student not found
     */
    public static EntityNotFoundException studentNotFound(int id) {
        return new EntityNotFoundException("Student with ID " + id + " not found.");
    }

    /**
     * Convenience factory method for Course not found
     */
    public static EntityNotFoundException courseNotFound(int id) {
        return new EntityNotFoundException("Course with ID " + id + " not found.");
    }

    /**
     * Convenience factory method for Enrollment not found
     */
    public static EntityNotFoundException enrollmentNotFound(int id) {
        return new EntityNotFoundException("Enrollment with ID " + id + " not found.");
    }
}
