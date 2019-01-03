package com.example.mersad.asrar.Activities;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.R;

public class Employment_Info_Activity extends AppCompatActivity {

    Cash _Cash;
    private ImageView Employment_IV_profile_pic;
    private TextView Employment_Tv_Fname, Employment_Tv_Lname, Employment_Tv_Tel, Employment_Tv_Mobile,
            Employment_Tv_Email, Employment_Tv_FatherName, Employment_Tv_CodeMeli, Employment_Tv_PersonalNumber, Employment_Tv_DateBirthda,
            Employment_Tv_TypeID;
    Typeface custom_font;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment_info);

        _Cash = (Cash) getApplication();
        initialize();
    }

    private void initialize() {
        findViews();
        change_notification_color();
        fill_info();
    }


    private void findViews() {
        Employment_Tv_Fname = findViewById(R.id.Employment_Tv_Fname);
        Employment_Tv_Lname = findViewById(R.id.Employment_Tv_Lname);
        Employment_Tv_Tel = findViewById(R.id.Employment_Tv_Tel);
        Employment_Tv_Mobile = findViewById(R.id.Employment_Tv_Mobile);
        Employment_Tv_Email = findViewById(R.id.Employment_Tv_Email);
        Employment_Tv_FatherName = findViewById(R.id.Employment_Tv_FatherName);
        Employment_Tv_CodeMeli = findViewById(R.id.Employment_Tv_CodeMeli);
        Employment_Tv_PersonalNumber = findViewById(R.id.Employment_Tv_PersonalNumber);
        Employment_Tv_DateBirthda = findViewById(R.id.Employment_Tv_DateBirthda);
        Employment_Tv_TypeID = findViewById(R.id.Employment_Tv_TypeID);
    }

    private void fill_info() {
        String test = _Cash.getFname();
        Employment_Tv_Fname.setText(test);

        String test1 = _Cash.getLname();
        Employment_Tv_Lname.setText(test1);

        String test2 = _Cash.getTel();
        Employment_Tv_Tel.setText(test2);

        String test3 = _Cash.getMobile();
        Employment_Tv_Mobile.setText(test3);

        String test4 = _Cash.getEmail();
        Employment_Tv_Email.setText(test4);

        String test5 = _Cash.getFatherName();
        Employment_Tv_FatherName.setText(test5);

        String test6 = String.valueOf(_Cash.getCodeMeli());
        Employment_Tv_CodeMeli.setText(test6);

        String test7 = String.valueOf(_Cash.getPersonalNumber());
        Employment_Tv_PersonalNumber.setText(test7);

        String test8 = _Cash.getDateBirthday();
        Employment_Tv_DateBirthda.setText(test8);

        String test9 = String.valueOf(_Cash.getTypeID());
        Employment_Tv_TypeID.setText(test9);
    }

    private void change_notification_color() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Employment_Info_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Employment_Info_Activity.this, R.color.useful_dark));

        }
    }

}