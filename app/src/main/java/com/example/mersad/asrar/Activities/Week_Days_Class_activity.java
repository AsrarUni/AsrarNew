package com.example.mersad.asrar.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.Fragments.Find_Classes_By_Name_Fragment;
import com.example.mersad.asrar.Fragments.Class_List_Fragment;
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.Model.model_class;
import com.example.mersad.asrar.Model.model_time;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.Utils.AppSingleton;
import com.example.mersad.asrar.Utils.PersianDate;
import com.example.mersad.asrar.Utils.Util_Tablayout;
import com.example.mersad.asrar.Volley_WebService.FetchDataListener;
import com.example.mersad.asrar.Volley_WebService.GETAPIRequest;
import com.example.mersad.asrar.Volley_WebService.RequestQueueService;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import wiadevelopers.com.library.DivarUtils;


public class Week_Days_Class_activity extends AppCompatActivity {

    ViewPager viewPager;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    TabLayout tabLayout;
    TextView Weekly_Tv_From_Date_To_Date;
    Typeface custom_font;
    ImageButton  weekdays_ib_before , weekdays_ib_next ;


    Class_List_Fragment Shanbe = new Class_List_Fragment();
    Class_List_Fragment Yek_Shanbe = new Class_List_Fragment();
    Class_List_Fragment Do_Shanbe = new Class_List_Fragment();
    Class_List_Fragment Se_Shanbe = new Class_List_Fragment();
    Class_List_Fragment Chahar_Shanbe = new Class_List_Fragment();
    Class_List_Fragment Panj_Shanbe = new Class_List_Fragment();
    Find_Classes_By_Name_Fragment kanseli_jobrani = new Find_Classes_By_Name_Fragment();

    ProgressDialog pDialog;

    Cash _Cash;

    String Today_Date_Local;
    String Today_Day_Local;

    Date Today_Date;
    Date StartOfWeek;
    Date EndOfWeek;

    String url_for_getclass;
    int next_or_past = 0 ;

    List<Class_List_Entity> List_Class0 ;
    List<Class_List_Entity> List_Class1 ;
    List<Class_List_Entity> List_Class2 ;
    List<Class_List_Entity> List_Class3 ;
    List<Class_List_Entity> List_Class4 ;
    List<Class_List_Entity> List_Class5 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week__days__class_activity);

        _Cash = (Cash) getApplication();

         List_Class0 = new ArrayList<Class_List_Entity>();
         List_Class1 = new ArrayList<Class_List_Entity>();
         List_Class2 = new ArrayList<Class_List_Entity>();
         List_Class3 = new ArrayList<Class_List_Entity>();
         List_Class4 = new ArrayList<Class_List_Entity>();
         List_Class5 = new ArrayList<Class_List_Entity>();


        initialize();

//----- tayine rooze hafte baraye har obj -----//
        Shanbe.what_day = 0;
        Yek_Shanbe.what_day = 1;
        Do_Shanbe.what_day = 2;
        Se_Shanbe.what_day = 3;
        Chahar_Shanbe.what_day = 4;
        Panj_Shanbe.what_day = 5;

//        getApiCall("http://thtc.ir/nazer/api/date", Weekly_Class_Tab_Activity.this);

    }


    private void initialize() {

        find_views();
        set_Date_Text(next_or_past);
        change_notification_color();
        setup_toolbar();
        setup_navigation();
        set_typefaces();
        set_listeners();
        setup_progress_dialog();
//        request2("http://nazer.thtc.ir/api/class/2018-12-02/2019-01-24/609");
//        request2(url_for_getclass);
    }

    private void setup_viewpager() {

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(7);

    }

    private void setup_navigation() {

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_class_list) {

                    Intent goto_classes = new Intent(Week_Days_Class_activity.this, Week_Days_Class_activity.class);
                    startActivity(goto_classes);

                }
                if (item.getItemId() == R.id.nav_about_us) {

                    Intent about_us = new Intent(Week_Days_Class_activity.this, About_Us_Activity.class);
                    startActivity(about_us);
                    finish();

                }
                if (item.getItemId() == R.id.nav_setting) {

                    Toast.makeText(Week_Days_Class_activity.this, "test 1 successfull", Toast.LENGTH_LONG).show();

                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(Week_Days_Class_activity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

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

    private void setup_progress_dialog() {

        pDialog = new ProgressDialog(Week_Days_Class_activity.this);
        pDialog.setMessage("در حال دریافت اطلاعات ");
        pDialog.setCancelable(false);

    }

    private void set_listeners() {

        tabLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                _Cash.what_week_day = tabLayout.getSelectedTabPosition();
            }
        });

        weekdays_ib_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_or_past -- ;
                Shanbe.DataList = null ;
                Yek_Shanbe.DataList = null ;
                Do_Shanbe.DataList = null ;
                Se_Shanbe.DataList = null ;
                Chahar_Shanbe.DataList = null ;
                Panj_Shanbe.DataList = null;

                Shanbe.List_Class = null ;
                Yek_Shanbe.List_Class = null ;
                Do_Shanbe.List_Class = null ;
                Se_Shanbe.List_Class = null ;
                Chahar_Shanbe.List_Class = null ;
                Panj_Shanbe.List_Class = null ;

