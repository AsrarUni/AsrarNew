package com.example.mersad.asrar.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.mersad.asrar.Fragments.Class_List_Fragment;
import com.example.mersad.asrar.Fragments.Find_Classes_By_Name_Fragment;
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.Model.Login_Entity;
import com.example.mersad.asrar.Model.model_class;
import com.example.mersad.asrar.Model.model_time;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.Utils.AppSingleton;
import com.example.mersad.asrar.Volley_WebService.FetchDataListener;
import com.example.mersad.asrar.Volley_WebService.GETAPIRequest;
import com.example.mersad.asrar.Volley_WebService.RequestQueueService;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import wiadevelopers.com.library.DivarUtils;
import wiadevelopers.com.library.MaskdEditText.MaskedEditText;

public class Security_Activity extends AppCompatActivity {

    TextView number_1 , number_2 , number_3 , number_4 , number_5 , number_6 , number_7 , number_8 , number_9 , number_0 , ok  , description ;
    ImageButton del ;
    Typeface custom_font;
    Cash _Cash;
    String is_has_pass , first_choose ;
    MaskedEditText Securuty_Mtv_Pass ;


//    for time :


    String Today_Date_Local;
    String Today_Day_Local;

    Date Today_Date;
    Date StartOfWeek;
    Date EndOfWeek;

    String Start_day_for_ws;
    String End_day_for_ws;

    String url_for_getclass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        _Cash = (Cash) getApplication();

        is_has_pass = DivarUtils.readDataFromStorage(Constant.IS_HAS_PATTERN, null);

