package com.example.mersad.asrar.Activities;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

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

    }

    private void setup_recycler() {

        adapter = new Setting_Adapter(this , Setting_List);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Setting_Recycle.setLayoutManager(linearLayoutManager);
        Setting_Recycle.setAdapter(adapter);
        Setting_Recycle.setItemAnimator(new DefaultItemAnimator());

    }

    private void find_views() {

        Setting_List = findViewById(R.id.Setting_recycle);

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


}
