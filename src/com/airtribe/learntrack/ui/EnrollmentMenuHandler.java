package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;

/**
 * Handles all enrollment management menu operations.
 * Package-private - as we have used default access modifier, it is only accessible within the ui package.
 */
class EnrollmentMenuHandler {

    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final ConsoleHelper console;

    EnrollmentMenuHandler(EnrollmentService enrollmentService, 
                          StudentService studentService,
                          CourseService courseService,
                          ConsoleHelper console) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.console = console;
    }

    /**
     * Displays and handles the enrollment management sub-menu
     */
    void showMenu() {
        boolean inMenu = true;
        while (inMenu) {
            displayMenuOptions();
            int choice = console.getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    enrollStudentInCourse();
                    break;
                case 2:
                    viewAllEnrollments();
                    break;
                case 3:
                    viewEnrollmentsByStudent();
                    break;
                case 4:
                    viewEnrollmentsByCourse();
                    break;
                case 5:
                    markEnrollmentCompleted();
                    break;
                case 6:
                    cancelEnrollment();
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
        System.out.println("\n=========== ENROLLMENT MANAGEMENT ===========");
        System.out.println("  1. Enroll Student in Course");
        System.out.println("  2. View All Enrollments");
        System.out.println("  3. View Enrollments by Student");
        System.out.println("  4. View Enrollments by Course");
        System.out.println("  5. Mark Enrollment as Completed");
        System.out.println("  6. Cancel Enrollment");
        System.out.println("  0. Back to Main Menu");
        System.out.println("==============================================");
    }

    private void enrollStudentInCourse() {
        console.printHeader("Enroll Student in Course");

        // Show available students
        List<Student> students = studentService.getActiveStudents();
        if (students.isEmpty()) {
            System.out.println("No active students available. Please add a student first.");
            return;
        }
        System.out.println("\nAvailable Students:");
        for (Student s : students) {
            System.out.println("  ID: " + s.getId() + " - " + s.getDisplayName());
        }

        // Show available courses
        List<Course> courses = courseService.getActiveCourses();
        if (courses.isEmpty()) {
            System.out.println("No active courses available. Please add a course first.");
            return;
        }
        System.out.println("\nAvailable Courses:");
        for (Course c : courses) {
            System.out.println("  ID: " + c.getId() + " - " + c.getDisplayName());
        }

        int studentId = console.getIntInput("\nEnter Student ID: ");
        int courseId = console.getIntInput("Enter Course ID: ");

        try {
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            console.printSuccess("Student enrolled successfully!");
            displayEnrollmentDetails(enrollment);
        } catch (EntityNotFoundException | InvalidInputException e) {
            console.printError(e.getMessage());
        }
    }

    private void viewAllEnrollments() {
        console.printHeader("All Enrollments");
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found in the system.");
            return;
        }

        displayEnrollmentTable(enrollments);
    }

    private void viewEnrollmentsByStudent() {
        console.printHeader("View Enrollments by Student");
        int studentId = console.getIntInput("Enter Student ID: ");

        try {
            Student student = studentService.getStudentById(studentId);
            System.out.println("\nEnrollments for: " + student.getDisplayName());

            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this student.");
                return;
            }

            displayEnrollmentTable(enrollments);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void viewEnrollmentsByCourse() {
        console.printHeader("View Enrollments by Course");
        int courseId = console.getIntInput("Enter Course ID: ");

        try {
            Course course = courseService.getCourseById(courseId);
            System.out.println("\nEnrollments for: " + course.getCourseName());

            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this course.");
                return;
            }

            displayEnrollmentTable(enrollments);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void markEnrollmentCompleted() {
        console.printHeader("Mark Enrollment as Completed");
        int enrollmentId = console.getIntInput("Enter Enrollment ID: ");

        try {
            Enrollment enrollment = enrollmentService.completeEnrollment(enrollmentId);
            console.printSuccess("Enrollment marked as completed!");
            displayEnrollmentDetails(enrollment);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void cancelEnrollment() {
        console.printHeader("Cancel Enrollment");
        int enrollmentId = console.getIntInput("Enter Enrollment ID: ");

        try {
            Enrollment enrollment = enrollmentService.cancelEnrollment(enrollmentId);
            console.printSuccess("Enrollment cancelled!");
            displayEnrollmentDetails(enrollment);
        } catch (EntityNotFoundException e) {
            console.printError(e.getMessage());
        }
    }

    private void displayEnrollmentTable(List<Enrollment> enrollments) {
        System.out.println("\nTotal Enrollments: " + enrollments.size());
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("| ID | Student ID | Course ID | Enrollment Date | Status |");
        System.out.println("------------------------------------------------------------------------------");

        for (Enrollment enrollment : enrollments) {
            System.out.println("| " + enrollment.getId() + " | " + enrollment.getStudentId() + " | " + enrollment.getCourseId() + " | " + enrollment.getEnrollmentDate() + " | " + enrollment.getStatus().getDisplayName() + " |");
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    private void displayEnrollmentDetails(Enrollment enrollment) {
        System.out.println("\n+--------------------------------------+");
        System.out.println("| ENROLLMENT DETAILS                   |");
        System.out.println("+--------------------------------------+");
        System.out.println("| Enrollment ID: " + enrollment.getId());
        System.out.println("| Student ID:    " + enrollment.getStudentId());
        System.out.println("| Course ID:     " + enrollment.getCourseId());
        System.out.println("| Enrolled On:   " + enrollment.getEnrollmentDate());
        System.out.println("| Status:        " + enrollment.getStatus().getDisplayName());

        // Try to get student and course names for better display
        try {
            Student student = studentService.getStudentById(enrollment.getStudentId());
            System.out.println("| Student Name:  " + student.getDisplayName());
        } catch (EntityNotFoundException e) {
            // Student not found, skip
        }

        try {
            Course course = courseService.getCourseById(enrollment.getCourseId());
            System.out.println("| Course Name:   " + course.getCourseName());
        } catch (EntityNotFoundException e) {
            // Course not found, skip
        }

        System.out.println("+--------------------------------------+");
    }
}
