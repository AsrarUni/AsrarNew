package com.example.mersad.asrar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.R;

import wiadevelopers.com.library.DivarUtils;

import static wiadevelopers.com.library.DivarUtils.readDataFromStorage;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initialize();
    }

    private void initialize() {


//----- chek if first time -----//
        String user_code = DivarUtils.readDataFromStorage(Constant.USER_CODE, null);

        if (user_code == null) {
//----- baraye eejade yek vaghfeye 4 saniye ee -----//
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, Login_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }, 4000);
        } else {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }

    }


}