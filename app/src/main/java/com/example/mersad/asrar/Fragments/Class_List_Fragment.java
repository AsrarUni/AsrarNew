package com.example.mersad.asrar.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mersad.asrar.Cash;
import com.example.mersad.asrar.Adapter.Class_List_Adapter;
import com.example.mersad.asrar.Model.Class_List_Entity;
import com.example.mersad.asrar.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class Class_List_Fragment extends Fragment {

    RecyclerView recycle_class_list;
    List<Class_List_Entity> DataList;
    Class_List_Adapter adapter;
    Cash _Cash;
    List<Class_List_Entity> List_Class;
    ShimmerFrameLayout Shimmer_recycle_ClassList;
    private TextView empty_view_class;
    Context _Context;
    Activity _Activity;
//    public int what_day;

    public Class_List_Fragment() {

    }
//    public Class_List_Fragment(List<Class_List_Entity> List_Class) {
//
//        DataList = List_Class ;
//    }


    @SuppressLint("ValidFragment")
    public Class_List_Fragment(List<Class_List_Entity> dataList) {
        DataList = dataList;
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


//            switch (what_day) {
////
////                case 0:
////                    DataList = _Cash.getList_Class_0Shanbe();
////                    break;
////
////                case 1:
////                    if (_Cash.getList_Class_1Shanbe()==null)
////                        DataList = _Cash.getList_Class_0Shanbe();
////                    else
////                    DataList = _Cash.getList_Class_1Shanbe();
////                    break;
////
////                case 2:
////                    DataList = _Cash.getList_Class_2Shanbe();
////                    break;
////
////                case 3:
////                    DataList = _Cash.getList_Class_3Shanbe();
////                    break;
////
////                case 4:
////                    DataList = _Cash.getList_Class_4Shanbe();
////                    break;
////
////                case 5:
////                    if (_Cash.getList_Class_5Shanbe()==null)
////                        DataList = _Cash.getList_Class_0Shanbe();
////                    else
////                        DataList = _Cash.getList_Class_5Shanbe();
////                    break;
////
////            }

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

                    recycle_class_list.setVisibility(View.VISIBLE);
                    empty_view_class.setVisibility(View.GONE);
//                    adapter = new Class_List_Adapter(_Context, _Activity, List_Class, what_day);
                    adapter = new Class_List_Adapter(_Context, _Activity, List_Class);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_Context, LinearLayoutManager.VERTICAL, false);
                    recycle_class_list.setLayoutManager(linearLayoutManager);
                    recycle_class_list.setAdapter(adapter);
                    recycle_class_list.setItemAnimator(new DefaultItemAnimator());

                }
            }.start();


        } catch (Exception ex) {
            Toast.makeText(_Context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }

}