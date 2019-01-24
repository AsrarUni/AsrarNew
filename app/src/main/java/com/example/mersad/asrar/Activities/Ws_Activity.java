package com.example.mersad.asrar.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Utils.AppSingleton;

public class Ws_Activity extends AppCompatActivity {

    Cash _Cash ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        _Cash = (Cash) getApplication();

    }

    public void request()
    {
        String url = "https://thtc.ir/nazer/api/class/2018-12-02/2019-01-24/609" ;

        final ProgressDialog pDialog;
        pDialog = new ProgressDialog(Ws_Activity.this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                //txtResult.setText(response);

                _Cash.setString_Json(response);


                pDialog.dismiss();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                pDialog.dismiss();
            }
        };

        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

}
