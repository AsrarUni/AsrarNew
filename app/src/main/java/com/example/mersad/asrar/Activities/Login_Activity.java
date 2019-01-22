package com.example.mersad.asrar.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.Model.Login_Entity;
import com.example.mersad.asrar.Utils.AppSingleton;
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Model.Students_Attendance_Entity;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wiadevelopers.com.library.DivarUtils;


public class Login_Activity extends AppCompatActivity {

    LinearLayout Login_Layout;
    Button Login_btn_Enter;
    EditText Login_edtUsername, Login_edt_pass;
    TextView Login_Tv_InsertUserPass;
    ImageView Login_Iv_Logo;
    Typeface custom_font;
    Cash _Cash;
    List<Students_Attendance_Entity> List_test;
    List<Class_List_Entity> List_Class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _Cash = (Cash) getApplication();
        initialize();
    }

    private void initialize() {
        findViews();
        showContainer_and_Items();
//        set_unreal_infos();
        set_Listeners();
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
//        _Cash.setList_Students(List_test);
//        _Cash.setList_Class_0Shanbe(List_Class0);
//        _Cash.setList_Class_1Shanbe(List_Class1);
//        _Cash.setList_Class_2Shanbe(List_Class2);
//        _Cash.setList_Class_3Shanbe(List_Class3);
//        _Cash.setList_Class_4Shanbe(List_Class4);
//        _Cash.setList_Class_5Shanbe(List_Class5);

    }

    private void findViews() {
        Login_Layout = (LinearLayout) findViewById(R.id.Login_Layout);
        Login_edtUsername = (EditText) findViewById(R.id.Login_edtUsername);
        Login_edt_pass = (EditText) findViewById(R.id.Login_edt_pass);
        Login_Tv_InsertUserPass = (TextView) findViewById(R.id.Login_Tv_InsertUserPass);
        Login_btn_Enter = (Button) findViewById(R.id.Login_btn_Enter);
        Login_Iv_Logo = (ImageView) findViewById(R.id.Login_Iv_Logo);
    }

    private void set_Listeners() {

        Login_btn_Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });

    }

    private void showContainer_and_Items() {
//------ Animation ha -----//
        Login_Layout.animate().alpha(1f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

//----- Animaton Logo asrar -----//
                Login_Iv_Logo.setVisibility(View.VISIBLE);
                PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f);
                PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f);
                ObjectAnimator animProfilePic = ObjectAnimator.ofPropertyValuesHolder(Login_Iv_Logo, scaleXHolder, scaleYHolder);
                animProfilePic.setDuration(1300);

                animProfilePic.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Login_Tv_InsertUserPass.setVisibility(View.VISIBLE);
                    }
                });

//----- Animation matne welcome -----//
                float startXWelcome = 0 - Login_Tv_InsertUserPass.getWidth();
                float endXWelcome = Login_Tv_InsertUserPass.getX();
                PropertyValuesHolder TranslationX = PropertyValuesHolder.ofFloat(View.X, startXWelcome, endXWelcome);
                ObjectAnimator animWelcome = ObjectAnimator.ofPropertyValuesHolder(Login_Tv_InsertUserPass, TranslationX);
                animWelcome.setDuration(1700);

                animWelcome.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Login_edtUsername.setVisibility(View.VISIBLE);
                    }
                });

//----- Animation Username -----//
                float startXusername = 0 - Login_edtUsername.getWidth();
                float endXusername = Login_edtUsername.getX();
                PropertyValuesHolder TranslationXusername = PropertyValuesHolder.ofFloat(View.X, startXusername, endXusername);
                ObjectAnimator animUsername = ObjectAnimator.ofPropertyValuesHolder(Login_edtUsername, TranslationXusername);
                animUsername.setDuration(800);

                animUsername.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Login_edt_pass.setVisibility(View.VISIBLE);
                    }
                });


//----- Animation Password -----//
                float startXpassword = 0 - Login_edt_pass.getWidth();
                float endXpassword = Login_edt_pass.getX();
                PropertyValuesHolder TranslationXpassword = PropertyValuesHolder.ofFloat(View.X, startXpassword, endXpassword);
                ObjectAnimator animPassword = ObjectAnimator.ofPropertyValuesHolder(Login_edt_pass, TranslationXpassword);
                animPassword.setDuration(800);

                animPassword.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Login_btn_Enter.setVisibility(View.VISIBLE);
                    }
                });

//----- Animation button -----//
                float startYbutton = Login_btn_Enter.getHeight() * 2;
                float endYbutton = Login_btn_Enter.getY();
                PropertyValuesHolder TranslationYbutton = PropertyValuesHolder.ofFloat(View.Y, startYbutton, endYbutton);
                ObjectAnimator animButton = ObjectAnimator.ofPropertyValuesHolder(Login_btn_Enter, TranslationYbutton);
                animButton.setDuration(400);

                AnimatorSet set = new AnimatorSet();
                set.playSequentially(animProfilePic, animWelcome, animUsername, animPassword, animButton);
                set.start();
            }
        });


    }

    private void request() {
//----- Web servise Login -----//

        String url = Constant.Login_url + Login_edtUsername.getText().toString() + "/" + Login_edt_pass.getText().toString();

//----- Eejade dialog dar hale etesal -----//
        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(Login_Activity.this);
        pDialog.setMessage("در حال اتصال");
        pDialog.setCancelable(false);
        pDialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Error!")) {
                    pDialog.dismiss();
                    Login_Tv_InsertUserPass.setText("شماره پرسنلی یا کلمه عبور اشتباه است");
                    Login_Tv_InsertUserPass.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.Absent));
                } else {
                    pDialog.dismiss();
                    Toast.makeText(Login_Activity.this, "ورود با موفقیت انجام شد", Toast.LENGTH_LONG).show();
                    Intent login_ok = new Intent(Login_Activity.this, Security_Activity.class);
                    DivarUtils.writeDataInStorage(Constant.USER_CODE, Login_edtUsername.getText().toString());
                    DivarUtils.writeDataInStorage(Constant.USER_PASS, Login_edt_pass.getText().toString());
                    startActivity(login_ok);
                    finish();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(Login_Activity.this, "خطا در اتصال", Toast.LENGTH_LONG).show();
            }
        };

        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

}