package com.example.mersad.asrar.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.R;

import java.util.ArrayList;
import java.util.List;

public class About_Us_Activity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    TextView activity_about_us_tv_about_us;
    Typeface custom_font;

//    Cash _Cash ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

//        _Cash = (Cash) getApplication();

        initialize();

//        activity_about_us_tv_about_us.setText("hi");
//        activity_about_us_tv_about_us.append(_Cash.getCash_day() + "\n");
//        activity_about_us_tv_about_us.append( _Cash.getFname()    + "\n");
//        activity_about_us_tv_about_us.append(   _Cash.getCash_what_date()  + "\n");
//        List<Class_List_Entity> List_Class0 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class1 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class2 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class3 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class4 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class5 = new ArrayList<Class_List_Entity>();
//
//        List_Class0 = _Cash.getList_Class_0Shanbe();
//        List_Class1 = _Cash.getList_Class_1Shanbe();
//        List_Class2 = _Cash.getList_Class_2Shanbe();
//        List_Class3 = _Cash.getList_Class_3Shanbe();
//        List_Class4 = _Cash.getList_Class_4Shanbe();
//        List_Class5 = _Cash.getList_Class_5Shanbe();


    }

    private void initialize() {
        findViews();
        change_notification_color();
        setup_toolbar();
        setup_navigation();
        set_typefaces();
    }

    private void findViews() {

        activity_about_us_tv_about_us = findViewById(R.id.activity_about_us_tv_about_us);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.activity_about_us_nav_view);
        toolbar = (Toolbar) findViewById(R.id.activity_about_us_toolbar);

    }

    private void change_notification_color() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = About_Us_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(About_Us_Activity.this, R.color.useful_dark));

        }

    }

    private void setup_navigation() {

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_class_list) {

                    Toast.makeText(About_Us_Activity.this, "Comming soon", Toast.LENGTH_LONG).show();

                }
                if (item.getItemId() == R.id.return_to_main) {

                    Intent return_to_main = new Intent(About_Us_Activity.this, MainActivity.class);
                    startActivity(return_to_main);
                    finish();

                }
                if (item.getItemId() == R.id.nav_setting) {

                    Toast.makeText(About_Us_Activity.this, "test 1 successfull", Toast.LENGTH_LONG).show();

                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(About_Us_Activity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

                }


                return false;
            }

        });


    }

    private void setup_toolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about_company);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

    }

    private void set_typefaces() {

        custom_font = Typeface.createFromAsset(getAssets(), "fonts/koodak.ttf");
        activity_about_us_tv_about_us.setTypeface(custom_font);

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}