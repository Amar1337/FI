package com.example.sick.foodinspiration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.firebase.client.Firebase;
import com.rk.lib.view.SwipeView;

public class MainActivity extends Activity implements
        SwipeView.OnCardSwipedListener {

    private final static int CARDS_MAX_ELEMENTS = 5;

    private FrameLayout contentLayout;
    private SwipeView mSwipeView;
    private Firebase mRef;

    private int[] meals =                 {
            R.drawable.gevulde_avocados_met_ei,
            R.drawable.pasta_met_spinazie_en_garnalen,
            R.drawable.griekse_aardappelen,
            R.drawable.pasta_met_spinazie_en_gorgonzolasaus,
            R.drawable.zalm_spinazie,
            R.drawable.pasta_bloemkoolsaus,
            R.drawable.paella,
            R.drawable.zweedseballen_salade,
            R.drawable.rode_curry_met_runderreepjes,
            R.drawable.tonijnburger};

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_view_demo);
        contentLayout = (FrameLayout) findViewById(R.id.contentLayout);

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
            default:
                break;
        }
    }

    @Override
    public void onLikes() {
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
        final ImageView imgBike = (ImageView) cardView
                .findViewById(R.id.imgMeals);
        imgBike.setImageResource(meals[count]);
        count++;
        if (count == meals.length) {
            count = 0;
        }
        // Add a card to the swipe view.
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