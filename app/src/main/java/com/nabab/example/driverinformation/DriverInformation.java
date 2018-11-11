package com.nabab.example.driverinformation;

public class DriverInformation {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String mobileNumber;

    public DriverInformation() {

    }

    public DriverInformation(String firstName, String lastName, String address, String email, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
