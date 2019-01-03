package com.example.mersad.asrar.Activities;

import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mersad.asrar.R;

public class Rating_Activity extends AppCompatActivity {

    Button Rating_Activity_Btn_Save_Rate;
    private RatingBar Rating_Activity_Rb_Progress, Rating_Activity_Rb_Knowledge, Rating_Activity_Rb_Clean, Rating_Activity_Rb_Light_And_Air, Rating_Activity_Rb_Facilities;
    private TextView Rating_Activity_Tv_Progress, Rating_Activity_Tv_Knowledge, Rating_Activity_Tv_Clean, Rating_Activity_Tv_Light_And_Air, Rating_Activity_Tv_Facilities;
    Typeface custom_font;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        initialize();

    }

    private void initialize() {

        change_notification_color();
        find_views();
        setup_toolbar();
        setup_navigation();
        set_typefaces();
        set_listeners();

    }

    private void set_listeners() {

        Rating_Activity_Btn_Save_Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Rating_Activity_Tv_Progress.setText(String.valueOf (Rating_Activity_Rb_Progress.getRating()) );
                Toast.makeText(Rating_Activity.this, String.valueOf((int) Rating_Activity_Rb_Facilities.getRating()), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void set_typefaces() {

        custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/koodak.ttf");
        Rating_Activity_Tv_Progress.setTypeface(custom_font);
        Rating_Activity_Tv_Knowledge.setTypeface(custom_font);
        Rating_Activity_Tv_Clean.setTypeface(custom_font);
        Rating_Activity_Tv_Light_And_Air.setTypeface(custom_font);
        Rating_Activity_Tv_Facilities.setTypeface(custom_font);


    }

    private void setup_toolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_page); // for set actionbar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

    }

    private void setup_navigation() {

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void change_notification_color() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Rating_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Rating_Activity.this, R.color.useful_dark));

        }
    }

    private void find_views() {

        Rating_Activity_Rb_Progress = findViewById(R.id.Rating_Activity_Rb_Progress);
        Rating_Activity_Rb_Knowledge = findViewById(R.id.Rating_Activity_Rb_Knowledge);
        Rating_Activity_Rb_Clean = findViewById(R.id.Rating_Activity_Rb_Clean);
        Rating_Activity_Rb_Light_And_Air = findViewById(R.id.Rating_Activity_Rb_Light_And_Air);
        Rating_Activity_Rb_Facilities = findViewById(R.id.Rating_Activity_Rb_Facilities);

        Rating_Activity_Tv_Progress = findViewById(R.id.Rating_Activity_Tv_Progress);
        Rating_Activity_Tv_Knowledge = findViewById(R.id.Rating_Activity_Tv_Knowledge);
        Rating_Activity_Tv_Clean = findViewById(R.id.Rating_Activity_Tv_Clean);
        Rating_Activity_Tv_Light_And_Air = findViewById(R.id.Rating_Activity_Tv_Light_And_Air);
        Rating_Activity_Tv_Facilities = findViewById(R.id.Rating_Activity_Tv_Facilities);

        toolbar = (Toolbar) findViewById(R.id.Rating_toolbar);
        drawer = findViewById(R.id.Rating_drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Rating_Activity_Btn_Save_Rate = findViewById(R.id.Rating_Activity_Btn_Save_Rate);

    }

}
