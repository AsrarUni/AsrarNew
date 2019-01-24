package com.example.mersad.asrar.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

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

        change_notification_color();


//----- chek if first time -----//
        String user_code = DivarUtils.readDataFromStorage(Constant.USER_CODE, null);
        String is_has_pass = DivarUtils.readDataFromStorage(Constant.IS_HAS_PATTERN, null);

        if (user_code == null) {
//----- baraye eejade yek vaghfeye 4 saniye ee -----//
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent1 = new Intent(SplashScreenActivity.this, Login_Activity.class);
                    startActivity(intent1);
                    finish();
                }
            }, 4000);
        } else {
            if (is_has_pass == null) {
                Intent intent2 = new Intent(SplashScreenActivity.this, Login_Activity.class);
                startActivity(intent2);
                finish();
            } else {
                Intent intent3 = new Intent(SplashScreenActivity.this, Security_Activity.class);
                startActivity(intent3);
                finish();
            }
        }

    }

    private void change_notification_color(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = SplashScreenActivity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(SplashScreenActivity.this,R.color.useful_dark));

        }
    }


}