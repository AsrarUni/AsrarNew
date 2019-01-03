package com.example.mersad.asrar.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Activities.MainActivity;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.Adapter.Students_Aattendance_Adapter;
import com.example.mersad.asrar.Model.Students_Attendance_Entity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class Students_Attendance_Fragment extends Fragment {

        Context _Context;
        Activity _Activity;
        Cash _Cash;
        RecyclerView recycle_Students_Attendance;
        List<Students_Attendance_Entity> DataList;
        Students_Aattendance_Adapter adapter;
        Button btn_Save_Attendance_List;
        private TextView emptyView;
        ShimmerFrameLayout Shimmer_recycle_Students_Attendance;
        List<Students_Attendance_Entity> List_Students;
        private SwipeRefreshLayout attendance_refresh;


    public Students_Attendance_Fragment() {
    }


    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



            _Context = getActivity();
            _Activity = getActivity();
            _Cash = (Cash) _Activity.getApplication();

        }



        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
        }

        public static void hideKeyboard(Activity activity) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_students_attendence, container, false);

            try {


                recycle_Students_Attendance = view.findViewById(R.id.recycle_Students_Attendance);
                Shimmer_recycle_Students_Attendance = view.findViewById(R.id.Shimmer_recycle_Students_Attendance);
                Shimmer_recycle_Students_Attendance.startShimmer();
                emptyView = view.findViewById(R.id.empty_view);

                if (DataList != _Cash.Fill_Student_List()) {
                    recycle_Students_Attendance.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    hideKeyboard(_Activity);
                    new CountDownTimer(3000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            hideKeyboard(_Activity);
                        }

                        @Override
                        public void onFinish() {
                            hideKeyboard(_Activity);

                            List_Students = _Cash.Fill_Student_List();
                            DataList = _Cash.Fill_Student_List();
//                        adapter.notifyDataSetChanged();
                            Shimmer_recycle_Students_Attendance.setVisibility(View.GONE);

                            recycle_Students_Attendance.setVisibility(View.VISIBLE);
                            emptyView.setVisibility(View.GONE);
                            adapter = new Students_Aattendance_Adapter(_Context, _Activity, List_Students);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_Context, LinearLayoutManager.VERTICAL, false);
                            recycle_Students_Attendance.setLayoutManager(linearLayoutManager);
                            recycle_Students_Attendance.setAdapter(adapter);
                            recycle_Students_Attendance.setItemAnimator(new DefaultItemAnimator());

                        }
                    }.start();

                }

                btn_Save_Attendance_List = view.findViewById(R.id.btn_Save_Attendance_List);
                btn_Save_Attendance_List.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // send _Cash.get_teacherts_form ;
                        Intent d = new Intent(_Context, MainActivity.class);
                        startActivity(d);
                    }
                });


                attendance_refresh = (SwipeRefreshLayout) view.findViewById(R.id.attendance_refresh);

                attendance_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        attendance_refresh.setRefreshing(true);

                        Toast.makeText(_Context, "test recycle swipe successfull", Toast.LENGTH_LONG).show();

                        attendance_refresh.setRefreshing(false);
                    }
                });

            } catch (Exception ex) {
                Toast.makeText(_Context, ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            return view;
        }


}
