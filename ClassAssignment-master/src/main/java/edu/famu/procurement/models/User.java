package edu.famu.procurement.models;

import com.fasterxml.jackson.databind.JsonNode;

public class User {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean approver;
    private Boolean active;

    public User(String employeeId, String firstName, String lastName, String phoneNumber, Boolean approver, Boolean active) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.approver = approver;
        this.active = active;
    }

    public User() {
    }

    public User(JsonNode employeeId, JsonNode firstName, JsonNode lastName, JsonNode phoneNumber, JsonNode approver, JsonNode active) {
        this.employeeId = employeeId.asText();
        this.firstName = firstName.asText();
        this.lastName = lastName.asText();
        this.phoneNumber = phoneNumber.asText();
        this.approver = approver.asBoolean();
        this.active = active.asBoolean();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getApprover() {
        return approver;
    }

    public void setApprover(Boolean approver) {
        this.approver = approver;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", approver=" + approver +
                ", active=" + active +
                '}';
    }
}
