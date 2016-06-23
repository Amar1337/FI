package com.example.sick.foodinspiration.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.sick.foodinspiration.cookbook.CookbookActivity;
import com.example.sick.foodinspiration.login.Constants;
import com.example.sick.foodinspiration.login.LoginActivity;
import com.example.sick.foodinspiration.R;
import com.firebase.client.Firebase;
import com.rk.lib.view.SwipeView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/** Assignment: Food Inspiration
 * Created by Amar Skenderovic on 5-6-2016.
 * Honor code: I pledge that this program represents my own program code. I received help from
 * (Android documentation, Facebook API, Firebase API, Stackoverflow, Library for the SwipeView from IntelliJ IDEA,
 * Hella Haanstra, Jaap van Bergeijk and Martijn Stegeman)in designing and debugging my program.
 */

/* The MainActivity is the activity that shows the basic principle of Tinder. You can swipe to the left to dislike and
 * to the right to like a meal. But you also have the option to dislike and like via clicking on buttons (imageviews to be more
 * precise)The liked images get converted into bitmap and then saved on your phone. I chose for this option because the images
 * are hardcoded and I thought it would be easier this way. It wasn't possible to get a API for mealpictures, ingredients and
 * how-to. The images are getting retrieved in the next Activity called the CookbookActivity where all the "matches" are stored.
 */
public class MainActivity extends Activity implements SwipeView.OnCardSwipedListener {

    // Declaring variables
    private final static int CARDS_MAX_ELEMENTS = 5;
    private FrameLayout contentLayout;
    private SwipeView mSwipeView;
    private Firebase mRef;
    public ImageView imageLogo;
    public ImageView imageview;

    // Creating array of meals, getting them from the drawable folder
    private int[] meals = {
            R.drawable.pasta_met_spinazie_en_gorgonzolasaus,
            R.drawable.zalm_spinazie,
            R.drawable.rode_curry_met_runderreepjes,
            R.drawable.tonijnburger,
            R.drawable.zweedseballen_salade,
            R.drawable.pasta_bloemkoolsaus,
            R.drawable.paella
    };

    // Declared array for keeping track of what cardview is on top
    private ArrayList<View> cards = new ArrayList<View>();

    // Declaring a counter for the next method
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_view_demo);

        // Initialization for the logo that will be displayed as well as the meals
        contentLayout = (FrameLayout) findViewById(R.id.contentLayout);
        imageLogo = (ImageView) findViewById(R.id.imageView3);

        // Add the swipe view
        mSwipeView = new SwipeView(this, R.id.imgSwipeLike, R.id.imgSwipeNope, this);
        contentLayout.addView(mSwipeView);

        // Adding the cards initially with the maximum limits of cards.
        for (int i = 0; i < CARDS_MAX_ELEMENTS; i++) {
            addCard(i);
        }
    }

    /*
    Method for getting the topcard and converting it to a bitmap. Then saving it on the SD card and naming it with a do-while loop.
    After naming it, the SD card will be updated
     */
    public void saveCards(){

        // Removing the cardview that is on top
        View cardView = this.cards.remove(0);

        // Initializing the imagview
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imgMeals);

        // Convert from imageview to bitmap
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bm = drawable.getBitmap();

        OutputStream fOut = null;
        try {
            // Save on my sd card
            File root = new File(Environment.getExternalStorageDirectory()
                    // Making a folder name Food Inspiration
                    + File.separator + "Food Inspiration" + File.separator);
            root.mkdirs();
            File sdImageMainDirectory = null;

            // Loop for having a different name for every image
            int i = 0;
            do {
                sdImageMainDirectory = new File(root, "pic-" + i + ".png");
                i++;
            } while (sdImageMainDirectory.exists());
            fOut = new FileOutputStream(sdImageMainDirectory);

            // Updates the gallery of your phone with the folder and the "liked" images in it
            MediaScannerConnection.scanFile(this, new String[] { sdImageMainDirectory.getAbsolutePath() }, null, null);

            // If something goes wrong
        } catch (Exception e) {
            Toast.makeText(this, "Error occured. Please try again later.",
                    Toast.LENGTH_SHORT).show();
        }
        // Compresses the actual bitmap image
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e){}
    }

    /*
    Method for handling the onclick of like and dislike
     */
    public void onClickedView(View clickedView) {
        switch (clickedView.getId()) {

            // If the imageview of dislike is clicked
            case R.id.imgDisLike: {
                // The imageview in the contentlayout will be swiped to the left
                mSwipeView.dislikeCard();
                break;
            }

            // If the imageview of like is clicked
            case R.id.imgLike: {
                // The imageview in the contentlayout will be swiped to the right
                mSwipeView.likeCard();
                break;
            }
        }
    }

    /*
    Method for when a picture is being liked as well as swiped to the right
     */
    public void onLikes() {
        saveCards();
        // The imageview in the contentlayout will be swiped to the right
        System.out.println("An Card removed");
        // Add a card if you needed after any previous card swiped
        addCard(0);
    }

    /*
    Method if image Disliked the card goes to the left
     */
    @Override
    public void onDisLikes() {
        System.out.println("An Card removed");
        // Add a card if you needed after any previous card swiped
        addCard(0);
    }

    /*
    Calling onSingleTap from library that if the user taps, to slightly move the image
     */
    @Override
    public void onSingleTap() {

    }

    /*
    Adds the card to the swipe. Also puts the list of 'meals' in the imageview from the xml file
    called item_swipe_view then increments them in the cardview.
     */
    private void addCard(int position) {
        final View cardView = LayoutInflater.from(this).inflate(
                R.layout.item_swipe_view, null);
        final ImageView imgMeal = (ImageView) cardView
                .findViewById(R.id.imgMeals);
        imgMeal.setImageResource(meals[count]);
        count++;
        if (count == meals.length) {
            count = 0;
        }

        // Viewing the card that is on top (referring to saveCards();)
        this.cards.add(cardView);
        // Add a card to the swipe view
        mSwipeView.addCard(cardView, position);

        // Create OnClickListener for the CookBookActivity
        // Declare Button for the Cookbook
        Button btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CookbookActivity.class));
            }
        });

        // Check Authentication
        mRef = new Firebase(Constants.FIREBASE_URL);
        if (mRef.getAuth() == null) {
            loadLoginView();
        }
    }

    // Authentication process
    private void loadLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}