package com.example.mersad.asrar.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.R;

import wiadevelopers.com.library.DivarUtils;

public class Employment_Info_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    Cash _Cash;
    private ImageView Employment_IV_profile_pic;
    private TextView Employment_Tv_Fname, Employment_Tv_Lname, Employment_Tv_Tel, Employment_Tv_Mobile,
            Employment_Tv_Email, Employment_Tv_FatherName, Employment_Tv_CodeMeli, Employment_Tv_PersonalNumber, Employment_Tv_DateBirthda,
            Employment_Tv_TypeID;
    Typeface custom_font;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment_info);

        _Cash = (Cash) getApplication();
        initialize();
    }

    private void initialize() {
        findViews();
        change_notification_color();
        setup_toolbar();
        setup_navigation();
        fill_info();
    }


    private void findViews() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Employment_Tv_Fname = findViewById(R.id.Employment_Tv_Fname);
        Employment_Tv_Lname = findViewById(R.id.Employment_Tv_Lname);
        Employment_Tv_Tel = findViewById(R.id.Employment_Tv_Tel);
        Employment_Tv_Mobile = findViewById(R.id.Employment_Tv_Mobile);
        Employment_Tv_Email = findViewById(R.id.Employment_Tv_Email);
        Employment_Tv_FatherName = findViewById(R.id.Employment_Tv_FatherName);
        Employment_Tv_CodeMeli = findViewById(R.id.Employment_Tv_CodeMeli);
        Employment_Tv_PersonalNumber = findViewById(R.id.Employment_Tv_PersonalNumber);
        Employment_Tv_DateBirthda = findViewById(R.id.Employment_Tv_DateBirthda);
        Employment_Tv_TypeID = findViewById(R.id.Employment_Tv_TypeID);
    }

    private void fill_info() {
        String test = _Cash.getFname();
        Employment_Tv_Fname.setText(test);

        String test1 = _Cash.getLname();
        Employment_Tv_Lname.setText(test1);

        String test2 = _Cash.getTel();
        Employment_Tv_Tel.setText(test2);

        String test3 = _Cash.getMobile();
        Employment_Tv_Mobile.setText(test3);

        String test4 = _Cash.getEmail();
        Employment_Tv_Email.setText(test4);

        String test5 = _Cash.getFatherName();
        Employment_Tv_FatherName.setText(test5);

        String test6 = String.valueOf(_Cash.getCodeMeli());
        Employment_Tv_CodeMeli.setText(test6);

        String test7 = String.valueOf(_Cash.getPersonalNumber());
        Employment_Tv_PersonalNumber.setText(test7);

        String test8 = _Cash.getDateBirthday();
        Employment_Tv_DateBirthda.setText(test8);

        String test9 = String.valueOf(_Cash.getTypeID());
        Employment_Tv_TypeID.setText(test9);
    }

    private void change_notification_color() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Employment_Info_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Employment_Info_Activity.this, R.color.useful_dark));

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

                    Intent goto_classes = new Intent(Employment_Info_Activity.this, Week_Days_Class_activity.class);
                    startActivity(goto_classes);

                }
                if (item.getItemId() == R.id.nav_about_us) {

                    Intent about_us = new Intent(Employment_Info_Activity.this, About_Us_Activity.class);
                    startActivity(about_us);
                    finish();

                }
                if (item.getItemId() == R.id.nav_setting) {

                    Intent about_us = new Intent(Employment_Info_Activity.this, Setting_Activity.class);
                    startActivity(about_us);
                    finish();
                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(Employment_Info_Activity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

                }

                if (item.getItemId() == R.id.nav_close_to_app) {
                    finishAffinity();

                }

                if (item.getItemId() == R.id.nav_exit_credential) {

                    DivarUtils.writeDataInStorage(Constant.USER_CODE, null);
                    DivarUtils.writeDataInStorage(Constant.USER_PASS, null);
                    finishAffinity();

                }

                return false;
            }

        });


    }

    private void setup_toolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_page); // for set actionbar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

    }

}