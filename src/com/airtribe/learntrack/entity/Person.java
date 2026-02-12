package com.airtribe.learntrack.entity;

/**
 * Base class representing a person in the LearnTrack system.
 * Demonstrates inheritance foundation and encapsulation.
 */
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Default constructor
     */
    public Person() {
    }

    /**
     * Parameterized constructor with all fields
     */
    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Constructor without email (demonstrates constructor overloading)
     */
    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = null;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a display-friendly name.
     * This method is designed to be overridden by subclasses (polymorphism).
     */
    protected String getDisplayName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
