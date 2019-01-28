package com.example.mersad.asrar.Activities;

import android.content.Intent;
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
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.Utils.JustifiedTextView;

public class Sure_to_Cancell_Class_Activity extends AppCompatActivity {

    JustifiedTextView jtv_sure_to_cancell_Hint ;
    Button bikhiyal , cansel_she ;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    String Class_Code ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_to_cancell__class);

        Intent intent = getIntent();
        Class_Code = intent.getStringExtra(Constant.Code_Class);


        initialize();


    }

    private void initialize() {

        find_views();
        Setup_Justified_TextView();
        setup_toolbar();
        set_Listeners();
        setup_navigation();
        change_notification_color();


    }

    private void set_Listeners() {

        bikhiyal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sure_to_Cancell_Class_Activity.this , Week_Days_Class_activity.class);
                startActivity(i);
                finish();
            }
        });

        // TODO: 1/28/2019 ersal she server gozineye ersal 

    }

    private void find_views() {

        jtv_sure_to_cancell_Hint = findViewById(R.id.jtv_sure_to_cancell_Hint);
        bikhiyal = findViewById(R.id.btn_sure_to_cancell_class_cancell);
        cansel_she = findViewById(R.id.btn_sure_to_cancell_class_do);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void Setup_Justified_TextView() {


        jtv_sure_to_cancell_Hint.setText(getString(R.string.cancell_class_text1) + Class_Code + getString(R.string.cancell_class_text2) );
        jtv_sure_to_cancell_Hint.setTextSize(TypedValue.COMPLEX_UNIT_SP , 15);

    }

    private void change_notification_color(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Sure_to_Cancell_Class_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Sure_to_Cancell_Class_Activity.this,R.color.useful_dark));

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

                    Intent goto_classes = new Intent (Sure_to_Cancell_Class_Activity.this , Week_Days_Class_activity.class);
                    startActivity(goto_classes);

                }
                if (item.getItemId() == R.id.nav_about_us) {

                    Intent about_us = new Intent(Sure_to_Cancell_Class_Activity.this , About_Us_Activity.class );
                    startActivity(about_us);
                    finish();

                }
                if (item.getItemId() == R.id.nav_setting) {

//                    Toast.makeText(MainActivity.this, "test 1 successfull", Toast.LENGTH_LONG).show();

                    Intent edt = new Intent (Sure_to_Cancell_Class_Activity.this , Setting_Activity.class);
                    startActivity(edt);

                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(Sure_to_Cancell_Class_Activity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

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




}
