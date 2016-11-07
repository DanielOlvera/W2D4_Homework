package com.example.daniel.w2d4_homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class AsyncTaskActivity extends AppCompatActivity {

    String imageUrl = "https://api.vfolder.net/photos/mp7zOuougkc/hangovers-1.jpg";

    Button asyncTskBtn;
    ImageView asyncTskImgV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);


    }
}
