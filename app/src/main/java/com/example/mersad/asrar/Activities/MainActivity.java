package com.example.mersad.asrar.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private SwipeRefreshLayout mySwipe;
    CardView Activity_Main_Cv_Today ;
    CardView Activity_Main_Cv_About_Us ;
    CardView Activity_Main_Cv_weekly ;
    CardView Activity_Main_Cv_Students ;
    GridLayout Activity_Main_Gl ;
    TextView txttestname ;
    Cash _Cash ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _Cash = (Cash) getApplication();
        initialize();

//----- ijade yek timere kotah baraye ejraye animaton ha -----//
        new CountDownTimer(1, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                start_animations();
            }
        }.start();

    }

    private void initialize(){

        find_views();
        change_notification_color();
        set_on_clicks();
        setup_toolbar();
        setup_navigation();
        setup_swipe_refresh();
        start_animations();

    };



    private void setup_swipe_refresh(){

        mySwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mySwipe.setRefreshing(true);

                // sth to do
                Toast.makeText(MainActivity.this, "test swipe successfull", Toast.LENGTH_LONG).show();

                mySwipe.setRefreshing(false);
            }
        });

    }

    private void setup_navigation (){

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_class_list) {

                    Intent goto_classes = new Intent (MainActivity.this , Weekly_Class_Tab_Activity.class);
                    startActivity(goto_classes);

                }
                if (item.getItemId() == R.id.nav_about_us) {

                    Intent about_us = new Intent(MainActivity.this , About_Us_Activity.class );
                    startActivity(about_us);
                    finish();

                }
                if (item.getItemId() == R.id.nav_test1) {

                    Toast.makeText(MainActivity.this, "test 1 successfull", Toast.LENGTH_LONG).show();

                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(MainActivity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

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

    private void change_notification_color(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = MainActivity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.useful_dark));

        }
    }

    private void set_on_clicks (){
        Activity_Main_Cv_Today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity_Main_Cv_Today = new Intent (MainActivity.this , Weekly_Class_Tab_Activity.class);
                startActivity(Activity_Main_Cv_Today);


            }
        });

        Activity_Main_Cv_About_Us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity_Main_Cv_About_Us = new Intent (MainActivity.this , About_Us_Activity.class);
                startActivity(Activity_Main_Cv_About_Us);

            }
        });

        Activity_Main_Cv_weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"به زودی" , Toast.LENGTH_SHORT).show();
                String test = _Cash.getFname() +" "+ _Cash.getLname() ;
                txttestname.setText( test );
            }
        });

        Activity_Main_Cv_Students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"به زودی" , Toast.LENGTH_SHORT).show();
                Intent tab = new Intent(MainActivity.this , Employment_Info_Activity.class);
                startActivity(tab);
            }
        });
    }

    private void find_views (){
        Activity_Main_Cv_Today = findViewById(R.id.Activity_Main_Cv_Today);
        Activity_Main_Cv_About_Us = findViewById(R.id.Activity_Main_Cv_About_Us);
        Activity_Main_Cv_weekly = findViewById(R.id.Activity_Main_Cv_weekly);
        Activity_Main_Cv_Students = findViewById(R.id.Activity_Main_Cv_Students);
        mySwipe = (SwipeRefreshLayout) findViewById(R.id.mySwipe);
        drawer = findViewById(R.id.drawer_layout);
        Activity_Main_Gl = findViewById(R.id.Activity_Main_Gl);
        navigationView = findViewById(R.id.nav_view);
        txttestname = findViewById(R.id.txttestname);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void start_animations () {
        translateAnim(Activity_Main_Cv_Today ,-Activity_Main_Cv_Today.getX()-Activity_Main_Cv_Today.getWidth() , 0f , (0-Activity_Main_Cv_Today.getY())+(0-Activity_Main_Cv_Today.getHeight()), 0f , 1000);
        translateAnim(Activity_Main_Cv_weekly , (float) Activity_Main_Gl.getWidth(), 0f , (0-Activity_Main_Cv_weekly.getY())+(0-Activity_Main_Cv_weekly.getHeight()), 0f, 1000);
        translateAnim(Activity_Main_Cv_Students ,-Activity_Main_Cv_Students.getX()-Activity_Main_Cv_Students.getWidth() , 0f , Activity_Main_Cv_Students.getHeight()+Activity_Main_Cv_Students.getY(), 0f, 1000);
        translateAnim(Activity_Main_Cv_About_Us ,(float) Activity_Main_Gl.getWidth() , 0f , Activity_Main_Cv_About_Us.getHeight()+Activity_Main_Cv_About_Us.getY(), 0f, 1000);
    }

    private void translateAnim(View v , Float startX  , Float endX , Float startY , Float endY , int time) {
 //----- tabeye iijade animatione transfer dar har jahati -----//

        TranslateAnimation anim = new TranslateAnimation( startX, endX , startY, endY );
        anim.setFillAfter(true);
        anim.setDuration(time);
        v.startAnimation(anim);
    }

}

