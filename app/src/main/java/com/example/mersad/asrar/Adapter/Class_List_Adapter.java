package com.example.mersad.asrar.Adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mersad.asrar.Activities.Rating_Activity;
import com.example.mersad.asrar.Activities.Remember_Activity;
import com.example.mersad.asrar.Activities.Send_Message_To_Class_Activity;
import com.example.mersad.asrar.Activities.Student_Attendance_Tablayout_Activity;
import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.Holder.Class_List_Holder;
import com.example.mersad.asrar.Model.model_time;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.ItemClickListener;
import com.example.mersad.asrar.Utils.AppSingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Class_List_Adapter extends RecyclerView.Adapter<Class_List_Holder> {

    android.content.Context Context;
    Typeface custom_font;
    List<Class_List_Entity> List_Class;
    Cash _Cash;
    public Activity _Activity;
//    int what_day;

//    public Class_List_Adapter(android.content.Context Context, Activity activity, List<Class_List_Entity> List_Class, int day) {
//        custom_font = Typeface.createFromAsset(Context.getAssets(), "fonts/koodak.ttf");
//        this.Context = Context;
//        _Activity = activity;
//        _Cash = (Cash) _Activity.getApplication();
////        List_Class = _Cash.Fill_List_Class();
//        this.List_Class = List_Class;
////        this.what_day = day;
//    }

    public Class_List_Adapter(android.content.Context Context, Activity activity, List<Class_List_Entity> List_Class) {
        custom_font = Typeface.createFromAsset(Context.getAssets(), "fonts/koodak.ttf");
        this.Context = Context;
        _Activity = activity;
        _Cash = (Cash) _Activity.getApplication();
//        List_Class = _Cash.Fill_List_Class();
        this.List_Class = List_Class;
//        this.what_day = day;
    }


    @Override
    public Class_List_Holder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View View = LayoutInflater.from(Context).inflate(R.layout.item_class_list, viewGroup, false);
        return new Class_List_Holder(View);

    }


    @Override
    public void onBindViewHolder(Class_List_Holder ClassListHolder, int position) {

        ClassListHolder.tvClassName.setText(List_Class.get(position).ClassName);
        ClassListHolder.tvCapacityClass.setText(List_Class.get(position).ClassCapacity);
        ClassListHolder.tvClassTime.setText(List_Class.get(position).ClassTime);
        String Class_Code = String.valueOf(List_Class.get(position).ClassCode);
        ClassListHolder.tvClassCode.setText(Class_Code);
        ClassListHolder.tvClassLocation.setText(List_Class.get(position).ClassLocation);

        //set fonts :
        ClassListHolder.tvClassName.setTypeface(custom_font);
        ClassListHolder.tvCapacityClass.setTypeface(custom_font);
        ClassListHolder.tvClassTime.setTypeface(custom_font);
        ClassListHolder.tvClassCode.setTypeface(custom_font);
        ClassListHolder.tvClassLocation.setTypeface(custom_font);

        ClassListHolder.tvLableClassName.setTypeface(custom_font);
        ClassListHolder.tvLableClassCapacity.setTypeface(custom_font);
        ClassListHolder.tvLableClassTime.setTypeface(custom_font);
        ClassListHolder.tvLableClassCode.setTypeface(custom_font);
        ClassListHolder.tvLableClassLocation.setTypeface(custom_font);
        ClassListHolder.tvLableClassMemo.setTypeface(custom_font);

        if (List_Class.get(position).isCanseled) {
            Picasso
                    .get()
                    .load(R.drawable.background_red)
                    .into(ClassListHolder.Class_List_iv_background);

        } else {
            Picasso
                    .get()
                    .load(R.drawable.background_blue)
                    .into(ClassListHolder.Class_List_iv_background);
        }

//----- listener baraye click long ya short baraye ratfan be safheye bad ya baz kardane menu-----//
        ClassListHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View View, int position, boolean isLongClick) {
                if (isLongClick) {

                    customDialog(position);

                } else {
                    Intent go_to_students = new Intent(Context, Student_Attendance_Tablayout_Activity.class);
                    Context.startActivity(go_to_students);

                }
            }

        });

    }


    private void customDialog(final int position) {
        final Button cancell, rate , message, remind;

        LayoutInflater inflater = LayoutInflater.from(Context);
        final View view = inflater.inflate(R.layout.dialog_class_options, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(_Activity);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", null);
        alertDialogBuilder.setNegativeButton("CANCEL", null);

        cancell = (Button) view.findViewById(R.id.Dialog_Class_Options_Btn_Cancell_Class);
        rate = (Button) view.findViewById(R.id.Dialog_Class_Options_Btn_Rate_Class);
        message = (Button) view.findViewById(R.id.Dialog_Class_Options_Btn_Send_Message);
        remind = (Button) view.findViewById(R.id.Dialog_Class_Options_Btn_Reminder);


        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnPositive.setText("بستن");
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNegative.setVisibility(View.INVISIBLE);

            }
        });

        // baraye koochek tar kardane arze alert dialog
        alertDialog.getWindow().setBackgroundDrawable(_Activity.getResources().getDrawable(R.drawable.dialog_style));
        alertDialog.show();

        Display display = _Activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        width = (int) ((width) * ((double) 4 / 5));
        alertDialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (List_Class.get(position).isCanseled) {
            cancell.setText("تشکیل دادن کلاس");
        } else {
            cancell.setText("کنسل کردن کلاس");
        }
//        notifyDataSetChanged();


        cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (List_Class.get(position).isCanseled) {
                    List_Class.get(position).isCanseled = false;
                    Toast.makeText(Context, " کلاس تشکیل میشود ", Toast.LENGTH_SHORT).show();

//                    switch (what_day) {
//
//                        case 0:
//                            _Cash.setList_Class_0Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 1:
//                            _Cash.setList_Class_1Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 2:
//                            _Cash.setList_Class_2Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 3:
//                            _Cash.setList_Class_3Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 4:
//                            _Cash.setList_Class_4Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 5:
//                            _Cash.setList_Class_5Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//                    }

                    notifyDataSetChanged();

                } else if (!List_Class.get(position).isCanseled) {
                    List_Class.get(position).isCanseled = true;
                    Toast.makeText(Context, " کلاس تشکیل نمیشود ", Toast.LENGTH_SHORT).show();

//                    switch (what_day) {
//
//                        case 0:
//                            _Cash.setList_Class_0Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 1:
//                            _Cash.setList_Class_1Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 2:
//                            _Cash.setList_Class_2Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 3:
//                            _Cash.setList_Class_3Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 4:
//                            _Cash.setList_Class_4Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//
//                        case 5:
//                            _Cash.setList_Class_5Shanbe(List_Class);
//                            alertDialog.dismiss();
//                            break;
//                    }


                    notifyDataSetChanged();

                }
            }
        });


        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rate = new Intent( Context , Rating_Activity.class);
                _Activity.startActivity(rate);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent message = new Intent(Context , Send_Message_To_Class_Activity.class);
//                --------------------------------------------------------------------------------------
                String cc = String.valueOf(List_Class.get(position).ClassCode);
                message.putExtra(Constant.Code_Class , cc );
                _Activity.startActivity(message);

            }
        });

        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent remember = new Intent(Context , Remember_Activity.class);
//                --------------------------------------------------------------------------------------
                String clc = String.valueOf(List_Class.get(position).ClassCode);
                remember.putExtra(Constant.Code_Class , clc );
                _Activity.startActivity(remember);

            }
        });

    }

    @Override
    public int getItemCount() {
        return List_Class.size();
    }



}