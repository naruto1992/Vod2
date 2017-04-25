package com.zxwl.vod2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zxwl.vod2.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.signIn)
    public void signIn(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
