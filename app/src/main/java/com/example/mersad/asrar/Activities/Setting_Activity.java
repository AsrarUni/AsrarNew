package com.example.mersad.asrar.Activities;

import android.content.Intent;
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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mersad.asrar.Adapter.Setting_Adapter;
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.Model.Setting_Items;
import com.example.mersad.asrar.R;

import java.util.ArrayList;
import java.util.List;

public class Setting_Activity extends AppCompatActivity {

    List<Setting_Items> Setting_List ;
    RecyclerView Setting_Recycle ;
    Setting_Adapter adapter ;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initialize();

    }

    private void initialize() {

        find_views();
        change_notification_color();
        Create_Setting_List();
        setup_recycler();
        setup_toolbar();
        setup_navigation();

    }

    private void setup_recycler() {

        adapter = new Setting_Adapter(this , Setting_List);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Setting_Recycle.setLayoutManager(linearLayoutManager);
        Setting_Recycle.setAdapter(adapter);
        Setting_Recycle.setItemAnimator(new DefaultItemAnimator());

    }

    private void find_views() {

        Setting_Recycle = findViewById(R.id.Setting_recycle);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    private void Create_Setting_List() {

        Setting_List = new ArrayList<Setting_Items>();

//----- eejade item haye setting -----//
        Setting_Items Change_Pass = new Setting_Items();

        Change_Pass.setItem("تغییر رمز عبور آسان");
        Change_Pass.setIcone(R.drawable.ic_password);

        Setting_List.add(Change_Pass);
    }

    private void change_notification_color(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Setting_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Setting_Activity.this,R.color.useful_dark));

        }
    }


    private void setup_navigation (){

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_class_list) {

                    Intent goto_classes = new Intent (Setting_Activity.this , Week_Days_Class_activity.class);
                    startActivity(goto_classes);

                }
                if (item.getItemId() == R.id.nav_about_us) {

                    Intent about_us = new Intent(Setting_Activity.this , About_Us_Activity.class );
                    startActivity(about_us);
                    finish();

                }
                if (item.getItemId() == R.id.nav_setting) {

//                    Toast.makeText(MainActivity.this, "test 1 successfull", Toast.LENGTH_LONG).show();

                    Intent edt = new Intent (Setting_Activity.this , Setting_Activity.class);
                    startActivity(edt);

                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(Setting_Activity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

                }


                return false;
            }

        });

    }

    private void setup_toolbar (){

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_page); // for set actionbar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

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
