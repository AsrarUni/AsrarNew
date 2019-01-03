package com.example.mersad.asrar;

import android.app.Application;
import android.content.Context;

import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.Model.Login_Entity;
import com.example.mersad.asrar.Model.Students_Attendance_Entity;
import com.example.mersad.asrar.Model.Students_Attendance_teacherform_Entity;

import java.util.List;

import wiadevelopers.com.library.DivarUtils;

public class Cash extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DivarUtils.init(getApplicationContext());
    }


//----- Liste Daneshjooyan baraye namayesh dar recycler view ------//
    List<Students_Attendance_Entity> List_Students;

//----- liste hozor ghiab ke ma be server bar migardoonim -----//
    List<Students_Attendance_teacherform_Entity> Finall_Attendance_List;

//----- Liste kelas ha baraye namayesh dar recycler view ------//
//    List<Class_List_Entity>List_Class;

//----- Liste Daneshjooyan ra bar migardanad -----//
    public List<Students_Attendance_Entity> Fill_Student_List() {
        return this.List_Students;
    }

//----- Liste hozor ghiab ke ma be server bar migardoonim ra misazad -----//
    public List<Students_Attendance_teacherform_Entity> get_teacherts_form() {
        for (Students_Attendance_Entity item : List_Students) {

            Students_Attendance_teacherform_Entity obj = new Students_Attendance_teacherform_Entity();
            // populate obj
            obj.StudentCode = item.StudentCode;
            obj.IsPresent = item.IsPresent;
// inja bayad tozihaat ham ezafe shavad
            Finall_Attendance_List.add(obj);
        }
        return Finall_Attendance_List;
    }

//----- Liste class ha ra bar migardanad ------//
//    public List<Class_List_Entity> Fill_List_Class() {
//        return this.List_Class;
//    }
//    public void set_List_Class(List<Class_List_Entity> newList) {
//        this.List_Class = newList;
//    }

//----- Liste class ha ra for mikonad -----//
    public void setList_Students(List<Students_Attendance_Entity> newList) {
        this.List_Students = newList;
    }

//    =========================================== Personal Info ==============================================

    private int id;
    private String Fname;
    private String Lname;
    private String Tel;
    private String Mobile;
    private String email;
    private String FatherName;
    private int CodeMeli;
    private int PersonalNumber;
    private String DateBirthday;
    private int TypeID;
    private String password;
    private String created_at;
    private String updated_at;

//----- Liste ettelaate Login va getter va settere khode list -----//

    public List<Login_Entity.Data> info;

    public List<Login_Entity.Data> getInfo() {
        return info;
    }

    public void setInfo(List<Login_Entity.Data> info) {
        this.info = info;
    }
