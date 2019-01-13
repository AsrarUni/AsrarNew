package com.example.mersad.asrar.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.Fragments.Students_Attendance_Fragment;
import com.example.mersad.asrar.Utils.Util_Tablayout;

public class Student_Attendance_Tablayout_Activity extends AppCompatActivity {


    ViewPager viewPager;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    TabLayout tabLayout;

    Students_Attendance_Fragment attendance = new Students_Attendance_Fragment();

    Cash _Cash ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_tablayout_);

        _Cash = (Cash) getApplication();
        initialize();

    }


    private void initialize() {

        change_notification_color();
        find_views();
        setup_toolbar();
        setup_navigation();
        set_listeners();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setup_toolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_page); // for set actionbar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

//        toolbar.setTitle("Simple Tab");
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void setup_navigation() {

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_class_list) {

                    Intent goto_classes = new Intent (Student_Attendance_Tablayout_Activity.this , Weekly_Class_Tab_Activity.class);
                    startActivity(goto_classes);

                }
                if (item.getItemId() == R.id.nav_about_us) {

                    Intent about_us = new Intent(Student_Attendance_Tablayout_Activity.this , About_Us_Activity.class );
                    startActivity(about_us);
                    finish();

                }
                if (item.getItemId() == R.id.nav_setting) {

                    Toast.makeText(Student_Attendance_Tablayout_Activity.this, "test 1 successfull", Toast.LENGTH_LONG).show();

                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(Student_Attendance_Tablayout_Activity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

                }


                return false;
            }

        });

    }

    private void set_listeners() {

        tabLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                _Cash.what_week_day = tabLayout.getSelectedTabPosition();
            }
        });



    }

    private void find_views() {

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

    }

    private void change_notification_color(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Student_Attendance_Tablayout_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Student_Attendance_Tablayout_Activity.this,R.color.useful_dark));

        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Util_Tablayout.ViewPagerAdapter adapter = new Util_Tablayout.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment( attendance , "حظور و غیاب دانشجویان" );
        viewPager.setAdapter(adapter);
    }

}
