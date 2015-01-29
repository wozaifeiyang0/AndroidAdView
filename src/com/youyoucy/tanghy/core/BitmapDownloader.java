package com.youyoucy.tanghy.core;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.net.URL;

/**
 * Created by IBM on 2015/1/29.
 */
@TargetApi(3)
public class BitmapDownloader extends AsyncTask<String, Void, Bitmap>
{

    private String url;
    private ImageView imageView;

    public BitmapDownloader(ImageView imageView)
    {
        this.imageView = imageView;
    }



    protected Bitmap doInBackground(String[] paramArrayOfString)
    {
        this.url = paramArrayOfString[0];
        try
        {
            Bitmap localBitmap = BitmapFactory.decodeStream(new URL(this.url).openConnection().getInputStream());
            return localBitmap;
        }
        catch (Exception localException)
        {
            return null;
        }
    }

    protected void onPostExecute(Bitmap paramBitmap)
    {
        imageView.setImageBitmap(paramBitmap);
    }
}
