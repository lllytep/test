package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {
    MyScreen screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = new MyScreen(this);
        setContentView(screen);
    }

    @Override
    public void onResume() {
        super.onResume();
        screen.Resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        screen.Pause();
    }
}
