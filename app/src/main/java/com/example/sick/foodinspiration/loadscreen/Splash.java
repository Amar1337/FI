package com.example.sick.foodinspiration.loadscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.sick.foodinspiration.R;
import com.example.sick.foodinspiration.login.LoginActivity;

/**
 * Created by Sick on 19-6-2016.
 */

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