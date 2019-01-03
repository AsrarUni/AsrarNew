package com.example.mersad.asrar.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.ItemClickListener;
import com.example.mersad.asrar.Holder.Students_Aattendance_Holder;
import com.example.mersad.asrar.Model.Students_Attendance_Entity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Students_Aattendance_Adapter extends RecyclerView.Adapter<Students_Aattendance_Holder> {

    android.content.Context Context;
    Typeface custom_font;
    List<Students_Attendance_Entity> List_Students;
    Cash _Cash;
    public Activity _Activity;

    // axe testy 256 * 256 mibashad
    String axe_testy = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/Logo_TVE-1.svg/1200px-Logo_TVE-1.svg.png";

    public Students_Aattendance_Adapter(android.content.Context Context, Activity activity, List<Students_Attendance_Entity> List_Students) {
        custom_font = Typeface.createFromAsset(Context.getAssets(), "fonts/koodak.ttf");
        this.Context = Context;
        _Activity = activity;
        _Cash = (Cash) _Activity.getApplication();
        this.List_Students = List_Students;

    }


    @Override
    public Students_Aattendance_Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View View = LayoutInflater.from(Context).inflate(R.layout.items_students_attendance, viewGroup, false);
        return new Students_Aattendance_Holder(View);
    }


    @Override
    public void onBindViewHolder(Students_Aattendance_Holder studentsAattendanceHolder, int position) {

        studentsAattendanceHolder.tvFirstName.setText(List_Students.get(position).FirstName);
        studentsAattendanceHolder.tvLastName.setText(List_Students.get(position).LastName);
        studentsAattendanceHolder.tvMajor.setText(List_Students.get(position).Major);
        String Student_Code = String.valueOf(List_Students.get(position).StudentCode);
        studentsAattendanceHolder.tvStudentCode.setText(Student_Code);

//----- taghire range background cart -----//
        if (List_Students.get(position).IsPresent) {
            studentsAattendanceHolder.tvIsPresent.setText("حاضر");
            Picasso
                    .get()
                    .load(R.drawable.background_green)
                    .into(studentsAattendanceHolder.Students_iv_background);

        } else {
            studentsAattendanceHolder.tvIsPresent.setText("غایب");
            Picasso
                    .get()
                    .load(R.drawable.background_red)
                    .into(studentsAattendanceHolder.Students_iv_background);
        }
//----- agar ax ha load nashodand -----//
        Picasso
                .get()
                .load(List_Students.get(position).pic)
                .fit()
                .error(R.drawable.error)
                .into(studentsAattendanceHolder.ivStudentPic);


        //set fonts :
        studentsAattendanceHolder.tvFirstName.setTypeface(custom_font);
        studentsAattendanceHolder.tvLastName.setTypeface(custom_font);
        studentsAattendanceHolder.tvMajor.setTypeface(custom_font);
        studentsAattendanceHolder.tvStudentCode.setTypeface(custom_font);
        studentsAattendanceHolder.tvIsPresent.setTypeface(custom_font);

        studentsAattendanceHolder.tvLableFirstName.setTypeface(custom_font);
        studentsAattendanceHolder.tvLableLastName.setTypeface(custom_font);
        studentsAattendanceHolder.tvLableMajor.setTypeface(custom_font);
        studentsAattendanceHolder.tvLableStudentCode.setTypeface(custom_font);
        studentsAattendanceHolder.tvLableIsPresent.setTypeface(custom_font);
        studentsAattendanceHolder.tvLableMemo.setTypeface(custom_font);


        // listener :
        studentsAattendanceHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View View, int position, boolean isLongClick) {
                if (isLongClick) {
                    // do sth
                    Toast.makeText(Context, "badan set she (long) ^_^", Toast.LENGTH_LONG).show();


                } else {
                    // do sth
                    Toast.makeText(Context, "badan set she (short) ^_^", Toast.LENGTH_LONG).show();
                    if (List_Students.get(position).IsPresent) {
                        List_Students.get(position).IsPresent = false;
                        Toast.makeText(Context, " غایب ", Toast.LENGTH_SHORT).show();
                        _Cash.setList_Students(List_Students);
//----- baraye emale taghirate lahze ee -----//
                        notifyDataSetChanged();

                    } else if (!List_Students.get(position).IsPresent) {
                        List_Students.get(position).IsPresent = true;
                        Toast.makeText(Context, " حاضر ", Toast.LENGTH_SHORT).show();
                        try {
                            _Cash.setList_Students(List_Students);
                        } catch (Exception e) {
                            int a = 0;
                        }
                        notifyDataSetChanged();

                    } else {
                        Toast.makeText(Context, "خطای سیستمی رخ داده", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return List_Students.size();
    }

}