package com.ashitech.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView downloadIamge;
    public void downloadIamge(View view){
        FetchImage task = new FetchImage();
        Bitmap myBitMap;
        try {
            myBitMap = task.execute("http://www.thehindu.com/entertainment/movies/article19906429.ece/alternates/FREE_300/THJVNDULQUER").get();
            downloadIamge.setImageBitmap(myBitMap);
            Log.i("button","download button working");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         downloadIamge = findViewById(R.id.downloadImage);

    }
    public class FetchImage extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap myImage;
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                myImage = BitmapFactory.decodeStream(inputStream);
                return myImage;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


    }
}
