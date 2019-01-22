package com.example.mersad.asrar.Model;

import io.realm.RealmObject;

public class model_message extends RealmObject {
    public model_message() {
    }

    public model_message(String date, String classCode, String message) {
        this.date = date;
        ClassCode = classCode;
        Message = message;
    }

    String date ;
    String ClassCode ;
    String Message ;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassCode() {
        return ClassCode;
    }

    public void setClassCode(String classCode) {
        ClassCode = classCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }




}