//                _Cash.setList_Class_0Shanbe(null);
//                _Cash.setList_Class_1Shanbe(null);
//                _Cash.setList_Class_2Shanbe(null);
//                _Cash.setList_Class_3Shanbe(null);
//                _Cash.setList_Class_4Shanbe(null);
//                _Cash.setList_Class_5Shanbe(null);


//                for (int i = 0 ; i> _Cash.getList_Class_0Shanbe().size();i++ ){
//                    _Cash
//
//                }


//                Shanbe.recycle_class_list.notifyAll();
                Yek_Shanbe.adapter.notifyDataSetChanged();

                set_Date_Text(next_or_past);
            }
        });

        weekdays_ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_or_past ++ ;
                Shanbe.DataList = null ;
                Yek_Shanbe.DataList = null ;
                Do_Shanbe.DataList = null ;
                Se_Shanbe.DataList = null ;
                Chahar_Shanbe.DataList = null ;
                Panj_Shanbe.DataList = null;

                Shanbe.List_Class = null ;
                Yek_Shanbe.List_Class = null ;
                Do_Shanbe.List_Class = null ;
                Se_Shanbe.List_Class = null ;
                Chahar_Shanbe.List_Class = null ;
                Panj_Shanbe.List_Class = null ;

                _Cash.setList_Class_0Shanbe(null);
                _Cash.setList_Class_1Shanbe(null);
                _Cash.setList_Class_2Shanbe(null);
                _Cash.setList_Class_3Shanbe(null);
                _Cash.setList_Class_4Shanbe(null);
                _Cash.setList_Class_5Shanbe(null);

//                List_Class0 = null ;
//                List_Class1 = null ;
//                List_Class2 = null ;
//                List_Class3 = null ;
//                List_Class4 = null ;
//                List_Class5 = null ;

//                Shanbe.recycle_class_list.notifyAll();
                Yek_Shanbe.adapter.notifyDataSetChanged();


                set_Date_Text(next_or_past);


            }
        });

    }

    private void set_typefaces() {

        custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/koodak.ttf");
        Weekly_Tv_From_Date_To_Date.setTypeface(custom_font);

    }

    private void find_views() {

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        Weekly_Tv_From_Date_To_Date = findViewById(R.id.Weekly_Tv_From_Date_To_Date);
        weekdays_ib_before = findViewById(R.id.weekdays_ib_before);
        weekdays_ib_next = findViewById(R.id.weekdays_ib_next);

    }

    private void change_notification_color() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Week_Days_Class_activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Week_Days_Class_activity.this, R.color.useful_dark));

        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Util_Tablayout.ViewPagerAdapter adapter = new Util_Tablayout.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(kanseli_jobrani, "تقویم");
        adapter.addFragment(Panj_Shanbe, "پنج شنبه");
        adapter.addFragment(Chahar_Shanbe, "چهار شنبه");
        adapter.addFragment(Se_Shanbe, "سه شنبه");
        adapter.addFragment(Do_Shanbe, "دو شنبه");
        adapter.addFragment(Yek_Shanbe, "یک شنبه");
        adapter.addFragment(Shanbe, "شنبه");

        viewPager.setAdapter(adapter);

    }

//    ------------------------------------------------------WS--------------------------------------------------------------------

