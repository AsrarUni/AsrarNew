package com.example.mersad.asrar.Activities;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mersad.asrar.R;

public class Security_Activity extends AppCompatActivity {

    TextView number_1 , number_2 , number_3 , number_4 , number_5 , number_6 , number_7 , number_8 , number_9 , number_0 , ok , del ;
    Typeface custom_font;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        initialize();

    }

    private void initialize() {

        find_views();
        change_notification_color();
        set_typefaces();

//        set_on_clicks();
//        start_animations();


    }

    private void set_typefaces() {

        custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/koodak.ttf");

        number_0.setTypeface(custom_font);
        number_1.setTypeface(custom_font);
        number_2.setTypeface(custom_font);
        number_3.setTypeface(custom_font);
        number_4.setTypeface(custom_font);
        number_5.setTypeface(custom_font);
        number_6.setTypeface(custom_font);
        number_7.setTypeface(custom_font);
        number_8.setTypeface(custom_font);
        number_9.setTypeface(custom_font);

        ok.setTypeface(custom_font);
        del.setTypeface(custom_font);

    }

    private void find_views() {

        number_0 = findViewById(R.id.Securuty_tv_num_0);
        number_1 = findViewById(R.id.Securuty_tv_num_1);
        number_2 = findViewById(R.id.Securuty_tv_num_2);
        number_3 = findViewById(R.id.Securuty_tv_num_3);
        number_4 = findViewById(R.id.Securuty_tv_num_4);
        number_5 = findViewById(R.id.Securuty_tv_num_5);
        number_6 = findViewById(R.id.Securuty_tv_num_6);
        number_7 = findViewById(R.id.Securuty_tv_num_7);
        number_8 = findViewById(R.id.Securuty_tv_num_8);
        number_9 = findViewById(R.id.Securuty_tv_num_9);

        ok = findViewById(R.id.Securuty_tv_ok);
        del = findViewById(R.id.Securuty_tv_del);

    }


    private void change_notification_color(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Security_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Security_Activity.this,R.color.useful_dark));

        }
    }


}
