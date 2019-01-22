package com.example.mersad.asrar.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.AlarmClock;
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
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.Model.model_message;
import com.example.mersad.asrar.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import wiadevelopers.com.library.DivarUtils;

public class Remember_Activity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private RadioGroup remember_ig_when;
    private RadioButton remember_ig_one_day_before, remember_ig_eight_hours_before, remember_ig_on_time;
    private Button remember_btn_save;
    private EditText remember_edt_remember_text;
    private CheckBox remember_cb_is_alarm;

    int when;
    boolean is_alarm;
    String Class_Code;
    private Date date;
    private Realm _Realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);

        Intent intent = getIntent();
        Class_Code = intent.getStringExtra(Constant.Code_Class);
        _Realm = Realm.getDefaultInstance();
        initialize();

    }

    private void initialize() {

        find_views();
        change_notification_color();
        setup_toolbar();
        setup_navigation();
        set_listeners();

    }

    private void set_listeners() {

        // TODO: 1/17/2019  complete alarm

        remember_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (remember_ig_one_day_before.isChecked()) when = 1;
                else if (remember_ig_eight_hours_before.isChecked()) when = 2;
                else if (remember_ig_on_time.isChecked()) when = 3;

//                is_alarm = remember_cb_is_alarm.isChecked();
//
//                if (is_alarm) {
//                    setAlarm(date);
//                }

                Check_For_write_or_update("1396/12/21");

            }
        });

    }


    private void change_notification_color() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Remember_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Remember_Activity.this, R.color.useful_dark));

        }
    }


    private void find_views() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        remember_ig_when = findViewById(R.id.remember_ig_when);

        remember_ig_one_day_before = findViewById(R.id.remember_ig_one_day_before);
        remember_ig_eight_hours_before = findViewById(R.id.remember_ig_eight_hours_before);
        remember_ig_on_time = findViewById(R.id.remember_ig_on_time);
        remember_btn_save = findViewById(R.id.remember_btn_save);
        remember_edt_remember_text = findViewById(R.id.remember_edt_remember_text);

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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_class_list) {

                    Intent goto_classes = new Intent(Remember_Activity.this, Weekly_Class_Tab_Activity.class);
                    startActivity(goto_classes);

                }
                if (item.getItemId() == R.id.nav_about_us) {

                    Intent about_us = new Intent(Remember_Activity.this, About_Us_Activity.class);
                    startActivity(about_us);
                    finish();

                }
                if (item.getItemId() == R.id.nav_setting) {

//                    Toast.makeText(MainActivity.this, "test 1 successfull", Toast.LENGTH_LONG).show();

                    Intent edt = new Intent(Remember_Activity.this, Setting_Activity.class);
                    startActivity(edt);

                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(Remember_Activity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

                }


                return false;
            }


        });


    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    private void write_in_database(String classcode, String message, String Date) {

//        DivarUtils.writeDataInStorage("Message_For_Class_"+classcode+"_"+Date , message );

        model_message mmm = new model_message(Date, classcode, message);

        _Realm.beginTransaction();
        _Realm.copyToRealm(mmm);
        _Realm.commitTransaction();

    }

    private void Check_For_write_or_update(String Date) {

//        if( DivarUtils.readDataFromStorage("Message_For_Class_"+Class_Code+"_"+Date , null) == null ){
//            String Message = remember_edt_remember_text.getText().toString().trim();
//            write_in_database(Class_Code ,Message , Date );
//        }else {
//
//         customDialog(Date);
//
//        }

        RealmResults<model_message> mes = _Realm.where(model_message.class).equalTo("date", Date)
                .equalTo("ClassCode", Class_Code).findAll();
        if (mes.size() == 1) {
            customDialog(Date);
        } else {
            String Message = remember_edt_remember_text.getText().toString().trim();
            write_in_database(Class_Code, Message, Date);
            Toast.makeText(this, "یاد داشت با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
        }


    }

    private void customDialog(String Date) {
        final Button cancell, replace;

        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.dialog_duplicate, null);

        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", null);
        alertDialogBuilder.setNegativeButton("CANCEL", null);

        cancell = (Button) view.findViewById(R.id.Dialog_duolicate_cancell);
        replace = (Button) view.findViewById(R.id.Dialog_duolicate_replace);


        final android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                Button btnPossetive = alertDialog.getButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE);
                btnPossetive.setVisibility(View.INVISIBLE);

                Button btnNegative = alertDialog.getButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE);
                btnNegative.setVisibility(View.INVISIBLE);

            }
        });

        // baraye koochek tar kardane arze alert dialog
        alertDialog.getWindow().setBackgroundDrawable(this.getResources().getDrawable(R.drawable.dialog_style));
        alertDialog.show();

        Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        width = (int) ((width) * ((double) 4 / 5));
        alertDialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);


        cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message = remember_edt_remember_text.getText().toString().trim();
//                write_in_database(Class_Code ,Message , Date );

                RealmResults<model_message> mes = _Realm.where(model_message.class).equalTo("date", Date)
                        .equalTo("ClassCode", Class_Code).findAll();
                if (mes.size() == 1) {
                    mes.get(0).setMessage(Message);
                    Toast.makeText(Remember_Activity.this, "یاد داشت با موفقیت بروز رسانی گردید", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void setAlarm(Date Date) {


        Intent alarm = new Intent(AlarmClock.ACTION_SET_ALARM);
        alarm.putExtra(AlarmClock.EXTRA_HOUR, 13);
        alarm.putExtra(AlarmClock.EXTRA_MINUTES, 04);
        startActivity(alarm);


        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String reportDate = df.format(Date);


        switch (when) {
            case 1:

                break;
            case 2:
                break;
            case 3:

                break;
        }
    }
}
