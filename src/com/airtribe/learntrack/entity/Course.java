package com.airtribe.learntrack.entity;

/**
 * Course entity class representing a course in the system.
 */
public class Course {
    private long id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    /**
     * Default constructor
     */
    public Course() {
        this.active = true;
    }

    /**
     * Parameterized constructor with all fields
     */
    public Course(long id, String courseName, String description, int durationInWeeks, boolean active) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

    /**
     * Constructor with basic fields (active defaults to true)
     */
    public Course(long id, String courseName, String description, int durationInWeeks) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = true;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if(!validateString(courseName)) {
            throw new IllegalArgumentException("Course name cannot be null or empty");
        }
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(!validateString(description)) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        if(durationInWeeks <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.durationInWeeks = durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private boolean validateString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Returns a display-friendly representation of the course.
     */
    public String getDisplayName() {
        return courseName + " (" + durationInWeeks + " weeks)";
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", durationInWeeks=" + durationInWeeks +
                ", active=" + active +
                '}';
    }
}
