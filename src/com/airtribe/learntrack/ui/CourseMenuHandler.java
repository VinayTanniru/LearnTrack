package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;

import java.util.List;

/**
 * Handles all course management menu operations.
 * Package-private - as we have used default access modifier, it is only accessible within the ui package.
 */
class CourseMenuHandler {

    private final CourseService courseService;
    private final ConsoleHelper console;

    CourseMenuHandler(CourseService courseService, ConsoleHelper console) {
        this.courseService = courseService;
        this.console = console;
    }

    /**
     * Displays and handles the course management sub-menu
     */
    void showMenu() {
        boolean inMenu = true;
        while (inMenu) {
            displayMenuOptions();
            int choice = console.getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addNewCourse();
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    searchCourseById();
                    break;
                case 4:
                    updateCourse();
                    break;
                case 5:
                    activateCourse();
                    break;
                case 6:
                    deactivateCourse();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    console.printError("Invalid option. Please try again.");
            }
        }
    }

    private void displayMenuOptions() {
        System.out.println("\n============= COURSE MANAGEMENT =============");
        System.out.println("  1. Add New Course");
        System.out.println("  2. View All Courses");
        System.out.println("  3. Search Course by ID");
        System.out.println("  4. Update Course");
        System.out.println("  5. Activate Course");
        System.out.println("  6. Deactivate Course");
        System.out.println("  0. Back to Main Menu");
        System.out.println("==============================================");
    }

    private void addNewCourse() {
        console.printHeader("Add New Course");
        try {
            String courseName = console.getStringInput("Enter Course Name: ");
            String description = console.getStringInput("Enter Description: ");
            int duration = console.getIntInput("Enter Duration (in weeks): ");

            Course course = courseService.addCourse(courseName, description, duration);

            console.printSuccess("Course added successfully!");
            displayCourseDetails(course);

        } catch (InvalidInputException e) {
            console.printError(e.getMessage());
        }
    }

    private void viewAllCourses() {
        console.printHeader("All Courses");
        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found in the system.");
            return;
        }

        System.out.println("\nTotal Courses: " + courses.size());
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("| ID | Course Name | Description | Duration | Active |");
        System.out.println("---------------------------------------------------------------------------------");

        for (Course course : courses) {
            System.out.println("| " + course.getId() + " | " + ConsoleHelper.truncate(course.getCourseName(), 25) + " | " + ConsoleHelper.truncate(course.getDescription(), 30) + " | " + course.getDurationInWeeks() + " weeks | " + (course.isActive() ? "Yes" : "No") + " |");
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

    private void searchCourseById() {
        console.printHeader("Search Course by ID");
        int id = console.getIntInput("Enter Course ID: ");

        try {
            Course course = courseService.getCourseById(id);
            console.printSuccess("Course found!");
            displayCourseDetails(course);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void updateCourse() {
        console.printHeader("Update Course");
        int id = console.getIntInput("Enter Course ID to update: ");

        try {
            Course existing = courseService.getCourseById(id);
            System.out.println("Current details:");
            displayCourseDetails(existing);

            System.out.println("\nEnter new values (press Enter to keep current value, -1 for duration to keep):");
            String courseName = console.getStringInput("Course Name [" + existing.getCourseName() + "]: ");
            String description = console.getStringInput("Description [" + existing.getDescription() + "]: ");
            int duration = console.getIntInput("Duration in weeks [" + existing.getDurationInWeeks() + "] (enter -1 to keep): ");

            Course updated = courseService.updateCourse(id, courseName, description, duration);

            console.printSuccess("Course updated successfully!");
            displayCourseDetails(updated);

        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void activateCourse() {
        console.printHeader("Activate Course");
        int id = console.getIntInput("Enter Course ID to activate: ");

        try {
            Course course = courseService.activateCourse(id);
            console.printSuccess("Course activated successfully!");
            displayCourseDetails(course);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void deactivateCourse() {
        console.printHeader("Deactivate Course");
        int id = console.getIntInput("Enter Course ID to deactivate: ");

        try {
            Course course = courseService.deactivateCourse(id);
            console.printSuccess("Course deactivated successfully!");
            displayCourseDetails(course);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void displayCourseDetails(Course course) {
        System.out.println("\n+--------------------------------------+");
        System.out.println("| COURSE DETAILS                       |");
        System.out.println("+--------------------------------------+");
        System.out.println("| ID:          " + course.getId());
        System.out.println("| Name:        " + course.getCourseName());
        System.out.println("| Description: " + course.getDescription());
        System.out.println("| Duration:    " + course.getDurationInWeeks() + " weeks");
        System.out.println("| Status:      " + (course.isActive() ? "Active" : "Inactive"));
        System.out.println("+--------------------------------------+");
    }
}
