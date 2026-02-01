package com.airtribe.learntrack.entity;

/**
 * Student entity class extending Person.
 * Demonstrates inheritance, super keyword usage, and method overriding.
 */
public class Student extends Person {
    private String batch;
    private boolean active;

    /**
     * Default constructor
     */
    public Student() {
        super();
        this.active = true;
    }

    /**
     * Parameterized constructor with all fields including email
     */
    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    /**
     * Constructor without email (demonstrates constructor overloading)
     */
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName);
        this.batch = batch;
        this.active = true;
    }

    /**
     * Constructor with basic info only
     */
    public Student(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.batch = "";
        this.active = true;
    }

    // Getters and Setters
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Overrides Person's getDisplayName to include batch information.
     */
    @Override
    public String getDisplayName() {
        String baseName = super.getDisplayName();
        if (batch != null && !batch.isEmpty()) {
            return baseName + " (Batch: " + batch + ")";
        }
        return baseName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}
