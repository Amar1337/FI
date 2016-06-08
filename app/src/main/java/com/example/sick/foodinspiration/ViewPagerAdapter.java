package com.example.sick.foodinspiration;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    String[] mealname;
    int[] mealpicture;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, String[] mealname, int[] mealpicture) {
        this.context = context;
        this.mealname = mealname;
        this.mealpicture = mealpicture;
    }

    @Override
    public int getCount() {
        return mealpicture.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txtmealname;
        ImageView imgmeal;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtmealname = (TextView) itemView.findViewById(R.id.mealname);

        // Capture position and set to the TextViews
        txtmealname.setText(mealname[position]);

        // Locate the ImageView in viewpager_item.xml
        imgmeal = (ImageView) itemView.findViewById(R.id.mealpicture);
        // Capture position and set to the ImageView
        imgmeal.setImageResource(mealpicture[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}