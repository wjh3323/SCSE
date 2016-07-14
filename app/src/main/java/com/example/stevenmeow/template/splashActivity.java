package com.example.stevenmeow.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by StevenMeow on 2016/5/25.
 */
public class splashActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}