package com.airtribe.learntrack.util;

/**
 * Utility class for generating unique IDs for entities.
 */
public class IdGenerator {

    // Static counters for each entity type
    private static int studentIdCounter = 0;
    private static int courseIdCounter = 0;
    private static int enrollmentIdCounter = 0;
    private static int trainerIdCounter = 0;

    // Private constructor to prevent instantiation
    private IdGenerator() {
        // Utility class - should not be instantiated
    }

    /**
     * Generates the next unique Student ID.
     * @return Next student ID
     */
    public static int getNextStudentId() {
        return ++studentIdCounter;
    }

    /**
     * Generates the next unique Course ID.
     * @return Next course ID
     */
    public static int getNextCourseId() {
        return ++courseIdCounter;
    }

    /**
     * Generates the next unique Enrollment ID.
     * @return Next enrollment ID
     */
    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }

    /**
     * Generates the next unique Trainer ID.
     * @return Next trainer ID
     */
    public static int getNextTrainerId() {
        return ++trainerIdCounter;
    }
}
