package com.example.sick.foodinspiration.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

public class LoginActivity extends AppCompatActivity {
    
    // Declaring variables
    private Firebase myFirebaseRef;
    public User user;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    
    //FaceBook
    private CallbackManager callbackManager;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        
        // Initialize toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FaceBook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            
            // This will be called instead of FacebookCallback.onCancel() if any of the following conditions are true
            @Override
            public void onSuccess(LoginResult loginResult) {
                saveFacebookLoginData("facebook", loginResult.getAccessToken());
            }
            
            // This will becalled when the dialog is canceled
            @Override
            public void onCancel() {
            }

            // This will be called when a network or other error is encountered while logging in
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        //

        //Add Firebase Reference URL
        myFirebaseRef = new Firebase("https://food-inspiration.firebaseio.com/");

    }

    @Override
    protected void onStart() {
        super.onStart();
        
        // Initialize the following
        email = (EditText) findViewById(R.id.edit_text_email_id);
        password = (EditText) findViewById(R.id.edit_text_password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_login);
        checkUserLogin();
    }

    //FaceBook
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    

    protected void setUpUser() {
        user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
    }

    public void onSignUpClicked(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onLoginClicked(View view) {
        progressBar.setVisibility(View.VISIBLE);
        setUpUser();
        aunthenticateUserLogin();
    }

    //FaceBook
    public void onFacebookLogInClicked( View view ){
        LoginManager
                .getInstance()
                .logInWithReadPermissions(
                        this,
                        Arrays.asList("public_profile", "user_friends", "email")
                );
    }
    //

    private void checkUserLogin() {
        //getAuth Returns the current authentication state of the Firebase client. If the client is unauthenticated, this method will return null.
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
        //authWithPassword method attempts to authenticate to Firebase with the given credentials.
        //Paramters Are :
        // email - The email for the user to authenticate
        // password - The password for the account
        // handler - A handler which will be called with the result of the authentication attempt
        myFirebaseRef.authWithPassword(
                user.getEmail(),
                user.getPassword(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        //Log.i("TOKEN",authData.getToken());
                        //Log.i("PROVIDER",authData.getProvider());
                        //Log.i("UID",authData.getUid());
                        //Log.i("AUTH_MAP",authData.getAuth().toString());
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        String uid = myFirebaseRef.getAuth().getUid();
                        intent.putExtra("user_id", uid);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );
    }

    //FaceBook
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
                            Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else{
            myFirebaseRef.unauth();
        }
    }
}
