package com.example.sick.foodinspiration.howto;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.sick.foodinspiration.R;

/**
 * Created by Sick on 19-6-2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.griekse_aardappelen, R.drawable.pasta_met_spinazie_en_garnalen,
            R.drawable.zalm_spinazie, R.drawable.pasta_bloemkoolsaus,
            R.drawable.chevapcici
    };

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
        return imageView;
    }
}