//----- Getter va Setter baraye etelaati ke az safheye login migirim -----//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public int getCodeMeli() {
        return CodeMeli;
    }

    public void setCodeMeli(int codeMeli) {
        CodeMeli = codeMeli;
    }

    public int getPersonalNumber() {
        return PersonalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        PersonalNumber = personalNumber;
    }

    public String getDateBirthday() {
        return DateBirthday;
    }

    public void setDateBirthday(String dateBirthday) {
        DateBirthday = dateBirthday;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

//----- tabe ee baraye estekhraje tak tak mavared az liste info -----//

    public void Fill_Cash_Info () {

        this.setFname(this.info.get(0).getFname());
        this.setLname(this.info.get(0).getLname());
        this.setTel(this.info.get(0).getTel());
        this.setMobile(this.info.get(0).getMobile());
        this.setEmail(this.info.get(0).getEmail());
        this.setFatherName(this.info.get(0).getFatherName());
        this.setCodeMeli(this.info.get(0).getCodeMeli());
        this.setPersonalNumber(this.info.get(0).getPersonalNumber());
        this.setDateBirthday(this.info.get(0).getDateBirthday());
        this.setTypeID(this.info.get(0).getTypeID());
        this.setPassword(this.info.get(0).getPassword());
        this.setCreated_at(this.info.get(0).getCreated_at());
        this.setUpdated_at(this.info.get(0).getUpdated_at());
    }

//----- moteghayyeri movaghati baraye tayine inke kodaam tooz az fragmente kelas ha entekhab shode -----//
    public int what_week_day ;




    //----- Liste kelas ha baraye namayesh dar recycler view ------//
    List<Class_List_Entity>List_Class_0Shanbe;
    List<Class_List_Entity>List_Class_1Shanbe;
    List<Class_List_Entity>List_Class_2Shanbe;
    List<Class_List_Entity>List_Class_3Shanbe;
    List<Class_List_Entity>List_Class_4Shanbe;
    List<Class_List_Entity>List_Class_5Shanbe;

//----- Liste class ha ra bar migardanad va set mikonad ------//

    public List<Class_List_Entity> getList_Class_0Shanbe() {
        return this.List_Class_0Shanbe;
    }

    public void setList_Class_0Shanbe(List<Class_List_Entity> jadid) {
       this.List_Class_0Shanbe = jadid ;
    }

    public List<Class_List_Entity> getList_Class_1Shanbe() {
        return this.List_Class_1Shanbe;
    }

    public void setList_Class_1Shanbe(List<Class_List_Entity> jadid) {
        this.List_Class_1Shanbe = jadid ;
    }

    public List<Class_List_Entity> getList_Class_2Shanbe() {
        return this.List_Class_2Shanbe;
    }

    public void setList_Class_2Shanbe(List<Class_List_Entity> jadid) {
        this.List_Class_2Shanbe = jadid ;
    }

    public List<Class_List_Entity> getList_Class_3Shanbe() {
        return this.List_Class_3Shanbe;
    }

    public void setList_Class_3Shanbe(List<Class_List_Entity> jadid) {
        this.List_Class_3Shanbe = jadid ;
    }

    public List<Class_List_Entity> getList_Class_4Shanbe() {
        return this.List_Class_4Shanbe;
    }

    public void setList_Class_4Shanbe(List<Class_List_Entity> jadid) {
        this.List_Class_4Shanbe = jadid ;
    }

    public List<Class_List_Entity> getList_Class_5Shanbe() {
        return this.List_Class_5Shanbe;
    }

    public void setList_Class_5Shanbe(List<Class_List_Entity> jadid) {
        this.List_Class_5Shanbe = jadid ;
    }


//    public void set_List_Class(List<Class_List_Entity> newList) {
//        this.List_Class = newList;
//    }


//    about time
    private String today_Date_Cash ;
    private String today_Day_Cash ;

    public String getToday_Day_Cash() {
        return today_Day_Cash;
    }

    public void setToday_Day_Cash(String today_Day_Cash) {
        this.today_Day_Cash = today_Day_Cash;
    }

    public String getToday_Date_Cash() {
        return today_Date_Cash;
    }

    public void setToday_Date_Cash(String today_Time_Cash) {
        this.today_Date_Cash = today_Time_Cash;
    }


    private Context _Context ;

    public void set_Context(Context _Context) {
        this._Context = _Context;
    }

//   public void WebService_Cash (String url){
//
//
////       pDialog.show();
//
//       Response.Listener<String> listener = new Response.Listener<String>() {
//           @Override
//           public void onResponse(String response) {
//
//               Gson gson = new Gson();
//               model_time _model_time = gson.fromJson(response, model_time.class);
//
//               String miladi_date =  _model_time.getDate() ;
//               String miladi_day = _model_time.getDay() ;
//
//
//               String[] parts = miladi_date.split("/");
//
//
//               String Year = parts[0];
//               String m_d = parts[1];
//               String[] parts2 = m_d.split("-");
//               String Month = parts2[0];
//               String Day = parts2[1];
//
//               int year = Integer.parseInt(Year) ;
//               int month = Integer.parseInt(Month) ;
//               int day = Integer.parseInt(Day) ;
//
//               String Today = Day + "/" + Month + "/" + Year ;
//
////                _Cash.setToday_Date_Cash(Today);
////                _Cash.setToday_Day_Cash(miladi_day);
//
////               Today_Date_Local = Today ;
////               Today_Day_Local = miladi_day ;
//
//
//           }
//
//       };
//
//       Response.ErrorListener errorListener = new Response.ErrorListener() {
//           @Override
//           public void onErrorResponse(VolleyError error) {
////               pDialog.dismiss();
////               Toast.makeText(getApplicationContext(), "خطا در اتصال", Toast.LENGTH_LONG).show();
//               int a = 0 ;
//           }
//       };
//
//       StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
//       AppSingleton.getInstance(_Context).addToRequestQueue(request);
//
//   }


}
