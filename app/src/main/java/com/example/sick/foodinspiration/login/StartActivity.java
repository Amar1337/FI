package com.example.sick.foodinspiration.login;

/** Assignment: Food Inspiration
 * Created by Amar Skenderovic on 5-6-2016.
 * Honor code: I pledge that this program represents my own program code. I received help from
 * (Android documentation, Facebook API, Firebase API, Stackoverflow, Library for the SwipeView from IntelliJ IDEA,
 * Hella Haanstra, Jaap van Bergeijk and Martijn Stegeman)in designing and debugging my program.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sick.foodinspiration.R;
import com.example.sick.foodinspiration.cookbook.CookbookActivity;
import com.example.sick.foodinspiration.main.MainActivity;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* The StartActivity is the activity where the user can decide wether to immediatly start with the MainActivity, to
 * continue to the CookbookActivity or to Log out. But there is also happening allot on the background, after the user is logged in
 * there will appear a welcome message with the users registered/facebook name with a cool added thumbnail picture of their facebook
 * pro (if they're logged in with facebook that is).
 */
public class StartActivity extends AppCompatActivity {

    // Declaring Variables
    Firebase myFirebaseRef;
    TextView name;
    TextView welcomeText;
    ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);

        // Firebase Reference URL to maintain a valid authentication
        myFirebaseRef = new Firebase("https://food-inspiration.firebaseio.com/");
        setContentView(R.layout.activity_main);

        // Implementing the toolbar so the user has the option to logout
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // OnclickListener for going to the MainActivity
        Button start = (Button)findViewById(R.id.buttonstart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // OnclickListener for going to the CookbookActivity
        Button cookbook = (Button)findViewById(R.id.buttoncookbook);
        cookbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, CookbookActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        // Initialization for the users name, welcometext and profile picture
        name = (TextView) findViewById(R.id.text_view_name);
        welcomeText = (TextView) findViewById(R.id.text_view_welcome);
        profilePicture=(ImageView)findViewById(R.id.profile_picture);

        // Get the uid for the currently logged in User from intent data passed to this activity
        String uid = getIntent().getExtras().getString("user_id");

        // Getting the url of the facebook profile picture
        String imageUrl = getIntent().getExtras().getString("profile_picture");
        new ImageLoadTask(imageUrl,profilePicture).execute();

        // Referring to the name of the User who has logged in currently and adding a valueChangeListener
        myFirebaseRef.child("users").child(uid).child("name").addValueEventListener(new ValueEventListener() {

            // onDataChange is called every time the name of the User changes in the Firebase Database
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Inside onDataChange we can get the data as an Object from the dataSnapshot
                // getValue returns an Object and parameter added as type expected
                String data = dataSnapshot.getValue(String.class);
                name.setText("Hello "+data+", ");
            }
            // onCancelled is called in case of any error
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // A firebase reference to the welcomeText
        myFirebaseRef.child("welcomeText").addValueEventListener(new ValueEventListener() {

            // onDataChange is called every time the data changes in the Firebase Database
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Inside onDataChange we get the data as an Object from the dataSnapshot
                // getValue returns an Object. The type is specified by passing the type expected as a parameter
                String data = dataSnapshot.getValue(String.class);
                welcomeText.setText(data);
            }

            // onCancelled is called in case of any error
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Menu inflater added for logging out
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Logout option added
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // If the user wants to logout
        if (id == R.id.action_logout) {
            myFirebaseRef.unauth();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /* A class created for getting the facebook profile picture and displaying it
     * at the StartActivity.
     */
    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        // Declaring variables
        private String url;
        private ImageView imageView;

        // Load task from a certain url and imageview
        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        // URL object using void parameters, connecting to it, and using Android’s BitmapFactory class to decode the input stream.
        // The result is the desired Bitmap object
        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        // The result
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }
    }
}