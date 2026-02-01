package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;

/**
 * Service class for managing Student entities.
 * Handles all CRUD operations for students using ArrayList as in-memory storage.
 */
public class StudentService {

    // In-memory storage using ArrayList
    private ArrayList<Student> students;

    /**
     * Constructor initializes the students list
     */
    public StudentService() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds a new student with the provided details.
     * Sanitizes all inputs before validation and storage.
     * 
     * @param firstName Student's first name (required)
     * @param lastName Student's last name (required)
     * @param email Student's email (optional, can be null or empty)
     * @param batch Student's batch (optional, can be null or empty)
     * @return The created Student object
     * @throws InvalidInputException if required fields are empty
     */
    public Student addStudent(String firstName, String lastName, String email, String batch) 
            throws InvalidInputException {
        // Sanitize inputs first
        firstName = InputValidator.sanitize(firstName);
        lastName = InputValidator.sanitize(lastName);
        email = InputValidator.sanitize(email);
        batch = InputValidator.sanitize(batch);

        // Validate required fields
        if (!InputValidator.isNonEmpty(firstName)) {
            throw InvalidInputException.emptyField("First Name");
        }
        if (!InputValidator.isNonEmpty(lastName)) {
            throw InvalidInputException.emptyField("Last Name");
        }

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch, true);
        students.add(student);
        return student;
    }

    /**
     * Retrieves a student by their ID.
     * 
     * @param id The student ID to search for
     * @return The Student object
     * @throws EntityNotFoundException if student is not found
     */
    public Student getStudentById(int id) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw EntityNotFoundException.studentNotFound(id);
    }

    /**
     * Retrieves all students in the system.
     * 
     * @return ArrayList of all students
     */
    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Retrieves only active students.
     * 
     * @return ArrayList of active students
     */
    public ArrayList<Student> getActiveStudents() {
        ArrayList<Student> activeStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.isActive()) {
                activeStudents.add(student);
            }
        }
        return activeStudents;
    }

    /**
     * Updates an existing student's information.
     * 
     * @param id Student ID to update
     * @param firstName New first name (or null to keep existing)
     * @param lastName New last name (or null to keep existing)
     * @param email New email (or null to keep existing)
     * @param batch New batch (or null to keep existing)
     * @return The updated Student object
     * @throws EntityNotFoundException if student is not found
     */
    public Student updateStudent(int id, String firstName, String lastName, String email, String batch) 
            throws EntityNotFoundException {
        Student student = getStudentById(id);
        
        if (InputValidator.isNonEmpty(firstName)) {
            student.setFirstName(firstName);
        }
        if (InputValidator.isNonEmpty(lastName)) {
            student.setLastName(lastName);
        }
        if (InputValidator.isNonEmpty(email)) {
            student.setEmail(email);
        }
        if (InputValidator.isNonEmpty(batch)) {
            student.setBatch(batch);
        }
        
        return student;
    }

    /**
     * Deactivates a student (soft delete).
     * Instead of removing, we set active = false.
     * 
     * @param id Student ID to deactivate
     * @return The deactivated Student object
     * @throws EntityNotFoundException if student is not found
     */
    public Student deactivateStudent(int id) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setActive(false);
        return student;
    }

    /**
     * Reactivates a previously deactivated student.
     * 
     * @param id Student ID to reactivate
     * @return The reactivated Student object
     * @throws EntityNotFoundException if student is not found
     */
    public Student reactivateStudent(int id) throws EntityNotFoundException {
        Student student = getStudentById(id);
        student.setActive(true);
        return student;
    }
}
