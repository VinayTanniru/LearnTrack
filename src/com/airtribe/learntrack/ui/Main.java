package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.Scanner;

/**
 * Main class - Entry point for LearnTrack application.
 * Delegates menu handling to specialized handler classes.
 */
public class Main {

    public static void main(String[] args) {
        // Initialize shared resources
        Scanner scanner = new Scanner(System.in);
        ConsoleHelper console = new ConsoleHelper(scanner);

        // Initialize services
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

        // Initialize menu handlers
        StudentMenuHandler studentMenu = new StudentMenuHandler(studentService, console);
        CourseMenuHandler courseMenu = new CourseMenuHandler(courseService, console);
        EnrollmentMenuHandler enrollmentMenu = new EnrollmentMenuHandler(
                enrollmentService, studentService, courseService, console);

        // Welcome message
        System.out.println("============================================");
        System.out.println("   Welcome to LearnTrack Management System  ");
        System.out.println("============================================");
        System.out.println();

        // Main application loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = console.getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    studentMenu.showMenu();
                    break;
                case 2:
                    courseMenu.showMenu();
                    break;
                case 3:
                    enrollmentMenu.showMenu();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThank you for using LearnTrack. Goodbye!");
                    break;
                default:
                    console.printError("Invalid option. Please try again.\n");
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu options
     */
    private static void displayMainMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("               MAIN MENU                    ");
        System.out.println("--------------------------------------------");
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Enrollment Management");
        System.out.println("  0. Exit");
        System.out.println("--------------------------------------------");
    }
}
