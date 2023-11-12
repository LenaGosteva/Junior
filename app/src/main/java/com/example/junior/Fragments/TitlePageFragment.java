
package com.example.junior.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.junior.Activities.App;
import com.example.junior.Adapters.FieldsAdapter;
import com.example.junior.R;
import com.example.junior.databinding.FragmentTitlePageBinding;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class TitlePageFragment extends Fragment {
Activity activity;
    HashMap<String, String> list;
    FragmentTitlePageBinding binding;

    public TitlePageFragment(Activity activity) {
        this.activity = activity;
        list = new HashMap<>();
        list = App.mainInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTitlePageBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerToTitlePage.setLayoutManager(new LinearLayoutManager(getActivity()));
        HashMap<String, String> l = new HashMap<>();
        l.put("Ghrrjh", "ucuhiv");
        l.put("Ghrrhjjh", "ucuhiv");
        l.put("Ghrrfjh", "ucuhiv");
        l.put("Ghdrrjh", "ucuhiv");
binding.recyclerToTitlePage.setAdapter(new FieldsAdapter(l,getActivity()));

    }
}