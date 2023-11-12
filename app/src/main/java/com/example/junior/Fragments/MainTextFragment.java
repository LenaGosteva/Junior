package com.example.junior.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.junior.Activities.App;
import com.example.junior.Classes.UsersDocument;
import com.example.junior.R;
import com.itextpdf.text.Document;

import java.util.Collection;
import java.util.HashMap;

public class MainTextFragment extends Fragment {

    HashMap<String, String> list;
    Activity activity;
    public MainTextFragment(Activity activity) {
        this.activity = activity;
        list = new HashMap<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_text, container, false);
    }
}