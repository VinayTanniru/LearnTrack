package com.airtribe.learntrack.entity;

/**
 * Trainer entity class extending Person.
 */
public class Trainer extends Person {
    private String specialization;

    /**
     * Default constructor
     */
    public Trainer() {
        super();
    }

    /**
     * Parameterized constructor with all fields
     */
    public Trainer(int id, String firstName, String lastName, String email, String specialization) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
    }

    // Getters and Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Overrides Person's getDisplayName to include specialization.
     * Demonstrates polymorphism.
     */
    @Override
    public String getDisplayName() {
        String baseName = super.getDisplayName();
        if (specialization != null && !specialization.isEmpty()) {
            return baseName + " - " + specialization + " Trainer";
        }
        return baseName + " (Trainer)";
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
