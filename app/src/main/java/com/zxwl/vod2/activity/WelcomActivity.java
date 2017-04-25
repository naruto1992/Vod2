package com.zxwl.vod2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.zxwl.vod2.R;

public class WelcomActivity extends AppCompatActivity {

    static final long DELAY_TIME = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean hsaLogin = false;
                if (hsaLogin) {
                    startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(WelcomActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, DELAY_TIME);
    }
}
