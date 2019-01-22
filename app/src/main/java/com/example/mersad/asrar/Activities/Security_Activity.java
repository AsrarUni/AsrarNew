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
import com.example.mersad.asrar.Model.Login_Entity;
import com.example.mersad.asrar.Model.model_time;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.Utils.AppSingleton;
import com.example.mersad.asrar.Volley_WebService.FetchDataListener;
import com.example.mersad.asrar.Volley_WebService.GETAPIRequest;
import com.example.mersad.asrar.Volley_WebService.RequestQueueService;
import com.google.gson.Gson;

import org.json.JSONObject;

import wiadevelopers.com.library.DivarUtils;
import wiadevelopers.com.library.MaskdEditText.MaskedEditText;

public class Security_Activity extends AppCompatActivity {

    TextView number_1 , number_2 , number_3 , number_4 , number_5 , number_6 , number_7 , number_8 , number_9 , number_0 , ok  , description ;
    ImageButton del ;
    Typeface custom_font;
    Cash _Cash;
    String is_has_pass , first_choose ;
    MaskedEditText Securuty_Mtv_Pass ;



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
                        getApiCall("http://thtc.ir/nazer/api/date", Security_Activity.this);
                        request();
                    }
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


    }


}
