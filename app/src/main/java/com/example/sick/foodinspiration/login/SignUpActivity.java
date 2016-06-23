package com.example.sick.foodinspiration.login;

/** Assignment: Food Inspiration
 * Created by Amar Skenderovic on 9-6-2016.
 * Honor code: I pledge that this program represents my own program code. I received help from
 * (Android documentation, Facebook API, Firebase API, Stackoverflow, Library for the SwipeView from IntelliJ IDEA,
 * Hella Haanstra, Jaap van Bergeijk and Martijn Stegeman)in designing and debugging my program.
 */

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

        // Firebase Reference URL which will be used in  the SignUpActivity for saving users (database)
        myFirebaseRef =  new Firebase("https://food-inspiration.firebaseio.com/");

    }

    /*
    Method for initialising the account details when the user is going to start with account details
     */
    @Override
    protected void onStart() {
        super.onStart();
        name = (EditText) findViewById(R.id.edit_text_username);
        phoneNumber=(EditText) findViewById(R.id.edit_text_phone_number);
        email = (EditText) findViewById(R.id.edit_text_new_email);
        password = (EditText) findViewById( R.id.edit_text_new_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_sign_up);
    }


    /*
    Method for receiving user input
     */
    protected void setUpUser(){
        user = new User();
        user.setName(name.getText().toString());
        user.setPhoneNumber(phoneNumber.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
    }

    /*
    Method for when all the details are filled in and the user wants to sign up
     */
    public void onSignUpClicked(View view){
        progressBar.setVisibility(View.VISIBLE);

        // Using received input from user
        setUpUser();

        /*
        Method for CreateUser creates a new user account with the given email and password with different parameters
         */
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

                    /* Method for
                    Authenticated failed with error firebaseError
                     */
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
    }
}
