package com.example.sick.foodinspiration.howto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.sick.foodinspiration.cookbook.ImageAdapter;
import com.example.sick.foodinspiration.R;

public class FullImageActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        String[] recipe = {
                "2 uien", "2 teentjes knoflook",
                "1 ei", "500 gr rundergehakt (of 250 gr rund en 250 gr lam of 250 gr varkensgehakt)",
                "zout", "peper",
                "3 eetlepels fijngehakte peterselie", "1 eetlepel tomatenpuree",
                "1 eetlepel mosterd", "snufje cayennepeper",
                "2 eetlepels bloem", "olie, om in te bakken"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, recipe );
        getListView().setAdapter(adapter);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
    }

}