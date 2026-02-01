package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.StudentService;

import java.util.ArrayList;

/**
 * Handles all student management menu operations.
 * Package-private - as we have used default access modifier, it is only accessible within the ui package.
 */
class StudentMenuHandler {

    private final StudentService studentService;
    private final ConsoleHelper console;

    StudentMenuHandler(StudentService studentService, ConsoleHelper console) {
        this.studentService = studentService;
        this.console = console;
    }

    /**
     * Displays and handles the student management sub-menu
     */
    void showMenu() {
        boolean inMenu = true;
        while (inMenu) {
            displayMenuOptions();
            int choice = console.getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deactivateStudent();
                    break;
                case 6:
                    reactivateStudent();
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
        System.out.println("\n============ STUDENT MANAGEMENT ============");
        System.out.println("  1. Add New Student");
        System.out.println("  2. View All Students");
        System.out.println("  3. Search Student by ID");
        System.out.println("  4. Update Student");
        System.out.println("  5. Deactivate Student");
        System.out.println("  6. Reactivate Student");
        System.out.println("  0. Back to Main Menu");
        System.out.println("=============================================");
    }

    private void addNewStudent() {
        console.printHeader("Add New Student");
        try {
            String firstName = console.getStringInput("Enter First Name: ");
            String lastName = console.getStringInput("Enter Last Name: ");
            String email = console.getStringInput("Enter Email (optional, press Enter to skip): ");
            String batch = console.getStringInput("Enter Batch: ");

            // Now always pass all 4 parameters (service handles empty values)
            Student student = studentService.addStudent(firstName, lastName, email, batch);

            console.printSuccess("Student added successfully!");
            displayStudentDetails(student);

        } catch (InvalidInputException e) {
            console.printError(e.getMessage());
        }
    }

    private void viewAllStudents() {
        console.printHeader("All Students");
        ArrayList<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        System.out.println("\nTotal Students: " + students.size());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| ID    | Name                 | Email                    | Batch      | Active |");
        System.out.println("--------------------------------------------------------------------------------");

        for (Student student : students) {
                    System.out.println(
                "| " + student.getId()
                + " | " + student.getDisplayName()
                + " | " + student.getEmail()
                + " | " + student.getBatch()
                + " | " + student.isActive()
                + " |"
            );
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    private void searchStudentById() {
        console.printHeader("Search Student by ID");
        int id = console.getIntInput("Enter Student ID: ");

        try {
            Student student = studentService.getStudentById(id);
            console.printSuccess("Student found!");
            displayStudentDetails(student);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void updateStudent() {
        console.printHeader("Update Student");
        int id = console.getIntInput("Enter Student ID to update: ");

        try {
            Student existing = studentService.getStudentById(id);
            System.out.println("Current details:");
            displayStudentDetails(existing);

            System.out.println("\nEnter new values (press Enter to keep current value):");
            String firstName = console.getStringInput("First Name [" + existing.getFirstName() + "]: ");
            String lastName = console.getStringInput("Last Name [" + existing.getLastName() + "]: ");
            String email = console.getStringInput("Email [" + existing.getEmail() + "]: ");
            String batch = console.getStringInput("Batch [" + existing.getBatch() + "]: ");

            Student updated = studentService.updateStudent(id, firstName, lastName, email, batch);

            console.printSuccess("Student updated successfully!");
            displayStudentDetails(updated);

        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void deactivateStudent() {
        console.printHeader("Deactivate Student");
        int id = console.getIntInput("Enter Student ID to deactivate: ");

        try {
            Student student = studentService.deactivateStudent(id);
            console.printSuccess("Student deactivated successfully!");
            displayStudentDetails(student);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void reactivateStudent() {
        console.printHeader("Reactivate Student");
        int id = console.getIntInput("Enter Student ID to reactivate: ");

        try {
            Student student = studentService.reactivateStudent(id);
            console.printSuccess("Student reactivated successfully!");
            displayStudentDetails(student);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void displayStudentDetails(Student student) {
        System.out.println("\n+--------------------------------------+");
        System.out.println("| STUDENT DETAILS                      |");
        System.out.println("+--------------------------------------+");
        System.out.println("| ID:         " + student.getId());
        System.out.println("| Name:       " + student.getDisplayName());
        System.out.println("| Email:      " + (student.getEmail().isEmpty() ? "N/A" : student.getEmail()));
        System.out.println("| Batch:      " + (student.getBatch().isEmpty() ? "N/A" : student.getBatch()));
        System.out.println("| Status:     " + (student.isActive() ? "Active" : "Inactive"));
        System.out.println("+--------------------------------------+");
    }
}