        initialize();

    }

    private void initialize() {

        find_views();
        change_notification_color();
        set_typefaces();
        handle_is_has_pass ();
        set_Listeners();


//        start_animations();



    }

    private void handle_is_has_pass() {

        if ( is_has_pass==null ){
            description.setText("لطفا یک رمز چهار رقمی برای ورود آسان انتخاب نمایید");
            ok.setText("انتخاب");
        }else {
            description.setText("لطفا رمز ورود آسان خود را وارد نمایید");
        }

    }

    private void set_Listeners() {

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (is_has_pass == null && first_choose == null) {
                    first_choose = Securuty_Mtv_Pass.getRawText().toString();
                    description.setText("برای تایید رمز ُ لطفا یک بار دیگر رمز خود را وارد نمایید");
                    Securuty_Mtv_Pass.setText("");
                    ok.setText("تایید");
                }else if (is_has_pass == null && first_choose.length() > 0){
                    if (Securuty_Mtv_Pass.getRawText().trim().equals( first_choose)) {
                        Toast.makeText(Security_Activity.this, "رمز با موفقیإت انتخاب شد", Toast.LENGTH_SHORT).show();
                        DivarUtils.writeDataInStorage(Constant.IS_HAS_PATTERN, Securuty_Mtv_Pass.getRawText().toString().trim());
//   ------------------------------------------------------------------------------------------------------------------
//   ------------------------------------------------------------------------------------------------------------------
//   ------------------------------------------------------------------------------------------------------------------

                        getApiCall(Constant.Date_url , Security_Activity.this);
//                        request2("http://nazer.thtc.ir/api/class/2018-12-02/2019-01-24/609");
//                        request2(url_for_getclass);
                        request();
                    }else {
                        description.setText("رمز های انتخاب شده با هم تطابق ندارند");
                        new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                first_choose = null ;
                                ok.setText("انتخاب");
                                Securuty_Mtv_Pass.setText("");
                            }
                            @Override
                            public void onFinish() {
                                description.setText("لطفا رمز ورود آسان خود را وارد نمایید");
                            }
                        }.start();
                    }

                }else{
                    String true_pass = DivarUtils.readDataFromStorage(Constant.IS_HAS_PATTERN, null);
                    if (Securuty_Mtv_Pass.getRawText().trim().equals(true_pass) ) {
//  ********************** here *******************************************************************************************************

//                        String Username = DivarUtils.readDataFromStorage(Constant.USER_CODE, null);
//                        String url = Constant.Class_url + Start_day_for_ws + "/" + End_day_for_ws + "/" + Username;
//
//                        _Cash.ws_get_class_list("https://thtc.ir/nazer/api/class/2018-12-02/2019-01-24/609");
//                        getApiCall("http://thtc.ir/nazer/api/date", Security_Activity.this);
                        getApiCall(Constant.Date_url , Security_Activity.this);
//                        request2("http://nazer.thtc.ir/api/class/2018-12-02/2019-01-24/609");
//                        request2(url_for_getclass);
                        request();                    }
                    else {
                        description.setText("رمز وارد شده صحیح نیست !ُ لطفا دوباره امتحان کنید");
                        Securuty_Mtv_Pass.setText("");
                    }
                }

            }
        });

        set_keyboard_listeners(number_0);
        set_keyboard_listeners(number_1);
        set_keyboard_listeners(number_2);
        set_keyboard_listeners(number_3);
        set_keyboard_listeners(number_4);
        set_keyboard_listeners(number_5);
        set_keyboard_listeners(number_6);
        set_keyboard_listeners(number_7);
        set_keyboard_listeners(number_8);
        set_keyboard_listeners(number_9);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String before = Securuty_Mtv_Pass.getRawText().trim() ;
                if (before.length() >0 ) {
                    before = before.substring(0, before.length() - 1);
                    Securuty_Mtv_Pass.setText(before);
                }
                else if (before.length() < 1 ) {
                    Securuty_Mtv_Pass.setText("");
                }

            }
        });

    }

    private void request() {

            String Username = DivarUtils.readDataFromStorage(Constant.USER_CODE, null);
            String Password = DivarUtils.readDataFromStorage(Constant.USER_PASS, null);

            String url = Constant.Login_url + Username + "/" + Password ;

            final ProgressDialog pDialog;
            pDialog = new ProgressDialog(Security_Activity.this);
            pDialog.setMessage("در حال اتصال");
            pDialog.setCancelable(false);
            pDialog.show();

            Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Error!")) {
                        pDialog.dismiss();

                        // TODO: 1/6/2019  inja ye dialog baz she bege khatayi rokh dadeh lotfan dobareh vared shavid
                        // bad dobare bere to safheye loggin

                    } else {
                        Gson gson = new Gson();
                        Login_Entity _Login_Entity = gson.fromJson(response, Login_Entity.class);
//----- set kardane liste info ke haman data az json ast dar klase cash -----//
                        _Cash.setInfo(_Login_Entity.getData());
                        _Cash.Fill_Cash_Info();
                        pDialog.dismiss();
                        Intent login_ok = new Intent(Security_Activity.this, MainActivity.class);
                        startActivity(login_ok);
                        finish();
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pDialog.dismiss();
                    Toast.makeText(Security_Activity.this, "خطا در اتصال", Toast.LENGTH_LONG).show();
                }
            };

            StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    private void set_typefaces() {

        custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/koodak.ttf");

        number_0.setTypeface(custom_font);
        number_1.setTypeface(custom_font);
        number_2.setTypeface(custom_font);
        number_3.setTypeface(custom_font);
        number_4.setTypeface(custom_font);
        number_5.setTypeface(custom_font);
        number_6.setTypeface(custom_font);
        number_7.setTypeface(custom_font);
        number_8.setTypeface(custom_font);
        number_9.setTypeface(custom_font);

        ok.setTypeface(custom_font);

    }

    private void find_views() {

        number_0 = findViewById(R.id.Securuty_tv_num_0);
        number_1 = findViewById(R.id.Securuty_tv_num_1);
        number_2 = findViewById(R.id.Securuty_tv_num_2);
        number_3 = findViewById(R.id.Securuty_tv_num_3);
        number_4 = findViewById(R.id.Securuty_tv_num_4);
        number_5 = findViewById(R.id.Securuty_tv_num_5);
        number_6 = findViewById(R.id.Securuty_tv_num_6);
        number_7 = findViewById(R.id.Securuty_tv_num_7);
        number_8 = findViewById(R.id.Securuty_tv_num_8);
        number_9 = findViewById(R.id.Securuty_tv_num_9);

        ok = findViewById(R.id.Securuty_tv_ok);
        del = findViewById(R.id.Securuty_ib_del);

//        Drawable dr = getResources().getDrawable(R.drawable.ic_backspace9);
//        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
//        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));

//        del.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_backspace9, 0, 0, 0);



        description = findViewById(R.id.txt_description);
        Securuty_Mtv_Pass = findViewById(R.id.Securuty_Mtv_Pass);


    }

    private void change_notification_color(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Security_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Security_Activity.this,R.color.useful_dark));

        }
    }

    private void set_keyboard_listeners(TextView textView){

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Securuty_Mtv_Pass.append( textView.getText().toString().trim() );
                if (Securuty_Mtv_Pass.getText().toString().trim().length() > 16)
                    ok.performClick();
            }
        });


    }


