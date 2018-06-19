package com.example.pc.campusapplication;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String email;
    public String firstName;
    public String lastName;
    public String token;
    @Exclude
    public String uid;

    public User() {

    }

    public User(String uid, String email, String firstName, String lastName, String token) {
        this.uid = uid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }
}
