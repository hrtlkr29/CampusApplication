package com.example.pc.campusapplication;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private String uid;

    public User() {

    }

    public User(String uid, String email, String firstName, String lastName, String token) {
        this.setUid(uid);
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setToken(token);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
