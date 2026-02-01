package com.airtribe.learntrack.entity;

/**
 * Enrollment entity class representing a student's enrollment in a course.
 * Links students to courses with enrollment date and status tracking.
 */
public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private EnrollmentStatus status;

    /**
     * Default constructor
     */
    public Enrollment() {
        this.status = EnrollmentStatus.ACTIVE;
    }

    /**
     * Parameterized constructor with all fields
     */
    public Enrollment(int id, int studentId, int courseId, String enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    /**
     * Constructor with default ACTIVE status
     */
    public Enrollment(int id, int studentId, int courseId, String enrollmentDate) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = EnrollmentStatus.ACTIVE;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate='" + enrollmentDate + '\'' +
                ", status=" + status +
                '}';
    }
}
