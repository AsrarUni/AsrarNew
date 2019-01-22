package com.example.mersad.asrar.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class model_class {


    /**
     * Date : 2018-12-01
     * Teacher : 609
     * Time : 8
     * Week : 12
     * DayOfWeek : Saturday
     * ClassName : ریاضی 1
     * Capacity : 50
     * ClassPosition : 101
     * Facility : مهندسی
     * ClassID : 224
     */

    private String Date;
    private int Teacher;
    private String Time;
    private String Week;
    private String DayOfWeek;
    private String ClassName;
    private int Capacity;
    private String ClassPosition;
    private String Facility;
    private int ClassID;

    public static List<model_class> arraymodel_classFromData(String str) {

        Type listType = new TypeToken<ArrayList<model_class>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getTeacher() {
        return Teacher;
    }

    public void setTeacher(int Teacher) {
        this.Teacher = Teacher;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getWeek() {
        return Week;
    }

    public void setWeek(String Week) {
        this.Week = Week;
    }

    public String getDayOfWeek() {
        return DayOfWeek;
    }

    public void setDayOfWeek(String DayOfWeek) {
        this.DayOfWeek = DayOfWeek;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public String getClassPosition() {
        return ClassPosition;
    }

    public void setClassPosition(String ClassPosition) {
        this.ClassPosition = ClassPosition;
    }

    public String getFacility() {
        return Facility;
    }

    public void setFacility(String Facility) {
        this.Facility = Facility;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }
}
