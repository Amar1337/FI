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

/** The Activity that is creating for putting content in images that are being clicked from the CookbookActivity.
 * The first three images are hardcoded to represent what the application should do if the app would work with an API.
 */
public class FullImageActivity extends ActionBarActivity {

    private ListView mListView1, mListView2;

    // Hardcoded text for inside the listview
    private String [] data1 ={"2 uien", "2 teentjes knoflook",
            "1 ei", "500 gr rundergehakt (of 250 gr rund en 250 gr lam of 250 gr varkensgehakt)",
            "zout", "peper",
            "3 eetlepels fijngehakte peterselie", "1 eetlepel tomatenpuree",
            "1 eetlepel mosterd", "snufje cayennepeper",
            "2 eetlepels bloem", "olie, om in te bakken"};
    private String [] data2 ={"Breng een pan met water aan de kook en kook de spaghetti volgens de verpakking gaar.",
            "Verhit een beetje olie in een pan en bak de garnalen 2 minuutjes en bestrooi ze ondertussen met een beetje peper en zout.",
            "Haal de garnalen uit de pan. Snipper de ui en de knoflook en fruit aan in dezelfde pan.",
            "Voeg de spinazie toe en verwarm deze in de pan. Roer de creme fraiche en de pesto er door.",
            "Proef of de saus nog een snufje peper of zout nodig heeft. Schep dan ook de spaghetti en garnalen er door.",
            "Serveer de pasta met wat geraspte parmezaanse kaas. "};

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

    /** ListUtils is a class that add some more effect to the listviews
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