package com.example.sick.foodinspiration.login;

import com.firebase.client.Firebase;

/**
 * Created by Sick on 5-6-2016.
 */

/* A user class that is created for receiving string details and updating the firebase
 * database with these strings.
 */
public class User {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;


    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void saveUser() {

        // Added Firebase Reference URL
        Firebase myFirebaseRef = new Firebase("https://food-inspiration.firebaseio.com/");
        myFirebaseRef = myFirebaseRef.child("users").child(getId());
        myFirebaseRef.setValue(this);
    }

}
