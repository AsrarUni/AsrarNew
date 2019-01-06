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
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.Model.Students_Attendance_Entity;
import com.example.mersad.asrar.R;

import java.util.ArrayList;
import java.util.List;

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

    List<Students_Attendance_Entity> List_test;
    List<Class_List_Entity> List_Class;

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

        set_unreal_infos();

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
//                String test = _Cash.getFname() +" "+ _Cash.getLname() ;
//                txttestname.setText( test );
                Intent sec = new Intent(MainActivity.this , Security_Activity.class);
                startActivity(sec);
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

    private void set_unreal_infos() {

        List_test = new ArrayList<Students_Attendance_Entity>();
        List_Class = new ArrayList<Class_List_Entity>();

//-----eejade daneshjooyan va kelas haye farzi-----//
        Class_List_Entity ObjClass = new Class_List_Entity();
        ObjClass.ClassName = "اندیشه 1";
        ObjClass.ClassCapacity = "32 نفر";
        ObjClass.ClassTime = "شنبه 8-10 و دو شنبه 14-16";
        ObjClass.ClassCode = 123;
        ObjClass.ClassLocation = "دانشکده مهندسی-کلاس 214";
        ObjClass.isCanseled = false;
        List_Class.add(ObjClass);

        Class_List_Entity ObjClass2 = new Class_List_Entity();
        ObjClass2.ClassName = "اخلاق اسلامی";
        ObjClass2.ClassCapacity = "40 نفر";
        ObjClass2.ClassTime = "سه شنبه 12-10 و چهار شنبه 14-12";
        ObjClass2.ClassCode = 214;
        ObjClass2.ClassLocation = "دانشکده مهندسی-کلاس 342";
        ObjClass2.isCanseled = true;
        List_Class.add(ObjClass2);

        Students_Attendance_Entity obj = new Students_Attendance_Entity();
        obj.FirstName = "مرصاد";
        obj.LastName = "میرغلامی";
        obj.Major = "کامپیوتر";
        obj.StudentCode = 123;
        obj.IsPresent = true;
        obj.pic = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/Logo_TVE-1.svg/1200px-Logo_TVE-1.svg.png";
        List_test.add(obj);

        Students_Attendance_Entity obj3 = new Students_Attendance_Entity();
        obj3.FirstName = "حامد";
        obj3.LastName = "حقیقی";
        obj3.Major = "پزشکی";
        obj3.StudentCode = 456;
        obj3.IsPresent = false;
        obj3.pic = "https://vignette.wikia.nocookie.net/logopedia/images/3/39/2-23.jpg/revision/latest?cb=20170506171339";
        List_test.add(obj3);


        Students_Attendance_Entity obj4 = new Students_Attendance_Entity();
        obj4.FirstName = "مرصاد";
        obj4.LastName = "میرغلامی";
        obj4.Major = "کامپیوتر";
        obj4.StudentCode = 123;
        obj4.IsPresent = true;
        obj4.pic = "http://phones.dzyne.net/wp-content/uploads/2013/10/Three-Mobile-Phones-Logo.jpg";
        List_test.add(obj4);

        Students_Attendance_Entity obj5 = new Students_Attendance_Entity();
        obj5.FirstName = "حامد";
        obj5.LastName = "حقیقی";
        obj5.Major = "پذشکی";
        obj5.StudentCode = 456;
        obj5.IsPresent = false;
        obj5.pic = "lidgi";
        List_test.add(obj5);

        Students_Attendance_Entity obj6 = new Students_Attendance_Entity();
        obj6.FirstName = "مرصاد";
        obj6.LastName = "میرغلامی";
        obj6.Major = "کامپیوتر";
        obj6.StudentCode = 123;
        obj6.IsPresent = true;
        obj6.pic = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgsZTRppCp8ztzrt2kr3bcb7lEoyo2KSPYR8yLmFt7T93wQUk6";
        List_test.add(obj6);

        Students_Attendance_Entity obj7 = new Students_Attendance_Entity();
        obj7.FirstName = "حامد";
        obj7.LastName = "حقیقی";
        obj7.Major = "پذشکی";
        obj7.StudentCode = 456;
        obj7.IsPresent = false;
        obj7.pic = "jherkfwgliyg";
        List_test.add(obj7);

//----------------------------------------------------------------------------------------------
        //-------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------
        List<Class_List_Entity> List_Class0 = new ArrayList<Class_List_Entity>();
        List<Class_List_Entity> List_Class1 = new ArrayList<Class_List_Entity>();
        List<Class_List_Entity> List_Class2 = new ArrayList<Class_List_Entity>();
        List<Class_List_Entity> List_Class3 = new ArrayList<Class_List_Entity>();
        List<Class_List_Entity> List_Class4 = new ArrayList<Class_List_Entity>();
        List<Class_List_Entity> List_Class5 = new ArrayList<Class_List_Entity>();

        Class_List_Entity ObjClass00 = new Class_List_Entity();
        ObjClass00.ClassName = "اندیشه 1";
        ObjClass00.ClassCapacity = "32 نفر";
        ObjClass00.ClassTime = "شنبه 8-10 و دو شنبه 14-16";
        ObjClass00.ClassCode = 123;
        ObjClass00.ClassLocation = "دانشکده مهندسی-کلاس 214";
        ObjClass00.isCanseled = false;
        List_Class0.add(ObjClass00);

        Class_List_Entity ObjClass01 = new Class_List_Entity();
        ObjClass01.ClassName = "اخلاق اسلامی";
        ObjClass01.ClassCapacity = "40 نفر";
        ObjClass01.ClassTime = "سه شنبه 12-10 و چهار شنبه 14-12";
        ObjClass01.ClassCode = 214;
        ObjClass01.ClassLocation = "دانشکده مهندسی-کلاس 342";
        ObjClass01.isCanseled = true;
        List_Class0.add(ObjClass01);

        //------------------------------------

        Class_List_Entity ObjClass10 = new Class_List_Entity();
        ObjClass10.ClassName = "اندیشه 1";
        ObjClass10.ClassCapacity = "32 نفر";
        ObjClass10.ClassTime = "شنبه 8-10 و دو شنبه 14-16";
        ObjClass10.ClassCode = 123;
        ObjClass10.ClassLocation = "دانشکده مهندسی-کلاس 214";
        ObjClass10.isCanseled = false;
        List_Class1.add(ObjClass10);

        Class_List_Entity ObjClass11 = new Class_List_Entity();
        ObjClass11.ClassName = "اخلاق اسلامی";
        ObjClass11.ClassCapacity = "40 نفر";
        ObjClass11.ClassTime = "سه شنبه 12-10 و چهار شنبه 14-12";
        ObjClass11.ClassCode = 214;
        ObjClass11.ClassLocation = "دانشکده مهندسی-کلاس 342";
        ObjClass11.isCanseled = true;
        List_Class1.add(ObjClass11);

        //------------------------------------

        Class_List_Entity ObjClass20 = new Class_List_Entity();
        ObjClass20.ClassName = "اندیشه 1";
        ObjClass20.ClassCapacity = "32 نفر";
        ObjClass20.ClassTime = "شنبه 8-10 و دو شنبه 14-16";
        ObjClass20.ClassCode = 123;
        ObjClass20.ClassLocation = "دانشکده مهندسی-کلاس 214";
        ObjClass20.isCanseled = false;
        List_Class2.add(ObjClass20);

        Class_List_Entity ObjClass21 = new Class_List_Entity();
        ObjClass21.ClassName = "اخلاق اسلامی";
        ObjClass21.ClassCapacity = "40 نفر";
        ObjClass21.ClassTime = "سه شنبه 12-10 و چهار شنبه 14-12";
        ObjClass21.ClassCode = 214;
        ObjClass21.ClassLocation = "دانشکده مهندسی-کلاس 342";
        ObjClass21.isCanseled = true;
        List_Class2.add(ObjClass21);

        //------------------------------------

        Class_List_Entity ObjClass30 = new Class_List_Entity();
        ObjClass30.ClassName = "اندیشه 1";
        ObjClass30.ClassCapacity = "32 نفر";
        ObjClass30.ClassTime = "شنبه 8-10 و دو شنبه 14-16";
        ObjClass30.ClassCode = 123;
        ObjClass30.ClassLocation = "دانشکده مهندسی-کلاس 214";
        ObjClass30.isCanseled = false;
        List_Class3.add(ObjClass30);

        Class_List_Entity ObjClass31 = new Class_List_Entity();
        ObjClass31.ClassName = "اخلاق اسلامی";
        ObjClass31.ClassCapacity = "40 نفر";
        ObjClass31.ClassTime = "سه شنبه 12-10 و چهار شنبه 14-12";
        ObjClass31.ClassCode = 214;
        ObjClass31.ClassLocation = "دانشکده مهندسی-کلاس 342";
        ObjClass31.isCanseled = true;
        List_Class3.add(ObjClass31);

        //------------------------------------

        Class_List_Entity ObjClass40 = new Class_List_Entity();
        ObjClass40.ClassName = "اندیشه 1";
        ObjClass40.ClassCapacity = "32 نفر";
        ObjClass40.ClassTime = "شنبه 8-10 و دو شنبه 14-16";
        ObjClass40.ClassCode = 123;
        ObjClass40.ClassLocation = "دانشکده مهندسی-کلاس 214";
        ObjClass40.isCanseled = false;
        List_Class4.add(ObjClass40);

        Class_List_Entity ObjClass41 = new Class_List_Entity();
        ObjClass41.ClassName = "اخلاق اسلامی";
        ObjClass41.ClassCapacity = "40 نفر";
        ObjClass41.ClassTime = "سه شنبه 12-10 و چهار شنبه 14-12";
        ObjClass41.ClassCode = 214;
        ObjClass41.ClassLocation = "دانشکده مهندسی-کلاس 342";
        ObjClass41.isCanseled = true;
        List_Class4.add(ObjClass41);

        //------------------------------------

        Class_List_Entity ObjClass50 = new Class_List_Entity();
        ObjClass50.ClassName = "اندیشه 1";
        ObjClass50.ClassCapacity = "32 نفر";
        ObjClass50.ClassTime = "شنبه 8-10 و دو شنبه 14-16";
        ObjClass50.ClassCode = 123;
        ObjClass50.ClassLocation = "دانشکده مهندسی-کلاس 214";
        ObjClass50.isCanseled = false;
        List_Class5.add(ObjClass50);

        Class_List_Entity ObjClass51 = new Class_List_Entity();
        ObjClass51.ClassName = "اخلاق اسلامی";
        ObjClass51.ClassCapacity = "40 نفر";
        ObjClass51.ClassTime = "سه شنبه 12-10 و چهار شنبه 14-12";
        ObjClass51.ClassCode = 214;
        ObjClass51.ClassLocation = "دانشکده مهندسی-کلاس 342";
        ObjClass51.isCanseled = true;
        List_Class5.add(ObjClass51);


//-----por kardane etelaate farzi dar kelase cash-----//
        _Cash.setList_Students(List_test);
        _Cash.setList_Class_0Shanbe(List_Class0);
        _Cash.setList_Class_1Shanbe(List_Class1);
        _Cash.setList_Class_2Shanbe(List_Class2);
        _Cash.setList_Class_3Shanbe(List_Class3);
        _Cash.setList_Class_4Shanbe(List_Class4);
        _Cash.setList_Class_5Shanbe(List_Class5);

    }


}

