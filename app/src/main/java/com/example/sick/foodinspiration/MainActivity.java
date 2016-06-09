package com.example.sick.foodinspiration;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.firebase.client.Firebase;

import at.markushi.ui.CircleButton;

public class MainActivity extends Activity {

    // Declaring variables
    private Firebase mRef;
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] mealname;
    int[] mealpicture;
    CircleButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare DislikeButton named view
        imgButton =(CircleButton)findViewById(R.id.view);
        // Declare LikeButton named viewnew
        imgButton =(CircleButton) findViewById(R.id.viewnew);
        // Declare Button for the Cookbook
        Button btn = (Button) findViewById(R.id.button3);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When clicked change the currentitem to the next item
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                // Show toast when ImageButton pressed
                Toast.makeText(getApplicationContext(),"Disliked :(",Toast.LENGTH_LONG).show();
            }
        });

        // Set OnClickListener
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // When clicked change the currentitem to the next item
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);

                // Show toast when ImageButton pressed
                Toast.makeText(getApplicationContext(),"Liked :)",Toast.LENGTH_LONG).show();
            }
        });

        // Hardcoded strings for the mealnames
        mealname = new String[] { "Gevulde avocado met ei", "Pasta met spinazie en garnalen", "Griekse aardappelen", "Pasta met spinazie en gorgonzolasaus",
                                "Zalm spinazie", "Pasta bloemkoolsaus", "Paella met kip en gamba’s", "Zweedse balletjes met aardappelsalade", "Rode curry met runderreepjes", "Tonijnburger met frisse dip" };

        // Hardcoded images for the meals in the viewpager
        mealpicture = new int[] {
                R.drawable.gevulde_avocados_met_ei,
                R.drawable.pasta_met_spinazie_en_garnalen,
                R.drawable.griekse_aardappelen,
                R.drawable.pasta_met_spinazie_en_gorgonzolasaus,
                R.drawable.zalm_spinazie,
                R.drawable.pasta_bloemkoolsaus,
                R.drawable.paella,
                R.drawable.zweedseballen_salade,
                R.drawable.rode_curry_met_runderreepjes,
                R.drawable.tonijnburger
        };

        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(MainActivity.this, mealname, mealpicture);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        // Create OnClickListener for the CookBookActivity
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CookbookActivity.class));
            }
        });

        // Check Authentication
        mRef = new Firebase(com.example.sick.foodinspiration.Constants.FIREBASE_URL);
        if (mRef.getAuth() == null) {
            loadLoginView();
        }
    }

    private void loadLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
