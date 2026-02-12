package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service class for managing Enrollment entities.
 * Handles student-course enrollment operations using ArrayList as in-memory storage.
 */
public class EnrollmentService {

    // In-memory storage using ArrayList
    private List<Enrollment> enrollments;

    // Reference to other services for validation
    private StudentService studentService;
    private CourseService courseService;

    /**
     * Constructor initializes the enrollments list and service references
     */
    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollments = new ArrayList<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * Enrolls a student in a course.
     * 
     * @param studentId The ID of the student to enroll
     * @param courseId The ID of the course to enroll in
     * @return The created Enrollment object
     * @throws EntityNotFoundException if student or course is not found
     * @throws InvalidInputException if student is already enrolled in the course
     */
    public Enrollment enrollStudent(int studentId, int courseId) 
            throws EntityNotFoundException, InvalidInputException {
        if (!InputValidator.isPositiveNumber(studentId)) {
            throw InvalidInputException.invalidNumber("Student ID");
        }
        if (!InputValidator.isPositiveNumber(courseId)) {
            throw InvalidInputException.invalidNumber("Course ID");
        }

        // Validate that student exists
        studentService.getStudentById(studentId);
        // Validate that course exists
        courseService.getCourseById(courseId);
        
        // Check if already enrolled with ACTIVE status
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId 
                    && enrollment.getCourseId() == courseId 
                    && enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
                throw new InvalidInputException("Student is already enrolled in this course.");
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        LocalDate enrollmentDate = LocalDate.now();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, enrollmentDate, EnrollmentStatus.ACTIVE);
        enrollments.add(enrollment);
        return enrollment;
    }

    /**
     * Retrieves an enrollment by its ID.
     * 
     * @param id The enrollment ID to search for
     * @return The Enrollment object
     * @throws EntityNotFoundException if enrollment is not found
     */
    public Enrollment getEnrollmentById(int id) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        throw EntityNotFoundException.enrollmentNotFound(id);
    }

    /**
     * Retrieves all enrollments in the system.
     * 
     * @return List of all enrollments
     */
    public List<Enrollment> getAllEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    /**
     * Retrieves all enrollments for a specific student.
     * 
     * @param studentId The student ID to search for
     * @return List of enrollments for the student
     * @throws EntityNotFoundException if student is not found
     */
    public List<Enrollment> getEnrollmentsByStudent(int studentId) throws EntityNotFoundException {
        // Validate that student exists
        studentService.getStudentById(studentId);
        
        ArrayList<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }

    /**
     * Retrieves all enrollments for a specific course.
     * 
     * @param courseId The course ID to search for
     * @return List of enrollments for the course
     * @throws EntityNotFoundException if course is not found
     */
    public List<Enrollment> getEnrollmentsByCourse(int courseId) throws EntityNotFoundException {
        // Validate that course exists
        courseService.getCourseById(courseId);
        
        ArrayList<Enrollment> courseEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId() == courseId) {
                courseEnrollments.add(enrollment);
            }
        }
        return courseEnrollments;
    }

    /**
     * Updates the status of an enrollment.
     * 
     * @param enrollmentId The enrollment ID to update
     * @param newStatus The new status
     * @return The updated Enrollment object
     * @throws EntityNotFoundException if enrollment is not found
     */
    public Enrollment updateEnrollmentStatus(int enrollmentId, EnrollmentStatus newStatus) 
            throws EntityNotFoundException {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        enrollment.setStatus(newStatus);
        return enrollment;
    }

    /**
     * Marks an enrollment as completed.
     * 
     * @param enrollmentId The enrollment ID to complete
     * @return The updated Enrollment object
     * @throws EntityNotFoundException if enrollment is not found
     */
    public Enrollment completeEnrollment(int enrollmentId) throws EntityNotFoundException {
        return updateEnrollmentStatus(enrollmentId, EnrollmentStatus.COMPLETED);
    }

    /**
     * Cancels an enrollment.
     * 
     * @param enrollmentId The enrollment ID to cancel
     * @return The updated Enrollment object
     * @throws EntityNotFoundException if enrollment is not found
     */
    public Enrollment cancelEnrollment(int enrollmentId) throws EntityNotFoundException {
        return updateEnrollmentStatus(enrollmentId, EnrollmentStatus.CANCELLED);
    }
}
