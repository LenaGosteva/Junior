package com.example.junior.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.junior.Activities.App;
import com.example.junior.Activities.NewActivity;
import com.example.junior.Activities.ViewActivity;
import com.example.junior.Adapters.FieldsAdapter;
import com.example.junior.Adapters.FilesAdapter;
import com.example.junior.Classes.UsersDocument;
import com.example.junior.databinding.FragmentFilesBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FilesFragment extends Fragment {

    private FragmentFilesBinding binding;
    List<UsersDocument> list = new ArrayList<>();
    List<String> listOfNames = new ArrayList<>();
    List<String> firstListOfNames = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setOnClickListener(sdf->{
            startActivity(new Intent(getContext(), NewActivity.class));
        });
        firstListOfNames = Arrays.asList(App.directory.list());
        for (String s:firstListOfNames
             ) {
            if (!s.contains("_json")) listOfNames.add(s);

        }
        binding.recyclerOfFiles.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerOfFiles.setAdapter(new FilesAdapter(listOfNames,getActivity()));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//list.add(new UsersDocument());
//        list.forEach((e)->listOfNames.add(e.nameOfDocument));
        binding = FragmentFilesBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        listOfNames = new ArrayList<>();
    }
}