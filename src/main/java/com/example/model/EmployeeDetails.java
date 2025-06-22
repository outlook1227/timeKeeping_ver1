package com.example.model;

public class EmployeeDetails {
    private int id;
    private String username;
    private String fullname;
    private String dateofbirth;
    private String address;
    private String hiredate;
    private String profilepicture;

    // Constructors
    public EmployeeDetails(int i, String username) {}
    public EmployeeDetails(int id, String username, String fullname, String dateofbirth, String address, String hiredate, String profilepicture) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.dateofbirth = dateofbirth;
        this.address = address;
        this.hiredate = hiredate;
        this.profilepicture = profilepicture;
    }
    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return username;
    }
    public void setUserName(String userName) {
        this.username = userName;
    }
    public String getFullName() {
        return fullname;
    }
    public void setFullName(String fullName) {
        this.fullname = fullName;
    }
    public String getDateOfBirth() {
        return dateofbirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateofbirth = dateOfBirth;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getHireDate() {
        return hiredate;
    }
    public void setHireDate(String hireDate) {
        this.hiredate = hireDate;
    }
    public String getProfilePicture() {
        return profilepicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilepicture = profilePicture;
    }
}
