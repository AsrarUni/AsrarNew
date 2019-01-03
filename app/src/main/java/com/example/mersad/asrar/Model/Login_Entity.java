package com.example.mersad.asrar.Model;

import java.util.List;

public class Login_Entity {

//----- Eejaad shode tavassote plugin Gson -----//

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {

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

        public Data(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFname() {
            return Fname;
        }

        public void setFname(String Fname) {
            this.Fname = Fname;
        }

        public String getLname() {
            return Lname;
        }

        public void setLname(String Lname) {
            this.Lname = Lname;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
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

        public void setFatherName(String FatherName) {
            this.FatherName = FatherName;
        }

        public int getCodeMeli() {
            return CodeMeli;
        }

        public void setCodeMeli(int CodeMeli) {
            this.CodeMeli = CodeMeli;
        }

        public int getPersonalNumber() {
            return PersonalNumber;
        }

        public void setPersonalNumber(int PersonalNumber) {
            this.PersonalNumber = PersonalNumber;
        }

        public String getDateBirthday() {
            return DateBirthday;
        }

        public void setDateBirthday(String DateBirthday) {
            this.DateBirthday = DateBirthday;
        }

        public int getTypeID() {
            return TypeID;
        }

        public void setTypeID(int TypeID) {
            this.TypeID = TypeID;
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

        public String toString()
        {
            return   Fname + "#" + Lname ;
        }
    }
}
