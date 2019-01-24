package com.example.mersad.asrar.Activities;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
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

import java.util.ArrayList;
import java.util.Calendar;

public class Send_Message_To_Class_Activity extends AppCompatActivity {

    JustifiedTextView jtv_SendMessageToClassStudentsHint ;
    Typeface custom_font;
    Button btn_SendMessageToClassStudents ;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    String Class_Code ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message_to_class);

        Intent intent = getIntent();
        Class_Code = intent.getStringExtra(Constant.Code_Class);

        initialize();
        change_notification_color();
        set_Listeners();
//        scheduleNotification(getApplicationContext(), 2000,1 );
        //baraye call kardane unayi ke chand item daran bayad be unha value bedim


//        set_typefaces();

    }

    private void set_Listeners() {

        btn_SendMessageToClassStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 1/10/2019 inja bayad matne justified text view ro begire befreste server
//                Intent m = new Intent(Send_Message_To_Class_Activity.this , Exams_Activity.class);
//                startActivity(m);


                ArrayList<Integer> alarmDays= new ArrayList<Integer>();
                alarmDays.add(Calendar.SUNDAY);
                Intent alarm =new Intent(AlarmClock.ACTION_SET_ALARM);
                alarm.putExtra(AlarmClock.EXTRA_HOUR,13);
                alarm.putExtra(AlarmClock.EXTRA_MINUTES,30);
                alarm.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);
                startActivity(alarm);

                Calendar calendar =Calendar.getInstance();
//                calendar.set(calendar.YEAR, 2019);
                calendar.set(2019, Calendar.JANUARY, 22,11,30,15);

//                calendar.set(calendar.HOUR_OF_DAY,14);
//                calendar.set(calendar.MINUTE,18);
//                calendar.set(calendar.SECOND,15);


                Intent intent =new Intent(getApplicationContext(),MyNotificationPublisher.class);
                PendingIntent pendingIntent =PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);



            }
        });

    }

//    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                .setContentTitle(context.getString(R.string.insert_user_pass))
//                .setContentText(context.getString(R.string.pull_down))
//                .setAutoCancel(true)
//                .setSmallIcon(R.drawable.haghighi)
//                .setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.haghighi)).getBitmap())
//                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
//
//        Intent intent = new Intent(context, Send_Message_To_Class_Activity.class);
//        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        builder.setContentIntent(activity);
//
//        Notification notification = builder.build();
//
//        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
//        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
//        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification );
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        long futureInMillis = SystemClock.elapsedRealtime() + delay;
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
//    }








    private void set_typefaces() {

        custom_font = Typeface.createFromAsset(getAssets(), "fonts/koodak.ttf");
//        JustifiedTextView.setTypeface(custom_font);

    }

    private void initialize() {

        find_views();
        Setup_Justified_TextView();
        setup_toolbar();
        setup_navigation();

    }

    private void Setup_Justified_TextView() {


        jtv_SendMessageToClassStudentsHint.setText(getString(R.string.SendMessageToClassStudentsHint1) + Class_Code + getString(R.string.SendMessageToClassStudentsHint2) );
        jtv_SendMessageToClassStudentsHint.setTextSize(TypedValue.COMPLEX_UNIT_SP , 15);

    }

    private void find_views() {

        jtv_SendMessageToClassStudentsHint = findViewById(R.id.jtv_SendMessageToClassStudentsHint);
        btn_SendMessageToClassStudents = findViewById(R.id.btn_SendMessageToClassStudents);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }

    private void change_notification_color(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Send_Message_To_Class_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Send_Message_To_Class_Activity.this,R.color.useful_dark));

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

                    Intent goto_classes = new Intent (Send_Message_To_Class_Activity.this , Week_Days_Class_activity.class);
                    startActivity(goto_classes);

                }
                if (item.getItemId() == R.id.nav_about_us) {

                    Intent about_us = new Intent(Send_Message_To_Class_Activity.this , About_Us_Activity.class );
                    startActivity(about_us);
                    finish();

                }
                if (item.getItemId() == R.id.nav_setting) {

//                    Toast.makeText(MainActivity.this, "test 1 successfull", Toast.LENGTH_LONG).show();

                    Intent edt = new Intent (Send_Message_To_Class_Activity.this , Setting_Activity.class);
                    startActivity(edt);

                }
                if (item.getItemId() == R.id.nav_test2) {

                    Toast.makeText(Send_Message_To_Class_Activity.this, "test 2 successfull", Toast.LENGTH_LONG).show();

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
