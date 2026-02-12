package com.airtribe.learntrack.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility class for generating unique IDs for entities.
 */
public class IdGenerator {

    // Static counters for each entity type
    private static final AtomicInteger studentIdCounter = new AtomicInteger(0);
    private static final AtomicInteger courseIdCounter = new AtomicInteger(0);
    private static final AtomicInteger enrollmentIdCounter = new AtomicInteger(0);
    private static final AtomicInteger trainerIdCounter = new AtomicInteger(0);

    // Private constructor to prevent instantiation
    private IdGenerator() {
        // Utility class - should not be instantiated
    }

    /**
     * Generates the next unique Student ID.
     * @return Next student ID
     */
    public static int getNextStudentId() {
        return studentIdCounter.incrementAndGet();
    }

    /**
     * Generates the next unique Course ID.
     * @return Next course ID
     */
    public static int getNextCourseId() {
        return courseIdCounter.incrementAndGet();
    }

    /**
     * Generates the next unique Enrollment ID.
     * @return Next enrollment ID
     */
    public static int getNextEnrollmentId() {
        return enrollmentIdCounter.incrementAndGet();
    }

    /**
     * Generates the next unique Trainer ID.
     * @return Next trainer ID
     */
    public static int getNextTrainerId() {
        return trainerIdCounter.incrementAndGet();
    }
}
