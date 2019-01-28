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
import android.widget.TextView;
import android.widget.Toast;

import com.example.mersad.asrar.Activities.Week_Days_Class_activity;
import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Adapter.Class_List_Adapter;
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class Class_List_Fragment extends Fragment {

    public RecyclerView recycle_class_list;
    public List<Class_List_Entity> DataList;
    public Class_List_Adapter adapter;
    Cash _Cash;
    public List<Class_List_Entity> List_Class;
    ShimmerFrameLayout Shimmer_recycle_ClassList;
    TextView empty_view_class;
    Context _Context;
    Activity _Activity;
    public int what_day;
    SwipeRefreshLayout ClassList_refresh;

    public Class_List_Fragment() {

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

        View view = inflater.inflate(R.layout.fragment_class_list, container, false);

        try {

            recycle_class_list = view.findViewById(R.id.recycle_class_list);
            Shimmer_recycle_ClassList = view.findViewById(R.id.Shimmer_recycle_ClassList);
            Shimmer_recycle_ClassList.startShimmer();
            empty_view_class = view.findViewById(R.id.empty_view_class);
            ClassList_refresh = view.findViewById(R.id.ClassList_refresh);

            ClassList_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    ClassList_refresh.setRefreshing(true);

                    Intent intent = new Intent(_Activity, Week_Days_Class_activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    _Activity.finish();
                    ClassList_refresh.setRefreshing(false);
                }
            });


            switch (what_day) {

                case 0:
                    if (_Cash.getList_Class_0Shanbe().size() == 0)
                        recycle_class_list.dispatchWindowSystemUiVisiblityChanged(View.GONE);
                    else
                        DataList = _Cash.getList_Class_0Shanbe();
                    break;

                case 1:
                    if (_Cash.getList_Class_1Shanbe().size() == 0)
                        recycle_class_list.dispatchWindowSystemUiVisiblityChanged(View.GONE);
                    else
                        DataList = _Cash.getList_Class_1Shanbe();
                    break;

                case 2:
                    if (_Cash.getList_Class_2Shanbe().size() == 0)
                        recycle_class_list.dispatchWindowSystemUiVisiblityChanged(View.GONE);
                    else
                        DataList = _Cash.getList_Class_2Shanbe();
                    break;

                case 3:
                    if (_Cash.getList_Class_3Shanbe().size() == 0)
                        recycle_class_list.dispatchWindowSystemUiVisiblityChanged(View.GONE);
                    else
                        DataList = _Cash.getList_Class_3Shanbe();
                    break;

                case 4:
                    if (_Cash.getList_Class_4Shanbe().size() == 0)
                        recycle_class_list.dispatchWindowSystemUiVisiblityChanged(View.GONE);
                    else
                        DataList = _Cash.getList_Class_4Shanbe();
                    break;

                case 5:
                    if (_Cash.getList_Class_5Shanbe().size() == 0)
                        recycle_class_list.dispatchWindowSystemUiVisiblityChanged(View.GONE);
                    else
                        DataList = _Cash.getList_Class_5Shanbe();
                    break;

            }

            recycle_class_list.setVisibility(View.GONE);
            empty_view_class.setVisibility(View.VISIBLE);
            hideKeyboard(_Activity);
            new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    hideKeyboard(_Activity);
                }

                @Override
                public void onFinish() {
                    hideKeyboard(_Activity);

                    List_Class = DataList;
//                        DataList = _Cash.Fill_List_Class();
//                        adapter.notifyDataSetChanged();
                    Shimmer_recycle_ClassList.setVisibility(View.GONE);
                    if (DataList == null) {
                        empty_view_class.setVisibility(View.VISIBLE);
                    } else {
                        empty_view_class.setVisibility(View.GONE);
                        recycle_class_list.setVisibility(View.VISIBLE);
                        empty_view_class.setVisibility(View.GONE);
                        adapter = new Class_List_Adapter(_Context, _Activity, List_Class, what_day);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_Context, LinearLayoutManager.VERTICAL, false);
                        recycle_class_list.setLayoutManager(linearLayoutManager);
                        recycle_class_list.setAdapter(adapter);
                        recycle_class_list.setItemAnimator(new DefaultItemAnimator());
                    }
                }
            }.start();


        } catch (Exception ex) {
            Toast.makeText(_Context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }

}