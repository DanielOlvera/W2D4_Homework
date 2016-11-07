package com.example.daniel.w2d4_homework;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class AsyncTaskActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    
    String imageUrl = "https://api.vfolder.net/photos/mp7zOuougkc/hangovers-1.jpg";

    Button asyncTskBtn;
    ImageView asyncTskImgV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        asyncTskBtn = (Button) findViewById(R.id.at_dnwldBtn);
        asyncTskImgV = (ImageView) findViewById(R.id.at_imgView);
        
        asyncTskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                DownloadAsync downloadAsync = new DownloadAsync();
                downloadAsync.execute(imageUrl);
            }
        });
        Log.d(TAG, "onCreate: ");
    }

    class DownloadAsync extends AsyncTask<String, Integer, String>{

        String root = Environment.getExternalStorageDirectory().toString();
        String name = new Date().toString() + ".jpg";

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(AsyncTaskActivity.this);
            progressDialog.setTitle("Downloading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String urlImg = params[0];

            int fileLength;

            try {

                URL url = new URL(urlImg);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();

                fileLength = urlConnection.getContentLength();

                File extFolder = new File(root + "/photoAlbum");
                if (!extFolder.exists()){
                    extFolder.mkdir();
                }

                File inputFile = new File(extFolder, name);
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                byte[] data = new byte[1024];
                int total = 0;
                int count;
                FileOutputStream outputStream = new FileOutputStream(inputFile);
                while ((count = inputStream.read(data)) != -1){
                    total += count ;
                    outputStream.write(data, 0, count);

                    int dwnldProgress = total * 100 / fileLength;

                    publishProgress(dwnldProgress);
                }

                inputStream.close();
                outputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String aVoid) {
            progressDialog.hide();

            Toast.makeText(AsyncTaskActivity.this, aVoid, Toast.LENGTH_LONG).show();

            String route = root + name;
            asyncTskImgV.setImageDrawable(Drawable.createFromPath(route));

        }
    }
}
