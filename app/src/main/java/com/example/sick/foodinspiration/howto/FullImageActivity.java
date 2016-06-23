package com.example.sick.foodinspiration.howto;

/** Assignment: Food Inspiration
 * Created by Amar Skenderovic on 9-6-2016.
 * Honor code: I pledge that this program represents my own program code. I received help from
 * (Android documentation, Facebook API, Firebase API, Stackoverflow, Library for the SwipeView from IntelliJ IDEA,
 * Hella Haanstra, Jaap van Bergeijk and Martijn Stegeman)in designing and debugging my program.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.sick.foodinspiration.R;

/** The FullImageActivity that is created for putting content in images that are being clicked from the CookbookActivity.
 * The first three images are hardcoded to represent what the application should do if the app would work with an API.
 */
public class FullImageActivity extends ActionBarActivity {

    private ListView mListView1, mListView2, mListView3, mListView4;

    // Hardcoded text for inside the listview
    private String [] data1 ={"2 avocado’s", "4 eieren",
            "peper en zout", "snuf chili vlokken",
            "zout", "peper",
            "2 plakken ontbijtspek"};

    private String [] data2 ={"Verwarm de oven op 200 graden.",
            "Snijd de avocado’s doormidden en verwijder de pit.",
            "Leg ze in een ovenschaal. ",
            "Schep met een lepeltje het gat van de pit nog iets dieper. ",
            "Kluts in iedere avocado een ei. ",
            "Je kunt ook het ei eerst in een kommetje doen en dan voorzichtig in het gat laten glijden.",
            "Serveer de pasta met wat geraspte parmezaanse kaas. ",
                    "Bestrooi met peper, zout en chili. ",
                    "Snijd het spek in reepjes en verdeel over de avocado’s. ",
                    "Bak ze 15 minuten in de oven tot het ei gestold is. ",
                    "Je kunt ze ook iets langer er in laten als je van een harde dooier houd."};

    private String [] data3 ={"2 avocado’s", "4 eieren",
            "peper en zout", "snuf chili vlokken",
            "zout", "peper",
            "2 plakken ontbijtspek"};
    private String [] data4 ={"Verwarm de oven op 200 graden.",
            "Snijd de avocado’s doormidden en verwijder de pit.",
            "Leg ze in een ovenschaal. ",
            "Schep met een lepeltje het gat van de pit nog iets dieper. ",
            "Kluts in iedere avocado een ei. ",
            "Je kunt ook het ei eerst in een kommetje doen en dan voorzichtig in het gat laten glijden.",
            "Serveer de pasta met wat geraspte parmezaanse kaas. ",
            "Bestrooi met peper, zout en chili. ",
            "Snijd het spek in reepjes en verdeel over de avocado’s. ",
            "Bak ze 15 minuten in de oven tot het ei gestold is. ",
            "Je kunt ze ook iets langer er in laten als je van een harde dooier houd."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        // Initialize the imageview where the images from the ImageAdapter will be put in
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);

        // Get the position of the images
        imageView.setImageResource(imageAdapter.mThumbIds[position]);

        // Initialize the listviews
        mListView1 = (ListView)findViewById(R.id.listView1);
        mListView2 = (ListView)findViewById(R.id.listView2);

        // Set the strings inside of the listview
        mListView1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1));
        mListView2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data2));

        // Initialize the listutils for the visual of the listviews
        ListUtils.setDynamicHeight(mListView1);
        ListUtils.setDynamicHeight(mListView2);
    }

    /** 
     * ListUtils is a class that add some more effect to the listviews
     */
    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = MeasureSpec.makeMeasureSpec(mListView.getWidth(), MeasureSpec.UNSPECIFIED);

            // Constraints
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.setFocusable(false);
            mListView.requestLayout();
        }
    }
}
