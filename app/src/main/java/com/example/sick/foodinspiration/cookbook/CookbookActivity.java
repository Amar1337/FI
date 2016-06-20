package com.example.sick.foodinspiration.cookbook;

import java.io.File;
import java.util.ArrayList;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.sick.foodinspiration.R;
import com.example.sick.foodinspiration.howto.FullImageActivity;
/**
 * Created by Sick on 9-6-2016.
 */

/* The CookbookActivity is a gallery made to represent all the likes from the MainActivity. They are being retrieved from the SD card
 * and shown in 2 rows. The user can long click a single gridview item to delete it and it will also permanently be deleted from the
 * SD card.
 */
public class CookbookActivity extends Activity {

    AsyncTaskLoadFiles myAsyncTaskLoadFiles;

    /* Asynctask the get the files that are being saved in the sd card
     */
    public class AsyncTaskLoadFiles extends AsyncTask<Void, String, Void> {

        // Declare variables
        File targetDirector;
        ImageAdapter myTaskAdapter;

        public AsyncTaskLoadFiles(ImageAdapter adapter) {
            myTaskAdapter = adapter;
        }

        @Override
        protected void onPreExecute() {
            String ExternalStorageDirectoryPath = Environment
                    .getExternalStorageDirectory().getAbsolutePath();

            String targetPath = ExternalStorageDirectoryPath + "/Food Inspiration/";
            targetDirector = new File(targetPath);
            myTaskAdapter.clear();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            File[] files = targetDirector.listFiles();
            for (File file : files) {
                publishProgress(file.getAbsolutePath());
                if (isCancelled()) break;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            myTaskAdapter.add(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            myTaskAdapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }

    }

    /*
    Imageadapter is the source for all items to be displayed in the grid
     */
    public class ImageAdapter extends BaseAdapter {

        private Context mContext;
        ArrayList<String> itemList = new ArrayList<String>();

        public ImageAdapter(Context c) {
            mContext = c;
        }

        void add(String path) {
            itemList.add(path);
        }

        void clear() {
            itemList.clear();
        }

        void remove(int index) {
            itemList.remove(index);
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        // Creates a new ImageView for each item referenced by the Adapter
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // If it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(500, 420));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 220,
                    220);

            imageView.setImageBitmap(bm);
            return imageView;
        }

        public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth,
                                                 int reqHeight) {

            Bitmap bm = null;

            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth,
                    reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(path, options);

            // Return bitmap
            return bm;
        }

        public int calculateInSampleSize(

                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            // Statement with requirements
            if (height > reqHeight || width > reqWidth) {
                if (width > height) {
                    inSampleSize = Math.round((float) height
                            / (float) reqHeight);
                } else {
                    inSampleSize = Math.round((float) width / (float) reqWidth);
                }
            }
            return inSampleSize;
        }
    }

    // Imageadapter had to be declared here because not possible to declare in the oncreate, myImageAdapter will be used for the onCreate
    ImageAdapter myImageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook);

        final GridView gridview = (GridView) findViewById(R.id.gridView);
        myImageAdapter = new ImageAdapter(this);
        gridview.setAdapter(myImageAdapter);
        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(CookbookActivity.this, "You've removed an item", Toast.LENGTH_SHORT).show();

                // Removes the image from the gridview
                myImageAdapter.remove(position);

                // Deletes EVERY image when LongClicked... cant make it delete 1 image
                for (int i = 0; i < 50; i++){

                    // Trying to delete the LongClicked Image from the SD card
                    String myFile = "/Food Inspiration/" + "pic-" + i + ".png";
                    String myPath = Environment.getExternalStorageDirectory()+myFile;
                    File f = new File(myPath);
                    Boolean deleted = f.delete();
                }

                // Updates the gridview
                myImageAdapter.notifyDataSetChanged();
                return true;
            }
        });

         // On Click event for Single Gridview Item
        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });

        // Method to reload the images from the sd card (if any)
        myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
        myAsyncTaskLoadFiles.execute();

        // Initialize button + onClickListener
        Button buttonReload = (Button) findViewById(R.id.reload);
        buttonReload.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                    // Cancel the previous running task, if exist.
                    myAsyncTaskLoadFiles.cancel(true);

                    // New another ImageAdapter, to prevent the adapter have mixed files
                    myImageAdapter = new ImageAdapter(CookbookActivity.this);
                    gridview.setAdapter(myImageAdapter);
                    myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
                    myAsyncTaskLoadFiles.execute();
                }
            });
        }
    }