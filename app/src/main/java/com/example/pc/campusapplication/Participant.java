package com.example.pc.campusapplication;

public class Participant {
    private String firstName;
    private String lastName;
    private String uid;

    public Participant(String firstName, String lastName, String uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uid = uid;
    }

    public Participant(){
        this.firstName = "";
        this.lastName = "";
        this.uid = "";
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
