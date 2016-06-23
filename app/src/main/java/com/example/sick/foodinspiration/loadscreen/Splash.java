package com.example.sick.foodinspiration.loadscreen;

/** Assignment: Food Inspiration
 * Created by Amar Skenderovic on 19-6-2016.
 * Honor code: I pledge that this program represents my own program code. I received help from
 * (Android documentation, Facebook API, Firebase API, Stackoverflow, Library for the SwipeView from IntelliJ IDEA,
 * Hella Haanstra, Jaap van Bergeijk and Martijn Stegeman)in designing and debugging my program.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.sick.foodinspiration.R;
import com.example.sick.foodinspiration.login.LoginActivity;

/* Class created for having a more professional design. The Splash class handles a simple "loading screen" that will be
 * displayed for a certain amount of time before heading to the login/register page.
 * After the display length is finished the activity will automatically continue to the LoginActivity.
 */
public class Splash extends Activity {

    //Duration of wait
    private final int SPLASH_DISPLAY_LENGTH = 1100;

    // Called when the activity is first created
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_layout);

        // New Handler to start the Menu-Activity and close this Splash-Screen after some seconds
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                // Create an Intent that will start the Menu-Activity
                Intent mainIntent = new Intent(Splash.this,LoginActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}