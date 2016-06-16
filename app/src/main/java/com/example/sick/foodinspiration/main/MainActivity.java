package com.example.sick.foodinspiration.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
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

public class MainActivity extends Activity implements
        SwipeView.OnCardSwipedListener {

    private final static int CARDS_MAX_ELEMENTS = 5;

    private FrameLayout contentLayout;
    private SwipeView mSwipeView;
    private Firebase mRef;
    public ImageView imageLogo;
    public ImageView imageview;

    private int[] meals =                 {
            R.drawable.gevulde_avocados_met_ei,
            R.drawable.pasta_met_spinazie_en_garnalen,
            R.drawable.griekse_aardappelen,
            R.drawable.pasta_met_spinazie_en_gorgonzolasaus,
            R.drawable.zalm_spinazie};

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_view_demo);
        contentLayout = (FrameLayout) findViewById(R.id.contentLayout);
        imageLogo = (ImageView) findViewById(R.id.imageView3);
        imageview = (ImageView) findViewById(R.id.imageView);

        // Add the swipe view
        mSwipeView = new SwipeView(this, R.id.imgSwipeLike, R.id.imgSwipeNope,
                this);
        contentLayout.addView(mSwipeView);


        // Adding the cards initially with the maximum limits of cards.
        for (int i = 0; i < CARDS_MAX_ELEMENTS; i++) {
            addCard(i);
        }

    }

    /**
     * On clicked view.
     *
     * @param clickedView
     *            the clicked view
     */
    public void onClickedView(View clickedView) {
        switch (clickedView.getId()) {
            case R.id.imgDisLike: {
                mSwipeView.dislikeCard();
                break;
            }

            case R.id.imgLike: {
                mSwipeView.likeCard();
                break;
            }
        }
    }

    @Override
    public void onLikes() {
        imageview.setDrawingCacheEnabled(true); //Add this line.
        imageview.buildDrawingCache();
        Bitmap bm=imageview.getDrawingCache();

        OutputStream fOut = null;
        Uri outputFileUri;
        try {
            File root = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "folder_name" + File.separator);
            root.mkdirs();
            File sdImageMainDirectory = new File(root, "myPicName.jpg");
            outputFileUri = Uri.fromFile(sdImageMainDirectory);
            fOut = new FileOutputStream(sdImageMainDirectory);
            MediaScannerConnection.scanFile(this, new String[] { sdImageMainDirectory.getAbsolutePath() }, null, null);

        } catch (Exception e) {
            Toast.makeText(this, "Error occured. Please try again later.",
                    Toast.LENGTH_SHORT).show();
        }
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e){}
        System.out.println("An Card removed");
        // Add a card if you needed after any previous card swiped
        addCard(0);
    }

    @Override
    public void onDisLikes() {
        System.out.println("An Card removed");
        // Add a card if you needed after any previous card swiped
        addCard(0);
    }

    @Override
    public void onSingleTap() {

    }
    /**
     * Adds the card to the swipe.
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
        // Add a card to the swipe view..
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

    private void loadLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}