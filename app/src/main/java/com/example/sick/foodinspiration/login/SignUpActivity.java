package com.example.sick.foodinspiration.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.sick.foodinspiration.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.util.Map;

/**
 * Created by Sick on 5-6-2016.
 */

/* The SignUpActivity asks the user for input of account details. If the user wants to use the app, they will have the option to
 * create a new account. The Sign up works with the Firebase API, this means all the details of the account will be displayed in
 * the Firebase database.
 */
public class SignUpActivity extends AppCompatActivity {

    // Declaring variables
    private Firebase myFirebaseRef;
    private User user;
    private EditText name;
    private EditText phoneNumber;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Add Firebase Reference URL which will be used in combination with the SignUpActivity for saving users (database)
        myFirebaseRef =  new Firebase("https://food-inspiration.firebaseio.com/");

    }

    // Initialize the following when the user is going to start with account details
    @Override
    protected void onStart() {
        super.onStart();
        name = (EditText) findViewById(R.id.edit_text_username);
        phoneNumber=(EditText) findViewById(R.id.edit_text_phone_number);
        email = (EditText) findViewById(R.id.edit_text_new_email);
        password = (EditText) findViewById( R.id.edit_text_new_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_sign_up);
    }


    // Receiving user input
    protected void setUpUser(){
        user = new User();
        user.setName(name.getText().toString());
        user.setPhoneNumber(phoneNumber.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
    }

    // Method for when all the details are filled in and the user wants to sign up
    public void onSignUpClicked(View view){
        progressBar.setVisibility(View.VISIBLE);

        // Using received input from user
        setUpUser();

        // CreateUser method creates a new user account with the given email and password
        // Parameters are :
        // Email - The email for the account to be created
        // Password - The password for the account to be created
        // Handler - A handler which is called with the result of the operation
        myFirebaseRef.createUser(
                user.getEmail(),
                user.getPassword(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        user.setId(stringObjectMap.get("uid").toString());
                        user.saveUser();
                        myFirebaseRef.unauth();
                        Toast.makeText(getApplicationContext(), "Your Account has been Created", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Please Login With your Email and Password", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        finish();
                    }

                    // Authenticated failed with error firebaseError
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
    }
}
