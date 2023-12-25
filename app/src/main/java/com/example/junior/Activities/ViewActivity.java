package com.example.junior.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.junior.R;

public class ViewActivity extends AppCompatActivity {
String nameOfFile;
String pathOfFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        nameOfFile = getIntent().getStringExtra(App.EXTRA_TO_SEE_DOCUMENT);
        pathOfFile = getExternalFilesDir(null)+nameOfFile;

    }
}