//    private void getApiCall(String url, Context context) {
//
//        try {
//
//            GETAPIRequest getapiRequest = new GETAPIRequest();
//            getapiRequest.request(context, fetchGetResultListener, url);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //Implementing interfaces of FetchDataListener for GET api request
//    FetchDataListener fetchGetResultListener = new FetchDataListener() {
//
//        @Override
//        public void onFetchComplete(JSONObject data) {
//            RequestQueueService.cancelProgressDialog();
//            try {
//                if (data != null) {
//
//                    Pars_Json(data.toString());
//
//                } else {
//                    RequestQueueService.showAlert("Error! No data fetched", Week_Days_Class_activity.this);
//                }
//
//            } catch (Exception e) {
//                RequestQueueService.showAlert("Something went wrong", Week_Days_Class_activity.this);
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onFetchFailure(String msg) {
//            RequestQueueService.cancelProgressDialog();
//            //Show if any error message is there called from GETAPIRequest class
//            RequestQueueService.showAlert(msg, Week_Days_Class_activity.this);
//        }
//
//        @Override
//        public void onFetchStart() {
//            //Start showing progressbar or any loader you have
//            RequestQueueService.showProgressDialog(Week_Days_Class_activity.this);
//        }
//    };

//    -----------------------------------------------------------------------------------------------------------------------------

//    private void set_week_days() {
//
//        try {
//            Today_Date = new SimpleDateFormat("dd/MM/yyyy").parse(Today_Date_Local);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        switch (Today_Day_Local) {
//
//            case "Saturday":
//
//                StartOfWeek = Today_Date;
//                Calendar _sat = Calendar.getInstance();
//                _sat.setTime(Today_Date);
//                _sat.add(Calendar.DATE, 5);
//                EndOfWeek = _sat.getTime();
//                set_Date_Text();
//                break;
//
//            case "Sunday":
//
//                Calendar sun = Calendar.getInstance();
//                sun.setTime(Today_Date);
//                sun.add(Calendar.DATE, -1);
//                StartOfWeek = sun.getTime();
//
//                Calendar _sun = Calendar.getInstance();
//                _sun.setTime(Today_Date);
//                _sun.add(Calendar.DATE, 4);
//                EndOfWeek = _sun.getTime();
//                set_Date_Text();
//
//                break;
//
//            case "Monday":
//
//                Calendar mon = Calendar.getInstance();
//                mon.setTime(Today_Date);
//                mon.add(Calendar.DATE, -2);
//                StartOfWeek = mon.getTime();
//
//                Calendar _mon = Calendar.getInstance();
//                _mon.setTime(Today_Date);
//                _mon.add(Calendar.DATE, 3);
//                EndOfWeek = _mon.getTime();
//                set_Date_Text();
//
//                break;
//
//            case "Tuesday":
//
//                Calendar tue = Calendar.getInstance();
//                tue.setTime(Today_Date);
//                tue.add(Calendar.DATE, -3);
//                StartOfWeek = tue.getTime();
//
//                Calendar _tue = Calendar.getInstance();
//                _tue.setTime(Today_Date);
//                _tue.add(Calendar.DATE, 2);
//                EndOfWeek = _tue.getTime();
//                set_Date_Text();
//
//
//                break;
//
//            case "Wednesday":
//
//                Calendar wed = Calendar.getInstance();
//                wed.setTime(Today_Date);
//                wed.add(Calendar.DATE, -4);
//                StartOfWeek = wed.getTime();
//
//                Calendar _wed = Calendar.getInstance();
//                _wed.setTime(Today_Date);
//                _wed.add(Calendar.DATE, 1);
//                EndOfWeek = _wed.getTime();
//                set_Date_Text();
//
//
//                break;
//
//            case "Thursday ":
//
//                Calendar thu = Calendar.getInstance();
//                thu.setTime(Today_Date);
//                thu.add(Calendar.DATE, -5);
//                StartOfWeek = thu.getTime();
//
//                EndOfWeek = Today_Date;
//                set_Date_Text();
//                break;
//
//            case "Friday":
//
//                Calendar fri = Calendar.getInstance();
//                fri.setTime(Today_Date);
//                fri.add(Calendar.DATE, -6);
//                StartOfWeek = fri.getTime();
//
//                Calendar _fri = Calendar.getInstance();
//                _fri.setTime(Today_Date);
//                _fri.add(Calendar.DATE, -1);
//                EndOfWeek = _fri.getTime();
//                set_Date_Text();
//                break;
//
//
//        }
//
//    }
//
//    private void Pars_Json(String data) {
//
//        Gson gson = new Gson();
//
//        model_time _model_time = gson.fromJson(data, model_time.class);
//
//        String miladi_date = _model_time.getDate();
//        String miladi_day = _model_time.getDay();
//
//
//        String[] parts = miladi_date.split("/");
//
//
//        String Year = parts[0];
//        String m_d = parts[1];
//        String[] parts2 = m_d.split("-");
//        String Month = parts2[0];
//        String Day = parts2[1];
//
//        int year = Integer.parseInt(Year);
//        int month = Integer.parseInt(Month);
//        int day = Integer.parseInt(Day);
//
//        String Today = Day + "/" + Month + "/" + Year;
//
//        Today_Date_Local = Today;
//        Today_Day_Local = miladi_day;
//
//        set_week_days();
//    }
//
//    private void set_Date_Text() {
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        String start_s = dateFormat.format(StartOfWeek);
//        String end_s = dateFormat.format(EndOfWeek);
//
//        Weekly_Tv_From_Date_To_Date.setText(start_s + "   تا   " + end_s);
//
//    }

// ------------------------------------------------- time -------------------------------------------------


    private void set_Date_Text(int n) {


        String start_s = _Cash.getStart_day_for_show();
        String end_s = _Cash.getEnd_day_for_show();
        String start_w = _Cash.getStart_day_for_ws();
        String end_w = _Cash.getEnd_day_for_ws();
//        ====================================================================================================
//        ====================================================================================================
//        ====================================================================================================

//        start show , end show , start web service , end web service
//        DateFormat ss = new SimpleDateFormat("dd/MM/yyyy");
//        Date sss = ss.parse(start_s);
//
//        Date es = new SimpleDateFormat("dd/MM/yyyy").parse(end_s);
//        Date sw = new SimpleDateFormat("dd-MM-yyyy").parse(start_w);
//        Date ew = new SimpleDateFormat("dd-MM-yyyy").parse(end_w);

        Date s = _Cash.getS();
        Date e = _Cash.getE();

        Date new_s;
        Date new_e;

//        ------------------------------------------convert----------------------------------------------------------


//        String[] parts = miladi_date.split("/");
//
//
//        String Year = parts[0];
//        String m_d = parts[1];
//        String[] parts2 = m_d.split("-");
//        String Month = parts2[0];
//        String Day = parts2[1];

//        DateFormat dateFormat_new = new SimpleDateFormat("yyyy/MM-dd");
//        String a = dateFormat_new.parse(start_s);




        // TODO: 1/24/2019  ino befrestam qmarz convert kone bad to cash zakhire konam badan estefadash konam to week days


        if (n == 0) {


            String[] parts_s_s = start_s.split("/");
            String parts_s_s_1 = parts_s_s[0]; // year
            String parts_s_s_2 = parts_s_s[1]; // month
            String parts_s_s_3 = parts_s_s[2]; // day

            PersianDate date = new PersianDate();
            String kk =  date.Shamsi(Integer.valueOf(parts_s_s_1),Integer.valueOf(parts_s_s_2),Integer.valueOf(parts_s_s_3));
            String[] P = kk.split("/");
            int YearP = Integer.parseInt(P[0]);
            int MonthP =  Integer.parseInt(P[1]);
            int DayP = Integer.parseInt(P[2]);

            String shamsi_start_0_y = P[0];
            String shamsi_start_0_m = P[1];
            String shamsi_start_0_d = P[2];

            String  shamsi_start_0_ready = shamsi_start_0_y + "/" + shamsi_start_0_m + "/" + shamsi_start_0_d;

            String[] parts_s_e = end_s.split("/");
            String parts_s_e_1 = parts_s_e[0]; // year
            String parts_s_e_2 = parts_s_e[1]; // month
            String parts_s_e_3 = parts_s_e[2]; // day

            PersianDate date_e = new PersianDate();
            String kkk =  date_e.Shamsi(Integer.valueOf(parts_s_e_1),Integer.valueOf(parts_s_e_2),Integer.valueOf(parts_s_e_3));
            String[] Pp = kkk.split("/");
            int YearPp = Integer.parseInt(Pp[0]);
            int MonthPp =  Integer.parseInt(Pp[1]);
            int DayPp = Integer.parseInt(Pp[2]);

            String shamsi_start_0_e_y = Pp[0];
            String shamsi_start_0_e_m = Pp[1];
            String shamsi_start_0_e_d = Pp[2];

            String  shamsi_start_0_e_ready = shamsi_start_0_e_y + "/" + shamsi_start_0_e_m + "/" + shamsi_start_0_e_d;

            Weekly_Tv_From_Date_To_Date.setText(shamsi_start_0_ready + "   تا   " + shamsi_start_0_e_ready);

            String Username = DivarUtils.readDataFromStorage(Constant.USER_CODE, null);
            url_for_getclass = Constant.Class_url + start_w + "/" + end_w + "/" + Username;

            _Cash.setList_Class_0Shanbe(null);
            _Cash.setList_Class_1Shanbe(null);
            _Cash.setList_Class_2Shanbe(null);
            _Cash.setList_Class_3Shanbe(null);
            _Cash.setList_Class_4Shanbe(null);
            _Cash.setList_Class_5Shanbe(null);





//            Class_List_Fragment Shanbe = new Class_List_Fragment();
//            Class_List_Fragment Yek_Shanbe = new Class_List_Fragment();
//            Class_List_Fragment Do_Shanbe = new Class_List_Fragment();
//            Class_List_Fragment Se_Shanbe = new Class_List_Fragment();
//            Class_List_Fragment Chahar_Shanbe = new Class_List_Fragment();
//            Class_List_Fragment Panj_Shanbe = new Class_List_Fragment();
//            Find_Classes_By_Name_Fragment kanseli_jobrani = new Find_Classes_By_Name_Fragment();

            request2(url_for_getclass);
        } else {

            Calendar sss = Calendar.getInstance();
            sss.setTime(s);
            sss.add(Calendar.DATE, ( 7*n ));
            new_s = sss.getTime();

            Calendar eee = Calendar.getInstance();
            eee.setTime(e);
            eee.add(Calendar.DATE, ( 7*n ));
            new_e = eee.getTime();


            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String new_start_s = dateFormat.format(new_s);
            String new_end_s = dateFormat.format(new_e);

            DateFormat dateFormat_ws = new SimpleDateFormat("yyyy-MM-dd");
            String new_start_w = dateFormat_ws.format(new_s);
            String new_end_w = dateFormat_ws.format(new_e);

//            ----------------------------- convert -----------------------------------------------------

            String[] parts_s_s_1 = new_start_s.split("/");
            String parts_s_s_1_a = parts_s_s_1[0]; // year
            String parts_s_s_2_a = parts_s_s_1[1]; // month
            String parts_s_s_3_a = parts_s_s_1[2]; // day

            PersianDate date = new PersianDate();
            String kk =  date.Shamsi(Integer.valueOf(parts_s_s_1_a),Integer.valueOf(parts_s_s_2_a),Integer.valueOf(parts_s_s_3_a));
            String[] P = kk.split("/");
            int YearP = Integer.parseInt(P[0]);
            int MonthP =  Integer.parseInt(P[1]);
            int DayP = Integer.parseInt(P[2]);

            String shamsi_start_0_y_a = P[0];
            String shamsi_start_0_m_a = P[1];
            String shamsi_start_0_d_a = P[2];

            String  shamsi_start_0_ready_a = shamsi_start_0_y_a + "/" + shamsi_start_0_m_a + "/" + shamsi_start_0_d_a;

            String[] parts_s_e_b = new_end_s.split("/");
            String parts_s_e_1_b = parts_s_e_b[0]; // year
            String parts_s_e_2_b = parts_s_e_b[1]; // month
            String parts_s_e_3_b = parts_s_e_b[2]; // day

            PersianDate date_e = new PersianDate();
            String kkk =  date_e.Shamsi(Integer.valueOf(parts_s_e_1_b),Integer.valueOf(parts_s_e_2_b),Integer.valueOf(parts_s_e_3_b));
            String[] Pp = kkk.split("/");
            int YearPp = Integer.parseInt(Pp[0]);
            int MonthPp =  Integer.parseInt(Pp[1]);
            int DayPp = Integer.parseInt(Pp[2]);

            String shamsi_start_0_e_y_b = Pp[0];
            String shamsi_start_0_e_m_b = Pp[1];
            String shamsi_start_0_e_d_b = Pp[2];

            String  shamsi_start_0_e_ready_b = shamsi_start_0_e_y_b + "/" + shamsi_start_0_e_m_b + "/" + shamsi_start_0_e_d_b;


            Weekly_Tv_From_Date_To_Date.setText(shamsi_start_0_ready_a + "   تا   " + shamsi_start_0_e_ready_b);

            String Username = DivarUtils.readDataFromStorage(Constant.USER_CODE, null);
            url_for_getclass = Constant.Class_url + new_start_w + "/" + new_end_w + "/" + Username;

            List<Class_List_Entity> List_Class_null = new ArrayList<Class_List_Entity>();


            _Cash.setList_Class_0Shanbe(List_Class_null);
            _Cash.setList_Class_1Shanbe(List_Class_null);
            _Cash.setList_Class_2Shanbe(List_Class_null);
            _Cash.setList_Class_3Shanbe(List_Class_null);
            _Cash.setList_Class_4Shanbe(List_Class_null);
            _Cash.setList_Class_5Shanbe(List_Class_null);

            request2(url_for_getclass);



        }


    }


// ------------------------------- ws baraye gereftane liste clas ha ----------------------------------

    private void request2(String uurrll) {
        try {
            final ProgressDialog pDialog;
            pDialog = new ProgressDialog(Week_Days_Class_activity.this);
            pDialog.setMessage("در حال اتصال");
            pDialog.setCancelable(false);
            pDialog.show();

            Response.Listener<String> listener2 = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Error!")) {
                        pDialog.dismiss();
                        Toast.makeText(Week_Days_Class_activity.this, "eror", Toast.LENGTH_SHORT).show();
                    } else {
                        pDialog.dismiss();

//                        Toast.makeText(_Cash, response, Toast.LENGTH_SHORT).show();

                        String abc = response;
                        Pars_Json2(response);

                    }
                }
            };

            Response.ErrorListener errorListener2 = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pDialog.dismiss();
                    Toast.makeText(Week_Days_Class_activity.this, "خطا در اتصال", Toast.LENGTH_LONG).show();
                }
            };

            StringRequest request = new StringRequest(Request.Method.GET, uurrll, listener2, errorListener2);
            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

        } catch (Exception e) {
            Exception a = e;
        }
    }

    private void Pars_Json2(String data) {
        Gson gson = new Gson();
        model_class[] _model_class = gson.fromJson(data, model_class[].class);

//        List<Class_List_Entity> List_Class0 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class1 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class2 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class3 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class4 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class5 = new ArrayList<Class_List_Entity>();

//        for (int r = 0 ; r <= List_Class0.size() ; r++)
//            List_Class0.remove(r) ;
//        for (int r = 0 ; r < List_Class1.size() ; r++)
//            List_Class1.remove(r) ;
//        for (int r = 0 ; r < List_Class2.size() ; r++)
//            List_Class2.remove(r) ;
//        for (int r = 0 ; r < List_Class3.size() ; r++)
//            List_Class3.remove(r) ;
//        for (int r = 0 ; r < List_Class4.size() ; r++)
//            List_Class4.remove(r) ;
//        for (int r = 0 ; r < List_Class5.size() ; r++)
//            List_Class5.remove(r) ;

        List_Class0.clear();
        List_Class1.clear();
        List_Class2.clear();
        List_Class3.clear();
        List_Class4.clear();
        List_Class5.clear();

        for (int i = 0; i < _model_class.length; i++) {

            switch (_model_class[i].getDayOfWeek()) {


                case "Saturday":

                    Class_List_Entity shanbe = new Class_List_Entity();

                    shanbe.ClassCode = _model_class[i].getClassID();
                    shanbe.ClassName = _model_class[i].getClassName();
                    shanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
                    String Class_number_Shanbe = _model_class[i].getClassPosition();
                    String Facility_Shanbe = _model_class[i].getFacility();
                    shanbe.ClassLocation = Class_number_Shanbe + " کلاس " + Facility_Shanbe;
                    shanbe.ClassTime = "" + _model_class[i].getTime();
                    // TODO: 1/20/2019 felan hame hoozoor hastand
                    shanbe.isCanseled = false;



                    List_Class0.add(shanbe);

                    break;

                case "Sunday":

                    Class_List_Entity yekshanbe = new Class_List_Entity();

                    yekshanbe.ClassCode = _model_class[i].getClassID();
                    yekshanbe.ClassName = _model_class[i].getClassName();
                    yekshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
                    String Class_number_yekshanbe = _model_class[i].getClassPosition();
                    String Facility_yekshanbe = _model_class[i].getFacility();
                    yekshanbe.ClassLocation = Class_number_yekshanbe + " کلاس " + Facility_yekshanbe;
                    yekshanbe.ClassTime = "" + _model_class[i].getTime();
                    yekshanbe.isCanseled = false;



                    List_Class1.add(yekshanbe);

                    break;

                case "Monday":

                    Class_List_Entity doshanbe = new Class_List_Entity();

                    doshanbe.ClassCode = _model_class[i].getClassID();
                    doshanbe.ClassName = _model_class[i].getClassName();
                    doshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
                    String Class_number_doshanbe = _model_class[i].getClassPosition();
                    String Facility_doshanbe = _model_class[i].getFacility();
                    doshanbe.ClassLocation = Class_number_doshanbe + " کلاس " + Facility_doshanbe;
                    doshanbe.ClassTime = "" + _model_class[i].getTime();
                    doshanbe.isCanseled = false;


                    List_Class2.add(doshanbe);

                    break;

                case "Tuesday":

                    Class_List_Entity seshanbe = new Class_List_Entity();

                    seshanbe.ClassCode = _model_class[i].getClassID();
                    seshanbe.ClassName = _model_class[i].getClassName();
                    seshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
                    String Class_number_seshanbe = _model_class[i].getClassPosition();
                    String Facility_seshanbe = _model_class[i].getFacility();
                    seshanbe.ClassLocation = Class_number_seshanbe + " کلاس " + Facility_seshanbe;
                    seshanbe.ClassTime = "" + _model_class[i].getTime();
                    seshanbe.isCanseled = false;


                    List_Class3.add(seshanbe);

                    break;

                case "Wednesday":
                    Class_List_Entity charshanbe = new Class_List_Entity();

                    charshanbe.ClassCode = _model_class[i].getClassID();
                    charshanbe.ClassName = _model_class[i].getClassName();
                    charshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
                    String Class_number_charshanbe = _model_class[i].getClassPosition();
                    String Facility_charshanbe = _model_class[i].getFacility();
                    charshanbe.ClassLocation = Class_number_charshanbe + " کلاس " + Facility_charshanbe;
                    charshanbe.ClassTime = "" + _model_class[i].getTime();
                    charshanbe.isCanseled = false;


                    List_Class4.add(charshanbe);

                    break;

                case "Thursday ":
                    Class_List_Entity panjshanbe = new Class_List_Entity();

                    panjshanbe.ClassCode = _model_class[i].getClassID();
                    panjshanbe.ClassName = _model_class[i].getClassName();
                    panjshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
                    String Class_number_panjshanbe = _model_class[i].getClassPosition();
                    String Facility_panjshanbe = _model_class[i].getFacility();
                    panjshanbe.ClassLocation = Class_number_panjshanbe + " کلاس " + Facility_panjshanbe;
                    panjshanbe.ClassTime = "" + _model_class[i].getTime();
                    panjshanbe.isCanseled = false;


                    List_Class5.add(panjshanbe);

                    break;


            }

        }
        _Cash.setList_Class_0Shanbe(List_Class0);
        _Cash.setList_Class_1Shanbe(List_Class1);
        _Cash.setList_Class_2Shanbe(List_Class2);
        _Cash.setList_Class_3Shanbe(List_Class3);
        _Cash.setList_Class_4Shanbe(List_Class4);
        _Cash.setList_Class_5Shanbe(List_Class5);

        setup_viewpager();

    }


}