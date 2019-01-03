package com.example.mersad.asrar.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mersad.asrar.R;


public class Find_Classes_By_Name_Fragment extends Fragment
{

    public Find_Classes_By_Name_Fragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_find_classes_by_name, container, false);
        return view;
    }
}