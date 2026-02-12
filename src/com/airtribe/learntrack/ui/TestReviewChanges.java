package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;


public class TestReviewChanges {

    public static void main(String[] args) {
        testStudentImmutability();
        testCourseValidation();
    }

    private static void testStudentImmutability() {
        System.out.println("--- Testing Student Immutability ---");
        try {
            Student student = new Student(1, "John", "Doe", "john@test.com", "Batch A", true);
            System.out.println("Original Student Active: " + student.isActive());

            // Create new instance with updated status
            Student deactivatedStudent = student.withActive(false);
            
            System.out.println("Original Student Active (After update call): " + student.isActive());
            System.out.println("New Student Active: " + deactivatedStudent.isActive());

            if (student.isActive() && !deactivatedStudent.isActive()) {
                System.out.println("[PASS] Student Immutability Verified");
            } else {
                System.out.println("[FAIL] Student Immutability Failed");
            }
        } catch (Exception e) {
            System.out.println("[FAIL] Exception in Student Test: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void testCourseValidation() {
        System.out.println("--- Testing Course Validation ---");
        Course course = new Course(1, "Java Basics", "Intro to Java", 4);

        // Test Course Name Validation
        try {
            course.setCourseName("");
            System.out.println("[FAIL] setCourseName allowed empty string");
        } catch (IllegalArgumentException e) {
            System.out.println("[PASS] setCourseName caught invalid input: " + e.getMessage());
        }

        // Test Description Validation
        try {
            course.setDescription("   ");
            System.out.println("[FAIL] setDescription allowed blank string");
        } catch (IllegalArgumentException e) {
            System.out.println("[PASS] setDescription caught invalid input: " + e.getMessage());
        }

        // Test Duration Validation
        try {
            course.setDurationInWeeks(-5);
            System.out.println("[FAIL] setDurationInWeeks allowed negative number");
        } catch (IllegalArgumentException e) {
            System.out.println("[PASS] setDurationInWeeks caught invalid input: " + e.getMessage());
        }
        System.out.println();
    }
}