//    ------------------------------------------------------WS--------------------------------------------------------------------

    private void getApiCall(String url, Context context) {

        try {

            GETAPIRequest getapiRequest = new GETAPIRequest();
            getapiRequest.request(context, fetchGetResultListener, url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Implementing interfaces of FetchDataListener for GET api request
    FetchDataListener fetchGetResultListener = new FetchDataListener() {

        @Override
        public void onFetchComplete(JSONObject data) {
            RequestQueueService.cancelProgressDialog();
            try {
                if (data != null) {

                    Pars_Json(data.toString());

                } else {
                    RequestQueueService.showAlert("Error! No data fetched", Security_Activity.this);
                }

            } catch (Exception e) {
                RequestQueueService.showAlert("Something went wrong", Security_Activity.this);
                e.printStackTrace();
            }
        }

        @Override
        public void onFetchFailure(String msg) {
            RequestQueueService.cancelProgressDialog();
            //Show if any error message is there called from GETAPIRequest class
            RequestQueueService.showAlert(msg, Security_Activity.this);
        }

        @Override
        public void onFetchStart() {
            //Start showing progressbar or any loader you have
            RequestQueueService.showProgressDialog(Security_Activity.this);
        }
    };


    private void Pars_Json(String data) {
        Gson gson = new Gson();

        model_time _model_time = gson.fromJson(data, model_time.class);

        String miladi_date = _model_time.getDate();
        String miladi_day = _model_time.getDay();


        String[] parts = miladi_date.split("/");


        String Year = parts[0];
        String m_d = parts[1];
        String[] parts2 = m_d.split("-");
        String Month = parts2[0];
        String Day = parts2[1];

        _Cash.setCash_day(Day);
        _Cash.setCash_month(Month);
        _Cash.setCash_year(Year);
        _Cash.setCash_what_date(miladi_day);

        about_time();

    }

//    private void request2 (String uurrll) {
//        try {
//            final ProgressDialog pDialog;
//            pDialog = new ProgressDialog(Security_Activity.this);
//            pDialog.setMessage("در حال اتصال");
//            pDialog.setCancelable(false);
//            pDialog.show();
//
//            Response.Listener<String> listener2 = new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    if (response.equals("Error!")) {
//                        pDialog.dismiss();
//                        Toast.makeText(Security_Activity.this, "eror", Toast.LENGTH_SHORT).show();
//                    } else {
//                        pDialog.dismiss();
//
////                        Toast.makeText(_Cash, response, Toast.LENGTH_SHORT).show();
//
//                        Pars_Json2(response);
//                    }
//                }
//            };
//
//            Response.ErrorListener errorListener2 = new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    pDialog.dismiss();
//                    Toast.makeText(Security_Activity.this, "خطا در اتصال", Toast.LENGTH_LONG).show();
//                }
//            };
//
//            StringRequest request = new StringRequest(Request.Method.GET, uurrll, listener2, errorListener2);
//            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
//
//        } catch (Exception e) {
//            Exception a = e;
//        }
//    }

    private void about_time() {

        String Today = _Cash.getCash_day() + "/" + _Cash.getCash_month() + "/" + _Cash.getCash_year();

        Today_Date_Local = Today;
        Today_Day_Local = _Cash.getCash_what_date();
        Today_Date = _Cash.getCash_fulldate();



        try {
            Today_Date = new SimpleDateFormat("dd/MM/yyyy").parse(Today_Date_Local);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        switch (Today_Day_Local) {

            case "Saturday":

                StartOfWeek = Today_Date;
                Calendar _sat = Calendar.getInstance();
                _sat.setTime(Today_Date);
                _sat.add(Calendar.DATE, 5);
                EndOfWeek = _sat.getTime();
                set_Date_Text();


                break;

            case "Sunday":

                Calendar sun = Calendar.getInstance();
                sun.setTime(Today_Date);
                sun.add(Calendar.DATE, -1);
                StartOfWeek = sun.getTime();

                Calendar _sun = Calendar.getInstance();
                _sun.setTime(Today_Date);
                _sun.add(Calendar.DATE, 4);
                EndOfWeek = _sun.getTime();
                set_Date_Text();

                break;

            case "Monday":

                Calendar mon = Calendar.getInstance();
                mon.setTime(Today_Date);
                mon.add(Calendar.DATE, -2);
                StartOfWeek = mon.getTime();

                Calendar _mon = Calendar.getInstance();
                _mon.setTime(Today_Date);
                _mon.add(Calendar.DATE, 3);
                EndOfWeek = _mon.getTime();
                set_Date_Text();

                break;

            case "Tuesday":

                Calendar tue = Calendar.getInstance();
                tue.setTime(Today_Date);
                tue.add(Calendar.DATE, -3);
                StartOfWeek = tue.getTime();

                Calendar _tue = Calendar.getInstance();
                _tue.setTime(Today_Date);
                _tue.add(Calendar.DATE, 2);
                EndOfWeek = _tue.getTime();
                set_Date_Text();


                break;

            case "Wednesday":

                Calendar wed = Calendar.getInstance();
                wed.setTime(Today_Date);
                wed.add(Calendar.DATE, -4);
                StartOfWeek = wed.getTime();

                Calendar _wed = Calendar.getInstance();
                _wed.setTime(Today_Date);
                _wed.add(Calendar.DATE, 1);
                EndOfWeek = _wed.getTime();
                set_Date_Text();


                break;

            case "Thursday ":

                Calendar thu = Calendar.getInstance();
                thu.setTime(Today_Date);
                thu.add(Calendar.DATE, -5);
                StartOfWeek = thu.getTime();

                EndOfWeek = Today_Date;
                set_Date_Text();
                break;

            case "Friday":

                Calendar fri = Calendar.getInstance();
                fri.setTime(Today_Date);
                fri.add(Calendar.DATE, -6);
                StartOfWeek = fri.getTime();

                Calendar _fri = Calendar.getInstance();
                _fri.setTime(Today_Date);
                _fri.add(Calendar.DATE, -1);
                EndOfWeek = _fri.getTime();
                set_Date_Text();
                break;


        }


    }

    private void set_Date_Text() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String start_s = dateFormat.format(StartOfWeek);
        String end_s = dateFormat.format(EndOfWeek);

        // TODO: 1/24/2019  ino befrestam qmarz convert kone bad to cash zakhire konam badan estefadash konam to week days
//        Weekly_Tv_From_Date_To_Date.setText(start_s + "   تا   " + end_s);

        DateFormat dateFormat_ws = new SimpleDateFormat("yyyy-MM-dd");


        Start_day_for_ws = dateFormat_ws.format(StartOfWeek);
        End_day_for_ws = dateFormat_ws.format(EndOfWeek);

        String Username = DivarUtils.readDataFromStorage(Constant.USER_CODE, null);
        url_for_getclass = Constant.Class_url + Start_day_for_ws + "/" + End_day_for_ws + "/" + Username;

    }

//    private void Pars_Json2 (String data) {
//        Gson gson = new Gson();
//        model_class[] _model_class = gson.fromJson(data, model_class[].class);
//
//        List<Class_List_Entity> List_Class0 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class1 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class2 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class3 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class4 = new ArrayList<Class_List_Entity>();
//        List<Class_List_Entity> List_Class5 = new ArrayList<Class_List_Entity>();
//
//
//        for (int i = 0; i < _model_class.length; i++) {
//
//            switch (_model_class[i].getDayOfWeek()) {
//
//
//                case "Saturday":
//
//                    Class_List_Entity shanbe = new Class_List_Entity();
//
//                    shanbe.ClassCode = _model_class[i].getClassID();
//                    shanbe.ClassName = _model_class[i].getClassName();
//                    shanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
//                    String Class_number_Shanbe = _model_class[i].getClassPosition();
//                    String Facility_Shanbe = _model_class[i].getFacility();
//                    shanbe.ClassLocation = Class_number_Shanbe + " کلاس " + Facility_Shanbe;
//                    shanbe.ClassTime = "" + _model_class[i].getTime();
//                    // TODO: 1/20/2019 felan hame hoozoor hastand
//                    shanbe.isCanseled = false;
//
//                    List_Class0.add(shanbe);
//
//                    break;
//
//                case "Sunday":
//
//                    Class_List_Entity yekshanbe = new Class_List_Entity();
//
//                    yekshanbe.ClassCode = _model_class[i].getClassID();
//                    yekshanbe.ClassName = _model_class[i].getClassName();
//                    yekshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
//                    String Class_number_yekshanbe = _model_class[i].getClassPosition();
//                    String Facility_yekshanbe = _model_class[i].getFacility();
//                    yekshanbe.ClassLocation = Class_number_yekshanbe + " کلاس " + Facility_yekshanbe;
//                    yekshanbe.ClassTime = "" + _model_class[i].getTime();
//                    yekshanbe.isCanseled = false;
//
//                    List_Class1.add(yekshanbe);
//
//                    break;
//
//                case "Monday":
//
//                    Class_List_Entity doshanbe = new Class_List_Entity();
//
//                    doshanbe.ClassCode = _model_class[i].getClassID();
//                    doshanbe.ClassName = _model_class[i].getClassName();
//                    doshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
//                    String Class_number_doshanbe = _model_class[i].getClassPosition();
//                    String Facility_doshanbe = _model_class[i].getFacility();
//                    doshanbe.ClassLocation = Class_number_doshanbe + " کلاس " + Facility_doshanbe;
//                    doshanbe.ClassTime = "" + _model_class[i].getTime();
//                    doshanbe.isCanseled = false;
//
//                    List_Class2.add(doshanbe);
//
//                    break;
//
//                case "Tuesday":
//
//                    Class_List_Entity seshanbe = new Class_List_Entity();
//
//                    seshanbe.ClassCode = _model_class[i].getClassID();
//                    seshanbe.ClassName = _model_class[i].getClassName();
//                    seshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
//                    String Class_number_seshanbe = _model_class[i].getClassPosition();
//                    String Facility_seshanbe = _model_class[i].getFacility();
//                    seshanbe.ClassLocation = Class_number_seshanbe + " کلاس " + Facility_seshanbe;
//                    seshanbe.ClassTime = "" + _model_class[i].getTime();
//                    seshanbe.isCanseled = false;
//
//                    List_Class3.add(seshanbe);
//
//                    break;
//
//                case "Wednesday":
//                    Class_List_Entity charshanbe = new Class_List_Entity();
//
//                    charshanbe.ClassCode = _model_class[i].getClassID();
//                    charshanbe.ClassName = _model_class[i].getClassName();
//                    charshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
//                    String Class_number_charshanbe = _model_class[i].getClassPosition();
//                    String Facility_charshanbe = _model_class[i].getFacility();
//                    charshanbe.ClassLocation = Class_number_charshanbe + " کلاس " + Facility_charshanbe;
//                    charshanbe.ClassTime = "" + _model_class[i].getTime();
//                    charshanbe.isCanseled = false;
//
//                    List_Class4.add(charshanbe);
//
//                    break;
//
//                case "Thursday ":
//                    Class_List_Entity panjshanbe = new Class_List_Entity();
//
//                    panjshanbe.ClassCode = _model_class[i].getClassID();
//                    panjshanbe.ClassName = _model_class[i].getClassName();
//                    panjshanbe.ClassCapacity = String.valueOf(_model_class[i].getCapacity());
//                    String Class_number_panjshanbe = _model_class[i].getClassPosition();
//                    String Facility_panjshanbe = _model_class[i].getFacility();
//                    panjshanbe.ClassLocation = Class_number_panjshanbe + " کلاس " + Facility_panjshanbe;
//                    panjshanbe.ClassTime = "" + _model_class[i].getTime();
//                    panjshanbe.isCanseled = false;
//
//                    List_Class5.add(panjshanbe);
//
//                    break;
//
//
//            }
//
//        }
//        _Cash.setList_Class_0Shanbe(List_Class0);
//        _Cash.setList_Class_1Shanbe(List_Class1);
//        _Cash.setList_Class_2Shanbe(List_Class2);
//        _Cash.setList_Class_3Shanbe(List_Class3);
//        _Cash.setList_Class_4Shanbe(List_Class4);
//        _Cash.setList_Class_5Shanbe(List_Class5);
//
//    }


}
