package com.example.amoremsuamesa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    RelativeLayout loading = null;


    public DownloadImageTask(ImageView bmImage,RelativeLayout lB) {
        this.bmImage = bmImage;
        this.loading = lB;
    }

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if (loading!=null){
            bmImage.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
        staticStorageClass.imagensMainActivity.add(result);
        bmImage.setImageBitmap(result);
    }

}