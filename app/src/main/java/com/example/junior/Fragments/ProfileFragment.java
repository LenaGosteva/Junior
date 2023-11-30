package com.example.junior.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.junior.SharedPreferenses.DatabaseSP;
import com.example.junior.Views.InputField;
import com.example.junior.databinding.FragmentProfileBinding;
import com.example.junior.Activities.App;

import java.util.Date;

public class ProfileFragment extends Fragment {
DatabaseSP sharedPreferences;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences =App.getSharedPreferences();
        Log.e("DUFU", String.valueOf(sharedPreferences==null));
        setHintAndText();
        saveToSP();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    private void setHintAndText() {
        binding.nameByDefault.setHint("Имя");
        binding.nameByDefaultInput.setText(App.getSharedPreferences().getName());
        binding.teacherByDefaultInput.setText(App.getSharedPreferences().getTeacher());
        binding.yearByDefaultInput.setText(App.getSharedPreferences().getYear());
        binding.placeByDefaultInput.setText(App.getSharedPreferences().getPlace());
        binding.placeByDefault.setHint("Город");
        binding.organizationByDefaultInput.setText(App.getSharedPreferences().getOrganization());
        binding.organizationByDefault.setHint("Организация");
        binding.yearByDefault.setHint("Год исполнения");
        binding.teacherByDefault.setHint("Проверяющий/руководитель");
    }

    private void saveToSP() {
        binding.organizationByDefaultInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                App.getSharedPreferences().saveOrganization(editable.toString());
            }
        });
        binding.teacherByDefaultInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                App.getSharedPreferences().saveTeacher(editable.toString());
            }
        });
        binding.placeByDefaultInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                App.getSharedPreferences().savePlace(editable.toString());
            }
        });
        binding.yearByDefaultInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                App.getSharedPreferences().saveYear(editable.toString());
            }
        });
        binding.nameByDefaultInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                App.getSharedPreferences().saveName(editable.toString());
            }
        });
    }
}