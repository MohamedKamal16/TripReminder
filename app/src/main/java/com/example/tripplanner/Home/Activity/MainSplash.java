package com.example.tripplanner.Home.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.window.SplashScreen;

import com.example.tripplanner.MainActivity;
import com.example.tripplanner.R;

public class MainSplash extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainSplash.this,Home.class);

                //Intent is used to switch from one activity to another.

                startActivity(intent);
                //invoke the SecondActivity.
                finish();
            }

        },SPLASH_SCREEN_TIME_OUT);
    }
}