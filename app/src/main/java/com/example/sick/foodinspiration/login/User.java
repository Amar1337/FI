package com.example.sick.foodinspiration.login;

/** Assignment: Food Inspiration
 * Created by Amar Skenderovic on 5-6-2016.
 * Honor code: I pledge that this program represents my own program code. I received help from
 * (Android documentation, Facebook API, Firebase API, Stackoverflow, Library for the SwipeView from IntelliJ IDEA,
 * Hella Haanstra, Jaap van Bergeijk and Martijn Stegeman)in designing and debugging my program.
 */

import com.firebase.client.Firebase;

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
