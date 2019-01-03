package com.example.mersad.asrar.Volley_WebService;

import org.json.JSONObject;

public interface FetchDataListener {

    void onFetchComplete(JSONObject data);

    void onFetchFailure(String msg);

    void onFetchStart();
}
