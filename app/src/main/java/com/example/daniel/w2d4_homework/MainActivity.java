package com.example.daniel.w2d4_homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nestedFragments(View view) {

        Intent nestedFragment = new Intent(this, NestedFragmentsActivity.class);
        startActivity(nestedFragment);
    }

    public void imagAsync(View view) {

        Intent asyncTask = new Intent(this, AsyncTaskActivity.class);
        startActivity(asyncTask);
    }
}

/*
Reference:
    https://developer.android.com/about/versions/android-4.2.html#NestedFragments
    http://rootzwiki.com/topic/36433-nested-fragments-example-for-android-42-api-v17/
    http://stackoverflow.com/questions/17599450/how-to-inflate-view-inside-fragment
 */