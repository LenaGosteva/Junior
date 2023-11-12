package com.example.junior.Activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.junior.Adapters.DocumentAdapter;
import com.example.junior.Classes.UsersDocument;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.junior.databinding.ActivityNewBinding;

public class NewActivity extends AppCompatActivity {

    private ActivityNewBinding binding;

    public UsersDocument document = new UsersDocument();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.viewPager.setAdapter(new DocumentAdapter(getSupportFragmentManager(), NewActivity.this));
        binding.tabs.setupWithViewPager(binding.viewPager);


    }
}