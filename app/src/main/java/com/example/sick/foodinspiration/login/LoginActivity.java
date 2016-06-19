package com.example.sick.foodinspiration.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.sick.foodinspiration.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.util.Arrays;

/**
 * Created by Sick on 5-6-2016.
 */

/**
 * Login activity where the user has the option to login via facebook (API) or register via the registerpage (Firebase API) and
 * to login with the registered account. Every facebook login and firebase login/register are automatically updated in the Firebase
 * database.
 * Once logged in the user will go the StartActivity --> see class StartActivity in login folder.
 */
public class LoginActivity extends AppCompatActivity {

    // Declaring Variables
    private Firebase myFirebaseRef;
    public User user;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;

    // FaceBook
    private CallbackManager callbackManager;

    // Called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        // Add Firebase Reference URL which will be used in combination with facebook for saving users (database)
        myFirebaseRef = new Firebase("https://food-inspiration.firebaseio.com/");

        // Facebook initialization
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                saveFacebookLoginData("facebook", loginResult.getAccessToken());
            }

            // on cancel stops the callback
            @Override
            public void onCancel() {
            }

            // On error stops the callback
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Initialize email, password and progressbar on start of the activity
    @Override
    protected void onStart() {
        super.onStart();
        email = (EditText) findViewById(R.id.edit_text_email_id);
        password = (EditText) findViewById(R.id.edit_text_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_login);
        checkUserLogin();
    }

    // The method that should be called from the Activity's or Fragment's onActivityResult method.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    // Method for user for setting up user, email and password
    protected void setUpUser() {
        user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
    }

    // Intent for going to SignUpActivity
    public void onSignUpClicked(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    // Method for login after credentials are verified
    public void onLoginClicked(View view) {
        progressBar.setVisibility(View.VISIBLE);
        setUpUser();
        aunthenticateUserLogin();
    }

    // Facebook method for logging in via facebook
    public void onFacebookLogInClicked( View view ){
        LoginManager
                .getInstance()
                .logInWithReadPermissions(
                        this,
                        Arrays.asList("public_profile", "user_friends", "email")
                );
    }

    private void checkUserLogin() {

        // GetAuth Returns the current authentication state of the Firebase client. If the client is unauthenticated, this method will return null.
        // Otherwise, the return value will be an object containing at least the fields such as uid,provider,token,expires,auth
        if (myFirebaseRef.getAuth() != null) {
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            String uid = myFirebaseRef.getAuth().getUid();
            intent.putExtra("user_id", uid);
            startActivity(intent);
            finish();
        }
    }

    private void aunthenticateUserLogin() {
        // AuthWithPassword method attempts to authenticate to Firebase with the given credentials.
        // Parameters Are :
        // email - The email for the user to authenticate
        // password - The password for the account
        // handler - A handler which will be called with the result of the authentication attempt
        myFirebaseRef.authWithPassword(
                user.getEmail(),
                user.getPassword(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        String uid = myFirebaseRef.getAuth().getUid();
                        intent.putExtra("user_id", uid);
                        startActivity(intent);
                        finish();
                    }

                    // If the authentication fails reply with a toast
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
    }

    // Method for implementing Facebook with the Firebase database
    private void saveFacebookLoginData(String provider, AccessToken accessToken){
        String token=accessToken.getToken();
        setUpUser();
        if( token != null ){

            myFirebaseRef.authWithOAuthToken(
                    provider,
                    token,
                    new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {

                            // Authenticated successfully with payload authData
                            String uid=authData.getUid();
                            String name=authData.getProviderData().get("displayName").toString();
                            String email=authData.getProviderData().get("email").toString();
                            String image=authData.getProviderData().get("profileImageURL").toString();
                            user.setId(authData.getUid());
                            user.setName(name);
                            user.setEmail(email);
                            user.saveUser();
                            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                            intent.putExtra("user_id",uid);
                            intent.putExtra("profile_picture",image);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {

                            // Authenticated failed with error firebaseError
                            Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else{
            // Calling unauth() invalidates user's token, and logs them out
            myFirebaseRef.unauth();
        }
    }
}
