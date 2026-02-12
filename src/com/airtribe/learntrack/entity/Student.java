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
        super(validateId(id), requireNonEmpty(firstName, "First Name"), requireNonEmpty(lastName, "Last Name"), normalizeOptional(email));
        this.batch = normalizeOptional(batch);
        this.active = active;
    }

    /**
     * Constructor without email (demonstrates constructor overloading)
     */
    public Student(int id, String firstName, String lastName, String batch) {
        super(validateId(id), requireNonEmpty(firstName, "First Name"), requireNonEmpty(lastName, "Last Name"));
        this.batch = normalizeOptional(batch);
        this.active = true;
    }

    /**
     * Constructor with basic info only
     */
    public Student(int id, String firstName, String lastName) {
        super(validateId(id), requireNonEmpty(firstName, "First Name"), requireNonEmpty(lastName, "Last Name"));
        this.batch = null;
        this.active = true;
    }

    // Getters and Setters
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        String normalized = normalizeOptional(batch);
        if (normalized != null) {
            this.batch = normalized;
        }
    }

    public boolean isActive() {
        return active;
    }

    /**
     * Returns a new Student instance with the updated active status.
     * Implements immutability for state changes.
     */
    public Student withActive(boolean active) {
        return new Student(this.getId(), this.getFirstName(), this.getLastName(), this.getEmail(), this.batch, active);
    }

    private static int validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }
        return id;
    }

    private static String requireNonEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        return value.trim();
    }

    private static String normalizeOptional(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
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
        String email = getEmail();
        String emailDisplay = (email == null || email.isEmpty()) ? "N/A" : email;
        return "Student{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + emailDisplay + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}
