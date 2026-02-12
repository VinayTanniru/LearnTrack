package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing Course entities.
 * Handles all CRUD operations for courses using ArrayList as in-memory storage.
 */
public class CourseService {

    // In-memory storage using List
    private List<Course> courses;

    /**
     * Constructor initializes the courses list
     */
    public CourseService() {
        this.courses = new ArrayList<>();
    }

    /**
     * Adds a new course with the provided details.
     * Sanitizes all inputs before validation and storage.
     * 
     * @param courseName Course name (required)
     * @param description Course description (optional, can be null or empty)
     * @param durationInWeeks Course duration in weeks (required, must be positive)
     * @return The created Course object
     * @throws InvalidInputException if validation fails
     */
    public Course addCourse(String courseName, String description, int durationInWeeks) 
            throws InvalidInputException {
        // Sanitize inputs first (defense in depth)
        courseName = InputValidator.sanitize(courseName);
        description = InputValidator.sanitize(description);

        // Validate required fields
        if (!InputValidator.isNonEmpty(courseName)) {
            throw InvalidInputException.emptyField("Course Name");
        }
        if (!InputValidator.isPositiveNumber(durationInWeeks)) {
            throw InvalidInputException.invalidNumber("Duration");
        }

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks, true);
        courses.add(course);
        return course;
    }

    /**
     * Retrieves a course by its ID.
     * 
     * @param id The course ID to search for
     * @return The Course object
     * @throws EntityNotFoundException if course is not found
     */
    public Course getCourseById(int id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw EntityNotFoundException.courseNotFound(id);
    }

    /**
     * Retrieves all courses in the system.
     * 
     * @return List of all courses
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    /**
     * Retrieves only active courses.
     * 
     * @return List of active courses
     */
    public List<Course> getActiveCourses() {
        ArrayList<Course> activeCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.isActive()) {
                activeCourses.add(course);
            }
        }
        return activeCourses;
    }

    /**
     * Updates an existing course's information.
     * 
     * @param id Course ID to update
     * @param courseName New course name (or null to keep existing)
     * @param description New description (or null to keep existing)
     * @param durationInWeeks New duration (or -1 to keep existing)
     * @return The updated Course object
     * @throws EntityNotFoundException if course is not found
     */
    public Course updateCourse(int id, String courseName, String description, int durationInWeeks) 
            throws EntityNotFoundException {
        Course course = getCourseById(id);
        
        if (course != null &&InputValidator.isNonEmpty(courseName)) {
            course.setCourseName(courseName);
        }
        if (InputValidator.isNonEmpty(description)) {
            course.setDescription(description);
        }
        if (durationInWeeks > 0) {
            course.setDurationInWeeks(durationInWeeks);
        }
        
        return course;
    }

    /**
     * Activates a course.
     * 
     * @param id Course ID to activate
     * @return The activated Course object
     * @throws EntityNotFoundException if course is not found
     */
    public Course activateCourse(int id) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setActive(true);
        return course;
    }

    /**
     * Deactivates a course (soft delete).
     * 
     * @param id Course ID to deactivate
     * @return The deactivated Course object
     * @throws EntityNotFoundException if course is not found
     */
    public Course deactivateCourse(int id) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setActive(false);
        return course;
    }
}
