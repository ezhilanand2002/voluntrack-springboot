package com.voluntrack.dto;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role; // VOLUNTEER, CHARITY, DONOR, ADMIN

    // Volunteer Profile fields
    private String skills;
    private String location;
    private String availability;

    // Charity Profile fields
    private String orgName;
    private String regNumber;

    public RegisterRequest() {}

    public RegisterRequest(String name, String email, String password, String role, String skills, String location, String availability, String orgName, String regNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.skills = skills;
        this.location = location;
        this.availability = availability;
        this.orgName = orgName;
        this.regNumber = regNumber;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getOrgName() { return orgName; }
    public void setOrgName(String orgName) { this.orgName = orgName; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }
}
