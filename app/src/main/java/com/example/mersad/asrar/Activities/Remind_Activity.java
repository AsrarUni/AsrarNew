package com.example.mersad.asrar.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.mersad.asrar.R;

public class Remind_Activity extends AppCompatActivity {

    RecyclerView Remind_recycle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        initialize();

    }

    private void initialize() {
        findViews();
//        showContainer_and_Items();
//        set_Listeners();

    }

    private void findViews() {

        Remind_recycle = findViewById(R.id.Remind_recycle);


    }


}
